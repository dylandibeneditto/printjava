package printjava.Meshes;

import java.util.ArrayList;

import printjava.Mesh;
import printjava.Triangle;
import printjava.Point;
import printjava.Point2;

public class Shape extends Mesh {
    public ArrayList<Point2> points;

    /**
     * Constructs a new Shape.
     */
    public Shape() {
        this.points = new ArrayList<Point2>();
    }

    /**
     * Constructs a new Shape.
     * 
     * @param points the points value.
     */
    public Shape(ArrayList<Point2> points) {
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

    /**
     * Generates the specified value.
     */
    public void generate() {
        for (int i = 0; i < this.points.size(); i++) {
            Point2 p1 = this.points.get(i);
            Point2 p2 = this.points.get((i + 1) % this.points.size());
            super.add(new Triangle(new Point(p1), new Point(p2), new Point(0, 0, 0)));
        }
    }
}
