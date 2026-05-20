import printjava.*;


public class WasherPrints {
    public static void main(String args[]) {
        STL stl = new STL("Washer");

        Washer w = new Washer(WasherPrints::f, WasherPrints::g, WasherPrints::around, 1, 0, false, 20);
        stl.add(w);

        stl.write();
    }

    public static double f(double x) {
        return x*x;
    }

    public static double g(double x) {
        return Math.sqrt(x);
    }

    public static double around(double x) {
        return 0;
    }
}
