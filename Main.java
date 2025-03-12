import java.io.*;
// used to import lower level classes (STL, Mesh, Triangle, Points)
import printjava.*;
// used to import higher level meshes (Rect, Sphere, Pyramid, etc)
import printjava.Meshes.*;

class Main {
    public static void main(String[] args) throws IOException {

        // EXAMPLE CODE FOR PRINTJAVA
        // TODO: Implement Meshes to have rotation and scaling vectors
        // TODO: Possible future geometry: torus, lathe (line revolved around axis), text

		// create the stl file
        // param is the name of the file
		STL f = new STL("test");

        // create and add a cube
        Rect cube = new Rect(1);
        // set the position in space to a new point in space
        cube.position = new Point(1.2, 1, 1.2);
		f.add(cube);

        Sphere sphere = new Sphere(0.5);
        sphere.scale = new Point(3,1,1);
        sphere.position = new Point(-1.2, 0, -1.2);
        f.add(sphere);

        Pyramid pyramid = new Pyramid(1);
        f.add(pyramid);

        // commented out because it makes it hard to see the other shapes
        // just a 2d plane which is 10x10
        //Plane plane = new Plane(10);
        //f.add(plane);

        Cylinder cylinder = new Cylinder(0.5, 1);
        cylinder.position = new Point(-1.2, 0, 1.2);
        f.add(cylinder);

        Cone cone = new Cone(0.5, 1);
        cone.position = new Point(1.2, 0, -1.2);
        f.add(cone);

		// write the file to the disk
		f.write();
		// display the file in a window (soon?)
		//f.show();
    }
}
