//208001677 Shahar Moshonov
package collision;
import geometry.Point;

/**
 * CollisionInfo as - Point, Collidable.
 * methods- *constructor, getters.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * constructor - create collision info- collision point, collision object.
     * @param collisionPoint - the point at which the collision occurs.
     * @param collisionObject - the collidable object involved in the collision.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }


    /**
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return collisionPoint;
    }


    /**
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return collisionObject;
    }
}
