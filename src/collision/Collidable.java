//208001677 Shahar Moshonov

package collision;
import collisionSpirite.Velocity;
import sprites.Ball;
import geometry.Rectangle;
import geometry.Point;

/**
 * Collidable interface - *getCollisionRectangle- the object collision.
 *                        *hit- change the velocity by where the ball hits.
 */
public interface Collidable {

    /**
     * the "collision shape" of the object.
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();


    /**
     * check where the object hit the collidable and return the new velocity
     * expected after the hit.
     * @param collisionPoint of object that we collided.
     * @param currentVelocity object that we collided.
     * @param hitter - Ball;
     * @return new velocity expected after the hit.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
