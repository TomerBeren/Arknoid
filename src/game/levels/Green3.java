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
 * The Green3 class implements the LevelInformation interface to define the level information for the "Green3"
 * level.
 *
 * @author Tomer Berenstein tomerbrotem@gmail.com
 * @version 19.0.2
 * @since 2023-01-17
 */
public class Green3 implements LevelInformation {
    public static final int BALL_SPEED = 8;
    public static final int START_BALL_ANGLE = -45;
    public static final int END_BALL_ANGLE = 45;
    private static final int BLOCK_COUNT = 10;
    private static final int BLOCK_START_Y = 150;
    private static final int BLOCK_WIDTH = 54;
    private static final int BLOCK_HEIGHT = 29;
    private static final int BLOCK_START_X = 721;
    private static final int NUM_BALLS = 2;
    private static final int PADDLE_SPEED = 9;
    private static final int PADDLE_WIDTH = 85;
    private static final String LEVEL_NAME = "Green 3";

    @Override
    public int numberOfBalls() {
        return NUM_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballsVelocity = new ArrayList<>();
        ballsVelocity.add(Velocity.fromAngleAndSpeed(START_BALL_ANGLE, BALL_SPEED));
        ballsVelocity.add(Velocity.fromAngleAndSpeed(END_BALL_ANGLE, BALL_SPEED));
        return ballsVelocity;
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
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        int y = BLOCK_START_Y;
        int number = BLOCK_COUNT;
        //Adding the blocks in descending order.
        addMultipleBlocks(number--, y, Color.GRAY, blocks);
        y = y + BLOCK_HEIGHT;

        addMultipleBlocks(number--, y, Color.RED, blocks);
        y = y + BLOCK_HEIGHT;

        addMultipleBlocks(number--, y, Color.YELLOW, blocks);
        y = y + BLOCK_HEIGHT;

        addMultipleBlocks(number--, y, Color.BLUE, blocks);
        y = y + BLOCK_HEIGHT;

        addMultipleBlocks(number, y, Color.WHITE, blocks);

        return blocks;
    }

    /**
     * Adds multiple blocks with the given parameters.
     *
     * @param num    the number of blocks to add
     * @param y      the y-coordinate of the blocks
     * @param color  the color of the blocks
     * @param blocks the list to add the blocks to
     */
    public void addMultipleBlocks(int num, int y, Color color, java.util.List<Block> blocks) {
        for (int i = 0; i < num; i++) {
            Block b = new Block(new Rectangle(
                    new Point(BLOCK_START_X - (i * BLOCK_WIDTH), y), BLOCK_WIDTH, BLOCK_HEIGHT, color));
            blocks.add(b);
        }
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }

    @Override
    public Sprite getBackground() {
        return new Green3BackGround();
    }

    @Override
    public Color getColor() {
        return new Color(42, 130, 21);
    }
}
