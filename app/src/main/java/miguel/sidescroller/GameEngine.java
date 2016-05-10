package miguel.sidescroller;

import android.graphics.Canvas;

import java.util.Vector;

/**
 * Created by miguel on 5/4/2016.
 */
public class GameEngine {
    private int screenWidth, screenHeight;
    private int tileWidth, tileHeight;
    public int currentLevel;
    private Canvas c;
    Vector<Vector<Obstacle>> levels;
    Level level;
    public GameEngine(int sw, int sh){
        screenHeight = sh;
        screenWidth = sw;
        this.c = c;
        tileHeight = sh / 16;
        tileWidth = sw / 9;
        //level object
        level = new Level(screenWidth, screenHeight);
        //vector of levels
        levels = new Vector<Vector<Obstacle>>();
        //generate levels
        levels.add(level.Level0());
        currentLevel = 0;
    }
    public void drawLevel(int camX, Canvas c){
        Vector<Obstacle> current = levels.get(currentLevel);
        int size = current.size();
        for(int i = 0; i < size; i++){
            int x = tileWidth * current.get(i).x;
            int y = tileHeight * current.get(i).y;
            int w = tileWidth * current.get(i).width;
            int h = tileHeight * current.get(i).height;
            //checks if parts of block are drawable onto the screen
            if((x >= camX && x < camX+screenWidth) || (x < camX && x+w > camX)){
                //draw obstacle
                current.get(i).draw(x-camX, y, tileWidth, tileHeight, c);
            }
        }
    }

}
