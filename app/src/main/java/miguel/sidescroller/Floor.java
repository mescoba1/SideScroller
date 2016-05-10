package miguel.sidescroller;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by miguel on 5/10/2016.
 */

public class Floor extends Obstacle {
    public int x, y;
    public final int width, height;
    public Floor(int x, int y, int width){
        super(x,y, width);
        this.x = x;
        this.y = y;
        this.width = width;
        height = 1;
    }
    public void draw(int canvasX, int canvasY, int tileWidth, int tileHeight, Canvas c){
        Paint p = new Paint();
        p.setColor(Color.GREEN);
        c.drawRect(canvasX, canvasY,canvasX+(width*tileWidth), canvasY+(height*tileHeight), p);
    }
}