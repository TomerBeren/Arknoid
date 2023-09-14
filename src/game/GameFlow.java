// 209625946 Tomer Berenstein
package game;

import animation.AnimationRunner;
import animation.screenanimation.EndScreen;
import animation.KeyPressStoppableAnimation;
import animation.screenanimation.WinScreen;
import biuoop.KeyboardSensor;
import listeners.lhelpers.Counter;


import java.util.List;

/**
 * Controls the flow of the game, running each level in sequence.
 *
 * @author Tomer Berenstein tomerbrotem@gmail.com
 * @version 19.0.2
 * @since 2023-01-17
 */
public class GameFlow {
    private final AnimationRunner animationRunner;
    private final KeyboardSensor keyboardSensor;
    private Counter scoreCounter;
    private boolean hasPlayerLost;


    /**
     * Constructor.
     *
     * @param ar animation runner
     * @param ks keyboard sensor
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
    }

    /**
     * Run the provided list of levels.
     *
     * @param levels a list of levels to run
     */
    public void runLevels(List<LevelInformation> levels) {
        scoreCounter = new Counter();
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, animationRunner, keyboardSensor, scoreCounter);
            level.initialize();

            // Run the level while there are still blocks and balls
            while (level.hasBlocksRemaining() && level.hasBallsRemaining()) {
                level.run();
            }

            // If no balls remain, the player lost a life
            if (!level.hasBallsRemaining()) {
                hasPlayerLost = true;
                break;
            }
        }
        //if the player lost show the endscreen if not show the winscreen.
        if (hasPlayerLost) {
            EndScreen gameOverScreen = new EndScreen(scoreCounter);
            KeyPressStoppableAnimation gameOverAnimation =
                    new KeyPressStoppableAnimation(keyboardSensor, KeyboardSensor.SPACE_KEY, gameOverScreen);
            animationRunner.run(gameOverAnimation);
        } else {
            WinScreen winGameScreen = new WinScreen(scoreCounter);
            KeyPressStoppableAnimation winGameAnimation =
                    new KeyPressStoppableAnimation(keyboardSensor, KeyboardSensor.SPACE_KEY, winGameScreen);
            animationRunner.run(winGameAnimation);
        }

    }


}
