package miguel.sidescroller;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Christine on 5/14/2016.
 */
public class Coin  {
    public int x, y;
    public boolean aquired;
   // public final int width, height;
    public final double radius;
    public Coin(int x, int y){
        //super(x, y);
        this.x = x;
        this.y = y;
        radius = .5;
        aquired = false;



    }
    public void draw(int canvasX, int canvasY, int size, Canvas c){
        Paint p = new Paint();
        p.setColor(Color.YELLOW);
        //c.drawRect
        c.drawCircle(canvasX, canvasY,(int)(((double)size)*radius), p);

    }
}
/*public int x, y;
    public final int width, height;
    public Platform(int x, int y, int width){
        super(x,y, width);
        this.x = x;
        this.y = y;
        this.width = width;
        height = 1;
    }
    public void draw(int canvasX, int canvasY, int tileWidth, int tileHeight, Canvas c){
        Paint p = new Paint();
        p.setColor(Color.GRAY);
        c.drawRect(canvasX, canvasY,canvasX+(width*tileWidth), canvasY+(height*tileHeight), p);*/