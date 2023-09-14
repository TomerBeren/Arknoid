// 209625946 Tomer Berenstein
package game.levels;


import game.LevelInformation;
import geometry.Point;
import geometry.Rectangle;
import physics.Block;
import physics.Velocity;
import sprites.Sprite;

import java.awt.Color;
import java.util.Collections;
import java.util.List;

/**
 * The DirectHit class implements the LevelInformation interface to define the level information for the "Direct Hit"
 * level.
 *
 * @author Tomer Berenstein tomerbrotem@gmail.com
 * @version 19.0.2
 * @since 2023-01-17
 */
public class DirectHit implements LevelInformation {
    public static final int NUM_BALLS = 1;
    public static final int PADDLE_SPEED = 5;
    public static final int PADDLE_WIDTH = 150;
    private static final String LEVEL_NAME = "Direct Hit";
    private static final int BALL_SPEED = 5;
    private static final int BLOCK_WIDTH = 30;
    private static final int BLOCK_HEIGHT = 30;
    private static final int BLOCK_X = 385;
    private static final int BLOCK_Y = 150;

    @Override
    public int numberOfBalls() {
        return NUM_BALLS;
    }


    @Override
    public List<Velocity> initialBallVelocities() {
        return List.of(Velocity.fromAngleAndSpeed(0.0, BALL_SPEED));
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
    public Sprite getBackground() {
        return new DirectHitBackGround();
    }


    @Override
    public List<Block> blocks() {
        Block block = new Block(new Rectangle(new Point(BLOCK_X, BLOCK_Y), BLOCK_WIDTH, BLOCK_HEIGHT, Color.RED));
        return Collections.singletonList(block);
    }


    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }

    @Override
    public Color getColor() {
        return Color.BLACK;
    }
}