package printjava.Meshes;

import java.util.function.Function;

import printjava.Point;
import printjava.Quad;
import printjava.Mesh;

public class Grid extends Mesh {
    public double width, height, depth;
    int xDivisions, yDivisions, zDivisions;
    Function<Point, Boolean> f;

    public Grid(Function<Point, Boolean> f) {
        this(f, 1, 1, 1, 10, 10, 10);
    }
    public Grid(Function<Point, Boolean> f, double width, double height, double depth, int xDivisions, int yDivisions, int zDivisions) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.f = f;

        this.xDivisions = xDivisions;
        this.yDivisions = yDivisions;
        this.zDivisions = zDivisions;

        this.width = width;
        this.height = height;
        this.depth = depth;

    }
    
    public void generate() {
        double xStep = width / xDivisions;
        double yStep = height / yDivisions;
        double zStep = depth / zDivisions;

        for(int x = 0; x < xDivisions; x++) {
            for(int y = 0; y < yDivisions; y++) {
                for(int z = 0; z < zDivisions; z++) {
                    double xPos = (x*xStep-width/2.0);
                    double yPos = (y*yStep-height/2.0);
                    double zPos = (z*zStep-depth/2.0);

                    // Check the current voxel's center point
                    double centerX = xPos + xStep/2;
                    double centerY = yPos + yStep/2;
                    double centerZ = zPos + zStep/2;
                    
                    // Only proceed if this voxel is solid
                    if (f.apply(new Point(centerX, centerY, centerZ))) {
                        // Check -X neighbor (left)
                        if (x > 0 && !f.apply(new Point(centerX - xStep, centerY, centerZ))) {
                            this.add(new Quad(
                                new Point(xPos, yPos, zPos + zStep),
                                new Point(xPos, yPos + yStep, zPos + zStep),
                                new Point(xPos, yPos + yStep, zPos),
                                new Point(xPos, yPos, zPos)
                            ));
                        }
                        
                        // Check +X neighbor (right)
                        if (x < xDivisions - 1 && !f.apply(new Point(centerX + xStep, centerY, centerZ))) {
                            this.add(new Quad(
                                new Point(xPos + xStep, yPos + yStep, zPos),
                                new Point(xPos + xStep, yPos + yStep, zPos + zStep),
                                new Point(xPos + xStep, yPos, zPos + zStep),
                                new Point(xPos + xStep, yPos, zPos)
                            ));
                        }
                        
                        // Check -Y neighbor (bottom)
                        if (y > 0 && !f.apply(new Point(centerX, centerY - yStep, centerZ))) {
                            this.add(new Quad(
                                new Point(xPos + xStep, yPos, zPos),
                                new Point(xPos + xStep, yPos, zPos + zStep),
                                new Point(xPos, yPos, zPos + zStep),
                                new Point(xPos, yPos, zPos)
                            ));
                        }
                        
                        // Check +Y neighbor (top)
                        if (y < yDivisions - 1 && !f.apply(new Point(centerX, centerY + yStep, centerZ))) {
                            this.add(new Quad(
                                new Point(xPos, yPos + yStep, zPos + zStep),
                                new Point(xPos + xStep, yPos + yStep, zPos + zStep),
                                new Point(xPos + xStep, yPos + yStep, zPos),
                                new Point(xPos, yPos + yStep, zPos)
                            ));
                        }
                        
                        // Check -Z neighbor (back)
                        if (z > 0 && !f.apply(new Point(centerX, centerY, centerZ - zStep))) {
                            this.add(new Quad(
                                new Point(xPos, yPos + yStep, zPos),
                                new Point(xPos + xStep, yPos + yStep, zPos),
                                new Point(xPos + xStep, yPos, zPos),
                                new Point(xPos, yPos, zPos)
                            ));
                        }
                        
                        // Check +Z neighbor (front)
                        if (z < zDivisions - 1 && !f.apply(new Point(centerX, centerY, centerZ + zStep))) {
                            this.add(new Quad(
                                new Point(xPos + xStep, yPos, zPos + zStep),
                                new Point(xPos + xStep, yPos + yStep, zPos + zStep),
                                new Point(xPos, yPos + yStep, zPos + zStep),
                                new Point(xPos, yPos, zPos + zStep)
                            ));
                        }
                    }
                    
                    // Calculate progress based on current position
                    double progress = ((x * yDivisions * zDivisions) + (y * zDivisions) + z) / (double) ((xDivisions - 1) * (yDivisions - 1) * (zDivisions - 1));
                    if (progress % 0.25 == 0) {
                        System.out.print("\rRendering progress: " + (int)(progress * 100) + "%");
                    }
                }
            }
        }
    }
}
