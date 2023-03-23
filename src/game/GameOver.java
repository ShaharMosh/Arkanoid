//208001677 Shahar Moshonov
package game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * GameOver class implements Animation and display a screen
 * with message and the score of the game.
 */
public class GameOver implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private int counter;

    /**
     * constructor with configurables - keyboard, counter.
     * @param keyboard - KeyboardSensor.
     * @param counter - score of the game.
     */
    public GameOver(KeyboardSensor keyboard, int counter) {
        this.keyboard = keyboard;
        this.stop = false;
        this.counter = counter;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "Game Over. Your score is " + counter, 32);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
