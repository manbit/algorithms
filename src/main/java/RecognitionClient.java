import java.awt.*;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class RecognitionClient {
    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);

        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(Color.BLUE);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();
        while (!StdDraw.mousePressed()) {
        }

        // print and draw the line segments
//        StdDraw.setPenColor(Color.YELLOW);
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (int i = collinear.numberOfSegments()-1; i >= 0; i--) {
            if (collinear.segments()[i] != null) {
                StdOut.println(collinear.segments()[i]);
                collinear.segments()[i].draw();
                /*while (!StdDraw.mousePressed()) {
                }*/
            }
        }
        StdDraw.show();

        while (!StdDraw.mousePressed()) {
        }
    }
}
