// 209625946 Tomer Berenstein
package game.levels;

import biuoop.DrawSurface;
import sprites.Sprite;

import java.awt.Color;

/**
 * Background sprite for "Direct Hit" level.
 *
 * @author Tomer Berenstein tomerbrotem@gmail.com
 * @version 19.0.2
 * @since 2023-01-17
 */
public class DirectHitBackGround implements Sprite {
    public static final int CENTER_X = 400;
    public static final int CENTER_Y = 162;
    public static final int START_RADIUS = 60;
    public static final int END_RADIUS = 120;
    public static final int RADIUS_STEP = 30;
    public static final int LINE_LENGTH = 120;
    public static final int REC_Y = 25;


    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        int width = d.getWidth();
        int height = d.getHeight();
        d.fillRectangle(0, REC_Y, width, height);

        d.setColor(Color.BLUE);
        for (int radius = START_RADIUS; radius <= END_RADIUS; radius += RADIUS_STEP) {
            d.drawCircle(CENTER_X, CENTER_Y, radius);
        }

        d.drawLine(CENTER_X, CENTER_Y + RADIUS_STEP, CENTER_X, CENTER_Y + LINE_LENGTH);
        d.drawLine(CENTER_X + RADIUS_STEP, CENTER_Y, CENTER_X + LINE_LENGTH, CENTER_Y);
        d.drawLine(CENTER_X - RADIUS_STEP, CENTER_Y, CENTER_X - LINE_LENGTH, CENTER_Y);
        d.drawLine(CENTER_X, CENTER_Y - RADIUS_STEP, CENTER_X, CENTER_Y - LINE_LENGTH);
    }

    @Override
    public void timePassed() {

    }
}
