package miguel.sidescroller;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by miguel on 5/14/2016.
 */
public class Player {
    public double x,y;
    int width, size;
    public Paint p = new Paint();
    public boolean grounded, jumpState;
    public Player(int tileSize){
        width = tileSize;
        grounded = false;
        jumpState = false;
        x = tileSize*5;
        y = tileSize*5;
        size =tileSize;
        p.setColor(Color.BLACK);
    }

    public void draw(Canvas c){
        c.drawRect((int) x,(int)y,(int) x+width,(int) y+width, p);
    }
}
