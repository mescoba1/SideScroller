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
                return true;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas c){
    }

    public void draw(Canvas c) {
        c.drawColor(Color.WHITE);
        game.drawLevel(camera, c);
        if(camera < screenWidth){
            camera++;
        }
    }
}



