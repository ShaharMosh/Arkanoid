//208001677 Shahar Moshonov
package game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * AnimationRunner class-  takes an Animation object and runs it.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private biuoop.Sleeper sleeper;

    /**
     * constructor with configurables - GUI.
     * @param gui - GUI.
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
        this.framesPerSecond = 60;
        this.sleeper = new biuoop.Sleeper();
    }

    /**
     * @return Sleeper.
     */
    public Sleeper getSleeper() {
        return sleeper;
    }

    /**
     * takes an Animation object and runs thr doOneFrame method until the
     * shouldStop method is telling to stop.
     * @param animation - Animation.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); //timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
