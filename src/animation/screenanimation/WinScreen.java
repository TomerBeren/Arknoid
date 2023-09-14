// 209625946 Tomer Berenstein
package animation.screenanimation;


import listeners.lhelpers.Counter;

/**
 * The WinScreen class is a win screen animation that extends ScreenAnimation
 * and displays a message when the player wins the game.
 *
 * @author Tomer Berenstein tomerbrotem@gmail.com
 * @version 19.0.2
 * @since 2023-01-17
 */
public class WinScreen extends ScreenAnimation {
    private Counter scoreCounter;

    /**
     * Creates a new WinScreen with the given score counter.
     *
     * @param scoreCounter The counter for the player's score.
     */
    public WinScreen(Counter scoreCounter) {
        this.scoreCounter = scoreCounter;

    }

    @Override
    public String textOutput() {
        return "You Win! Your score is: " + this.scoreCounter.getValue();
    }

}
