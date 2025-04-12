package printjava;

public class Point2 {
    public double x;
    public double y;

    public Point2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Point2 p) {
        return Math.sqrt(Math.pow(this.x - p.x, 2) + Math.pow(this.y - p.y, 2));
    }

    public Point2 add(Point2 p) {
        return new Point2(this.x + p.x, this.y + p.y);
    }

    public Point2 subtract(Point2 p) {
        return new Point2(this.x - p.x, this.y - p.y);
    }

    public Point2 multiply(Point2 p) {
        return new Point2(this.x * p.x, this.y * p.y);
    }

    public Point2 divide(Point2 p) {
        return new Point2(this.x / p.x, this.y / p.y);
    }

    public double dot(Point2 p) {
        return this.x * p.x + this.y * p.y;
    }

    public double cross(Point2 p) {
        return this.x * p.y - this.y * p.x;
    }

    public Point2 rotate(double angleRadians, Point2 center) {
        double translatedX = this.x - center.x;
        double translatedY = this.y - center.y;

        double cosA = Math.cos(angleRadians);
        double sinA = Math.sin(angleRadians);

        double rotatedX = translatedX * cosA - translatedY * sinA;
        double rotatedY = translatedX * sinA + translatedY * cosA;

        return new Point2(rotatedX + center.x, rotatedY + center.y);
    }

    public boolean equals(Point2 p) {
        return this.x == p.x && this.y == p.y;
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

}
