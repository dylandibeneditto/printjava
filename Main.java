import java.io.IOException;

// used to import lower level classes (STL, Mesh, Triangle, Points)
import printjava.*;
// used to import higher level meshes (Rect, Sphere, Pyramid, etc)
import printjava.Meshes.*;

class Main {
    public static void main(String[] args) throws IOException {
        // EXAMPLE CODE FOR PRINTJAVA
		STL f = new STL("test");
        Graph g = new Graph(Main::f, -100, -100, 100, 100, 5, 5, 5);
		f.add(g);
		f.write();
    }

    private static double f(double x, double y) {
        return x*x + y*y;
    }
}
