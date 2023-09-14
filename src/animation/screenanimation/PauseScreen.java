// 209625946 Tomer Berenstein
package animation.screenanimation;

/**
 * The PauseScreen class is a pause screen animation that extends ScreenAnimation and allows the player to resume
 * the game by pressing the space key.
 *
 * @author Tomer Berenstein tomerbrotem@gmail.com
 * @version 19.0.2
 * @since 2023-01-17
 */
public class PauseScreen extends ScreenAnimation {
    @Override
    public String textOutput() {
        return "paused -- press space to continue";
    }

}