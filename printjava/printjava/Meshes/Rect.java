package printjava.Meshes;

import printjava.Mesh;
import printjava.Point;
import printjava.Triangle;

public class Rect extends Mesh {
    private double width, height, depth;

    // new Rect();
    public Rect() {
        super();
        this.width = 1;
        this.height = 1;
        this.depth = 1;
        this.generate();
    }

    // new Rect(1);
    public Rect(double size) {
        super();
        this.width = size;
        this.height = size;
        this.depth = size;
        this.generate();
    }

    // new Rect(1, 1, 1);
    public Rect(double width, double height, double depth) {
        super();
        this.width = width;
        this.height = height;
        this.depth = depth;
        generate();
    }

    public void snapToGround() {
        this.anchor.y = -this.height / 2;
    }

    public void snapToCenter() {
        this.anchor.y = 0;
    }

    /**
     * generates all the triangles that make up a rectangular prism
     */
    public void generate() {
        this.snapToGround();

        double halfWidth = this.width / 2.0;
        double halfHeight = this.height / 2.0;
        double halfDepth = this.depth / 2.0;

        Point v0 = new Point(-halfWidth, -halfHeight, -halfDepth);
        Point v1 = new Point(halfWidth, -halfHeight, -halfDepth);
        Point v2 = new Point(halfWidth, halfHeight, -halfDepth);
        Point v3 = new Point(-halfWidth, halfHeight, -halfDepth);
        Point v4 = new Point(-halfWidth, -halfHeight, halfDepth);
        Point v5 = new Point(halfWidth, -halfHeight, halfDepth);
        Point v6 = new Point(halfWidth, halfHeight, halfDepth);
        Point v7 = new Point(-halfWidth, halfHeight, halfDepth);

        add(new Triangle(v0, v1, v2));
        add(new Triangle(v0, v2, v3));
        add(new Triangle(v1, v5, v6));
        add(new Triangle(v1, v6, v2));
        add(new Triangle(v5, v4, v7));
        add(new Triangle(v5, v7, v6));
        add(new Triangle(v4, v0, v3));
        add(new Triangle(v4, v3, v7));
        add(new Triangle(v3, v2, v6));
        add(new Triangle(v3, v6, v7));
        add(new Triangle(v5, v1, v0));
        add(new Triangle(v4, v5, v0));        
    }
}
