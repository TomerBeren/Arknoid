// 209625946 Tomer Berenstein
package game.levels;

import game.LevelInformation;
import geometry.Point;
import geometry.Rectangle;
import physics.Block;
import physics.Velocity;
import sprites.Sprite;


import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The WideEasy class implements the LevelInformation interface to define the level information for the "WideEasy"
 * level.
 *
 * @author Tomer Berenstein tomerbrotem@gmail.com
 * @version 19.0.2
 * @since 2023-01-17
 */
public class WideEasy implements LevelInformation {
    public static final double SPEED = 8.0;
    public static final int START_ANGLE = 50;
    public static final int END_ANGLE = -50;
    private static final int STEP = -10;
    public static final int Y_COORDINATE = 250;
    public static final int BLOCK_HEIGHT = 25;
    public static final int BLOCK_WIDTH = 50;
    private static final int BLOCK_START_X = 25;
    public static final int NUM_BALLS = 10;
    public static final int PADDLE_SPEED = 2;
    public static final int PADDLE_WIDTH = 600;
    private static final String LEVEL_NAME = "Wide Easy";

    @Override
    public int numberOfBalls() {
        return NUM_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();

        for (int angle = START_ANGLE; angle >= END_ANGLE; angle += STEP) {
            if (angle == 0) {
                continue;
            }
            velocities.add(Velocity.fromAngleAndSpeed(angle, SPEED));
        }

        return velocities;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();

        Color[] colors = {Color.RED, Color.RED, Color.ORANGE, Color.ORANGE, Color.YELLOW, Color.YELLOW, Color.GREEN,
                Color.GREEN, Color.GREEN, Color.BLUE, Color.BLUE, Color.PINK, Color.PINK, Color.CYAN, Color.CYAN};

        for (int i = 0; i < colors.length; i++) {
            int x = BLOCK_START_X + i * BLOCK_WIDTH;
            blocks.add(new Block(new Rectangle(new Point(x, Y_COORDINATE), BLOCK_WIDTH, BLOCK_HEIGHT, colors[i])));
        }

        return blocks;
    }

    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    @Override
    public String levelName() {
        return LEVEL_NAME;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }

    @Override
    public Sprite getBackground() {
        return new WideEasyBackGround();
    }

    @Override
    public Color getColor() {
        return Color.white;
    }

}
