package printjava.Meshes;

import printjava.Mesh;
import printjava.Point;
import printjava.Triangle;

public class Plane extends Mesh {
    private double width, height;

    public Plane() {
        super();
        this.width = 1;
        this.height = 1;
        this.generate();
    }

    public Plane(double size) {
        super();
        this.width = size;
        this.height = size;
        this.generate();
    }

    public Plane(double width, double height) {
        super();
        this.width = width;
        this.height = height;
        generate();
    }

    private void generate() {
        double halfWidth = this.width / 2;
        double halfHeight = this.height / 2;

        add(new Triangle(
            new Point(-halfWidth, 0, -halfHeight),
            new Point(halfWidth, 0, -halfHeight),
            new Point(halfWidth, 0, halfHeight)
        ));

        add(new Triangle(
            new Point(-halfWidth, 0, -halfHeight),
            new Point(halfWidth, 0, halfHeight),
            new Point(-halfWidth, 0, halfHeight)
        ));
    }
}
