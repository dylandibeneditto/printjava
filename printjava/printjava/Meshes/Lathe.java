package printjava.Meshes;

import printjava.Line;
import printjava.Mesh;
import printjava.Point;
import printjava.Point2;
import printjava.Quad;

public class Lathe extends Mesh {
    public Line line;
    public int resolution;

    public Lathe() {
        this.line = new Line();
        this.resolution = 30;
    }

    public Lathe(int resolution) {
        this.line = new Line();
        this.resolution = resolution;
    }

    public Lathe(Line l) {
        this.line = l;
        this.resolution = 30;
    }

    public Lathe(Line l, int resolution) {
        this.line = l;
        this.resolution = resolution;
    }

    public void generate() {
        this.triangles.clear();
        double angleStep = 2 * Math.PI / resolution;

        int numPoints = this.line.points.size();
        if (numPoints < 2) return;

        for (int i = 0; i < resolution; i++) {
            double theta1 = i * angleStep;
            double theta2 = (i + 1) * angleStep;

            for (int j = 0; j < numPoints - 1; j++) {
                Point2 p1 = line.points.get(j);
                Point2 p2 = line.points.get(j + 1);

                Point a = new Point(p1.x * Math.cos(theta1), p1.y, p1.x * Math.sin(theta1));
                Point b = new Point(p2.x * Math.cos(theta1), p2.y, p2.x * Math.sin(theta1));
                Point c = new Point(p2.x * Math.cos(theta2), p2.y, p2.x * Math.sin(theta2));
                Point d = new Point(p1.x * Math.cos(theta2), p1.y, p1.x * Math.sin(theta2));

                this.add(new Quad(a, b, c, d));
            }
        }
    }

    public void add(Point2 p) {
        this.line.add(p);
    }
}
