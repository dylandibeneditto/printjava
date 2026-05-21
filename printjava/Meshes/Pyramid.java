package printjava.Meshes;

import printjava.Mesh;
import printjava.Triangle;
import printjava.Point;

public class Pyramid extends Mesh {
    // base x
    private double bx;
    // base y
    private double by;
    private double height;

    // new Pyramid();
    /**
     * Constructs a new Pyramid.
     */
    public Pyramid() {
        super();
        this.bx = 1;
        this.by = 1;
        this.height = 1;

    }

    // new Pyramid(1);
    /**
     * Constructs a new Pyramid.
     * 
     * @param size the size value.
     */
    public Pyramid(double size) {
        super();
        this.bx = size;
        this.by = size;
        this.height = size;

    }

    // new Pyramid(1, 1);
    /**
     * Constructs a new Pyramid.
     * 
     * @param b the b value.
     * @param h the h value.
     */
    public Pyramid(double b, double h) {
        super();
        this.bx = b;
        this.by = b;
        this.height = h;

    }

    // new Pyramid(1, 1, 1);
    /**
     * Constructs a new Pyramid.
     * 
     * @param bx the bx value.
     * @param by the by value.
     * @param h  the h value.
     */
    public Pyramid(double bx, double by, double h) {
        super();
        this.bx = bx;
        this.by = by;
        this.height = h;
    }

    /**
     * SnapToGrounds the specified value.
     */
    public void snapToGround() {
        this.anchor.y = -this.height / 2;
    }

    /**
     * SnapToCenters the specified value.
     */
    public void snapToCenter() {
        this.anchor.y = 0;
    }

    /**
     * generates all the triangles that make up a pyramid
     */
    public void generate() {
        double halfBx = this.bx / 2.0;
        double halfBy = this.by / 2.0;
        double halfHeight = this.height / 2.0;

        add(new Triangle(new Point(0, halfHeight, 0), new Point(halfBx, -halfHeight, -halfBy),
                new Point(-halfBx, -halfHeight, -halfBy)));

        add(new Triangle(new Point(0, halfHeight, 0), new Point(halfBx, -halfHeight, halfBy),
                new Point(halfBx, -halfHeight, -halfBy)));

        add(new Triangle(new Point(0, halfHeight, 0), new Point(-halfBx, -halfHeight, halfBy),
                new Point(halfBx, -halfHeight, halfBy)));

        add(new Triangle(new Point(0, halfHeight, 0), new Point(-halfBx, -halfHeight, -halfBy),
                new Point(-halfBx, -halfHeight, halfBy)));

        add(new Triangle(new Point(-halfBx, -halfHeight, -halfBy), new Point(halfBx, -halfHeight, -halfBy),
                new Point(halfBx, -halfHeight, halfBy)));

        add(new Triangle(new Point(halfBx, -halfHeight, halfBy), new Point(-halfBx, -halfHeight, halfBy),
                new Point(-halfBx, -halfHeight, -halfBy)));
    }
}
