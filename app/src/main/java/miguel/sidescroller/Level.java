package miguel.sidescroller;

import java.util.Vector;

/**
 * Created by miguel on 5/4/2016.
 */
public class Level {
    int width, height, levelWidth = 90, levelHeight = 16;
    public Level(int screenWidth, int screenHeight){
        width = screenWidth;
        height = screenHeight;
    }
    public Vector<Obstacle> Level0(){
        Vector<Obstacle> obstacles = new Vector<Obstacle>();
        obstacles.add(new Platform(1,1,4));
        obstacles.add(new Platform(2,4,5));
        obstacles.add(new Platform(4,6,6));
        obstacles.add(new Platform(4,8,7));
        obstacles.add(new Platform(8,10,8));
        obstacles.add(new Platform(20,10,9));
        obstacles.add(new Platform(40,10,10));
        obstacles.add(new Platform(0,13,90));
        obstacles.add(new Pipe(10,5,4));
        obstacles.add(new Floor(0,15,40));
        return obstacles;
    }
}
