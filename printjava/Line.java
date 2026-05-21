package printjava;

import java.util.ArrayList;

public class Line {
    public ArrayList<Point2> points;

    /**
     * Constructs a new Line.
     */
    public Line() {
        super();
        this.points = new ArrayList<Point2>();
    }

    /**
     * Constructs a new Line.
     * 
     * @param points the points value.
     */
    public Line(ArrayList<Point2> points) {
        this.points = points;
    }

    /**
     * Adds the specified value.
     * 
     * @param p the p value.
     */
    public void add(Point2 p) {
        this.points.add(p);
    }
}
