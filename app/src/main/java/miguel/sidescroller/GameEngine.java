package miguel.sidescroller;

import android.graphics.Canvas;
import android.graphics.Color;

import java.util.Random;
import java.util.Vector;

/**
 * Created by miguel on 5/4/2016.
 */
public class GameEngine {
    protected int screenWidth, screenHeight;
    private int radius, temp;
    double acceleration = 2.0;
    protected int tileWidth, tileHeight;
    public int currentLevel;
    public boolean canMoveRight = true, canMoveLeft = true, dragLeft = false, dragRight = false, triggerJump = false;
    private Canvas c;
    public int speed = 5;
    Vector<Vector<Obstacle>> levels;
    Level level;
    Player player;
    Level clevel;
    Enemy dude;
    double jumpSpeed = 12;
    Vector<Vector<Coin>> coins;
    Level coinLevel;

    public GameEngine(int sw, int sh){
        screenHeight = sh;
        screenWidth = sw;
        this.c = c;
        tileHeight = sh / 16;
        tileWidth = sw / 9;
        //level object
        level = new Level(screenWidth, screenHeight);
        coinLevel = new Level(screenWidth, screenHeight);
        //vector of levels
        levels = new Vector<Vector<Obstacle>>();
        //vector of coins
        coins= new Vector<Vector<Coin>>();

        //generate levels
        levels.add(level.Level2());
        coins.add(level.CoinLevel2());
        currentLevel = 0;
        player = new Player(tileWidth);
        player.x = 0;
        player.y = tileHeight*10 ;
        dude = new Enemy(4*tileWidth, 4*tileWidth, tileWidth);
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
            Random rand= new Random();
            //Color.rgb(rand.nextInt(256)
            player.p.setColor(Color.rgb(0,0,0));
        } else {
            player.p.setColor(Color.BLUE);
        }
        if(triggerJump){
            acceleration = 0;
            jumpSpeed -= .1;
            player.y -= jumpSpeed;
            if(player.y <= temp-(3.5)*tileWidth){
                triggerJump = false;
                player.grounded = true;
                player.jumpState = false;
                acceleration = 2.5;
                jumpSpeed = 12;
            }
        }
        if(!player.jumpState && !player.grounded){
            acceleration+= 0.1;
        }
        player.draw(c);
        //dude.draw(c);
        //dude.update();
        /*System.out.println("TJ " + triggerJump);
        System.out.println("Player y: " + player.y);
        System.out.println("temp " + (temp));
        System.out.println("limit " + (temp-3*tileWidth));*/
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
        else if(dir == ScrollView.Directions.Up && player.grounded &&player.jumpState==false){
            //trigger jumping frame by frame
            if(player.y+10> 0 && player.y < screenHeight) {
                triggerJump = true;
                temp = (int) player.y;
                //player.y -= 3*tileWidth;
            }
            if(triggerJump) {
                player.grounded = false;
                player.jumpState = true;
            }
        }
    }

    public void gravity(int camX){
       checkGround(camX);
        if(player.grounded == false){
            player.y += acceleration;
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
            if((player.x > x && player.x < x+w) && ((player.y+player.width >= (double) y*(.9975)) && player.y+player.width < (double) y*(1.005))){
                player.grounded = true;
                break;
            }
            //lands from right side
            else if((player.x+player.width > x && player.x+player.width < x+w) && (player.y+player.width >= (double) y*(.9975)) && player.y+player.width < (double) y*(1.005)) {
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
                /*canMoveRight = false;
                dragLeft = true;
                break;*/
            }
            //hitting obstacle from right side
            else if(player.x == x+w && (player.y > y && player.y < y+w)){
                /*canMoveLeft = false;
                dragRight = true;
                break;*/
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
    public void drawCoins(int camX, Canvas c){
        //draw the coins here with
        Vector<Coin> levelCoin = coins.get(currentLevel);
        int csize= levelCoin.size();

        for(int i = 0; i < csize; i++){
            //convert grid pos to screen positions
            int x = tileWidth * levelCoin.get(i).x;
            int y = tileHeight * levelCoin.get(i).y;
            //int x = levelCoin.get(i).x;
            //int y = levelCoin.get(i).y;

            //convert grid Width/Height to Screen

            int d = tileWidth*levelCoin.get(i).diameter;


            //checks if parts of block are drawable onto the screen
            if((x >= camX && x < camX+screenWidth) || (x < camX && x+d > camX)){
                //draw obstacle relative to camera position
                if(!levelCoin.get(i).aquired) {
                    levelCoin.get(i).draw(x - camX, y - d / 4, d, c);
                }
            }
        }
    }

    public void collision(){

    }


}
