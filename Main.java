import java.io.*;

// used to import lower level classes (STL, Mesh, Triangle, Points)
import printjava.*;
// used to import higher level meshes (Rect, Sphere, Pyramid, etc)
import printjava.Meshes.*;

class Main {
    public static void main(String[] args) throws IOException {

        // EXAMPLE CODE FOR PRINTJAVA
        // TODO: Implement Meshes to have rotation and scaling vectors
        // TODO: Possible future geometry: torus, lathe (line revolved around axis), text, 3d graph

		STL f = new STL("test");
        Graph g = new Graph(Main::surfaceFunction);
		f.add(g);

		f.write();

		// display the file in a window (soon?)
		//f.show();
    }

    private static double surfaceFunction(double x, double y) {
        return x*x + y*y;
    }
}
