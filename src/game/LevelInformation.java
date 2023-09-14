// 209625946 Tomer Berenstein
package game;

import physics.Block;
import physics.Velocity;
import sprites.Sprite;

import java.awt.Color;
import java.util.List;

/**
 * The LevelInformation interface represents the information of a game level.
 *
 * @author Tomer Berenstein tomerbrotem@gmail.com
 * @version 19.0.2
 * @since 2023 -01-17
 */
public interface LevelInformation {
    /**
     * Returns the number of balls in the level.
     *
     * @return The number of balls.
     */
    int numberOfBalls();

    /**
     * Returns a list of initial velocities for the balls.
     *
     * @return A list of initial velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * Returns the speed of the paddle.
     *
     * @return The paddle speed.
     */
    int paddleSpeed();

    /**
     * Returns the width of the paddle.
     *
     * @return The paddle width.
     */
    int paddleWidth();

    /**
     * Returns the name of the level.
     *
     * @return The level name.
     */
    String levelName();

    /**
     * Returns the blocks that make up this level.
     *
     * @return The blocks of the level.
     */
    List<Block> blocks();

    /**
     * Returns the number of blocks that need to be removed to complete the level.
     *
     * @return The number of blocks to remove.
     */
    int numberOfBlocksToRemove();

    /**
     * Returns the background sprite of the level.
     *
     * @return The background sprite.
     */
    Sprite getBackground();

    /**
     * Return the color of the levels background.
     *
     * @return The background color.
     */
    Color getColor();

}
