package com.example.slimesurvival;


import android.graphics.Canvas;
import android.view.Surface;
import android.view.SurfaceHolder;

import java.util.zip.Adler32;

/*
The game Loop of the game is the main engine of the game
This will handle game progression
Updates states and objects within the game (E.G. position and texture)
Renders the objects to the screen

 */
public class GameLoop extends Thread{
    private boolean isRunning = false;
    private SurfaceHolder surfaceHolder;
    private Game game;

    public GameLoop(Game game, SurfaceHolder surfaceHolder) {
        this.game = game;
        this.surfaceHolder = surfaceHolder;
    }

    public double getAverageUPS() {
        return 0;
    }

    public double getAverageFPS() {
        return 0;
    }

    public void startLoop() {
        isRunning = true;
        start();
    }

    @Override
    public void run() {
        super.run();

        //Game Loop
        Canvas canvas;
        while(isRunning){
            //On each cycle, try to update and render the game
            try{//Try the lock the canvas
                canvas = surfaceHolder.lockCanvas();
                //Game loop will pause to not exceed max UPS frequency
                game.update();
                game.draw(canvas);
                surfaceHolder.unlockCanvasAndPost(canvas);
            }catch (IllegalArgumentException e){
                e.printStackTrace();
            }

            //If the rendering takes too much time frames will be skipped to keep target UPS

            //Calculate average UPS and FPS
        }
    }
}