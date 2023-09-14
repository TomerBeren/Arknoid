// 209625946 Tomer Berenstein
package sprites;

import biuoop.DrawSurface;
import listeners.lhelpers.Counter;

import java.awt.Color;

/**
 * A class that represents a ScoreIndicator.
 * ScoreIndicator is a Sprite that displays the current score of the game on the screen.
 *
 * @author Tomer Berenstein tomerbrotem@gmail.com
 * @version 19.0.2
 * @since 2023 -01-17
 */
public class ScoreIndicator implements Sprite {
    private static final int X_POSITION = 350;
    private static final int HEIGHT = 17;
    private static final int FONT = 17;
    private Counter score;

    /**
     * Constructor for ScoreIndicator.
     *
     * @param cScore The initial score
     */
    public ScoreIndicator(Counter cScore) {
        this.score = cScore;

    }

    /**
     * Method to draw the ScoreIndicator on the screen.
     *
     * @param d The surface to draw on
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.gray);
        d.setColor(Color.BLACK);
        String s = "Score: " + this.score.getValue();
        d.drawText(X_POSITION, HEIGHT, s, FONT);

    }

    /**
     * Method to do nothing, but is implemented from the Sprite interface.
     */
    @Override
    public void timePassed() {

    }
}