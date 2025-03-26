import java.io.IOException;
// Point, Triangle, Mesh, STL
import printjava.*;
// Rect, Sphere, Graph, Field, etc.
import printjava.Meshes.*;

public class Main {
    public static void main(String[] args) throws IOException {
        STL f = new STL("model");

        Graph g = new Graph(Main::f);
        f.add(g);

        f.write();
    }

    /**
     * given x and y, return the height of the graph at that point
     * will be normalized by the Graph object
     */
    public static double f(double x, double y) {
        return x * x + y * y;
    }
}
