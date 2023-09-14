// 209625946 Tomer Berenstein
package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The KeyPressStoppableAnimation class represents a wrapper animation that stops when a specific key is pressed.
 * It delegates the drawing of frames to the underlying animation.
 *
 * @author Tomer Berenstein tomerbrotem@gmail.com
 * @version 19.0.2
 * @since 2023-01-17
 */
public class KeyPressStoppableAnimation implements Animation {
    private final KeyboardSensor keyboardSensor;
    private final String key;
    private final Animation animation;
    private boolean isStopped;
    private boolean isAlreadyPressed;

    /**
     * Creates a new KeyPressStoppableAnimation.
     *
     * @param sensor    The KeyboardSensor used for key detection.
     * @param key       The key that triggers the animation to stop.
     * @param animation The underlying Animation to be displayed.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboardSensor = sensor;
        this.key = key;
        this.animation = animation;
        this.isAlreadyPressed = true;
        this.isStopped = false;
    }

    /**
     * Performs one frame of the animation.
     * Delegates the frame drawing to the underlying animation.
     *
     * @param d The DrawSurface on which to draw the frame.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);

        if (this.keyboardSensor.isPressed(key)) {
            if (!isAlreadyPressed) {
                this.isStopped = true;
            }
        } else {
            // if the key is not being pressed, set isAlreadyPressed to false
            isAlreadyPressed = false;
        }
    }

    /**
     * Checks if the animation should stop.
     *
     * @return True if the animation should stop, false otherwise.
     */
    @Override
    public boolean shouldStop() {
        return isStopped;
    }
}
