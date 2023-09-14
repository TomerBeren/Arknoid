// 209625946 Tomer Berenstein
package animation;

import biuoop.DrawSurface;

/**
 * The Animation interface represents a single frame of an animation.
 * It is designed to be used in a loop to display a sequence of frames.
 *
 * @author Tomer Berenstein tomerbrotem@gmail.com
 * @version 19.0.2
 * @since 2023-01-17
 */
public interface Animation {

    /**
     * Performs one frame of the animation on the given draw surface.
     *
     * @param d the draw surface to perform the frame on
     */
    void doOneFrame(DrawSurface d);

    /**
     * Determines whether the animation should stop.
     *
     * @return true if the animation should stop, false otherwise
     */
    boolean shouldStop();
}
