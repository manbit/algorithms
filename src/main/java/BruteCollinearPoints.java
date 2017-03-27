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
        for (int i = 0; i < points.length - 3; i++) {
            for (int j = i + 1; j < points.length - 2; j++) {
                for (int x = j + 1; x < points.length - 1; x++) {
                    for (int z = x + 1; z < points.length; z++) {

                        if (points[i].equals(points[j])
                                || points[i].equals(points[x])
                                || points[i].equals(points[z])
                                || points[j].equals(points[x])
                                || points[j].equals(points[z])
                                || points[x].equals(points[z])) {
                            throw new IllegalArgumentException();
                        }

                        double xj = points[i].slopeTo(points[j]);
                        double xi = points[i].slopeTo(points[x]);
                        double xz = points[i].slopeTo(points[z]);
                        if (xj == xi && xi == xz) {
                            segments[count++] = new LineSegment(points[x], points[z]);
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
        return segments;
    }                // the line segments

}
