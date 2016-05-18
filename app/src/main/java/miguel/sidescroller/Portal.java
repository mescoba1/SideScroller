package miguel.sidescroller;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by miguel on 5/18/2016.
 */
public class Portal {
    public int x,y,w,h, size;
    public Portal(int x, int y, int size){
        this.x = x;
        this.y = y;
        this.w = 1;
        this.h = 4;
        this.size = size;
    }
    public void draw(Canvas c, int tw, int th, int camX){
        Paint p = new Paint();
        p.setColor(Color.YELLOW);
        c.drawRect(this.x*tw-camX,this.y*th,this.x*w*tw+size-camX,(this.y+h)*th+size,p);
    }
}
