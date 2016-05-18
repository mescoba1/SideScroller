package miguel.sidescroller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class ScrollView extends SurfaceView
        implements  SurfaceHolder.Callback {
    public Bitmap mybitmap;
    public int screenHeight, screenWidth, tileWidth, buttonWidth;
    public boolean movement = false;
    int camera = 0;
    public enum Directions{
        Up,Down,Left,Right
    }
    public boolean moveLeft = false, moveRight = false;
    public Directions dir;
    public ScrollView (Context context) {
        super(context) ;
        getHolder().addCallback(this);
        setFocusable(true);
        //initialize game state variables
        Bitmap mybitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
    }
    ScrollThread st;
    GameEngine game;

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        screenWidth = getWidth();
        screenHeight = getHeight();
        game = new GameEngine(screenWidth,screenHeight);
        st = new ScrollThread(this);
        st.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){
        //respond to surface changes
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    public void surfaceDestroyer(SurfaceHolder holder){
        //stops thread by interrupting it
        //checks for interrupt flag
        st.interrupt();
    }

    @Override
    public boolean onTouchEvent(MotionEvent e){
        float x = e.getX();
        float y = e.getY();
        switch (e.getAction()){
            case MotionEvent.ACTION_DOWN:
                //move left
                if(x < screenWidth/2 && y > screenHeight/2) {
                    moveLeft = true;
                    dir = Directions.Left;
                }
                //move right
                else if(x > screenWidth/2 && y > screenHeight/2) {
                    moveRight = true;
                    dir = Directions.Right;
                }
                //jump
                else if (y < screenHeight / 2) {
                    dir = Directions.Up;
                }
                return true;
            case MotionEvent.ACTION_UP:
                dir =Directions.Down;
                moveLeft = false;
                moveRight = false;
                break;
        }
        return false;
    }

    @Override
    protected void onDraw(Canvas c){
    }
    boolean nextlevel = false;
    public void draw(Canvas c) {
        c.drawColor(Color.rgb(135, 206, 250));
        //camera = 6700;
        game.drawLevel(camera, c);
        game.drawCoins(camera, c);
        //implements gravity
        game.gravity(camera);
        game.movePlayer(dir, moveLeft, moveRight);
        game.drawEnemy(c, camera);
        //pans the camera
        if(camera < (game.tileWidth * game.level.levelWidth) - game.screenWidth){
            camera+=2;
            //updates position of enemy
            game.enemies.get(game.currentLevel).update(2);
        }
        //check collision with coins and enemy
        game.collision(camera, c);
        game.drawScoreboard(c);
        game.drawPortal(c, camera);

        //move to next level falg
        nextlevel = game.checkNextLevel(camera);
        if(nextlevel){
            camera = 0;
            nextlevel = false;
        }
        //gameover screen
        if(game.lives <= 0){
            c.drawColor(Color.rgb(0,0,0));
            Paint p = new Paint();
            p.setColor(Color.BLACK);
            c.drawText("GAME OVER", 0, 100, p);
        }
    }
}



