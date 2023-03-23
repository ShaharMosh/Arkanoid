//208001677 Shahar Moshonov
package geometry;
/**
 * Point has an x and a y value (double),
 * methods - *get and set functions,
 *           *measure the distance to other points,
 *           *if it is equal to another point.
 */
public class Point {
    private double x;
    private double y;
    static final double ERROR = 1E-10;

    /**
     * constructor with configurables x and y values of the point.
     * @param x - x value of the point.
     * @param y - x value of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * calculate the distance of this point to the other point.
     * @param other Point(with values of x and y).
     * @return the distance of this point to the other point.
     */
    public double distance(Point other) {
        double distX = (this.x - other.x) * (this.x - other.x);
        double distY = (this.y - other.y) * (this.y - other.y);
        return Math.sqrt(distX + distY);
    }
    /**
     * checks if the points are equals by compare the x values.
     * @param other Point.
     * @return true if the x value of the points are equal, false otherwise.
     */
    public boolean equalsX(Point other) {
        return (Math.abs(this.x - other.getX()) < ERROR);
    }

    /**
     * checks if the points are equals by compare the y values.
     * @param other Point.
     * @return true if the y value of the points are equal, false otherwise.
     */
    public boolean equalsY(Point other) {
        return (Math.abs(this.y - other.getY()) < ERROR);
    }

    /**
    * checks if the points are equals by compare x and y values.
    * @param other Point(with values of x and y).
    * @return true if the points are equal, false otherwise.
    */
    public boolean equals(Point other) {
        return (Math.abs(this.x - other.getX()) < ERROR
                && Math.abs(this.y - other.getY()) < ERROR);
    }

    /**
     * Return x value of the point.
     * @return x value of the point.
     */
    public double getX() {
        return x;
    }

    /**
     * Return y value of the point.
     * @return y value of the point.
     */
    public double getY() {
        return y;
    }
}
