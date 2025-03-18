package printjava;

import java.io.*;
import java.util.*;

public class STL {
    // name of the file
    private String name;
    // list of meshes
    private ArrayList<Mesh> meshes;
    // whether to print output
    public boolean verbose = true;
    // whether to use metric units, only accessable through getters and setters
    private boolean metric = true;
    // whether to rotate meshes in radians
    public boolean radians = true;
    // the width, height, and depth of the printer bed
    public double w = 26.5;
    public double h = 26.5;
    public double d = 26.5;

    // new STL();
    public STL() {
        this.name = "model";
        this.meshes = new ArrayList<>();
    }

    // new STL("name");
    public STL(String name) {
        this.name = name;
        this.meshes = new ArrayList<>();
    }

    // new STL("name", 10, 10, 10);
    public STL(String name, double w, double h, double d) {
        this.name = name;
        this.meshes = new ArrayList<>();
        this.w = w;
        this.h = h;
        this.d = d;
    }

    /**
     * convert inches to centimeters and vice versa 
     */
    private double convertSize(double value, boolean toMetric) {
        return toMetric ? value * 2.54 : value / 2.54;
    }

    /**
     * changes the measurement system and updates the w, h, and d
     */
    public void setMetric(boolean metric) {
        if (this.metric != metric) {
            this.w = convertSize(this.w, metric);
            this.h = convertSize(this.h, metric);
            this.d = convertSize(this.d, metric);
        }
        this.metric = metric;
    }

    public boolean isMetric() {
        return this.metric;
    }

    /** 
     * adds any mesh and mesh subclasses to the list of meshes 
     */
    public void add(Mesh m) {
        this.meshes.add(m);
    }

    /** 
     * used for rotating points by a certain number of radians or degrees (based on status of this.radians) for each axis
     * @return the rotated point
     */
    private Point rotatePoint(Point p, double rx, double ry, double rz, double theta) {

        if(!this.radians) { // convert if in degrees mode
            theta = theta * Math.PI / 180;
        }

        double cosTheta = Math.cos(theta);
        double sinTheta = Math.sin(theta);

        // crossing the point with the rotation axis
        double crossX = ry * p.z - rz * p.y;
        double crossY = rz * p.x - rx * p.z;
        double crossZ = rx * p.y - ry * p.x;

        // finding the dot product
        double dot = rx * p.x + ry * p.y + rz * p.z;

        double newX = p.x * cosTheta + crossX * sinTheta + rx * dot * (1 - cosTheta);
        double newY = p.y * cosTheta + crossY * sinTheta + ry * dot * (1 - cosTheta);
        double newZ = p.z * cosTheta + crossZ * sinTheta + rz * dot * (1 - cosTheta);

        return new Point(newX, newY, newZ);
    }

    /**
     * writes to the stl file
     */
    public void write() {
        String filePath = this.name + ".stl";

        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }

        int boundErrors = 0;
        if (this.verbose)
            System.out.println("Beginning write to '" + filePath + "'");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int i = 0; i < this.meshes.size(); i++) {
                writer.write(String.format("solid %s\r\n", i));
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
                    Point rotatedNormal = (rotationAngle != 0) ? rotatePoint(t.normal, rx, ry, rz, rotationAngle)
                            : t.normal;

                    double tx = rotatedNormal.x + mx;
                    double ty = rotatedNormal.y + my;
                    double tz = rotatedNormal.z + mz;
                    writer.write(String.format("  facet normal %f %f %f\r\n", tx, ty, tz));
                    writer.write("    outer loop\r\n");

                    for (Point v : List.of(t.p3, t.p2, t.p1)) {
                        double x = v.x * sx;
                        double y = v.y * sy;
                        double z = v.z * sz;

                        Point transformedVertex = (rotationAngle != 0)
                                ? rotatePoint(new Point(x, y, z), rx, ry, rz, rotationAngle)
                                : new Point(x, y, z);

                        transformedVertex.x += mx;
                        transformedVertex.y += my;
                        transformedVertex.z += mz;

                        if (transformedVertex.x > this.w / 2 || transformedVertex.x < -this.w / 2
                                || transformedVertex.y > this.h / 2 || transformedVertex.y < -this.h / 2
                                || transformedVertex.z > this.d || transformedVertex.z < 0) {
                            boundErrors++;
                        }

                        writer.write(String.format("      vertex %f %f %f\r\n", transformedVertex.x,
                                transformedVertex.y, transformedVertex.z));
                    }

                    writer.write("    endloop\r\n  endfacet\r\n");
                }
                writer.write(String.format("endsolid %s\r\n", i));
            }

            if (boundErrors > 0) {
                System.out.println("WARNING: " + boundErrors + " vertices are outside the bounds of your printer.");
            }
            if (this.verbose)
                System.out.println("File saved as '" + filePath + "'");

        } catch (IOException e) {
            System.err.println("Error writing STL file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return String.format(
                "STL: %s (%d meshes)\n%.2fx%.2f %s",
                this.name,
                this.meshes.size(),
                this.w,
                this.h,
                this.metric ? "cm" : "in");
    }
}