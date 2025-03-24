import java.io.IOException;
// Point, Triangle, Mesh, STL
import printjava.*;
// Rect, Sphere, Graph, Field, etc.
import printjava.Meshes.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // Will create a new STL file called "mode.stl"
        STL f = new STL("model");

        // Measurements will be in cm unless you change to inches with
        // f.setMetric(false)

        // Rectangular prism with width of 1cm, height of 2cm, and depth of 3cm
        Rect r = new Rect(1, 2, 3);
        r.position.set(1, 0, 1);
        // rotation will be in radians unless you change to degrees with `f.radians =
        // false`
        r.rotation.x = Math.PI / 4;
        // adds Rect to the STL file
        f.add(r);

        // Sphere with a radius of 1cm
        Sphere s = new Sphere(1);
        s.position.x = -1;
        f.add(s);

        // Cone with default parameters
        Cone c = new Cone();
        c.position.set(0, 1, 0);
        // snapToCenter() means that the center of the cone will be at (0, 1, 0) instead
        // of the base being at (0, 1, 0), snapToGround() is usually automatically
        // applied so that all Meshes are flat on the printbed
        c.snapToCenter();
        f.add(c);

        // Creates the file
        f.write();
    }
}