import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.awt.*;

import edu.princeton.cs.algs4.StdDraw;

@RunWith(JUnit4.class)
public class PointTest {

    @Test
    public void testDraw() throws InterruptedException {
        int scale = 10;
        StdDraw.setXscale(-scale, scale);
        StdDraw.setYscale(-scale, scale);

        StdDraw.setPenRadius(0.01);

        for (int x = 0; x <= scale; x++) {
            StdDraw.text(x, -1, String.valueOf(x));
            StdDraw.text(-1, x, String.valueOf(x));
        }
        for (int x = scale; x >= 0; x--) {
            StdDraw.text(-x, -1, String.valueOf(-x));
            StdDraw.text(-1, -x, String.valueOf(-x));
        }

        StdDraw.setPenRadius(0.002);
        StdDraw.line(-scale, 0, scale, 0);
        StdDraw.line(0, -scale, 0, scale);

        StdDraw.setPenRadius(0.01);
        Point _01 = new Point(0, 1);
        Point _10 = new Point(1, 0);
        Point _12 = new Point(1, 2);
        Point _21 = new Point(2, 1);

        StdDraw.setPenColor(Color.BLUE);
        _01.draw();
        sleepShow();

        StdDraw.setPenColor(Color.RED);
        _10.draw();
        sleepShow();

        StdDraw.setPenColor(Color.GREEN);
        _01.drawTo(_10);

        StdDraw.setPenColor(Color.BLUE);
        _12.draw();
        sleepShow();

        StdDraw.setPenColor(Color.RED);
        _21.draw();
        sleepShow();

        StdDraw.setPenColor(Color.GREEN);
        _12.drawTo(_21);

        while (!StdDraw.mousePressed()) {
        }
    }

    @Test
    public void testDraw2() throws InterruptedException {
        int scale = 20;
        StdDraw.setXscale(-2, scale);
        StdDraw.setYscale(-2, scale);

        StdDraw.setPenRadius(0.01);

        for (int x = 0; x <= scale; x++) {
            StdDraw.text(x, -1, String.valueOf(x));
            StdDraw.text(-1, x, String.valueOf(x));
        }

        StdDraw.setPenRadius(0.002);
        StdDraw.line(-scale, 0, scale, 0);
        StdDraw.line(0, -scale, 0, scale);

        StdDraw.setPenRadius(0.01);
        Point _01 = new Point(0, 1);
        Point _10 = new Point(1, 0);
        Point _12 = new Point(1, 2);
        Point _21 = new Point(2, 1);

        StdDraw.setPenColor(Color.BLUE);
        _01.draw();
        sleepShow();

        StdDraw.setPenColor(Color.RED);
        _10.draw();
        sleepShow();

        StdDraw.setPenColor(Color.GREEN);
        _01.drawTo(_10);

        StdDraw.setPenColor(Color.BLUE);
        _12.draw();
        sleepShow();

        StdDraw.setPenColor(Color.RED);
        _21.draw();
        sleepShow();

        StdDraw.setPenColor(Color.GREEN);
        _12.drawTo(_21);

        while (!StdDraw.mousePressed()) {
        }
    }

    private void sleepShow() throws InterruptedException {
        StdDraw.show();
        Thread.sleep(2_000);
    }

    @Test
    public void testSlopeTo() {
        Point p = new Point(10, 10);
        Point q = new Point(-10, 20);

        double v = p.slopeTo(q);
        double v1 = q.slopeTo(p);
    }
}