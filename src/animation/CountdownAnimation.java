// 209625946 Tomer Berenstein
package animation;

import biuoop.DrawSurface;
import game.LevelInformation;
import sprites.SpriteCollection;

import java.awt.Color;

/**
 * Displays a countdown from a given number over a specified duration.
 * Each number is displayed for a fraction of the total duration before switching to the next.
 *
 * @author Tomer Berenstein tomerbrotem@gmail.com
 * @version 19.0.2
 * @since 2023-01-17
 */
public class CountdownAnimation implements Animation {
    public static final int CENTER_X_MARGIN = 10;
    public static final int CENTER_Y_MARGIN = 80;
    public static final int FONT_SIZE = 50;
    private final double totalDuration;
    private final int startCount;
    private final SpriteCollection sprites;
    private final LevelInformation level;
    private boolean stopped;
    private long startTime;

    /**
     * Creates a new CountdownAnimation.
     *
     * @param totalDuration The total duration the animation should run.
     * @param startCount    The number from which to start the countdown.
     * @param sprites       The sprites of the game.
     * @param level         The current level.
     */
    public CountdownAnimation(double totalDuration, int startCount, SpriteCollection sprites, LevelInformation level) {
        this.totalDuration = totalDuration;
        this.startCount = startCount;
        this.sprites = sprites;
        this.level = level;
        this.stopped = false;
    }

    /**
     * The action to be performed in one frame.
     *
     * @param surface The surface to draw on.
     */
    @Override
    public void doOneFrame(DrawSurface surface) {
        // Initialize the start time if it hasn't been set.
        initializeStartTime();
        // Draw the background and sprites on the surface.
        level.getBackground().drawOn(surface);
        sprites.drawAllOn(surface);
        // Calculate the current count based on elapsed time.
        int currentCount = getCurrentCount();
        // Draw the countdown on the surface.
        drawCountdown(surface, currentCount);
        // Stop the animation if the countdown is finished.
        if (hasCountdownFinished()) {
            stopped = true;
        }
    }

    /**
     * Initialize the start time for the countdown.
     * If the start time is not set, then it is set to the current time.
     */
    private void initializeStartTime() {
        // If the start time isn't set, set it to the current time.
        if (startTime == 0) {
            startTime = System.currentTimeMillis();
        }
    }

    /**
     * Calculate the current count for the countdown.
     * This is based on the elapsed time since the start,
     * and the duration allocated for each count.
     *
     * @return the current count as an integer
     */
    private int getCurrentCount() {
        long elapsedTime = System.currentTimeMillis() - startTime;
        double durationPerCount = totalDuration / startCount;
        return (int) ((startCount + 1) - elapsedTime / (durationPerCount * 1000.0));
    }

    /**
     * Draw the countdown on the given surface.
     *
     * @param surface      The DrawSurface to draw the countdown on
     * @param currentCount The current count to draw
     */
    private void drawCountdown(DrawSurface surface, int currentCount) {
        // Set the color of the text based on the level's color.
        if (this.level.getColor() == Color.WHITE) {
            surface.setColor(Color.BLACK);
        } else {
            surface.setColor(Color.WHITE);
        }

        // Draw the countdown text on the surface at the calculated coordinates.
        String countdownText = Integer.toString(currentCount);
        int centerX = surface.getWidth() / 2 - CENTER_X_MARGIN;
        int centerY = surface.getHeight() / 2 + CENTER_Y_MARGIN;
        surface.drawText(centerX, centerY, countdownText, FONT_SIZE);
    }

    /**
     * Check if the countdown has finished.
     *
     * @return true if the elapsed time since the start is greater than the total duration and false otherwise.
     */
    private boolean hasCountdownFinished() {
        return System.currentTimeMillis() - startTime > totalDuration * 1000.0;
    }

    /**
     * Method to acknowledge if the animation should stop.
     *
     * @return If the animation should stop.
     */
    @Override
    public boolean shouldStop() {
        return stopped;
    }
}
