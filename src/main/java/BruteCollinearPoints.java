import java.util.Arrays;

public class BruteCollinearPoints {
    private int count = 0;
    private LineSegment[] segments;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new NullPointerException();
        }
        for (Point point : points) {
            if (point == null) {
                throw new NullPointerException();
            }
        }
        segments = new LineSegment[points.length];
        for (int a = 0; a < points.length - 3; a++) {
            for (int b = a + 1; b < points.length - 2; b++) {
                for (int c = b + 1; c < points.length - 1; c++) {
                    for (int d = c + 1; d < points.length; d++) {

                        if (points[a].equals(points[b])
                                || points[a].equals(points[c])
                                || points[a].equals(points[d])
                                || points[b].equals(points[c])
                                || points[b].equals(points[d])
                                || points[c].equals(points[d])) {
                            throw new IllegalArgumentException();
                        }

                        double ab = points[a].slopeTo(points[b]);
                        double ac = points[a].slopeTo(points[c]);
                        double ad = points[a].slopeTo(points[d]);
                        if (Double.compare(ab, ac) == 0 && Double.compare(ac, ad) == 0) {
                            Point[] results = new Point[]{points[a], points[b], points[c], points[d]};
                            Arrays.sort(results);
                            segments[count++] = new LineSegment(results[0], results[results.length - 1]);
                        }
                    }
                }
            }
        }

    }    // finds all line segments containing 4 points

    public int numberOfSegments() {
        return count;
    }        // the number of line segments

    public LineSegment[] segments() {
        LineSegment[] copy = new LineSegment[count];
        System.arraycopy(segments, 0, copy, 0, count);
        return copy;
    }                // the line segments

}
