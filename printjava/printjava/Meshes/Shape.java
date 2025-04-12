package printjava.Meshes;

import java.util.ArrayList;

import printjava.Mesh;
import printjava.Triangle;
import printjava.Point;
import printjava.Point2;

public class Shape extends Mesh {
    public ArrayList<Point2> points;

    public Shape() {
        this.points = new ArrayList<Point2>();
    }

    public Shape(ArrayList<Point2> points) {
        this.points = points;
    }

    public void add(Point2 p) {
        this.points.add(p);
    }

    public void generate() {
        for(int i = 0; i < this.points.size(); i++) {
            Point2 p1 = this.points.get(i);
            Point2 p2 = this.points.get((i + 1) % this.points.size());
            super.add(new Triangle(new Point(0,0,0), new Point(p2), new Point(p1)));
        }
    }
}
