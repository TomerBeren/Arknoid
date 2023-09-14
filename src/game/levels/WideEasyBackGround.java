// 209625946 Tomer Berenstein
package game.levels;

import biuoop.DrawSurface;
import sprites.Sprite;

import java.awt.Color;

/**
 * Background sprite for "Wide Easy" level.
 *
 * @author Tomer Berenstein tomerbrotem@gmail.com
 * @version 19.0.2
 * @since 2023-01-17
 */
public class WideEasyBackGround implements Sprite {
    public static final int CENTER_X = 150;
    public static final int CENTER_Y = 150;
    public static final int NUM_LINES = 100;
    public static final int STAR_X = 25;
    public static final int END_X = 775;
    public static final int END_Y = 250;
    public static final int REC_Y = 25;
    public static final int OUTER_CIRCLE_RAD = 60;
    public static final int BETWEEN_CIRCLE_RAD = 50;
    public static final int INNER_CIRCLE_RAD = 40;

    @Override
    public void drawOn(DrawSurface d) {
        // Filling background
        d.setColor(Color.white);
        d.fillRectangle(0, REC_Y, d.getWidth(), d.getHeight());

        // Drawing radial lines
        d.setColor(new Color(249, 245, 180));
        for (int i = NUM_LINES; i > 0; --i) {
            int x = (END_X - STAR_X) / NUM_LINES * i;
            d.drawLine(CENTER_X, CENTER_Y, x, END_Y);
        }

        // Drawing concentric circles
        int[] radii = {OUTER_CIRCLE_RAD, BETWEEN_CIRCLE_RAD, INNER_CIRCLE_RAD};
        Color[] colors = {
                new Color(239, 245, 159),
                new Color(238, 236, 99),
                new Color(249, 245, 33)
        };

        for (int i = 0; i < radii.length; i++) {
            d.setColor(colors[i]);
            d.fillCircle(CENTER_X, CENTER_Y, radii[i]);
        }

    }

    @Override
    public void timePassed() {

    }
}
