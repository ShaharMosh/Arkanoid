//208001677 Shahar Moshonov
package game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * PauseScreen class implements Animation and display a pause screen if the
 * player want to stop the game.
 */
public class PauseScreen implements Animation {
private KeyboardSensor keyboard;
private boolean stop;

    /**
     * constructor with configurables - KeyboardSensor.
     * @param k - KeyboardSensor.
     */
    public PauseScreen(KeyboardSensor k) {
            this.keyboard = k;
            this.stop = false;
            }
    @Override
    public void doOneFrame(DrawSurface d) {
            d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return this.stop; }
    }
