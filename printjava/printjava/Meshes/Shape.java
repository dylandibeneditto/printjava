package printjava.Meshes;

import java.util.ArrayList;

import printjava.Mesh;
import printjava.Point2;

public class Shape extends Mesh {
    public ArrayList<Point2> points;

    public Shape() {
        this.points = new ArrayList<Point2>();
    }

    public void add(Point2 p) {
        this.points.add(p);
    }

    // TODO: standardize so that every shape has a generate method, which is called on STL.add
    public void generate() {
    }
}
