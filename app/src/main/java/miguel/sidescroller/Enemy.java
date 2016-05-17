package miguel.sidescroller;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

/**
 * Created by miguel on 5/15/2016.
 */
public class Enemy {
    public int x, y, width, size;
    private int count = 0, dir = 1;
    public Enemy(int x, int y, int size){
        this.x = x;
        this.y = y;
        width = 1;
        this.size = size;
    }
    public void draw(Canvas c){
        Paint p = new Paint();
        p.setColor(Color.RED);
        c.drawRect(x, y, x+size, y+size, p);
    }

    public void update(){
        if(count >= size){
            dir*=-1;
            count = 0;
        }
        count++;
        x+=dir;
    }


}
