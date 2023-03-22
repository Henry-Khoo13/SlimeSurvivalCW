package com.example.slimesurvival;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.example.slimesurvival.object.Circle;
import com.example.slimesurvival.object.Enemy;
import com.example.slimesurvival.object.Player;
import com.example.slimesurvival.object.Spell;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Alt Return to get recommendations
public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private final Player player;
    private final Joystick joystick;
    //private final Enemy enemy;
    private List<Enemy> enemyList = new ArrayList<Enemy>();
    private List<Spell> spellList = new ArrayList<Spell>();
    private GameLoop gameLoop;
    private int joystickPointerID=0;
    private int numberOfSpellsToCast = 0;


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
        //enemy = new Enemy(getContext(),player,500,200,30);


        setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getActionMasked()){
            case MotionEvent.ACTION_DOWN: //Upon pressing the screen move position of player
            case MotionEvent.ACTION_POINTER_DOWN://Handles presses as pointer indices for multiple touch points
                if(joystick.getisPressed()){
                    //If the joystick is already pressed then the next press on the screen can fire a spell
                    numberOfSpellsToCast++;
                }
                else if(joystick.isPressed((double)event.getX(),(double)event.getY())){
                    joystickPointerID= event.getPointerId(event.getActionIndex());
                    joystick.setIsPress(true);

                }//Handling press on joystick
                else{
                    numberOfSpellsToCast++;
                }//Handling press but not on joystick

                return true;
            case MotionEvent.ACTION_MOVE: //Upon pressing and holding the screen move position of player
                if(joystick.getisPressed()){
                    joystick.setActuator((double)event.getX(),(double)event.getY());
                }//Checks if joystick was pressed and moves when held down

                return true;
            case MotionEvent.ACTION_UP: //Upon pressing and holding the screen move position of player
            case MotionEvent.ACTION_POINTER_UP:
                if(joystickPointerID == event.getPointerId(event.getActionIndex())){
                    joystick.setIsPress(false);
                    joystick.resetActuator();
                }

                //Check if joystick was released to reset
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
        for(Enemy enemy:enemyList){
            enemy.draw(canvas);
        }
        for(Spell spell:spellList){//Update through spell list
            spell.draw(canvas);
        }
        //enemy.draw(canvas);
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

        while(numberOfSpellsToCast > 0){
            spellList.add(new Spell(getContext(),player));
            numberOfSpellsToCast--;
        }
        //Create a new enemy if it is time to create a new enemy (No spamming spawning, setting them up at a fair rate)
        if(Enemy.readyToSpawn()){
            enemyList.add(new Enemy(getContext(),player));
        }
        //enemy.update();
        for(Enemy enemy:enemyList){ //Update through enemy list
            enemy.update();
        }

        for(Spell spell:spellList){//Update through spell list
            spell.update();
        }

        //Check for collisions between enemies and players
        //Check for collisions between enemies and spells
        //Iterator can be used to help support element-wise operations
        Iterator<Enemy> iteratorEnemy = enemyList.iterator();
        while(iteratorEnemy.hasNext()){
            Circle enemy = iteratorEnemy.next();
            if(Circle.isColliding(enemy,player)){
                iteratorEnemy.remove(); // If collide with player delete
                player.setHealthPoints(player.getHealthPoints()-1);
                continue;
            }
            Iterator<Spell> iteratorSpell = spellList.iterator();
            while(iteratorSpell.hasNext()){
                Circle spell = iteratorSpell.next();
                if(Circle.isColliding(spell,enemy)){
                    iteratorSpell.remove();
                    iteratorEnemy.remove();
                    break;//Only need to collide with one spell
                }

            }
        }

    }
}
