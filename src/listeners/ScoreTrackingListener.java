//208001677 Shahar Moshonov
package listeners;


import collisionSpirite.Block;
import sprites.Ball;

/**
 * ScoreTrackingListener class implements HitListener -
 * update this counter when blocks are being hit and removed.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * constructor with configurables - Counter.
     * @param scoreCounter - Counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
}
