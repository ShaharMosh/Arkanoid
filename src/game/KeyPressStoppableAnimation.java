//208001677 Shahar Moshonov
package game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * KeyPressStoppableAnimation - a decorator-class that will wrap an existing
 * animation and add a "waiting-for-key" behavior to it.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private Boolean stop;
    private boolean isAlreadyPressed;


    /**
     * constructor with configurables - KeyboardSensor, String, Animation.
     * @param sensor - KeyboardSensor.
     * @param key - String.
     * @param animation - Animation.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (!sensor.isPressed(KeyboardSensor.SPACE_KEY)) {
            isAlreadyPressed = false;
        }
        if (sensor.isPressed(KeyboardSensor.SPACE_KEY) && !isAlreadyPressed) {
            stop = true;
            isAlreadyPressed = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return stop;
    }
}
