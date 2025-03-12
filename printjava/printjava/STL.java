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
				for (Triangle t : this.meshes.get(i).triangles) {
					writer.write(String.format("  facet normal %f %f %f\n", t.normal.x, t.normal.y, t.normal.z));
					writer.write("    outer loop\n");
					for (Point v : List.of(t.p1, t.p2, t.p3)) {
						writer.write(String.format("      vertex %f %f %f\n", v.x, v.y, v.z));
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
