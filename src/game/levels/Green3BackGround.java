// 209625946 Tomer Berenstein
package game.levels;

import biuoop.DrawSurface;
import sprites.Sprite;

import java.awt.Color;

/**
 * Background sprite for "Green3" level.
 *
 * @author Tomer Berenstein tomerbrotem@gmail.com
 * @version 19.0.2
 * @since 2023-01-17
 */
public class Green3BackGround implements Sprite {
    public static final int WINDOW_START_X = 75;
    public static final int WINDOW_START_Y = 460;
    public static final int WINDOW_ROWS = 5;
    private static final int WINDOW_COLUMNS = 5;
    private static final int WINDOW_WIDTH = 10;
    private static final int WINDOW_HEIGHT = 25;
    private static final int WINDOW_ROW_SPACING = 32;
    private static final int WINDOW_COLUMN_SPACING = 18;
    private static final int REC_Y = 25;

    @Override
    public void drawOn(DrawSurface d) {
        // Define Colors
        Color greenBackground = new Color(42, 130, 21);
        Color towerColor = new Color(78, 74, 73);
        Color circleColorOuter = new Color(216, 172, 102);
        Color circleColorInner = new Color(225, 73, 14);
        Color circleColorCenter = Color.WHITE;
        Color baseColor = new Color(62, 58, 57);
        Color rootColor = new Color(46, 42, 41);
        Color windowsColor = Color.WHITE;

        // Background
        d.setColor(greenBackground);
        d.fillRectangle(0, REC_Y, d.getWidth(), d.getHeight());

        // Tower
        d.setColor(towerColor);
        d.fillRectangle(110, 200, 10, 200);

        // Circles on the Tower
        d.setColor(circleColorOuter);
        d.fillCircle(115, 200, 13);
        d.setColor(circleColorInner);
        d.fillCircle(115, 200, 9);
        d.setColor(circleColorCenter);
        d.fillCircle(115, 200, 4);

        // Base
        d.setColor(baseColor);
        d.fillRectangle(95, 400, 40, 210);

        // Root
        d.setColor(rootColor);
        d.fillRectangle(65, 450, 100, 200);

        // Windows
        d.setColor(windowsColor);
        for (int row = 0; row < WINDOW_ROWS; ++row) {
            for (int column = 0; column < WINDOW_COLUMNS; ++column) {
                int x = WINDOW_START_X + column * WINDOW_COLUMN_SPACING;
                int y = WINDOW_START_Y + row * WINDOW_ROW_SPACING;
                d.fillRectangle(x, y, WINDOW_WIDTH, WINDOW_HEIGHT);
            }
        }
    }

    @Override
    public void timePassed() {

    }
}
