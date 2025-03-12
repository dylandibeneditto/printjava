import java.io.*;
import printjava.*;

class Main {
    public static void main(String[] args) throws IOException {
        // Generate a simple cube mesh
        Mesh cubeMesh = generateCube(10.0);

		// Create the stl file
		STL f = new STL("cube");
		// add the mesh
		f.add(cubeMesh);
		// write the file to the disk
		f.write();
		// display the file in a window
		f.show();
    }

    static Mesh generateCube(double size) {
        Mesh mesh = new Mesh();
        double half = size / 2.0;

        Point v0 = new Point(-half, -half, -half);
        Point v1 = new Point(half, -half, -half);
        Point v2 = new Point(half, half, -half);
        Point v3 = new Point(-half, half, -half);
        Point v4 = new Point(-half, -half, half);
        Point v5 = new Point(half, -half, half);
        Point v6 = new Point(half, half, half);
        Point v7 = new Point(-half, half, half);

        mesh.add(new Triangle(v0, v1, v2));
        mesh.add(new Triangle(v0, v2, v3));
        mesh.add(new Triangle(v1, v5, v6));
        mesh.add(new Triangle(v1, v6, v2));
        mesh.add(new Triangle(v5, v4, v7));
        mesh.add(new Triangle(v5, v7, v6));
        mesh.add(new Triangle(v4, v0, v3));
        mesh.add(new Triangle(v4, v3, v7));
        mesh.add(new Triangle(v3, v2, v6));
        mesh.add(new Triangle(v3, v6, v7));
        mesh.add(new Triangle(v0, v1, v5));
        mesh.add(new Triangle(v0, v5, v4));

        return mesh;
    }
}
