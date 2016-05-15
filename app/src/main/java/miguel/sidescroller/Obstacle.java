package miguel.sidescroller;

import android.graphics.Canvas;

/**
 * Created by miguel on 5/4/2016.
 */
public class Obstacle {
    int x, y;
    int width, height, radius;
    public Obstacle(){

    }
    public Obstacle(int x, int y, int width){
        this.x = x;
        this.y = y;
        this.width = width;
    }
    public void draw(int canvasX, int canvasY, int tileWidth, int tileHeight, Canvas c){
    }

}
