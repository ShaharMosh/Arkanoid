//208001677 Shahar Moshonov
package game;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import sprites.SpriteCollection;

import java.awt.Color;



/**
 * display the given gameScreen, for numOfSeconds seconds and show
 * // a countdown from countFrom back to 1.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;

    /**
     * constructor with configurables - double, int, SpriteCollection.
     * @param numOfSeconds - double.
     * @param countFrom - a countdown from countFrom back to 1.
     * @param gameScreen - SpriteCollection of the game.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
         }

    @Override
    public void doOneFrame(DrawSurface d) {
        Sleeper sleeper = new Sleeper();
        if (countFrom > -1) {
            gameScreen.drawAllOn(d);
            d.setColor(Color.black);
            d.fillRectangle(350, 250, 100, 100);
            d.setColor(Color.white);
            String s = Integer.toString(countFrom);
            d.drawText(390, 315, s, 50);
            countFrom = countFrom - 1;
            if (countFrom < 2) {
                sleeper.sleepFor(700);
            }
        } else {
                stop = true;
            }
        }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
