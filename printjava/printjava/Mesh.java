package printjava;
import java.util.*;

public class Mesh {
	public ArrayList<Triangle> triangles = new ArrayList<Triangle>();

	public void add(Triangle t) {
		this.triangles.add(t);
	}
}
