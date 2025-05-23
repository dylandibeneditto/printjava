package printjava.Meshes;

import printjava.Mesh;
import printjava.Point;
import printjava.Triangle;

public class Plane extends Mesh {
    private double width, height;

    // new Plane();
    public Plane() {
        super();
        this.width = 1;
        this.height = 1;
        
    }

    // new Plane(1);
    public Plane(double size) {
        super();
        this.width = size;
        this.height = size;
        
    }

    // new Plane(1, 1);
    public Plane(double width, double height) {
        super();
        this.width = width;
        this.height = height;
        
    }

    /**
     * generates both triangles needed to make a one sided plane
     */
    public void generate() {
        double halfWidth = this.width / 2;
        double halfHeight = this.height / 2;

        add(new Triangle(new Point(halfWidth, 0, halfHeight), new Point(halfWidth, 0, -halfHeight),  new Point(-halfWidth, 0, -halfHeight)));

        add(new Triangle(new Point(-halfWidth, 0, halfHeight), new Point(halfWidth, 0, halfHeight),  new Point(-halfWidth, 0, -halfHeight)));
    }
}
