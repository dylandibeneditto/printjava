package printjava.Meshes;

import printjava.Mesh;
import printjava.Triangle;
import printjava.Point;
import java.util.function.BiFunction;

public class Graph extends Mesh {
    public double startX, startY, endX, endY;
    public double width, height, depth;
    public int xDivisions, yDivisions;

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

    private void generate(BiFunction<Double, Double, Double> f) {
        double dx = (this.endX - this.startX) / this.xDivisions;
        double dy = (this.endY - this.startY) / this.yDivisions;
    
        double rdx = this.width / this.xDivisions;
        double rdy = this.height / this.yDivisions;
    
        double[][] points = new double[this.xDivisions + 1][this.yDivisions + 1];
    
        double top = Double.NEGATIVE_INFINITY;
        double bottom = Double.POSITIVE_INFINITY;
    
        // Compute function values
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
        if (range == 0) range = 1; // Prevent division by zero
    
        // Generate mesh
        for (int i = 0; i < this.xDivisions; i++) {
            double x0 = -this.width / 2 + i * rdx;
            double x1 = -this.width / 2 + (i + 1) * rdx; // Fix here
    
            for (int j = 0; j < this.yDivisions; j++) {
                double y0 = -this.height / 2 + j * rdy;
                double y1 = -this.height / 2 + (j + 1) * rdy; // Fix here
    
                double h00 = ((points[i][j] - bottom) / range) * this.depth;
                double h10 = ((points[i + 1][j] - bottom) / range) * this.depth;
                double h11 = ((points[i + 1][j + 1] - bottom) / range) * this.depth;
                double h01 = ((points[i][j + 1] - bottom) / range) * this.depth;
    
                // Ensure depth is applied correctly
                add(new Triangle(
                    new Point(x0, h00, y0), // y0 should be the position, hXX should be depth
                    new Point(x1, h10, y0),
                    new Point(x1, h11, y1)
                ));
    
                add(new Triangle(
                    new Point(x0, h00, y0),
                    new Point(x1, h11, y1),
                    new Point(x0, h01, y1)
                ));
            }
        }
    }
}
