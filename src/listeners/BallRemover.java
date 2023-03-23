//208001677 Shahar Moshonov
package listeners;

import collisionSpirite.Block;
import game.GameLevel;
import sprites.Ball;

/**
 * BallRemover implements HitListener is in charge of keeping count
 * of the number of balls that remain.
 * methods - constructor and hitEvent(HitListener).
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;


    /**
     * constructor with configurables - Game and Counter.
     * @param game - Game.
     * @param remainingBalls - Counter.
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        remainingBalls.decrease(1);
    }
}
