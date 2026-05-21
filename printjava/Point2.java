package printjava;

public class Point2 {
    public double x;
    public double y;

    /**
     * Constructs a new Point2.
     * 
     * @param x the x value.
     * @param y the y value.
     */
    public Point2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Sets the specified value.
     * 
     * @param x the x value.
     * @param y the y value.
     */
    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Distances the specified value.
     * 
     * @param p the p value.
     * @return the double result.
     */
    public double distance(Point2 p) {
        return Math.sqrt(Math.pow(this.x - p.x, 2) + Math.pow(this.y - p.y, 2));
    }

    /**
     * Adds the specified value.
     * 
     * @param p the p value.
     * @return the Point2 result.
     */
    public Point2 add(Point2 p) {
        return new Point2(this.x + p.x, this.y + p.y);
    }

    /**
     * Subtracts the specified value.
     * 
     * @param p the p value.
     * @return the Point2 result.
     */
    public Point2 subtract(Point2 p) {
        return new Point2(this.x - p.x, this.y - p.y);
    }

    /**
     * Multiplys the specified value.
     * 
     * @param p the p value.
     * @return the Point2 result.
     */
    public Point2 multiply(Point2 p) {
        return new Point2(this.x * p.x, this.y * p.y);
    }

    /**
     * Multiplys the specified value.
     * 
     * @param factor the factor value.
     * @return the Point2 result.
     */
    public Point2 multiply(double factor) {
        return new Point2(this.x * factor, this.y * factor);
    }

    /**
     * Divides the specified value.
     * 
     * @param p the p value.
     * @return the Point2 result.
     */
    public Point2 divide(Point2 p) {
        return new Point2(this.x / p.x, this.y / p.y);
    }

    /**
     * Divides the specified value.
     * 
     * @param factor the factor value.
     * @return the Point2 result.
     */
    public Point2 divide(double factor) {
        return new Point2(this.x / factor, this.y / factor);
    }

    /**
     * Dots the specified value.
     * 
     * @param p the p value.
     * @return the double result.
     */
    public double dot(Point2 p) {
        return this.x * p.x + this.y * p.y;
    }

    /**
     * Cross the specified values.
     * 
     * @param p the p value.
     * @return the double result.
     */
    public double cross(Point2 p) {
        return this.x * p.y - this.y * p.x;
    }

    /**
     * rotates the point around a center by a given angle of radians
     * 
     * @returns the rotated point, DOESNT CHANGE THIS POINT
     */
    public Point2 rotate(double angleRadians, Point2 center) {
        double translatedX = this.x - center.x;
        double translatedY = this.y - center.y;

        double cosA = Math.cos(angleRadians);
        double sinA = Math.sin(angleRadians);

        double rotatedX = translatedX * cosA - translatedY * sinA;
        double rotatedY = translatedX * sinA + translatedY * cosA;

        return new Point2(rotatedX + center.x, rotatedY + center.y);
    }

    /**
     * Equals the specified values.
     * 
     * @param p the p value.
     * @return the boolean result.
     */
    public boolean equals(Point2 p) {
        return this.x == p.x && this.y == p.y;
    }

    @Override
    /**
     * ToStrings the specified value.
     * 
     * @return the String result.
     */
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

}
