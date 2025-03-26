package printjava;

public class Point {
	public double x, y, z;
	// new Point(0, 0, 0);
	public Point(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * sets all values of the point
	 */
	public void set(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * returns the distance between this point and another point
	 */
	public double distance(Point p) {
		double dx = x - p.x;
		double dy = y - p.y;
		double dz = z - p.z;
		return Math.sqrt(dx * dx + dy * dy + dz * dz);
	}
}
