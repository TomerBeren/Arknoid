// 209625946 Tomer Berenstein
package sprites;

import biuoop.DrawSurface;

/**
 * The Sprite interface represents drawable game objects that can also
 * be updated according to the passage of time.
 *
 * @author Tomer Berenstein tomerbrotem@gmail.com
 * @version 19.0.2
 * @since 2023-01-17
 */
public interface Sprite {
    /**
     * Draws the sprite on the given DrawSurface.
     *
     * @param d The DrawSurface to draw the sprite on
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite that time has passed, allowing it to update
     * its state accordingly.
     */
    void timePassed();
}
