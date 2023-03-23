package com.example.slimesurvival;


import android.graphics.Canvas;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.content.Intent;
/*
The game Loop of the game is the main engine of the game
This will handle game progression
Updates states and objects within the game (E.G. position and texture)
Renders the objects to the screen

 */
public class GameLoop extends Thread{
    public static final double MAX_UPS = 60.0;
    private static final double UPS_PERIOD = 1E+3/MAX_UPS;
    private boolean isRunning = false;
    private SurfaceHolder surfaceHolder;
    private Game game;
    private double AverageUPS;
    private double AverageFPS;


    public GameLoop(Game game, SurfaceHolder surfaceHolder) {
        this.game = game;
        this.surfaceHolder = surfaceHolder;
    }

    public double getAverageUPS() {
        return AverageUPS;
    }

    public double getAverageFPS() {
        return AverageFPS;
    }

    public void startLoop() {
        isRunning = true;
        start();
    }

    @Override
    public void run() {
        super.run();
        //Declare time and cycle count variables
        int updateCount = 0;
        int frameCount = 0;

        long startTime;
        long elapsedTime;
        long sleepTime;


        //Game Loop
        Canvas canvas = null;
        startTime = System.currentTimeMillis();
        while(isRunning){
            //On each cycle, try to update and render the game
            try{//Many try catches added to handle expections
                //Try the lock the canvas
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){
                    //Prevents multiple threads calling from update and draw from surface holder as the same time this thread
                    game.update();
                    updateCount++;//Update update count on each update
                    game.draw(canvas);
                }
                try{
                    surfaceHolder.unlockCanvasAndPost(canvas);
                    frameCount++;//Update frame count if the update is posted to the surfaceholder
                }catch (Exception e){
                    e.printStackTrace();
                }

            }catch (IllegalArgumentException e){
                e.printStackTrace();
            } finally{
                if(canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                        frameCount++;//Update frame count if the update is posted to the surfaceholder
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            //Game loop will pause to not exceed max UPS frequency
            elapsedTime = System.currentTimeMillis() - startTime;
            sleepTime = (long)(updateCount*UPS_PERIOD - elapsedTime);//Maintaining Target UPS
            if(sleepTime > 0){
                try {
                    sleep(sleepTime);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            //If the rendering takes too much time frames will be skipped to keep target UPS
            while(sleepTime <0 && updateCount < MAX_UPS -1){
                game.update();
                updateCount++;
                elapsedTime = System.currentTimeMillis() - startTime;
                sleepTime = (long)(updateCount*UPS_PERIOD - elapsedTime);
            }
            //Calculate average UPS and FPS
            elapsedTime = System.currentTimeMillis() - startTime;
            if(elapsedTime >= 1000){
                AverageUPS = updateCount / (1E-3*elapsedTime);//Same as multiply to 1000
                AverageFPS = frameCount / (1E-3*elapsedTime);//Same as multiply to 1000
                updateCount = 0;
                frameCount = 0;
                startTime = System.currentTimeMillis();
            } //One second
        }
    }

    public void stoploop() {
        isRunning=false;
        //Wait for the run method to return
        try{
            join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}