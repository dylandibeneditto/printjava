package printjava;

public class Point2 {
    public int x;
    public int y;

    public Point2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Point2{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
