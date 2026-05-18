import java.util.function.Function;
import java.util.ArrayList;
import printjava.*;

public class Volume extends Mesh {
    // f(x) and g(x)
    private Function<Double, Double> f, g;

    // from a to b
    private double a, b;

    // given the height at a certain point, return a list of points making up the
    // shape
    private Function<Double, ArrayList<Point2>> shape = Volume::squareShape;

    // how many slices out of the area?
    private int divisions = 20;

    public Volume(Function<Double, Double> f, Function<Double, Double> g, double a, double b) {
        this.f = f;
        this.g = g;
        this.a = a;
        this.b = b;
    }

    public Volume(Function<Double, Double> f, Function<Double, Double> g, double a, double b, int divisions) {
        this.f = f;
        this.g = g;
        this.a = a;
        this.b = b;
        this.divisions = divisions;
    }

    public Volume(Function<Double, Double> f, Function<Double, Double> g, double a, double b,
            Function<Double, ArrayList<Point2>> shape) {
        this.f = f;
        this.g = g;
        this.a = a;
        this.b = b;
        this.shape = shape;
    }

    public Volume(Function<Double, Double> f, Function<Double, Double> g, double a, double b,
            Function<Double, ArrayList<Point2>> shape, int divisions) {
        this.f = f;
        this.g = g;
        this.a = a;
        this.b = b;
        this.shape = shape;
        this.divisions = divisions;
    }

    public void generate() {
        double n = (this.b - this.a) / this.divisions;
        for(double x = this.a; x < this.b; x += n) {
            double fa = f.apply(x);
            double ga = g.apply(x);
            double ha = Math.abs(fa - ga);

            double fb = f.apply(x+n);
            double gb = g.apply(x+n);
            double hb = Math.abs(fb - gb);

            ArrayList<Point2> sa = shape.apply(ha);
            ArrayList<Point2> sb = shape.apply(hb);

            for(int i = 0; i < sa.size(); i++) {
                Point2 s1 = sa.get(i);
                Point2 s2 = sb.get(i);
                Point2 s3;
                Point2 s4;
                if(i+1==sa.size()) {
                    s3 = sa.get(0);
                    s4 = sb.get(0);
                } else {
                    s3 = sa.get(i+1);
                    s4 = sb.get(i+1);
                }

                Point2 vShiftA = new Point2(0,Math.min(fa, ga));
                s1 = s1.add(vShiftA);
                s3 = s3.add(vShiftA);

                Point2 vShiftB = new Point2(0,Math.min(fb, gb));
                s2 = s2.add(vShiftB);
                s4 = s4.add(vShiftB);

                Point p1 = new Point(s1, x - a);
                Point p2 = new Point(s2, x+n - a);
                Point p3 = new Point(s3, x - a);
                Point p4 = new Point(s4, x+n - a);

                add(new Quad(p1, p2, p4, p3));
                add(new Quad(p3, p4, p2, p1)); // redundant backface, just if you flip the x values of the shape
            }
        }
    }

    //
    //  (-h,h)   (0,h)|
    //                |
    //                | <- [cross sectional line]
    //                |
    //  (-h,0)   (0,0)|
    //
    private static ArrayList<Point2> squareShape(double height) {
        ArrayList<Point2> points = new ArrayList<Point2>();
        points.add(new Point2(0,0));
        points.add(new Point2(0,height));
        points.add(new Point2(height, height));
        points.add(new Point2(height, 0));
        return points;
    }
}
