package miguel.sidescroller;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by miguel on 5/10/2016.
 */
public class Pipe extends Obstacle {
    public int x, y;
    public final int width, height;
    public Pipe(int x, int y, int height){
        super(x,y, 1);
        this.x = x;
        this.y = y;
        this.width = 1;
        this.height = height;
    }
    public void draw(int canvasX, int canvasY, int tileWidth, int tileHeight, Canvas c){
        Paint p = new Paint();
        p.setColor(Color.rgb(0, 0, 0));
        c.drawRect(canvasX, canvasY,canvasX+(width*tileWidth), canvasY+(height*tileHeight), p);
    }
}
