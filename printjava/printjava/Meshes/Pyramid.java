package printjava.Meshes;

import printjava.Mesh;
import printjava.Triangle;
import printjava.Point;

public class Pyramid extends Mesh {
    public double bx;
    public double by;
    public double height;

    public Pyramid() {
        super();
        this.bx = 1;
        this.by = 1;
        this.height = 1;
        this.generate();
    }

    public Pyramid(double size) {
        super();
        this.bx = size;
        this.by = size;
        this.height = size;
        this.generate();
    }

    public Pyramid(double b, double h) {
        super();
        this.bx = b;
        this.by = b;
        this.height = h;
        this.generate();
    }

    public Pyramid(double bx, double by, double h) {
        super();
        this.bx = bx;
        this.by = by;
        this.height = h;
        this.generate();
    }

    public void snapToGround() {
        this.anchor.y = -this.height / 2;
    }

    public void snapToCenter() {
        this.anchor.y = 0;
    }

    private void generate() {
        this.snapToGround();

        double halfBx = this.bx / 2.0;
        double halfBy = this.by / 2.0;
        double halfHeight = this.height / 2.0;

        add(new Triangle(
            new Point(-halfBx, -halfHeight, -halfBy),
            new Point(halfBx, -halfHeight, -halfBy),
            new Point(0, halfHeight, 0)
        ));

        add(new Triangle(
            new Point(halfBx, -halfHeight, -halfBy),
            new Point(halfBx, -halfHeight, halfBy),
            new Point(0, halfHeight, 0)
        ));

        add(new Triangle(
            new Point(halfBx, -halfHeight, halfBy),
            new Point(-halfBx, -halfHeight, halfBy),
            new Point(0, halfHeight, 0)
        ));

        add(new Triangle(
            new Point(-halfBx, -halfHeight, halfBy),
            new Point(-halfBx, -halfHeight, -halfBy),
            new Point(0, halfHeight, 0)
        ));

        add(new Triangle(
            new Point(-halfBx, -halfHeight, -halfBy),
            new Point(halfBx, -halfHeight, -halfBy),
            new Point(halfBx, -halfHeight, halfBy)
        ));

        add(new Triangle(
            new Point(halfBx, -halfHeight, halfBy),
            new Point(-halfBx, -halfHeight, halfBy),
            new Point(-halfBx, -halfHeight, -halfBy)
        ));
    }
}
