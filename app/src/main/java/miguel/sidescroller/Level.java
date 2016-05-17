package miguel.sidescroller;

import java.util.Vector;

/**
 * Created by miguel on 5/4/2016.
 */
public class Level {
    int width, height, levelWidth = 45, levelHeight = 16;

    public Level(int screenWidth, int screenHeight) {
        width = screenWidth;
        height = screenHeight;
    }

    /*Obstacle vector in level

     */
    //coinLevel
    public Vector<Coin> CoinLevel0() {
        Vector<Coin> coins = new Vector<Coin>();

        coins.add(new Coin(2, 1));
        coins.add(new Coin(3, 1));
        coins.add(new Coin(4, 1));
        coins.add(new Coin(5, 1));
        coins.add(new Coin(3, 4));
        coins.add(new Coin(4, 4));
        coins.add(new Coin(5, 4));
        coins.add(new Coin(10, 15));
        coins.add(new Coin(15, 15));
        coins.add(new Coin(20, 15));
        coins.add(new Coin(25, 15));
        coins.add(new Coin(30, 15));
        coins.add(new Coin(35, 15));
        coins.add(new Coin(40, 15));
        coins.add(new Coin(45, 15));
        coins.add(new Coin(4, 6));
        coins.add(new Coin(5, 6));
        coins.add(new Coin(6, 6));
        coins.add(new Coin(7, 6));
        coins.add(new Coin(8, 6));
        coins.add(new Coin(9, 6));
        coins.add(new Coin(10, 6));
        coins.add(new Coin(4, 8));
        coins.add(new Coin(5, 8));
        coins.add(new Coin(6, 8));
        coins.add(new Coin(7, 8));
        coins.add(new Coin(8, 13));
        coins.add(new Coin(10, 13));
        coins.add(new Coin(12, 13));
        coins.add(new Coin(14, 13));
        coins.add(new Coin(16, 13));
        coins.add(new Coin(18, 13));
        coins.add(new Coin(20, 13));
        coins.add(new Coin(22, 13));
        coins.add(new Coin(24, 13));
        coins.add(new Coin(26, 13));
        coins.add(new Coin(28, 13));
        coins.add(new Coin(30, 13));
        coins.add(new Coin(32, 13));
        coins.add(new Coin(34, 13));
        coins.add(new Coin(36, 13));
        coins.add(new Coin(38, 13));
        coins.add(new Coin(40, 13));
        return coins;
    }

    public Vector<Obstacle> Level0() {
        Vector<Obstacle> obstacles = new Vector<Obstacle>();


        obstacles.add(new Platform(1, 1, 4));
        obstacles.add(new Platform(2, 4, 5));
        obstacles.add(new Platform(4, 6, 6));
        obstacles.add(new Platform(4, 8, 7));
        obstacles.add(new Platform(8, 10, 8));
        obstacles.add(new Platform(20, 10, 9));
        obstacles.add(new Platform(33, 10, 10));
        obstacles.add(new Platform(0, 13, 45));
        obstacles.add(new Pipe(10, 5, 4));

        obstacles.add(new Floor(0, 15, 45));
        return obstacles;
    }

    public Vector<Coin> CoinLevel1() {
        Vector<Coin> coins = new Vector<Coin>();
        coins.add(new Coin(11, 5));
        coins.add(new Coin(12, 5));
        coins.add(new Coin(13, 5));
        coins.add(new Coin(14, 5));
        coins.add(new Coin(15, 5));
        coins.add(new Coin(16, 5));
        coins.add(new Coin(17, 5));
        coins.add(new Coin(1, 13));
        coins.add(new Coin(2, 13));
        coins.add(new Coin(3, 13));
        // coins.add(new Coin(4, 13));
        // coins.add(new Coin(4, 12));
        coins.add(new Coin(10, 13));
        coins.add(new Coin(11, 13));
        coins.add(new Coin(12, 13));
        coins.add(new Coin(13, 13));
        coins.add(new Coin(14, 13));
        //  coins.add(new Coin(15, 13));
        //  coins.add(new Coin(16, 13));
        coins.add(new Coin(17, 13));
        coins.add(new Coin(40, 6));
        coins.add(new Coin(41, 6));
        coins.add(new Coin(42, 6));
        coins.add(new Coin(43, 6));
        coins.add(new Coin(44, 6));
        coins.add(new Coin(45, 6));
        coins.add(new Coin(40, 9));
        coins.add(new Coin(41, 9));
        coins.add(new Coin(42, 9));
        coins.add(new Coin(43, 9));
        coins.add(new Coin(44, 9));
        coins.add(new Coin(45, 9));

        return coins;
    }

    public Vector<Obstacle> Level1() {
        Vector<Obstacle> obstacles = new Vector<Obstacle>();

        obstacles.add(new Floor(0, 15, 45));
        obstacles.add(new Platform(1, 13, 4));
        obstacles.add(new Pipe(4, 12, 3));
        obstacles.add(new Pipe(6, 10, 5));
        obstacles.add(new Pipe(8, 9, 6));
        obstacles.add(new Platform(10, 13, 8));
        obstacles.add(new Platform(11, 5, 7));
        obstacles.add(new Pipe(15, 10, 5));
        obstacles.add(new Platform(29, 5, 7));
        obstacles.add(new Platform(20, 12, 5));
        obstacles.add(new Platform(30, 3, 7));
        obstacles.add(new Pipe(30, 9, 6));
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
        return coins;
    }
    public Vector<Obstacle> Level2() {
        Vector<Obstacle> obstacles = new Vector<Obstacle>();
        obstacles.add(new Floor(0, 15, 45));
        obstacles.add(new Pipe(1, 11, 4));
        obstacles.add(new Pipe(3, 10, 5));
        obstacles.add(new Pipe(5, 11, 4));
        obstacles.add(new Platform(3, 5, 9));
        obstacles.add(new Platform(13, 9, 9));
        obstacles.add(new Platform(13, 2, 11));
        obstacles.add(new Platform(15, 20, 9));
        obstacles.add(new Platform(20, 4, 15));
        obstacles.add(new Platform(20,7, 22));
        obstacles.add(new Platform(30,10,15));
        obstacles.add(new Pipe(9, 11, 4));
        obstacles.add(new Pipe(12, 14, 1));
        obstacles.add(new Platform(10, 9, 7));
        // obstacles.add(new Platform(0,13,90));

        return obstacles;
    }
}
