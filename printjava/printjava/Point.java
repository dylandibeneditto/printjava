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
}
