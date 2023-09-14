// 209625946 Tomer Berenstein
package animation.screenanimation;

import animation.Animation;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The ScreenAnimation class represents a screen animation with a background color and text output.
 * It implements the Animation interface.
 *
 * @author Tomer Berenstein tomerbrotem@gmail.com
 * @version 19.0.2
 * @since 2023-01-17
 */
public abstract class ScreenAnimation implements Animation {
    private boolean stop = false;

    /**
     * Returns the text to be displayed on the screen animation.
     *
     * @return The text to be displayed on the screen animation.
     */
    public abstract String textOutput();

    /**
     * Performs one frame of the screen animation.
     *
     * @param d The DrawSurface on which to draw the frame.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        //background
        d.setColor(new Color(40, 45, 55));
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

        // Game Over text
        String outPut = this.textOutput();
        // Adjusting the x and y coordinate to center the text
        int textX = d.getWidth() / 2 - outPut.length() * 7;
        int textY = d.getHeight() / 2;

        d.setColor(new Color(139, 0, 0));
        // Shadow for 3D effect
        d.drawText(textX + 2, textY + 2, outPut, 32);
        d.setColor(Color.WHITE);
        d.drawText(textX, textY, outPut, 32);
    }

    /**
     * Checks if the animation should stop.
     *
     * @return True if the animation should stop, false otherwise.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
