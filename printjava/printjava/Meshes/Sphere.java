package printjava.Meshes;

import java.util.ArrayList;

import printjava.Mesh;
import printjava.Triangle;
import printjava.Point;

public class Sphere extends Mesh {
    // the radius of the sphere
    private double radius;
    
    // the number of subdivisions along the latitude and longitude of the sphere
    private int latitude = 30;
    private int longitude = 30;

    // new Sphere();
    public Sphere() {
        super();
        this.radius = 1;
        this.generate();
    }

    // new Sphere(1);
    public Sphere(double radius) {
        super();
        this.radius = radius;
        this.generate();
    }

    // new Sphere(1, 30, 30);
    public Sphere(double radius, int latitude, int longitude) {
        super();
        this.radius = radius;
        this.latitude = latitude;
        this.longitude = longitude;
        this.generate();
    }

    public void snapToGround() {
        this.anchor.y = -this.radius / 2;
    }

    public void snapToCenter() {
        this.anchor.y = 0;
    }

    /**
     * Generate all the triangles that make up the sphere
     */
    private void generate() {
        this.snapToGround();

        // generate the points of the sphere that intersect the latitude and longitude
        ArrayList<Point> points = new ArrayList<Point>();
        for (int i = 0; i <= this.latitude; i++) {
            double theta = i * Math.PI / this.latitude;
            double sinTheta = Math.sin(theta);
            double cosTheta = Math.cos(theta);

            for (int j = 0; j <= this.longitude; j++) {
                double phi = j * 2 * Math.PI / this.longitude;
                double sinPhi = Math.sin(phi);
                double cosPhi = Math.cos(phi);

                double x = this.radius * cosPhi * sinTheta;
                double y = this.radius * cosTheta;
                double z = this.radius * sinPhi * sinTheta;

                points.add(new Point(x, y, z));
            }
        }

        // connect all the points with a triangle
        for(int i = 0; i < this.latitude; i++) {
            for(int j = 0; j < this.longitude; j++) {
                int p0 = i * (this.longitude + 1) + j;
                int p1 = p0 + this.longitude + 1;

                add(new Triangle(points.get(p0), points.get(p1), points.get(p0 + 1)));
                add(new Triangle(points.get(p1), points.get(p1 + 1), points.get(p0 + 1)));
            }
        }
    }
}
