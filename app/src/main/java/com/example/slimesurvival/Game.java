package com.example.slimesurvival;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.example.slimesurvival.graphics.SpriteSheet;
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
    private GameDisplay gameDisplay;
    private GameOver gameOver;


    public Game(Context context,int Difficulty) {
        super(context);

        // Get surface holder and add callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        //Initialising Game Loop
        gameLoop = new GameLoop(this, surfaceHolder);
        //Initialising Joystick
        joystick = new Joystick(275, 700, 70, 40);

        //Initialising Player
        SpriteSheet spriteSheet = new SpriteSheet(context);
        switch(Difficulty){
            case 1:
                player = new Player(getContext(),joystick,500,500,64, spriteSheet.getPlayerSprite(),10);
                break;
            case 2:
                player = new Player(getContext(),joystick,500,500,64, spriteSheet.getPlayerSprite(),5);
                break;
            case 3:
                player = new Player(getContext(),joystick,500,500,64, spriteSheet.getPlayerSprite(),1);
                break;
            default:
                player = new Player(getContext(),joystick,500,500,64, spriteSheet.getPlayerSprite(),10);
                break;

        }
        //player = new Player(getContext(),joystick,500,500,64, spriteSheet.getPlayerSprite());


        //Initialising Game over overlay
        gameOver = new GameOver(context);


        //Initialising Enemy
        //enemy = new Enemy(getContext(),player,500,200,30);

        //Initialising GameDisplay centered around player
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        gameDisplay = new GameDisplay(displayMetrics.widthPixels, displayMetrics.heightPixels,player);
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
        if (gameLoop.getState().equals(Thread.State.TERMINATED)) {
            gameLoop = new GameLoop(this, surfaceHolder);
        }
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

        //First drawing the background since everything else will layer ontop
        //It's utilising bitmaps and the stored iamge to provide a background
        //canvas.drawRGB(0, 0, 0);
        //Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        //Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, canvas.getWidth(),    canvas.getHeight(), true);
        //canvas.drawBitmap(scaledBitmap, 0, 0, null);
        //Current backgrounds looks weird leaving it blank

        drawUPS(canvas);
        drawFPS(canvas);
        drawPlayerHealth(canvas);
        drawScore(canvas);


        joystick.draw(canvas);
        player.draw(canvas, gameDisplay);
        for(Enemy enemy:enemyList){
            enemy.draw(canvas, gameDisplay);
        }
        for(Spell spell:spellList){//Update through spell list
            spell.draw(canvas, gameDisplay);
        }
        //enemy.draw(canvas);


        //Draw Game over when dead
        if(player.getHealthPoints() <= 0){
            gameOver.draw(canvas);
        }

    }

    //Displays on the screen     how many updates per second there are
    public void drawUPS(Canvas canvas){
        String averageUPS = Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(30);
        canvas.drawText("UPS: "+averageUPS,100,50,paint);
    }


    //Displays on the screen how many frames per second there are
    public void drawFPS(Canvas canvas){
        String averageFPS = Double.toString(gameLoop.getAverageFPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(30);
        canvas.drawText("FPS: "+averageFPS,100,110,paint);
    }
    public void drawPlayerHealth(Canvas canvas){
        String health = Integer.toString(player.getHealthPoints());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.blue);
        paint.setColor(color);
        paint.setTextSize(30);
        canvas.drawText("health: "+health,1800,50,paint);
    }
    public void drawScore(Canvas canvas){
        String score = Integer.toString(gameLoop.getscore());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.blue);
        paint.setColor(color);
        paint.setTextSize(30);
        canvas.drawText("Score: "+score,1800,110,paint);
    }
    public void update() {
        //Updates the game state

        //stop updating the game if the player is dead
        if(player.getHealthPoints() <= 0){
            return;
        }
        joystick.update();
        player.update();

        while(numberOfSpellsToCast > 0){
            spellList.add(new Spell(getContext(),player));
            numberOfSpellsToCast--;
        }
        //Create a new enemy if it is time to create a new enemy (No spamming spawning, setting them up at a fair rate)
        if(Enemy.readyToSpawn()){
            SpriteSheet spriteSheet = new SpriteSheet(getContext());
            enemyList.add(new Enemy(getContext(),player,spriteSheet.getEnemySprite()));
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
                    gameLoop.setscore(1);
                    break;//Only need to collide with one spell
                }

            }
        }
        gameDisplay.update();
    }


    public void pause() {
        gameLoop.stoploop();
    }
}
