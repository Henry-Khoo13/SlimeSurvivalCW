package com.example.slimesurvival;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.example.slimesurvival.object.Enemy;
import com.example.slimesurvival.object.Player;

// Alt Return to get recommendations
public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private final Player player;
    private final Joystick joystick;
    private final Enemy enemy;
    private GameLoop gameLoop;


    public Game(Context context) {
        super(context);

        // Get surface holder and add callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        //Initialising Game Loop
        gameLoop = new GameLoop(this, surfaceHolder);
        //Initialising Joystick
        joystick = new Joystick(275, 700, 70, 40);

        //Initialising Player
        player = new Player(getContext(),joystick,500,500,30);

        //Initialising Enemy
        enemy = new Enemy(getContext(),player,500,200,30);


        setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN: //Upon pressing the screen move position of player
                if(joystick.isPressed((double)event.getX(),(double)event.getY())){
                    joystick.setIsPress(true);
                }

                return true;
            case MotionEvent.ACTION_MOVE: //Upon pressing and holding the screen move position of player
                if(joystick.getisPressed()){
                    joystick.setActuator((double)event.getX(),(double)event.getY());
                }

                return true;
            case MotionEvent.ACTION_UP: //Upon pressing and holding the screen move position of player
                joystick.setIsPress(false);
                joystick.resetActuator();
                return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

    //Rendering calls handled here
    //Canvas is where objects are drawn
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawUPS(canvas);
        drawFPS(canvas);

        joystick.draw(canvas);
        player.draw(canvas);
        enemy.draw(canvas);
    }

    //Displays on the screen how many updates per second there are
    public void drawUPS(Canvas canvas){
        String averageUPS = Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(30);
        canvas.drawText("UPS: "+averageUPS,100,40,paint);
    }


    //Displays on the screen how many frames per second there are
    public void drawFPS(Canvas canvas){
        String averageFPS = Double.toString(gameLoop.getAverageFPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(30);
        canvas.drawText("FPS: "+averageFPS,100,100,paint);
    }

    public void update() {
        //Updates the game state
        joystick.update();
        player.update();
        enemy.update();

    }
}
