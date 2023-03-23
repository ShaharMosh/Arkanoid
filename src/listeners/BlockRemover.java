//208001677 Shahar Moshonov
package listeners;

import collisionSpirite.Block;
import game.GameLevel;
import sprites.Ball;

/**
 * BlockRemover implements HitListener is in charge of removing blocks from
 * the game, and keeping count of the number of blocks that remain.
 * methods - constructor and hitEvent(HitListener).
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * constructor with configurables - Game and Counter.
     * @param game - Game.
     * @param removedBlocks - Counter.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }


    /**
     * if the block is being hit we remove it from the game
     * (from Collidable and Sprite), decrease the number of remaining Blocks.
     * @param beingHit - Block.
     * @param hitter - Ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        game.removeCollidable(beingHit);
        game.removeSprite(beingHit);
        remainingBlocks.decrease(1);
        beingHit.removeHitListener(this);
    }
}