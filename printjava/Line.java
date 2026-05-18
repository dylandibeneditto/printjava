package printjava;

import java.util.ArrayList;

public class Line {
    public ArrayList<Point2> points;

    public Line() {
        super();
        this.points = new ArrayList<Point2>();
    }

    public Line(ArrayList<Point2> points) {
        this.points = points;
    }

    public void add(Point2 p) {
        this.points.add(p);
    }
}
