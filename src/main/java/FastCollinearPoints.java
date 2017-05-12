public class FastCollinearPoints {
    private LineSegment[] segments;
    private int count = 0;

    public FastCollinearPoints(Point[] points) {
        segments = new LineSegment[0];
    }     // finds all line segments containing 4 or more points

    public int numberOfSegments() {
        return segments.length - 1;
    }        // the number of line segments

    public LineSegment[] segments() {
        LineSegment[] copy = new LineSegment[count];
        System.arraycopy(segments, 0, copy, 0, count);
        return copy;
    }                // the line segments
}
