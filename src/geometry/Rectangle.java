//208001677 Shahar Moshonov
package geometry;
import java.util.ArrayList;

/**
 * Rectangle has-upperLeft and downRight points, width and height of rectangle,
 *                 the ribs of the rectangle.
 * methods - constructor, get methods, intersectionPoints - find intersection
 *          between line and the rectangle.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private double upperY;
    private double downY;
    private double leftX;
    private double rightX;
    private Point downRight;

    /**
     *  constructor - create a new rectangle with location, width/height,
     *  and set the sides of the rectangle.
     * @param upperLeft - upper-left Point of the rectangle.
     * @param width - of the rectangle.
     * @param height - of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.upperY = upperLeft.getY();
        this.downY = upperLeft.getY() + height;
        this.leftX = upperLeft.getX();
        this.rightX = upperLeft.getX() + width;
        this.downRight = new Point(upperLeft.getX() + width,
                upperLeft.getY() + height);
    }



    /**
     * create the lines of the rectangle, and check if they are
     * intersected with the line, and return List of intersection points.
     * @param line - Line.
     * @return List of intersection points with the line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {

        //create the other points of the rectangle.
        Point upperRight = new Point(this.getUpperLeft().getX()
                + this.getWidth(), this.getUpperLeft().getY());
        Point downLeft = new Point(this.getUpperLeft().getX(),
                this.getUpperLeft().getY() + this.getHeight());
        Point downRight = new Point(downLeft.getX() + this.getWidth(),
                upperRight.getY() + this.getHeight());

        //create the lines of the rectangle.
        Line upperY = new Line(this.getUpperLeft(), upperRight);
        Line downY = new Line(downLeft, downRight);
        Line leftX = new Line(this.getUpperLeft(), downLeft);
        Line rightX = new Line(upperRight, downRight);

        ArrayList<Point> listPoint = new ArrayList<>(); //create list of points.

        /*
        find the intersection point of the line with the lines of the rectangle.
         */
        Point interPointUpY = upperY.intersectionWith(line);
        if (interPointUpY != null) {
            listPoint.add(interPointUpY);
        }
        Point interPointDoY = downY.intersectionWith(line);
        if (interPointDoY != null) {
            listPoint.add(interPointDoY);
        }
        Point interPointLeX = leftX.intersectionWith(line);
        if (interPointLeX != null) {
            listPoint.add(interPointLeX);
        }
        Point interPointRiX = rightX.intersectionWith(line);
        if (interPointRiX != null) {
            listPoint.add(interPointRiX);
        }
        return listPoint;
    }
    /**
     * @return the up side of the rectangle.
     */
    public double getUpperY() {
        return upperY;
    }

    /**
     * @return the down side of the rectangle.
     */
    public double getDownY() {
        return downY;
    }

    /**
     * @return the left side of the rectangle.
     */
    public double getLeftX() {
        return leftX;
    }

    /**
     * @return the right side of the rectangle.
     */
    public double getRightX() {
        return rightX;
    }

    /**
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return width;
    }

    /**
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return height;
    }

    /**
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * @return the upper-left point of the rectangle.
     */
    public Point getDownRight() {
        return downRight;
    }
}
