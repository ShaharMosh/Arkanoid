//208001677 Shahar Moshonov
package game;

import biuoop.DrawSurface;

/**
 * Animation interface - apply the game specific logic and stopping conditions.
 */
public interface Animation {

    /**
     * apply the things we want to display on the game.
     * @param d - DrawSurface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * @return when to stop display the animation.
     */
    boolean shouldStop();
}
