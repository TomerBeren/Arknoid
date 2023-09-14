// 209625946 Tomer Berenstein
package animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * The AnimationRunner class is responsible for running a given animation.
 *
 * @author Tomer Berenstein tomerbrotem@gmail.com
 * @version 19.0.2
 * @since 2023-01-17
 */
public class AnimationRunner {
    public static final int MILLI_SECONDS = 1000;
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * Creates a new AnimationRunner.
     *
     * @param gui             The GUI that the animation should be displayed on.
     * @param framesPerSecond The number of frames that should be shown per second.
     * @param sleeper         The Sleeper object to control the timing of the animation.
     */
    public AnimationRunner(GUI gui, int framesPerSecond, Sleeper sleeper) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
        this.sleeper = sleeper;
    }

    /**
     * Returns the width of the GUI window.
     *
     * @return the width of the GUI window.
     */
    public int getGameWidth() {
        return gui.getDrawSurface().getWidth();
    }

    /**
     * Returns the height of the GUI window.
     *
     * @return the height of the GUI window.
     */
    public int getGameHeight() {
        return gui.getDrawSurface().getHeight();
    }

    /**
     * Runs the provided Animation.
     * It will keep looping the animation until it should stop, based on the Animation's logic.
     *
     * @param animation The Animation to run.
     */
    public void run(Animation animation) {
        // Calculates the milliseconds per frame.
        int millisecondsPerFrame = MILLI_SECONDS / this.framesPerSecond;

        //shows frames on the screen until the animation should stop.
        while (!animation.shouldStop()) {

            // the start time we start from every frame.
            long startTime = System.currentTimeMillis();
            DrawSurface d = this.gui.getDrawSurface();
            animation.doOneFrame(d);
            this.gui.show(d);

            // Sleeps the program in the remaining time.
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }

    }
}
