//208001677 Shahar Moshonov
package game;

import collisionSpirite.Block;
import collisionSpirite.Velocity;
import geometry.Rectangle;
import sprites.Ball;
import sprites.Sprite;
import java.util.List;

/**
 * LevelInformation interface that give information required to describe a level.
 */
public interface LevelInformation {

    /**
     * @return the number of balls.
     */
    int numberOfBalls();

    /**
     * @return initial velocity of each ball.
     */
    List<Velocity> initialBallVelocities();

    /**
     * create balls with location.
     * @return lis of balls.
     */
    List<Ball> balls();

    /**
     * @return the speed of the paddle.
     */
    int paddleSpeed();

    /**
     * @return the width of the paddle.
     */
    int paddleWidth();

    /**
     * @return - Rectangle of th paddle.
     */
    Rectangle rectPaddle();

    /**
     * @return the level name will be displayed at the top of the screen.
     */
    String levelName();

    /**
     * @return a sprite with the background of the level.
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains
     *  its size, color and location.
     * @return list of that blocks.
     */
    List<Block> blocks();
    /**
     * @return Number of blocks that should be removed before the level is end.
     */
    int numberOfBlocksToRemove();
}
