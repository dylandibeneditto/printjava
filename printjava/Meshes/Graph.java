package printjava.Meshes;

import printjava.Mesh;
import printjava.Quad;
import printjava.Point;
import printjava.Point2;
import printjava.Triangle;
import java.util.function.Function;

public class Graph extends Mesh {
    // describes the bounds of the graph
    private double startX, startY, endX, endY;
    // describes the size of the printed graph
    private double width, height, depth;
    // describes the number of divisions in the x and y directions
    private int xDivisions, yDivisions;

    private static final int MAX_DIVISIONS = 2000;
    private static final long MAX_GRID_POINTS = 8_000_000L;

    private Function<Point2, Double> f;

    // whether or not to have a base, and how tall the base is
    private boolean base = true;
    private double baseHeight = 0.1;
    private double thickness = 0.1;

    // new Graph(Main::f);
    /**
     * Constructs a Graph mesh from a function that takes a Point2 and returns a
     * double.
     * 
     * @param f The function that defines the graph. It should take a Point2 and
     *          return a double representing the height at that point. The x and y
     *          coordinates of the Point2 will be determined by the startX, startY,
     *          endX, and endY parameters, and the width and height of the graph
     *          will be determined by the width and height parameters. The depth of
     *          the graph will be determined by the depth parameter. The number of
     *          divisions in the x and y directions will be determined by the
     *          xDivisions and yDivisions parameters. The base of the graph will be
     *          determined by the base and baseHeight parameters.
     */
    public Graph(Function<Point2, Double> f) {
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
        this.f = f;
    }

    // new Graph(Main::f, -10, -10, 10, 10);
    /**
     * Constructs a Graph mesh from a function and bounds.
     * 
     * @param f      The function that defines the graph. It should take a Point2
     *               and return a double representing the height at that point. The
     *               x and y coordinates of the Point2 will be determined by the
     *               startX, startY, endX, and endY parameters, and the width and
     *               height of the graph will be determined by the width and height
     *               parameters. The depth of the graph will be determined by the
     *               depth parameter. The number of divisions in the x and y
     *               directions will be determined by the xDivisions and yDivisions
     *               parameters. The base of the graph will be determined by the
     *               base and baseHeight parameters.
     * @param startX the starting x-coordinate of the graph
     * @param startY the starting y-coordinate of the graph
     * @param endX   the ending x-coordinate of the graph
     * @param endY   the ending y-coordinate of the graph
     */
    public Graph(Function<Point2, Double> f, double startX, double startY, double endX, double endY) {
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
        this.f = f;
    }

    // new Graph(Main::f, -10, -10, 10, 10, 1, 1, 1);
    /**
     * Constructs a Graph mesh from a function, bounds, and dimensions.
     * 
     * @param f      The function that defines the graph. It should take a Point2
     *               and return a double representing the height at that point. The
     *               x and y coordinates of the Point2 will be determined by the
     *               startX, startY, endX, and endY parameters, and the width and
     *               height of the graph will be determined by the width and height
     *               parameters. The depth of the graph will be determined by the
     *               depth parameter. The number of divisions in the x and y
     *               directions will be determined by the xDivisions and yDivisions
     *               parameters. The base of the graph will be determined by the
     *               base and baseHeight parameters.
     * @param startX the starting x-coordinate of the graph
     * @param startY the starting y-coordinate of the graph
     * @param endX   the ending x-coordinate of the graph
     * @param endY   the ending y-coordinate of the graph
     * @param width  the width of the graph in the x-axis
     * @param height the height of the graph in the y-axis
     * @param depth  the depth of the graph in the z-axis
     */
    public Graph(Function<Point2, Double> f, double startX, double startY, double endX, double endY, double width,
            double height, double depth) {
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
        this.f = f;
    }

    // new Graph(Main::f, -10, -10, 10, 10, 1, 1, 1, 100, 100);
    /**
     * Constructs a Graph mesh from a function, bounds, dimensions, and divisions.
     * 
     * @param f          The function that defines the graph. It should take a
     *                   Point2 and return a double representing the height at that
     *                   point. The x and y coordinates of the Point2 will be
     *                   determined by the startX, startY, endX, and endY
     *                   parameters, and the width and height of the graph will be
     *                   determined by the width and height parameters. The depth of
     *                   the graph will be determined by the depth parameter. The
     *                   number of divisions in the x and y directions will be
     *                   determined by the xDivisions and yDivisions parameters. The
     *                   base of the graph will be determined by the base and
     *                   baseHeight parameters.
     * @param startX     the starting x-coordinate of the graph
     * @param startY     the starting y-coordinate of the graph
     * @param endX       the ending x-coordinate of the graph
     * @param endY       the ending y-coordinate of the graph
     * @param width      the width of the graph in the x-axis
     * @param height     the height of the graph in the y-axis
     * @param depth      the depth of the graph in the z-axis
     * @param xDivisions the number of divisions in the x-axis. This determines the
     *                   resolution of the mesh. Higher values will result in a more
     *                   detailed mesh but will take longer to generate.
     * @param yDivisions the number of divisions in the y-axis. This determines the
     *                   resolution of the mesh. Higher values will result in a more
     *                   detailed mesh but will take longer to generate.
     */
    public Graph(Function<Point2, Double> f, double startX, double startY, double endX, double endY, double width,
            double height, double depth, int xDivisions, int yDivisions) {
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
        this.f = f;
    }

    // new Graph(Main::f, -10, -10, 10, 10, 1, 1, 1, 100, 100, true, 0.1);
    /**
     * Constructs a Graph mesh from a function, bounds, dimensions, divisions, and
     * base settings.
     * 
     * @param f          The function that defines the graph. It should take a
     *                   Point2 and return a double representing the height at that
     *                   point. The x and y coordinates of the Point2 will be
     *                   determined by the startX, startY, endX, and endY
     *                   parameters, and the width and height of the graph will be
     *                   determined by the width and height parameters. The depth of
     *                   the graph will be determined by the depth parameter. The
     *                   number of divisions in the x and y directions will be
     *                   determined by the xDivisions and yDivisions parameters. The
     *                   base of the graph will be determined by the base and
     *                   baseHeight parameters.
     * @param startX     the starting x-coordinate of the graph
     * @param startY     the starting y-coordinate of the graph
     * @param endX       the ending x-coordinate of the graph
     * @param endY       the ending y-coordinate of the graph
     * @param width      the width of the graph in the x-axis
     * @param height     the height of the graph in the y-axis
     * @param depth      the depth of the graph in the z-axis
     * @param xDivisions the number of divisions in the x-axis. This determines the
     *                   resolution of the mesh. Higher values will result in a more
     *                   detailed mesh but will take longer to generate.
     * @param yDivisions the number of divisions in the y-axis. This determines the
     *                   resolution of the mesh. Higher values will result in a more
     *                   detailed mesh but will take longer to generate.
     * @param base       whether or not to include a base for the graph. If true,
     *                   the graph will have a flat base at the height specified by
     *                   baseHeight. If false, the graph will not have a base and
     *                   will only consist of the surface defined by the function.
     * @param baseHeight the height of the base if base is true. This determines how
     *                   tall the base of the graph will be. If base is false, this
     *                   parameter is ignored.
     */
    public Graph(Function<Point2, Double> f, double startX, double startY, double endX, double endY, double width,
            double height, double depth, int xDivisions, int yDivisions, boolean base, double baseHeight) {
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
        this.f = f;
    }

    /**
     * evaluates the function at each subdivision point and creates a triange for
     * each
     */
    private int clampDivisions(int value) {
        return Math.max(2, Math.min(value, MAX_DIVISIONS));
    }

    private void normalizeDivisions() {
        this.xDivisions = clampDivisions(this.xDivisions);
        this.yDivisions = clampDivisions(this.yDivisions);

        long pointCount = (long) (this.xDivisions + 1) * (this.yDivisions + 1);
        if (pointCount <= MAX_GRID_POINTS) {
            return;
        }

        double scale = Math.sqrt((double) MAX_GRID_POINTS / pointCount);
        this.xDivisions = Math.max(2, (int) (this.xDivisions * scale));
        this.yDivisions = Math.max(2, (int) (this.yDivisions * scale));

        this.xDivisions = clampDivisions(this.xDivisions);
        this.yDivisions = clampDivisions(this.yDivisions);
    }

    /**
     * Generates the triangles for the graph mesh by evaluating the function at each
     * subdivision point and creating triangles for each quad formed by adjacent
     * points. The generated triangles are added to the mesh using the add method.
     */
    public void generate() {
        this.triangles.clear();
        generateTriangles(this::add);
    }

    /**
     * Generates the triangles for the graph mesh by evaluating the function at each
     * subdivision point and creating triangles for each quad formed by adjacent
     * points. The generated triangles are passed to a consumer function, which can
     * be used to add them to the mesh or perform other operations on them.
     */
    public void generateTriangles(java.util.function.Consumer<Triangle> consumer) {
        normalizeDivisions();
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
                double val = f.apply(new Point2(x0, y0));
                points[i][j] = val;

                if (val > top)
                    top = val;
                if (val < bottom)
                    bottom = val;
            }
        }

        double range = top - bottom;
        if (range == 0)
            range = 1;

        // create quads from normalized values
        for (int i = 0; i < this.xDivisions; i++) {
            double x0 = -this.width / 2 + i * rdx;
            double x1 = -this.width / 2 + (i + 1) * rdx;

            for (int j = 0; j < this.yDivisions; j++) {
                double y0 = -this.height / 2 + j * rdy;
                double y1 = -this.height / 2 + (j + 1) * rdy;

                // normalized points
                double h00 = this.baseHeight + ((points[i][j] - bottom) / range) * (this.depth - this.baseHeight);
                double h10 = this.baseHeight + ((points[i + 1][j] - bottom) / range) * (this.depth - this.baseHeight);
                double h11 = this.baseHeight
                        + ((points[i + 1][j + 1] - bottom) / range) * (this.depth - this.baseHeight);
                double h01 = this.baseHeight + ((points[i][j + 1] - bottom) / range) * (this.depth - this.baseHeight);

                // add walls
                if (this.base && (i == 0 || i == this.xDivisions - 1 || j == 0 || j == this.yDivisions - 1)) {
                    if (i == 0) {
                        consumer.accept(
                                new Triangle(new Point(x0, h01, y1), new Point(x0, h00, y0), new Point(x0, 0, y0)));
                        consumer.accept(
                                new Triangle(new Point(x0, h01, y1), new Point(x0, 0, y0), new Point(x0, 0, y1)));
                    }
                    if (i == this.xDivisions - 1) {
                        consumer.accept(
                                new Triangle(new Point(x1, h10, y0), new Point(x1, h11, y1), new Point(x1, 0, y1)));
                        consumer.accept(
                                new Triangle(new Point(x1, h10, y0), new Point(x1, 0, y1), new Point(x1, 0, y0)));
                    }
                    if (j == 0) {
                        consumer.accept(
                                new Triangle(new Point(x0, h00, y0), new Point(x1, h10, y0), new Point(x1, 0, y0)));
                        consumer.accept(
                                new Triangle(new Point(x0, h00, y0), new Point(x1, 0, y0), new Point(x0, 0, y0)));
                    }
                    if (j == this.yDivisions - 1) {
                        consumer.accept(
                                new Triangle(new Point(x0, 0, y1), new Point(x1, 0, y1), new Point(x1, h11, y1)));
                        consumer.accept(
                                new Triangle(new Point(x0, 0, y1), new Point(x1, h11, y1), new Point(x0, h01, y1)));
                    }
                } else {
                    if (i == 0) {
                        consumer.accept(new Triangle(new Point(x0, h01, y1), new Point(x0, h00, y0),
                                new Point(x0, h00 - this.thickness, y0)));
                        consumer.accept(new Triangle(new Point(x0, h01, y1), new Point(x0, h00 - this.thickness, y0),
                                new Point(x0, h01 - this.thickness, y0)));
                    }
                    if (i == this.xDivisions - 1) {
                        consumer.accept(new Triangle(new Point(x1, h10, y0), new Point(x1, h11, y1),
                                new Point(x1, h10 - this.thickness, y1)));
                        consumer.accept(new Triangle(new Point(x1, h10 - this.thickness, y1), new Point(x1, h11, y1),
                                new Point(x1, h11 - this.thickness, y0)));
                    }
                    if (j == 0) {
                        consumer.accept(new Triangle(new Point(x0, h00, y0), new Point(x1, h10, y0),
                                new Point(x1, h00 - this.thickness, y0)));
                        consumer.accept(new Triangle(new Point(x0, h00, y0), new Point(x1, h00 - this.thickness, y0),
                                new Point(x0, h10 - this.thickness, y0)));
                    }
                    if (j == this.yDivisions - 1) {
                        consumer.accept(new Triangle(new Point(x0, h11 - this.thickness, y1),
                                new Point(x1, h01 - this.thickness, y1), new Point(x1, h11, y1)));
                        consumer.accept(new Triangle(new Point(x0, h11 - this.thickness, y1), new Point(x1, h11, y1),
                                new Point(x0, h01, y1)));
                    }
                }

                // add surface quad
                consumer.accept(new Triangle(new Point(x0, h01, y1), new Point(x1, h11, y1), new Point(x1, h10, y0)));
                consumer.accept(new Triangle(new Point(x0, h01, y1), new Point(x1, h10, y0), new Point(x0, h00, y0)));

                // add backface if no walls
                if (!this.base) {
                    consumer.accept(new Triangle(new Point(x0, h00 - this.thickness, y0),
                            new Point(x1, h10 - this.thickness, y0), new Point(x1, h11 - this.thickness, y1)));
                    consumer.accept(new Triangle(new Point(x0, h00 - this.thickness, y0),
                            new Point(x1, h11 - this.thickness, y1), new Point(x0, h01 - this.thickness, y1)));
                }
            }
        }

        // bottom face
        if (this.base) {
            consumer.accept(new Triangle(new Point(-this.width / 2, 0, -this.height / 2),
                    new Point(this.width / 2, 0, -this.height / 2),
                    new Point(this.width / 2, 0, this.height / 2)));
            consumer.accept(new Triangle(new Point(-this.width / 2, 0, -this.height / 2),
                    new Point(this.width / 2, 0, this.height / 2), new Point(-this.width / 2, 0, this.height / 2)));
        }
    }
}
