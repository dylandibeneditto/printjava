package printjava.Meshes;

import printjava.Mesh;
import printjava.Triangle;
import printjava.Point;

public class Cone extends Mesh {
    private double radius, height;

    // number of subdivisions of the cone
    private int resolution = 30;

    // new Cone();
    public Cone() {
        super();
        this.radius = 1;
        this.height = 1;
        
    }

    // new Cone(1, 1);
    public Cone(double radius, double height) {
        super();
        this.radius = radius;
        this.height = height;
        
    }

    /// new Cone(1, 1, 30);
    public Cone(double radius, double height, int resolution) {
        super();
        this.radius = radius;
        this.height = height;
        this.resolution = resolution;
        
    }

    public void snapToGround() {
        this.anchor.y = 0;
    }

    public void snapToCenter() {
        this.anchor.y = this.height / 2;
    }

    /**
     * generates all the triangles that make up a cone
     */
    public void generate() {
        Point topCenter = new Point(0, this.height, 0);
        Point bottomCenter = new Point(0, 0, 0);

        for(int i = 0; i < this.resolution; i++) {
            double theta0 = i * 2 * Math.PI / this.resolution;
            double theta1 = (i + 1) * 2 * Math.PI / this.resolution;

            double x0 = this.radius * Math.cos(theta0);
            double z0 = this.radius * Math.sin(theta0);
            double x1 = this.radius * Math.cos(theta1);
            double z1 = this.radius * Math.sin(theta1);

            Point top0 = new Point(x0, 0, z0);
            Point top1 = new Point(x1, 0, z1);

            add(new Triangle(topCenter, top1, top0));
            add(new Triangle(top0, top1, bottomCenter));
        }
    }
}
