//208001677 Shahar Moshonov
package geometry;

/**
 * Line class- start and end points.
 * methods- constructors Line, gets and sets functions of the Line,
 *          length and middle point of the line, equality between two lines,
 *          intersection between two line, intersection point,
 *          methods to find the equation of the line.
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * constructor with configurables of the start and end points of the line.
     *
     * @param start - the start point of the line.
     * @param end   - the end point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * constructor with configurables x1,x2,y1,y2 values of the start and
     * end points of the line.
     *
     * @param x1 - x1 value of the start point.
     * @param y1 - y1 value of the start point.
     * @param x2 - x2 value of the start point.
     * @param y2 - y2 value of the start point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        start = new Point(x1, y1);
        end = new Point(x2, y2);
    }

    /**
     * calculate the length of the line by sent it to the 'distance' function
     * that is implemented in 'Point' class.
     *
     * @return the length of the line.
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * calculate the middle point of the line.
     *
     * @return the middle point of the line.
     */
    public Point middle() {
        double x = (start.getX() + end.getX()) / 2;
        double y = (start.getY() + end.getY()) / 2;
        return new Point(x, y);
    }

    /**
     * @return the start point of the line.
     */
    public Point start() {
        return start;
    }

    /**
     * @return the end point of the line.
     */
    public Point end() {
        return end;
    }

    /**
     * check if the line are intersecting, if they are return the point.
     * @param other Line.
     * @return the intersection point if the lines intersect and null otherwise.
     */
    public Point intersectionWith(Line other) {
        if (!this.isIntersecting(other)) {
            return null;
        }
        //if the lines are equal return null point.
        if (this.equals(other)) {
            return null;
        }
        //check if the line have the same slope and then check the range.
        if (this.slope(this.start, this.end) == other.slope(other.start,
                other.end)) {
            if ((this.end.getX() <= Math.max(other.start.getX(),
                    other.end.getX())) && (this.end.getX()
                    >= Math.min(other.start.getX(), other.end.getX()))) {
                return null;
            }
        }

        if (this.start.equalsX(this.end) && other.start.equalsX(other.end)
                && this.start.equalsX(other.start)) {
            return null;
        }
        if (this.start.equalsY(this.end) && other.start.equalsY(other.end)
                && this.start.equalsY(other.start)) {
            return null;
        }
        if (this.end.equalsX(this.start) && (this.end.getX()
                <= Math.max(other.start.getX(), other.end.getX())
                && this.end.getX() >= Math.min(other.start.getX(), other.end.getX()))) {
            double slope = slope(other.start, other.end);
            double point = pointLine(slope, other.start.getX(), other.start.getY());
            double y = slope * this.end.getX() + point;
            return new Point(this.end.getX(), y);
        }

        return this.intersectionPoint(other);
    }

    /**
     * calculate the slope between to points.
     * @param start - start Point.
     * @param end - end Point.
     * @return the slope between to points.
     */
    public double slope(Point start, Point end) {
        double slopeLine = (end.getY() - start.getY())
                / (end.getX() - start.getX());
        return slopeLine;
    }

    /**
     * The equation of the line - y=ax+b - this method calc the b.
     * @param slope -slope of the line.
     * @param x - x of a point on the line.
     * @param y - y of a point on the line.
     * @return y=ax+b - this method return the b.
     */
    public double pointLine(double slope, double x, double y) {
        double pointLine = y - (slope * x);
        return pointLine;
    }

    /**
     * find the intersection Point (by the possible cases).
     * @param two other line.
     * @return intersection Point
     */
    public Point intersectionPoint(Line two) {

        //calculate the slope of the lines.
        double slope1 = slope(this.start, this.end);
        double slope2 = slope(two.start, two.end);

        //if line 1 is parallel to y and the second line is parallel to x.
        if (this.start.equalsX(this.end) && two.start.equalsY(two.end)) {
            return new Point(this.start.getX(), two.start.getY());
        }

        //if line 1 is parallel to x and the second line is parallel to y
        if (this.start.equalsY(this.end) && two.start.equalsX(two.end)) {
            return new Point(two.start.getX(), this.start.getY());
        }

        // if line 1 is parallel to y.
        if (slope1 == 0 && this.start.equalsX(this.end)) {
            return new Point(this.start.getX(),
                    intersectionPointY(this.start.getX(), two));
        }

        // if line 1 is parallel to x.
        if (slope1 == 0 && slope2 != 0 && this.start.equalsY(this.end)) {
            double b2 = pointLine(slope2, two.end.getX(), two.end.getY());
            double x = (this.start.getY() - b2) / slope2;
            return new Point(x, this.start.getY());
        }

        // if line 2 is parallel to y.
        if (slope2 == 0 && two.start.equalsX(two.end)) {
            return new Point(two.start.getX(),
                    intersectionPointY(two.start.getX(), two));
        }

        // if line 2 is parallel to x.
        if (slope2 == 0 && slope1 != 0 && two.start.equalsY(two.end)) {
            double b1 = pointLine(slope1, this.end.getX(), this.end.getY());
            double x = (two.start.getY() - b1) / slope1;
            return new Point(x, intersectionPointY(x, two));
        }

        // calculate the equation of the lines
        double b1 = pointLine(slope1, this.start.getX(), this.start.getY());
        double b2 = pointLine(slope2, two.end.getX(), two.end.getY());
        double x = (b2 - b1) / (slope1 - slope2);
        return new Point(x, intersectionPointY(x, two));
    }

    /**
     * calc and return the y of the intersection point.
     * @param x of the intersection point.
     * @param other line.
     * @return the y of the intersection point.
     */
    public double intersectionPointY(double x, Line other) {
        double slope1 = slope(other.start, other.end);
        double b1 = pointLine(slope1, other.start.getX(), other.start.getY());
        double y = (slope1 * x) + b1;
        return y;
    }

    /**
     * checks if the start and end points are equals by compare x and y values.
     *
     * @param other Point(with values of x and y).
     * @return true is the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        return ((this.start.equals(other.start) && this.end.equals(other.end)
                || (this.start.equals(other.end)
                && this.end.equals(other.start))));
    }

    /**
     * get 3 points-check if the point is in the range of line(create by
     * the other points).
     * @param p the point we want to check.
     * @param q - one point of the line.
     * @param r - second point of the line.
     * @return true if the point is in the range of the line.
     */
    public boolean onSegment(Point p, Point q, Point r) {
        if (q.getX() <= Math.max(p.getX(), r.getX())
                && q.getX() >= Math.min(p.getX(), r.getX())
                && q.getY() <= Math.max(p.getY(), r.getY())
                && q.getY() >= Math.min(p.getY(), r.getY())) {
            return true;
        }
        return false;
    }

    /**
     * check if the 3 points are on one line.(same direction).
     * @param p first point.
     * @param q second point.
     * @param r third point.
     * @return 0 if the direction is the same, 1 if is above 0, 2 if under 0.
     */
    public int orientation(Point p, Point q, Point r) {
        double val = (q.getY() - p.getY()) * (r.getX() - q.getX())
                - (q.getX() - p.getX()) * (r.getY() - q.getY());

        if (val == 0) {
            return 0; //has the same slope.
        }

        if (val > 0) {
            return 1;
        } else {
            return 2;
        }
    }

    /**
     * check if two lines are intersecting (refer to different cases).
     * @param other Line.
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        // find the four directions between the point.
        int o1 = orientation(this.start, this.end, other.start);
        int o2 = orientation(this.start, this.end, other.end);
        int o3 = orientation(other.start, other.end, this.start);
        int o4 = orientation(other.start, other.end, this.end);

        // general case
        if (o1 != o2 && o3 != o4) {
            return true;
        }

        /*
         the lines have the same slope and have more than one common point.
         in each case another point is found on the second straight line
         */
        if (o1 == 0 && onSegment(this.start, other.start, this.end)) {
            return true;
        }
        if (o2 == 0 && onSegment(this.start, other.end, this.end)) {
            return true;
        }
        if (o3 == 0 && onSegment(other.start, this.start, other.end)) {
            return true;
        }
        if (o4 == 0 && onSegment(other.start, this.end, other.end)) {
            return true;
        }

        return false; // doesn't mach in any of the above cases
    }


    /**
     * send to 'intersectionPoints' method in Rectangle that find the
     * intersection points with line, and check what is the closest point
     * to the start of the line.
     * @param rect Rectangle
     * @return the closest intersection point to the start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        //get the intersection points between the rectangle and the line.
        java.util.List<Point> listPoint = rect.intersectionPoints(this);

        //if there is no intersection points return null.
        if (listPoint.size() == 0) {
            return null;
        }

        //check what is the closest point to the start of the line.
        Point closePoint = listPoint.get(0);
        double minDistance = this.start.distance(listPoint.get(0));
        for (int i = 0; i < listPoint.size(); i++) {
            if (this.start.distance(listPoint.get(i)) < minDistance) {
                closePoint = listPoint.get(i);
                minDistance = this.start.distance(listPoint.get(i));
            }
        }
        return closePoint; //return the closest intersection point.
        }
    }