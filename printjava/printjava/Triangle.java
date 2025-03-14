package printjava;

public class Triangle {
    private Point p1, p2, p3, normal;

    public Triangle(Point p1, Point p2, Point p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.normal = this.calculateNormal();
    }

    private Point calculateNormal() {
        double ux = p1.x - p3.x;
        double uy = p1.y - p3.y;
        double uz = p1.z - p3.z;

        double vx = p2.x - p3.x;
        double vy = p2.y - p3.y;
        double vz = p2.z - p3.z;

        double nx = uy * vz - uz * vy;
        double ny = uz * vx - ux * vz;
        double nz = ux * vy - uy * vx;

        double length = Math.sqrt(nx * nx + ny * ny + nz * nz);
        if(length == 0){
            return new Point(0,0,0);
        }
        return new Point(nx / length, ny / length, nz / length);
    }

}
