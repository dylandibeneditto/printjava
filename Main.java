import java.io.*;
// used to import lower level classes (STL, Mesh, Triangle, Points)
import printjava.*;
// used to import higher level meshes (Rect, Sphere, Pyramid, etc)
import printjava.Meshes.*;

class Main {
    public static void main(String[] args) throws IOException {
		// create the stl file
        // param is the name of the file
		STL f = new STL("test");

        // create and add a cube
        // param is the size of the cube
        Rect cube = new Rect(1);
        // set the position in space to a new point in space
        cube.position = new Point(1, 0, 1);
		f.add(cube);

        // create and add a sphere
        // param is the radius of the sphere
        Sphere sphere = new Sphere(1);
        sphere.position = new Point(-1, 0, -1);
        f.add(sphere);

		// write the file to the disk
		f.write();
		// display the file in a window
		f.show();
    }
}
