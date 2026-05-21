package printjava;

public class Triangle {
    // Points of the triangle and its normal
    // The normal is basically what direction the triangle is facing
    public Point p1, p2, p3, normal;

    // new Triangle(new Point(0, 0, 0), new Point(1, 0, 0), new Point(0, 1, 0));
    /**
     * Constructs a new Triangle.
     * 
     * @param p1 the p1 value.
     * @param p2 the p2 value.
     * @param p3 the p3 value.
     */
    public Triangle(Point p1, Point p2, Point p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.normal = this.calculateNormal();
    }

    /**
     * calculate the normal of the triangle
     * 
     * @return the normal point
     */
    private Point calculateNormal() {
        Point u = p2.subtract(p1);
        Point v = p3.subtract(p1);

        Point normal = u.cross(v).normalize();

        if (normal.magnitude() == 0) {
            return new Point(0, 0, 0);
        }

        return normal;
    }

}
