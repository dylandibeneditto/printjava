package printjava;
import java.util.*;

public class Mesh {
	public ArrayList<Triangle> triangles = new ArrayList<Triangle>();
	// the position in the print, (0,0,0) is the center of the print bed
	public Point position = new Point(0,0,0);
	// the anchor point of the mesh, the shift of the center point of a mesh
	// (0,0,0) is the center of the mesh
	// meshes will rotate and scale around the anchor point, and the position describes where this anchor point is in 3d space
	public Point anchor = new Point(0,0,0);
	// the rotation of the mesh in radians or degrees, depending on the mode set in the stl file
	public Point rotation = new Point(0,0,0);
	// the scale of the mesh
	// 2 corresponds to twice the size, 0.5 corresponds to half the size
	public Point scale = new Point(1,1,1);

	/**
	 * adds a triangle to the mesh
	 */
	public void add(Triangle t) {
		this.triangles.add(t);
	}

	/**
	 * adds a quad to the mesh
	 */
	public void add(Quad q) {
		Triangle[] qtriangles = q.toTriangle();
		for (Triangle t : qtriangles) {
			this.add(t);
		}
	}
}
