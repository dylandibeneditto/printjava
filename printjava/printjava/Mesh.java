package printjava;
import java.util.*;

public class Mesh {
	public ArrayList<Triangle> triangles = new ArrayList<Triangle>();
	public Point position = new Point(0,0,0);
	public Point anchor = new Point(0,0,0);
	public Point rotation = new Point(0,0,0);
	public Point scale = new Point(1,1,1);

	public void add(Triangle t) {
		this.triangles.add(t);
	}
}
