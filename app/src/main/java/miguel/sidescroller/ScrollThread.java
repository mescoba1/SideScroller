package miguel.sidescroller;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by miguel on 5/4/2016.
 */

public class ScrollThread extends Thread {
    ScrollView sv;
    public ScrollThread(ScrollView sv) {
        this.sv=sv;
    }
    public void run() {
        SurfaceHolder sh = sv.getHolder();
        // Main game loop.
        while( !Thread.interrupted() ) {
            //You might want to do game specific processing in a method you call here
            Canvas c = sh.lockCanvas(null);
            try {
                synchronized(sh) {
                    sv.draw(c);
                }
            } catch (Exception e) {
            } finally {
                if ( c != null ) {
                    sh.unlockCanvasAndPost(c);
                }
            }
            // Set the frame rate by setting this delay
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                // Thread was interrupted while sleeping.
                return;
            }
        }
    }
}

