package printjava;
import java.io.*;
import java.util.*;

public class STL {
	public String name;
	public ArrayList<Mesh> meshes;

	public STL() {
		this.name = "model";
		this.meshes = new ArrayList<Mesh>();
	}

	public STL(String name) {
		this.name = name;
		this.meshes = new ArrayList<Mesh>();
	}

	public void add(Mesh m) {
		this.meshes.add(m);
	}

	/*
	 * logic for writing stl file to disk
	 */
	public void write() throws IOException {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.name + ".stl"))) {
			for(int i = 0; i < this.meshes.size(); i++) {
				writer.write(String.format("solid %s\n", i));
				Mesh m = this.meshes.get(i);

				double mx = m.position.x - m.anchor.x;
				double my = m.position.y - m.anchor.y;
				double mz = m.position.z - m.anchor.z;

				double rx = m.rotation.x;
				double ry = m.rotation.y;
				double rz = m.rotation.z;

				double sx = m.scale.x;
				double sy = m.scale.y;
				double sz = m.scale.z;

				for (Triangle t : this.meshes.get(i).triangles) {
					double tx = t.normal.x + mx;
					double ty = t.normal.y + my;
					double tz = t.normal.z + mz;
					writer.write(String.format("  facet normal %f %f %f\n", tx, ty, tz));
					writer.write("    outer loop\n");
					for (Point v : List.of(t.p1, t.p2, t.p3)) {
						double x = v.x * sx;
						double y = v.y * sy;
						double z = v.z * sz;
						x += mx;
						y += my;
						z += mz;
						writer.write(String.format("      vertex %f %f %f\n", x, y, z));
					}
					writer.write("    endloop\n  endfacet\n");
				}
				writer.write(String.format("endsolid %s\n", i));
			}
		}
	}

	/*
	 * for previewing files in a dedicated window
	 */
	public void show() {
		// do something
	}
}
