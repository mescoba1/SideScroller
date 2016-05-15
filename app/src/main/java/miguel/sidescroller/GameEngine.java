package miguel.sidescroller;

import android.graphics.Canvas;

import java.util.Vector;

/**
 * Created by miguel on 5/4/2016.
 */
public class GameEngine {
    private int screenWidth, screenHeight;
    private int radius;
    protected int tileWidth, tileHeight;
    public int currentLevel;
    public boolean canMoveRight = true, canMoveLeft = true, dragLeft = false, dragRight = false;
    private Canvas c;
    public int speed = 2;
    Vector<Vector<Obstacle>> levels;
    Level level;
    Player player;
    Level clevel;

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
        player = new Player(tileWidth);
    }
    public void drawLevel(int camX, Canvas c){
        Vector<Obstacle> current = levels.get(currentLevel);
        int size = current.size();
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
        if(player.grounded == false){
            player.p.setColor(Color.LTGRAY);
        } else {
            player.p.setColor(Color.BLUE);
        }
        player.draw(c);
        //System.out.println("Player X: " + player.x);
        //System.out.println("Player y: " + player.y);
    }
    public void movePlayer(ScrollView.Directions dir, boolean left, boolean right){
        if (dir == ScrollView.Directions.Right && canMoveRight) {
            if(player.x+player.width < screenWidth)
                player.x+=5;
        }
        else if (dir == ScrollView.Directions.Left && canMoveLeft) {
            if(player.x > 0)
                player.x-=5;
        }
        else if(dir == ScrollView.Directions.Up && player.grounded && player.jumpState == false){
            if(player.y+10> 0 && player.y < screenHeight)
                player.y-=3*tileHeight;
            player.grounded = false;
            player.jumpState = true;
        }
    }

    public void gravity(int camX){
       checkGround(camX);
        if(player.grounded == false){
            player.y += 1;
            player.jumpState = false;
            checkGround(camX);
        }
    }
    public void checkGround(int camX){
        Vector<Obstacle> currentObstacles = levels.get(currentLevel);
        for(int i =0; i < currentObstacles.size(); i++){
            Obstacle temp = currentObstacles.get(i);
            //convert grid pos to screen positions
            int x = tileWidth * temp.x;
            int y = tileHeight * temp.y;
            x = x - camX;
            //convert grid Width/Height to Screen
            int w = tileWidth * temp.width;

            //lands on another obstacle from left side
            if((player.x > x && player.x < x+w) && (player.y + player.width == y)){
                player.grounded = true;
                break;
            }
            //lands from right side
            else if((player.x+player.width > x && player.x+player.width < x+w) && (player.y + player.width == y)) {
                player.grounded = true;
                break;
            }
            else {
                player.grounded = false;
            }
        }
    }
    public void checkWall(int camX){
        Vector<Obstacle> currentObstacles = levels.get(currentLevel);
        if(dragLeft)
            player.x-=speed;
        if(dragRight)
            player.x+=speed;
        for(int i =0; i < currentObstacles.size(); i++){
            Obstacle temp = currentObstacles.get(i);
            //convert grid pos to screen positions
            int x = tileWidth * temp.x;
            int y = tileHeight * temp.y;
            x = x - camX;
            //convert grid Width/Height to Screen
            int w = tileWidth * temp.width;
            //hitting obstacle from left side
            if((player.x+player.width == x) && (player.y > y && player.y < y+w)){
                canMoveRight = false;
                dragLeft = true;
                break;
            }
            //hitting obstacle from right side
            else if(player.x == x+w && (player.y > y && player.y < y+w)){
                canMoveLeft = false;
                dragRight = true;
                break;
            }
            //resetting flags if not colliding
            else {
                dragLeft = false;
                dragRight = false;
                canMoveRight = true;
                canMoveLeft = true;
            }
        }
    }

}
