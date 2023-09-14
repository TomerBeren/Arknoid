// 209625946 Tomer Berenstein
package sprites;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The LevelIndicator class represents a sprite that displays the current level name on the screen.
 *
 * @author Tomer Berenstein tomerbrotem@gmail.com
 * @version 19.0.2
 * @since 2023-01-17
 */
public class LevelIndicator implements Sprite {
    public static final int X_POSITION = 500;
    public static final int HEIGHT = 17;
    public static final int FONT = 17;
    private String level;

    /**
     * Constructor for LevelIndicator.
     *
     * @param level The current level.
     */
    public LevelIndicator(String level) {
        this.level = level;

    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.gray);
        d.setColor(Color.BLACK);
        String s = "Level Name: " + this.level;
        d.drawText(X_POSITION, HEIGHT, s, FONT);
    }

    @Override
    public void timePassed() {

    }
}
