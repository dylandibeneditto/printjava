import printjava.*;
import printjava.Meshes.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        STL stl = new STL("Test");

        ArrayList<Mesh> meshes = new ArrayList<>(Arrays.asList(new Cone(), new Sphere(), new Pyramid(), new Cylinder(), new Graph(Test::f), new Field(Test::g), new Plane(), new Rect()));

        for (int i = 0; i < meshes.size(); i++) {
            meshes.get(i).position.set((i%10)*3, 0, (i/10)*3);
            stl.add(meshes.get(i));
        }

        stl.write();
    }

    public static double f(Point2 p) {
        return Math.sqrt(p.x * p.x + p.y * p.y);
    }

    public static double g(Point p) {
        return p.distance(new Point(0, 0, 0))/2;
    }
}
