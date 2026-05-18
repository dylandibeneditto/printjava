import printjava.*;
import java.util.ArrayList;

public class VolumePrints {
    public static void main(String args[]) {
        STL stl = new STL("Volume");

        Volume v = new Volume(VolumePrints::top, VolumePrints::bottom, 0, 2, 100);
        stl.add(v);

        stl.write();

    }

    public static double top(double x) {
        return 1 + x + Math.pow(Math.E, x*x - 2*x);
    }

    public static double bottom(double x) {
        return Math.pow(x, 4) - 6.5*x*x + 6*x + 2;
    }

    // finding points that look something like this
    //
    //           . . .| (0,h)
    //         . .    |
    //         .      | <- [cross sectional line]
    //         . .    |
    //           . . .| (0,0)
    //
    public static ArrayList<Point2> semicircleShape(double height) {
        int resolution = 50;
        ArrayList<Point2> points = new ArrayList<Point2>();
        double r = height / 2;
        double dtheta = 1.0/resolution;
        points.add(new Point2(0,0));
        points.add(new Point2(0, height));
        for(int i = 0; i <= resolution; i++) {
            double rad = Math.PI * i * dtheta;
            double y = r * Math.cos(rad) + r;
            double x = r * Math.sin(rad);
            points.add(new Point2(x,y));
        }
        return points;
    }
}
