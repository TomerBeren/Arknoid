// 209625946 Tomer Berenstein
package animation.screenanimation;


import listeners.lhelpers.Counter;

/**
 * This class represents an end screen for the game.
 * It extends the Screen class, and is displayed when the game ends.
 *
 * @author Tomer Berenstein tomerbrotem@gmail.com
 * @version 19.0.2
 * @since 2023-01-17
 */
public class EndScreen extends ScreenAnimation {
    private Counter scoreCounter;

    /**
     * Creates a new end screen with the given counter.
     *
     * @param scoreCounter The counter for the score in the game.
     */
    public EndScreen(Counter scoreCounter) {
        this.scoreCounter = scoreCounter;
    }

    @Override
    public String textOutput() {
        return "Game Over. Your score is: " + this.scoreCounter.getValue();
    }

}
