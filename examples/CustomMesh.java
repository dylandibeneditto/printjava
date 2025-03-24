import java.io.IOException;
// Point, Triangle, Mesh, STL
import printjava.*;
// Rect, Sphere, Graph, Field, etc.
import printjava.Meshes.*;

public class Main {
    public static void main(String[] args) throws IOException {
        STL f = new STL("model");

        // Create a custom mesh
        Mesh customMesh = new Mesh();

        // Define points for the mesh
        Point p1 = new Point(0, 0, 0);
        Point p2 = new Point(1, 0, 0);
        Point p3 = new Point(0, 1, 0);
        Point p4 = new Point(0, 0, 1);

        // Add triangles to the mesh
        customMesh.add(new Triangle(p1, p2, p3));
        customMesh.add(new Triangle(p1, p3, p4));
        customMesh.add(new Triangle(p1, p4, p2));
        customMesh.add(new Triangle(p2, p3, p4));

        // Add the custom mesh to the STL file
        f.add(customMesh);

        f.write();
    }
}
