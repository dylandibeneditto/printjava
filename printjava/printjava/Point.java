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

	public Point add(Point p) {
		return new Point(this.x + p.x, this.y + p.y, this.z + p.z);
	}

	public Point subtract(Point p) {
		return new Point(this.x - p.x, this.y - p.y, this.z - p.z);
	}

	public Point multiply(Point p) {
		return new Point(this.x * p.x, this.y * p.y, this.z * p.z);
	}

	public Point divide(Point p) {
		return new Point(this.x / p.x, this.y / p.y, this.z / p.z);
	}

	public double dot(Point p) {
		return this.x * p.x + this.y * p.y + this.z * p.z;
	}

	public Point cross(Point other) {
		double cx = this.y * other.z - this.z * other.y;
		double cy = this.z * other.x - this.x * other.z;
		double cz = this.x * other.y - this.y * other.x;
		return new Point(cx, cy, cz);
	}

	/**
	 * will rotate the point around the X AXIS
	 * in radians!
	 * @returns the rotated point, DOESNT CHANGE THIS POINT
	 */
    public Point rotateAroundX(double angleRadians, Point center) {
        double dy = this.y - center.y;
        double dz = this.z - center.z;

        double cosA = Math.cos(angleRadians);
        double sinA = Math.sin(angleRadians);

        double newY = dy * cosA - dz * sinA;
        double newZ = dy * sinA + dz * cosA;

        return new Point(this.x, newY + center.y, newZ + center.z);
    }

	/**
	 * will rotate the point around the Y AXIS
	 * in radians!
	 * @returns the rotated point, DOESNT CHANGE THIS POINT
	 */
    public Point rotateAroundY(double angleRadians, Point center) {
        double dx = this.x - center.x;
        double dz = this.z - center.z;

        double cosA = Math.cos(angleRadians);
        double sinA = Math.sin(angleRadians);

        double newX = dx * cosA + dz * sinA;
        double newZ = -dx * sinA + dz * cosA;

        return new Point(newX + center.x, this.y, newZ + center.z);
    }

	/**
	 * will rotate the point around the Z AXIS
	 * in radians!
	 * @returns the rotated point, DOESNT CHANGE THIS POINT
	 */
    public Point rotateAroundZ(double angleRadians, Point center) {
        double dx = this.x - center.x;
        double dy = this.y - center.y;

        double cosA = Math.cos(angleRadians);
        double sinA = Math.sin(angleRadians);

        double newX = dx * cosA - dy * sinA;
        double newY = dx * sinA + dy * cosA;

        return new Point(newX + center.x, newY + center.y, this.z);
    }

	/**
	 * will rotate the point around the X, Y, and Z AXIS
	 * @param anglesRadians the angles in radians to rotate around the x, y, and z axes
	 * @returns the rotated point, DOESNT CHANGE THIS POINT
	 */
	public Point rotate(Point anglesRadians, Point center) {
		return new Point(this.x, this.y, this.z)
				.rotateAroundX(anglesRadians.x, center)
				.rotateAroundY(anglesRadians.y, center)
				.rotateAroundZ(anglesRadians.z, center);
	}
}
