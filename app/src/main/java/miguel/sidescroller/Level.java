package miguel.sidescroller;

import java.util.Vector;

/**
 * Created by miguel on 5/4/2016.
 */
public class Level {
    int width, height, levelWidth = 45, levelHeight = 16;
    int tileWidth, tileHeight;
    public Level(int screenWidth, int screenHeight) {
        width = screenWidth;
        height = screenHeight;
        tileWidth = screenWidth / 9;
        tileHeight = screenHeight / 16;
    }

    /*Obstacle vector in level

     */
    //coinLevel
    public Vector<Coin> CoinLevel0() {
        Vector<Coin> coins = new Vector<Coin>();

       // coins.add(new Coin(2, 1));
        coins.add(new Coin(7, 1));
        coins.add(new Coin(6, 1));
        coins.add(new Coin(5, 1));
        coins.add(new Coin(3, 4));
        coins.add(new Coin(4, 4));
        coins.add(new Coin(5, 4));
        for(int i=10; i<=45; i+=5){
            coins.add(new Coin(i,15));
        }
        for(int i=4; i<=10; i++){
            coins.add(new Coin(i,6));
        }
        for(int i=4; i<=7; i++){
            coins.add(new Coin(i,8));
        }
        for(int i=10; i<40; i+=2){
            coins.add(new Coin(i,13));
        }
        for(int i=35;i<44;i++){
            coins.add(new Coin(i,3));
        }
        for(int i=33;i<3;i++){
            coins.add(new Coin(i,10));
        }
        return coins;
    }

    public Vector<Obstacle> Level0() {
        Vector<Obstacle> obstacles = new Vector<Obstacle>();

        obstacles.add(new Pipe(2,9,4));
        obstacles.add(new Platform(1, 15, 4));
        obstacles.add(new Platform(2, 4, 5));
        obstacles.add(new Platform(4, 6, 6));
        obstacles.add(new Platform(5,1,3));
        obstacles.add(new Platform(4, 8, 7));
        obstacles.add(new Platform(8, 10, 8));
        obstacles.add(new Platform(20, 10, 9));
        obstacles.add(new Platform(23, 4, 6));
        obstacles.add(new Platform(19, 6,5));
        obstacles.add(new Platform(33, 10, 10));
        obstacles.add(new Platform(35, 3, 9));
        obstacles.add(new Platform(30, 4, 2));
        obstacles.add(new Platform(0, 13, 40));
        obstacles.add(new Pipe(30, 10,3));
        obstacles.add(new Pipe(10, 5, 4));

        obstacles.add(new Floor(0, 15, 45));
        return obstacles;
    }

    public Vector<Coin> CoinLevel1() {
        Vector<Coin> coins = new Vector<Coin>();
        for(int i=11; i<=17; i++){
            coins.add(new Coin(i,5));
        }
        for(int i=1; i<=17; i++){
            coins.add(new Coin(i,13));
        }
        for(int i=40; i<=45; i++){
            coins.add(new Coin(i,6));
        }
        for(int i=40; i<=45; i++){
            coins.add(new Coin(i,9));
        }
        for(int i=27; i<=36; i++){
            coins.add(new Coin(i,13));
        }
        for(int i=29; i<=37; i++){
            coins.add(new Coin(i,5));
        }
        return coins;
    }

    public Vector<Obstacle> Level1() {
        Vector<Obstacle> obstacles = new Vector<Obstacle>();

        obstacles.add(new Floor(0, 15, 45));
        obstacles.add(new Platform(1, 13, 4));
        obstacles.add(new Pipe(4, 12, 3));
        obstacles.add(new Pipe(6, 10, 5));
        obstacles.add(new Pipe(8, 8, 7));
        obstacles.add(new Platform(10, 13, 8));
        obstacles.add(new Platform(11, 5, 7));
        obstacles.add(new Pipe(15, 10, 5));
        obstacles.add(new Platform(17, 5, 5));
        obstacles.add(new Platform(23, 4, 5));
        obstacles.add(new Platform(29, 5, 7));
        obstacles.add(new Platform(32, 3, 7));
        obstacles.add(new Pipe(30, 9, 6));
        obstacles.add(new Platform(27,13,9));
        obstacles.add(new Pipe(35,9, 7));
        obstacles.add(new Platform(37, 6, 2));
        obstacles.add(new Platform(40, 6, 5));
        obstacles.add(new Platform(40, 9, 5));

        // obstacles.add(new Platform(0,13,90));

        return obstacles;
    }
    public Vector<Coin> CoinLevel2() {
        Vector<Coin> coins = new Vector<Coin>();
      /*  for(int i=2; i<45;i+=2){
            coins.add(new Coin(i,15));
        }*/
        for(int i=3; i<+12;i++){
            coins.add(new Coin(i,5));
        }
        for(int i=20;i<=44;i++){
            coins.add(new Coin(i,7));
        }
        for(int i=13;i<=23;i++){
            coins.add(new Coin(i,20));
        }
        for(int i=30;i<=45;i++){
            coins.add(new Coin(i,10));
        }
        for(int i=13;i<=24;i++){
            coins.add(new Coin(i,2));
        }
        for(int i=3; i<=8; i++){
            coins.add(new Coin(i,10));
        }
        for(int i=20; i<=44; i++){
            coins.add(new Coin(i,7));
        }
        return coins;
    }
    public Vector<Obstacle> Level2() {
        Vector<Obstacle> obstacles = new Vector<Obstacle>();
        obstacles.add(new Floor(0, 15, 45));
        obstacles.add(new Pipe(1, 14, 2));
        obstacles.add(new Pipe(3, 10, 5));
        obstacles.add(new Pipe(5, 11, 4));
        obstacles.add(new Platform(3, 5, 9));
        obstacles.add(new Platform(13, 9, 9));
        obstacles.add(new Platform(13, 2, 11));
        obstacles.add(new Platform(15, 20, 9));
        obstacles.add(new Platform(20, 4, 15));
        obstacles.add(new Platform(20,7, 22));
        obstacles.add(new Platform(30,10,15));
        obstacles.add(new Platform(35,12,5));
        obstacles.add(new Pipe(9, 11, 4));
        obstacles.add(new Pipe(12, 14, 1));
        obstacles.add(new Platform(10, 9, 7));
        // obstacles.add(new Platform(0,13,90));

        return obstacles;
    }
    public Enemy Level0Enemy(){
        return new Enemy(13*tileWidth,5*tileHeight,tileWidth);
    }
    public Enemy Level2Enemy(){
        return new Enemy(44*tileWidth,7*tileHeight,tileWidth);
    }
    public Enemy Level1Enemy(){
        return new Enemy(20*tileWidth,1*tileHeight,tileWidth);
    }

    public Portal Level0Port(){
        return new Portal(44, 8,tileWidth);
    }
    public Portal Level1Port(){
        return new Portal(44,12,tileWidth);
    }
    public Portal Level2Port(){
        return new Portal(44,7,tileWidth);
    }
}
