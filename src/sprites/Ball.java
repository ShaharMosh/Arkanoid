//208001677 Shahar Moshonov
package sprites;
import collisionSpirite.Velocity;
import game.GameLevel;
import game.GameEnvironment;
import geometry.Point;
import geometry.Line;
import biuoop.DrawSurface;

/**
 * Ball class- has center point, color, radius,
 *             velocity, the boundaries of the screen.
 * methods- constructors ball,
 *          gets and sets functions of the ball and velocity,
 *          sets of the boundaries,
 *          draw the ball on a given DrawSurface,
 *          move one step- set the dx, dy of the ball and change it position.
 */
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;
    static final double ERROR = 1E-10;

    /**
     * constructor with configurables - center, radius, color of the ball.
     * @param center - Point object: center of the ball.
     * @param r - int: radius of the ball.
     * @param color java.awt.Color: color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
    }

    /**
     * set a ball his details.
     * @param x the x value of the center ball.
     * @param y the y value of the center ball.
     * @param r the radius of the ball.
     * @param color the color of the ball.
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
    }

    /**
     * @return X center of the ball.
     */
    public int getX() {
        int x = (int) center.getX();
        return x;
    }

    /**
     * @return Y center of the ball.
     */
    public int getY() {
        int y = (int) center.getY();
        return y;
    }

    /**
     * @return radius of the ball.
     */
    public int getSize() {
        return radius;
    }

    /**
     * @return color of the ball.
     */
    public java.awt.Color getColor() {
        return color;
    }


    /**
     * set the velocity of the ball.
     * @param v - the given velocity.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * set the velocity of the ball( separate by dx and dy).
     * @param dx - the given dx velocity.
     * @param dy - the given dx velocity.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }


    /**
     * return the velocity of the ball.
     * @return velocity of the ball.
     */
    public Velocity getVelocity() {
        return velocity;
    }


    /**
     * set the game environment of the ball.
     * @param gameEnvironment - GameEnvironment.
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * @return the game environment of the ball.
     */
    public GameEnvironment getGameEnvironment() {
        return gameEnvironment;
    }

    /**
     * moveOneStep change the ball position.(call the function applyToPoint)
     * change the velocity so that the ball will stay on the bounding.
     */
    public void moveOneStep() {
        Velocity v = getVelocity();
        Line trajectory = new Line(this.center, v.applyToPoint(this.center));

        if (this.gameEnvironment.getClosestCollision(trajectory) != null) {
            Point coloPoint = getGameEnvironment().
                    getClosestCollision(trajectory).collisionPoint();
            if (this.center.distance(coloPoint) <= radius + 1
                    && this.center.distance(coloPoint) > 0) {
                v = getGameEnvironment().getClosestCollision(trajectory).
                        collisionObject().hit(this, coloPoint, v);
                setVelocity(v);
            }
        }

        //send the point to applyToPoint function that change the point position
        this.center = v.applyToPoint(this.center);
    }

    @Override
    /**
     * draw the ball on the given DrawSurface.
     *
     * @param surface - DrawSurface.
     */
    public void drawOn(DrawSurface surface) {
        java.awt.Color colorBall = this.getColor();
        surface.setColor(colorBall);
        surface.fillCircle((int) center.getX(), (int) center.getY(), radius);
    }

    @Override
    /**
     * active the move one step method.
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Add this ball to the game (as Sprite).
     * @param g - Game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Remove this ball from the game.
     * @param g - Game.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}

