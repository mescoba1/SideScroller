package miguel.sidescroller;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

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
    Vector<Enemy> enemies;
    Vector<Portal> portals;
    Level coinLevel;
    int score, lives;
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

        enemies = new Vector<Enemy>();
        enemies.add(level.Level0Enemy());
        enemies.add(level.Level1Enemy());
        enemies.add(level.Level2Enemy());

        portals = new Vector<Portal>();
        portals.add(level.Level0Port());
        portals.add(level.Level1Port());
        portals.add(level.Level2Port());

        //generate levels
        levels.add(level.Level0());
        levels.add(level.Level1());
        levels.add(level.Level2());
        coins.add(level.CoinLevel0());
        coins.add(level.CoinLevel1());
        coins.add(level.CoinLevel2());

        currentLevel = 0;
        player = new Player(tileWidth);
        player.x = 0;
        player.y = tileHeight*10 ;
        score = 0;
        lives = 5;
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
            if((player.y + tileWidth< (.95)*y && player.y+acceleration+tileWidth >= (1.05)*y) && ((player.x > x && player.x < x+w) || (player.x+player.width > x && player.x+player.width < x+w))){
                System.out.println("pre gravity catch");
                player.y = y-tileWidth;
                player.grounded = true;
                break;
            }
            //lands on another obstacle from left side
            if((player.x > x && player.x < x+w) && ((player.y+player.width >= (double) y*(.99)) && player.y+player.width < (double) y*(1.01)) && acceleration>1){
                player.grounded = true;
                player.y = y-tileWidth;
                break;
            }
            //lands from right side
            else if((player.x+player.width > x && player.x+player.width < x+w) && (player.y+player.width >= (double) y*(.99)) && player.y+player.width < (double) y*(1.01) && acceleration > 1) {
                player.grounded = true;
                player.y = y-tileWidth;
                break;
            }
            else {
                player.grounded = false;
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

            double d = tileWidth*levelCoin.get(i).radius;

            //checks if parts of block are drawable onto the screen
            if((x >= camX && x < camX+screenWidth) || (x < camX && x+d > camX)){
                //draw obstacle relative to camera position
                if(!levelCoin.get(i).aquired) {
                    levelCoin.get(i).draw(x-camX, y-tileWidth/2, tileWidth, c);
                }
            }
        }
    }

    Rect coin = new Rect(0,0,1,1);
    Rect playerRect = new Rect(0,0,1,1);
    Rect enemyRect = new Rect(0,0,0,0);
    Rect portalRect = new Rect(0,0,0,0);
    public void collision(int camX, Canvas c) {
        int px = (int) player.x;
        int py = (int) player.y;
        Vector<Coin> levelCoin = coins.get(currentLevel);
        int coinSize = levelCoin.size();
        //initialize
        //set
        playerRect.set(px, py, px + tileWidth, py + tileWidth);

        //check enemy collision
        Enemy enemy = enemies.get(currentLevel);
        int xE = enemy.x - camX;
        enemyRect.set(xE, enemy.y, xE + tileWidth, enemy.y + tileWidth);
        if (playerRect.intersect(enemyRect)) {
            player.y = -tileHeight;
            lives--;
        }

        //check coin collision
        for (int i = 0; i < coinSize; i++) {
            int x = tileWidth * levelCoin.get(i).x;
            int y = tileHeight * levelCoin.get(i).y;
            double d = tileWidth * levelCoin.get(i).radius / 2;
            x -= camX;
            //int r = (int) ((double)tileWidth*d);
            coin.set(x - (int) d, y - (int) (2 * d), x + (int) d, y + (int) (d));
            if (playerRect.intersect(coin) && levelCoin.get(i).aquired == false) {
                levelCoin.get(i).aquired = true;
                score += 100;
            }
        }
    }
    public boolean checkNextLevel(int camX){
        Portal port = portals.get(currentLevel);
        portalRect.set(port.x*tileWidth-camX,port.y*tileHeight,port.x*tileWidth+tileWidth-camX,(port.y+port.h)*tileHeight+tileWidth);
        if(playerRect.intersect(portalRect)){
            player.y = -tileHeight;
            player.x = 0;
            score = 0;
            currentLevel++;
            return true;
        }else {
            return false;
        }

    }

    public void drawScoreboard(Canvas c){
        Paint p = new Paint();
        p.setColor(Color.BLACK);
        p.setTextSize(100);
        c.drawText("Score: " + score, 0, 100, p);
        c.drawText("Lives: " + lives, screenWidth-350, 100, p);
    }

    public void drawEnemy(Canvas c, int camX){
        Enemy current  = enemies.get(currentLevel);
        current.draw(c);
    }

    public void drawPortal(Canvas c, int camX){
        Portal current = portals.get(currentLevel);
        current.draw(c,tileWidth,tileHeight,camX);
    }


}
