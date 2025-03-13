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

	private Point rotatePoint(Point p, double rx, double ry, double rz, double theta) {
		double cosTheta = Math.cos(theta);
		double sinTheta = Math.sin(theta);
	
		double crossX = ry * p.z - rz * p.y;
		double crossY = rz * p.x - rx * p.z;
		double crossZ = rx * p.y - ry * p.x;
	
		double dot = rx * p.x + ry * p.y + rz * p.z;
	
		double newX = p.x * cosTheta + crossX * sinTheta + rx * dot * (1 - cosTheta);
		double newY = p.y * cosTheta + crossY * sinTheta + ry * dot * (1 - cosTheta);
		double newZ = p.z * cosTheta + crossZ * sinTheta + rz * dot * (1 - cosTheta);
	
		return new Point(newX, newY, newZ);
	}

	/*
	 * logic for writing stl file to disk
	 */
	public void write() throws IOException {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.name + ".stl"))) {
			for (int i = 0; i < this.meshes.size(); i++) {
				writer.write(String.format("solid %s\n", i));
				Mesh m = this.meshes.get(i);
	
				double mx = m.position.x - m.anchor.x;
				double my = m.position.y - m.anchor.y;
				double mz = m.position.z - m.anchor.z;
	
				double rx = m.rotation.x;
				double ry = m.rotation.y;
				double rz = m.rotation.z;
				double rotationAngle = Math.sqrt(rx * rx + ry * ry + rz * rz);
	
				if (rotationAngle != 0) {
					rx /= rotationAngle;
					ry /= rotationAngle;
					rz /= rotationAngle;
				}
	
				double sx = m.scale.x;
				double sy = m.scale.y;
				double sz = m.scale.z;
	
				for (Triangle t : m.triangles) {
					Point rotatedNormal = (rotationAngle != 0) ? rotatePoint(t.normal, rx, ry, rz, rotationAngle) : t.normal;
	
					double tx = rotatedNormal.x + mx;
					double ty = rotatedNormal.y + my;
					double tz = rotatedNormal.z + mz;
					writer.write(String.format("  facet normal %f %f %f\n", tx, ty, tz));
					writer.write("    outer loop\n");
	
					for (Point v : List.of(t.p3, t.p2, t.p1)) {
						double x = v.x * sx;
						double y = v.y * sy;
						double z = v.z * sz;
	
						Point transformedVertex = (rotationAngle != 0) ? rotatePoint(new Point(x, y, z), rx, ry, rz, rotationAngle) : new Point(x, y, z);
	
						transformedVertex.x += mx;
						transformedVertex.y += my;
						transformedVertex.z += mz;
	
						writer.write(String.format("      vertex %f %f %f\n", transformedVertex.x, transformedVertex.y, transformedVertex.z));
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
