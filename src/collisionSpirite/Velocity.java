//208001677 Shahar Moshonov

package collisionSpirite;
import geometry.Point;
/**
 * Velocity has dx, dy (change in position on the X and the Y axes).
 * methods - *constructor,
 *           *get and set functions,
 *           *velocity by speed and angle,
 *           *change position of the point by velocity.
 */
public class Velocity {
    private double dx;
    private double dy;


    /**
     * constructor of velocity by dx and dy.
     * @param dx - velocity in the X axe.
     * @param dy - velocity in the Y axe.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }



    /**
     * get the velocity change in the X axe.
     * @return - the velocity change in the X axe.
     */
    public double getVelocityX() {
        return dx;
    }

    /**
     * get the velocity change in the Y axe.
     * @return - the velocity change in the Y axe.
     */
    public double getVelocityY() {
        return dy;
    }



    /**
     * get a point and change it position,
     * by add dx and dy correspondingly to X and Y (x+dx, y+dy).
     *
     * @param p - Point.
     * @return new point with a new position - (x+dx, y+dy).
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }


    /**
     * define the dx and dy velocity by angle and the speed.
     * @param angle the given angle of the object.
     * @param speed the speed of the object.
     * @return Velocity by dx and dy.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radian = Math.toRadians(angle);
        double dx = speed * Math.sin(radian);
        double dy = speed * Math.cos(radian);
        return new Velocity(dx, -dy);
    }

    /**
     * calculate the speed of the object.
     * @return speed of the object.
     */
    public double getSpeed() {
        return Math.sqrt((getVelocityX() * getVelocityX()) + (getVelocityY() * getVelocityY()));
    }
}