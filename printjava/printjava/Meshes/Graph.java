package printjava.Meshes;

import printjava.Mesh;
import printjava.Triangle;
import printjava.Point;
import java.util.function.BiFunction;

public class Graph extends Mesh {
    // describes the bounds of the graph
    private double startX, startY, endX, endY;
    // describes the size of the printed graph
    private double width, height, depth;
    // describes the number of divisions in the x and y directions
    private int xDivisions, yDivisions;

    // whether or not to have a base, and how tall the base is
    private boolean base = true;
    private double baseHeight = 0.1;

    // new Graph(Main::f);
    public Graph(BiFunction<Double, Double, Double> f) {
        super();
        this.startX = -10;
        this.startY = -10;
        this.endX = 10;
        this.endY = 10;
        this.width = 1;
        this.height = 1;
        this.depth = 1;
        this.xDivisions = 100;
        this.yDivisions = 100;
        this.generate(f);
    }

    // new Graph(Main::f, -10, -10, 10, 10);
    public Graph(BiFunction<Double, Double, Double> f, double startX, double startY, double endX, double endY) {
        super();
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.width = 1;
        this.height = 1;
        this.depth = 1;
        this.xDivisions = 100;
        this.yDivisions = 100;
        this.generate(f);
    }

    // new Graph(Main::f, -10, -10, 10, 10, 1, 1, 1);
    public Graph(BiFunction<Double, Double, Double> f, double startX, double startY, double endX, double endY, double width, double height, double depth) {
        super();
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.xDivisions = 100;
        this.yDivisions = 100;
        this.generate(f);
    }

    // new Graph(Main::f, -10, -10, 10, 10, 1, 1, 1, 100, 100);
    public Graph(BiFunction<Double, Double, Double> f, double startX, double startY, double endX, double endY, double width, double height, double depth, int xDivisions, int yDivisions) {
        super();
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.xDivisions = xDivisions;
        this.yDivisions = yDivisions;
        this.generate(f);
    }

    // new Graph(Main::f, -10, -10, 10, 10, 1, 1, 1, 100, 100, true, 0.1);
    public Graph(BiFunction<Double, Double, Double> f, double startX, double startY, double endX, double endY, double width, double height, double depth, int xDivisions, int yDivisions, boolean base, double baseHeight) {
        super();
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.xDivisions = xDivisions;
        this.yDivisions = yDivisions;
        this.base = base;
        this.baseHeight = baseHeight;
        this.generate(f);
    }

    /**
     * evaluates the function at each subdivision point and creates a triange for each
     */
    private void generate(BiFunction<Double, Double, Double> f) {
        double dx = (this.endX - this.startX) / this.xDivisions;
        double dy = (this.endY - this.startY) / this.yDivisions;
    
        double rdx = this.width / this.xDivisions;
        double rdy = this.height / this.yDivisions;
    
        double[][] points = new double[this.xDivisions + 1][this.yDivisions + 1];
    
        double top = Double.NEGATIVE_INFINITY;
        double bottom = Double.POSITIVE_INFINITY;
    
        // compute function values
        for (int i = 0; i <= this.xDivisions; i++) {
            double x0 = this.startX + i * dx;
            for (int j = 0; j <= this.yDivisions; j++) {
                double y0 = this.startY + j * dy;
                double val = f.apply(x0, y0);
                points[i][j] = val;
    
                if (val > top) top = val;
                if (val < bottom) bottom = val;
            }
        }
    
        double range = top - bottom;
        if (range == 0) range = 1;
    
        // create triangles from normalized values
        for (int i = 0; i < this.xDivisions; i++) {
            double x0 = -this.width / 2 + i * rdx;
            double x1 = -this.width / 2 + (i + 1) * rdx;
        
            for (int j = 0; j < this.yDivisions; j++) {
                double y0 = -this.height / 2 + j * rdy;
                double y1 = -this.height / 2 + (j + 1) * rdy;
        
                // normalized points
                double h00 = this.baseHeight + ((points[i][j] - bottom) / range) * (this.depth - this.baseHeight);
                double h10 = this.baseHeight + ((points[i + 1][j] - bottom) / range) * (this.depth - this.baseHeight);
                double h11 = this.baseHeight + ((points[i + 1][j + 1] - bottom) / range) * (this.depth - this.baseHeight);
                double h01 = this.baseHeight + ((points[i][j + 1] - bottom) / range) * (this.depth - this.baseHeight);
        
                // add walls
                if (this.base && (i == 0 || i == this.xDivisions - 1 || j == 0 || j == this.yDivisions - 1)) {
                    if (i == 0) {
                        add(new Triangle(new Point(x0, 0, y1), new Point(x0, 0, y0), new Point(x0, h00, y0)));
                        add(new Triangle(new Point(x0, h01, y1), new Point(x0, 0, y1), new Point(x0, h00, y0)));
                    }
                    if (i == this.xDivisions - 1) {
                        add(new Triangle(new Point(x1, 0, y1), new Point(x1, h11, y1), new Point(x1, h10, y0)));
                        add(new Triangle(new Point(x1, 0, y0), new Point(x1, 0, y1), new Point(x1, h10, y0)));
                    }
                    if (j == 0) {
                        add(new Triangle(new Point(x1, 0, y0), new Point(x1, h10, y0), new Point(x0, h00, y0)));
                        add(new Triangle(new Point(x0, 0, y0), new Point(x1, 0, y0), new Point(x0, h00, y0)));
                    }
                    if (j == this.yDivisions - 1) {
                        add(new Triangle(new Point(x1, h11, y1), new Point(x1, 0, y1), new Point(x0, 0, y1)));
                        add(new Triangle(new Point(x0, h01, y1), new Point(x1, h11, y1), new Point(x0, 0, y1)));
                    }
                }
        
                // add surface triangles
                add(new Triangle(new Point(x0, h00, y0), new Point(x1, h10, y0), new Point(x1, h11, y1)));
                add(new Triangle(new Point(x0, h00, y0), new Point(x1, h11, y1), new Point(x0, h01, y1)));
            }
        }

        // bottom face
        add(new Triangle(new Point(this.width / 2, 0, this.height / 2), new Point(this.width / 2, 0, -this.height / 2), new Point(-this.width / 2, 0, -this.height / 2)));
        add(new Triangle(new Point(-this.width / 2, 0, this.height / 2), new Point(this.width / 2, 0, this.height / 2), new Point(-this.width / 2, 0, -this.height / 2)));
    }
}
