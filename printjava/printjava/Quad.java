package printjava;

public class Quad {
    // 4 points on the quad, will use these to construct 2 triangles
    public Point p1;
    public Point p2;
    public Point p3;
    public Point p4;

    public Quad(Point p1, Point p2, Point p3, Point p4) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
    }

    /**
     * This converts the quad to 2 triangles, so that the mesh can take a quad in the 'add' function
     */
    public Triangle[] toTriangle() {
        Triangle t1 = new Triangle(p1, p2, p3);
        Triangle t2 = new Triangle(p1, p3, p4);
        return new Triangle[] { t1, t2 };
    }
}
