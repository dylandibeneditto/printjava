package printjava.Meshes;

import printjava.Quad;
import printjava.Triangle;
import printjava.Point;
import printjava.Point2;

public class Extrude extends Shape {
    public double height;

    public Extrude(Shape s) {
        super(s.points);
        this.height = 1;
    }

    public Extrude(Shape s, double height) {
        super(s.points);
        this.height = height;
    }

    public void generate() {
        for (int i = 0; i < this.points.size(); i++) {
            Point2 p1 = this.points.get(i);
            Point2 p2 = this.points.get((i + 1) % this.points.size());
            super.add(new Triangle(new Point(p1), new Point(p2), new Point(0,0,0)));
            super.add(new Triangle(new Point(0,this.height,0), new Point(p2, this.height), new Point(p1, this.height)));
            super.add(new Quad(new Point(p1, this.height), new Point(p2, this.height), new Point(p2, 0), new Point(p1, 0)));
        }
    }

    
}
