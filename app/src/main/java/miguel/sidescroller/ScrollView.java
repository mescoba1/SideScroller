package miguel.sidescroller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
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

    public void draw(Canvas c) {
        camera = 5000;
        c.drawColor(Color.rgb(135, 206, 250));
        game.drawCoins(camera,c);
        game.drawLevel(camera, c);
        game.gravity(camera);
        game.movePlayer(dir, moveLeft, moveRight);
        game.checkWall(camera);
        game.collision(camera);
        if(camera < (game.tileWidth * game.level.levelWidth) - game.screenWidth){
            //camera+=2;
        }
        game.drawScoreboard(c);
    }
}



