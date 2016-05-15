package miguel.sidescroller;

import android.graphics.Canvas;

import java.util.Vector;

/**
 * Created by miguel on 5/4/2016.
 */
public class GameEngine {
    private int screenWidth, screenHeight;
    protected int tileWidth, tileHeight;
    private int radius;
    public int currentLevel;
    private Canvas c;
    Vector<Vector<Obstacle>> levels;
    Vector<Vector<Coin>> coins;
    Level level;
    Level clevel;

    public GameEngine(int sw, int sh){
        screenHeight = sh;
        screenWidth = sw;
        this.c = c;
        tileHeight = sh / 16;
        tileWidth = sw / 9;
        //level object
        level = new Level(screenWidth, screenHeight);
        clevel = new Level(screenWidth, screenHeight);
        //vector of coins
      // clevels = new Vector<Coin>();
        //generate coins

        //leval0.coins
        //coin.add.level0
      //  coins.add.level.Level0();
        //vector of levels
        levels = new Vector<Vector<Obstacle>>();
        coins= new Vector<Vector<Coin>>();
        //generate levels
        levels.add(level.Level3());
        coins.add(level.Level0_());
        currentLevel = 0;
    }
    public void drawLevel(int camX, Canvas c){
        Vector<Obstacle> current = levels.get(currentLevel);
        Vector<Coin> levelCoin = coins.get(currentLevel);

        int size = current.size();
        int csize= levelCoin.size();

        for(int i = 0; i < size; i++){
            //convert grid pos to screen positions
            int x = tileWidth * current.get(i).x;
            int y = tileHeight * current.get(i).y;

            //convert grid Width/Height to Screen
            int w = tileWidth * current.get(i).width;
            int h = tileHeight * current.get(i).height;


            //checks if parts of block are drawable onto the screen
            if((x >= camX && x < camX+screenWidth) || (x < camX && x+w > camX)){
                //draw obstacle relative to camera position
                current.get(i).draw(x-camX, y, tileWidth, tileHeight, c);
            }
        }
        for(int i = 0; i < csize; i++){
            //convert grid pos to screen positions
            int x = tileWidth * levelCoin.get(i).x;
            int y = tileHeight * levelCoin.get(i).y;

            //convert grid Width/Height to Screen
            int w = tileWidth * levelCoin.get(i).radius;



            //checks if parts of block are drawable onto the screen
            if((x >= camX && x < camX+screenWidth) || (x < camX && x+w > camX)){
                //draw obstacle relative to camera position
                levelCoin.get(i).draw(x-camX, y, tileWidth, tileHeight, c);
            }
        }
    }

}
