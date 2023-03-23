//208001677 Shahar Moshonov
package listeners;

import collisionSpirite.Block;
import sprites.Ball;

/**
 * HitListener- interface called whenever the beingHit
 * object is hit and implement hitEvent().
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * @param beingHit - Block.
     * @param hitter - Ball.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
