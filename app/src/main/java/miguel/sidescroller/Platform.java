package miguel.sidescroller;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by miguel on 5/4/2016.
 */
public class Platform extends Obstacle {
    public int x, y;
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
        p.setColor(Color.BLACK);
        c.drawRect(canvasX, canvasY,canvasX+(width*tileWidth), canvasY+(height*tileHeight), p);
    }
}
