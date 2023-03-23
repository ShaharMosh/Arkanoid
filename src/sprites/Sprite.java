//208001677 Shahar Moshonov
package sprites;
import biuoop.DrawSurface;
import game.GameLevel;

/**
 * Sprite interface - *drawOn- draw the sprite
 *                    *timePassed -  notify the sprite that time has passed.
 *                    *addToGame - Add this object to the game.
 */
public interface Sprite {

    /**
     * draw the sprite to the screen.
     * @param d - a DrawSurface.
     */
    void drawOn(DrawSurface d);


    /**
     * notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * Add this object to the game.
     * @param g - Game.
     */
    void addToGame(GameLevel g);
}
