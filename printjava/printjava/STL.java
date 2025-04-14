package printjava;

import java.io.*;
import java.util.*;
import java.lang.reflect.Method;

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
    // whether to use ascii or binary format
    public boolean ascii = false;
    // the width, height, and depth of the printer bed
    public double width = 26.5;
    public double height = 26.5;
    public double depth = 26.5;

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
    public STL(String name, double width, double height, double depth) {
        this.name = name;
        this.meshes = new ArrayList<>();
        this.width = width;
        this.height = height;
        this.depth = depth;
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
            this.width = convertSize(this.width, metric);
            this.height = convertSize(this.height, metric);
            this.depth = convertSize(this.depth, metric);
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
     * adds an array of meshes and mesh subclasses to the list of meshes
     */
    public void add(Mesh[] meshes) {
        for (Mesh m : meshes) {
            this.meshes.add(m);
        }
    }

    /**
     * adds and arraylist of meshes and mesh subclasses to the list of meshes
     */
    public void add(ArrayList<Mesh> meshes) {
        for (Mesh m : meshes) {
            this.meshes.add(m);
        }
    }

    /**
     * writes to the stl file in ascii format
     */
    private void writeAscii() {
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
                Mesh m = this.meshes.get(i);

                if (this.verbose)
                    System.out.println("\t[" + (i+1) + "/" + this.meshes.size() + "] Generating " + m.getClass().getSimpleName() + "...");

                try {
                    Method method = m.getClass().getMethod("generate");
                    method.setAccessible(true);
                    method.invoke(m);
                } catch (NoSuchMethodException e) {
                    // e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                writer.write(String.format("solid %s\r\n", i));

                Point offset = m.position.subtract(m.anchor);
                Point rotationAngles = this.radians ? m.rotation : m.rotation.multiply(Math.PI / 180.0);
                Point scale = m.scale;

                for (Triangle t : m.triangles) {
                    Point rotatedNormal = t.normal.rotate(rotationAngles, m.anchor);
                    Point translatedNormal = rotatedNormal.add(offset);

                    writer.write(String.format("  facet normal %f %f %f\r\n",
                            translatedNormal.x, translatedNormal.y, translatedNormal.z));
                    writer.write("    outer loop\r\n");

                    for (Point v : List.of(t.p3, t.p2, t.p1)) {
                        Point scaledVertex = v.multiply(scale);
                        Point rotatedVertex = scaledVertex.rotate(rotationAngles, m.anchor);
                        Point translatedVertex = rotatedVertex.add(offset);

                        if (translatedVertex.x > this.width / 2 || translatedVertex.x < -this.width / 2
                                || translatedVertex.y > this.height / 2 || translatedVertex.y < -this.height / 2
                                || translatedVertex.z > this.depth || translatedVertex.z < 0) {
                            boundErrors++;
                        }

                        writer.write(String.format("      vertex %f %f %f\r\n",
                                translatedVertex.x, translatedVertex.y, translatedVertex.z));
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

    private void writeLittleEndianFloat(DataOutputStream writer, float value) throws IOException {
        int bits = Float.floatToIntBits(value);
        writer.writeByte(bits & 0xFF);
        writer.writeByte((bits >> 8) & 0xFF);
        writer.writeByte((bits >> 16) & 0xFF);
        writer.writeByte((bits >> 24) & 0xFF);
    }

    private void writePoint(DataOutputStream writer, Point p) throws IOException {
        writeLittleEndianFloat(writer, (float) p.x);
        writeLittleEndianFloat(writer, (float) p.y);
        writeLittleEndianFloat(writer, (float) p.z);
    }

    /**
     * writes to the stl file
     */
    public void write() {

        if (this.ascii) {
            this.writeAscii();
            return;
        }

        String filePath = this.name + ".stl";

        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }

        int boundErrors = 0;
        if (this.verbose)
            System.out.println("Beginning write to '" + filePath + "'");

        try (DataOutputStream writer = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(filePath)))) {

            byte[] header = new byte[80];
            writer.write(header);

            int triangleCount = 0;
            for (int i = 0; i < this.meshes.size(); i++) {
                Mesh m = this.meshes.get(i);
                if (this.verbose)
                    System.out.println(" [" + (i+1) + "/" + this.meshes.size() + "] Generating " + m.getClass().getSimpleName() + "...");
                try {
                    Method method = m.getClass().getMethod("generate");
                    method.setAccessible(true);
                    method.invoke(m);
                } catch (NoSuchMethodException e) {
                    // e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                triangleCount += m.triangles.size();
            }
            writer.writeInt(Integer.reverseBytes(triangleCount));

            for (int i = 0; i < this.meshes.size(); i++) {
                Mesh m = this.meshes.get(i);

                Point offset = m.position.subtract(m.anchor);
                Point rotationAngles = this.radians ? m.rotation : m.rotation.multiply(Math.PI / 180.0);
                Point scale = m.scale;

                for (Triangle t : m.triangles) {
                    Point rotatedNormal = t.normal.rotate(rotationAngles, m.anchor);
                    Point translatedNormal = rotatedNormal.add(offset);

                    writePoint(writer, translatedNormal);

                    for (Point v : List.of(t.p1, t.p2, t.p3)) {
                        Point scaledVertex = v.multiply(scale);
                        Point rotatedVertex = scaledVertex.rotate(rotationAngles, m.anchor);
                        Point translatedVertex = rotatedVertex.add(offset);

                        if (translatedVertex.x > this.width / 2 || translatedVertex.x < -this.width / 2
                                || translatedVertex.y > this.height / 2 || translatedVertex.y < -this.height / 2
                                || translatedVertex.z > this.depth || translatedVertex.z < 0) {
                            boundErrors++;
                        }

                        writePoint(writer, translatedVertex);
                    }

                    writer.writeShort(0);
                }
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
                this.width,
                this.height,
                this.metric ? "cm" : "in");
    }
}