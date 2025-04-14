package printjava.Meshes;

import java.util.ArrayList;
import printjava.Mesh;
import printjava.Triangle;
import printjava.Point;

public class Cylinder extends Mesh {
    private double radius;
    private double height;
    // number of subdivisions of the cylinder
    private int resolution = 30;

    // new Cylinder();
    public Cylinder() {
        super();
        this.radius = 1;
        this.height = 1;
        
    }

    // new Cylinder(1, 1);
    public Cylinder(double radius, double height) {
        super();
        this.radius = radius;
        this.height = height;
        
    }

    // new Cylinder(1, 1, 30);
    public Cylinder(double radius, double height, int resolution) {
        super();
        this.radius = radius;
        this.height = height;
        this.resolution = resolution;
        
    }

    public void snapToGround() {
        this.anchor.y = -this.height / 2;
    }

    public void snapToCenter() {
        this.anchor.y = 0;
    }

    /**
     * generates all the triangles that make up a cylinder
     */
    public void generate() {
        

        ArrayList<Point> topPoints = new ArrayList<Point>();
        ArrayList<Point> bottomPoints = new ArrayList<Point>();

        Point topCenter = new Point(0, this.height / 2, 0);
        Point bottomCenter = new Point(0, -this.height / 2, 0);

        for(int i = 0; i < this.resolution; i++) {
            double theta = i * 2 * Math.PI / this.resolution;
            double x = this.radius * Math.cos(theta);
            double z = this.radius * Math.sin(theta);

            topPoints.add(new Point(x, this.height / 2, z));
            bottomPoints.add(new Point(x, -this.height / 2, z));
        }

        for(int i = 0; i < this.resolution; i++) {
            Point top0 = topPoints.get(i);
            Point top1 = topPoints.get((i + 1) % this.resolution);
            Point bottom0 = bottomPoints.get(i);
            Point bottom1 = bottomPoints.get((i + 1) % this.resolution);

            add(new Triangle(topCenter, top1, top0));
            add(new Triangle(bottomCenter, bottom0, bottom1));
            add(new Triangle(top0, top1, bottom1));
            add(new Triangle(top0, bottom1, bottom0));
        }
    }
}
