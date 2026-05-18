import java.util.function.Function;
import java.util.ArrayList;
import printjava.*;

public class Washer extends Mesh {
    private Function<Double, Double> f, g, around;

    private double a, b;

    private int divisions = 20;

    private boolean aroundX = true;
    
    public Washer(Function<Double, Double> f, Function<Double, Double> g, Function<Double, Double> around, double a, double b) {
        this.f = f;
        this.g = g;
        this.around = around;
        this.a = a;
        this.b = b;
    }

    public Washer(Function<Double, Double> f, Function<Double, Double> g, Function<Double, Double> around, double a, double b, boolean aroundX) {
        this.f = f;
        this.g = g;
        this.around = around;
        this.a = a;
        this.b = b;
        this.aroundX = aroundX;
    }

    public Washer(Function<Double, Double> f, Function<Double, Double> g, Function<Double, Double> around, double a, double b, int divisions) {
        this.f = f;
        this.g = g;
        this.around = around;
        this.a = a;
        this.b = b;
        this.divisions = divisions;
    }

    public Washer(Function<Double, Double> f, Function<Double, Double> g, Function<Double, Double> around, double a, double b, boolean aroundX, int divisions) {
        this.f = f;
        this.g = g;
        this.around = around;
        this.a = a;
        this.b = b;
        this.aroundX = aroundX;
        this.divisions = divisions;
    }

    public void generate() {
        double n = (this.b - this.a) / this.divisions;
        for(double x = a; x < this.b; x += n) {
            double fa = this.f.apply(x);
            double ga = this.g.apply(x);
            double fb = this.f.apply(x+n);
            double gb = this.g.apply(x+n);

            
        }
    }
}
