import java.io.IOException;

// used to import lower level classes (STL, Mesh, Triangle, Points)
import printjava.*;
// used to import higher level meshes (Rect, Sphere, Pyramid, etc)
import printjava.Meshes.*;

class Main {
    public static void main(String[] args) throws IOException {
		STL f = new STL("test");
        Rect r = new Rect(1);
        r.position.x = 2;
		f.add(r);
        System.out.println("\n" + f + "\n");
		f.write();
    }

    private static double f(double x, double y) {
        return x*x + y*y;
    }
}
