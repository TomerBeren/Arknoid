// 209625946 Tomer Berenstein
package game;

import animation.Animation;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.KeyPressStoppableAnimation;
import animation.screenanimation.PauseScreen;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Point;
import geometry.Rectangle;
import listeners.lhelpers.Counter;
import listeners.lobjects.BallRemover;
import listeners.lobjects.BlockRemover;
import listeners.lobjects.ScoreTrackingListener;
import physics.Ball;
import physics.Paddle;
import physics.Block;
import physics.Collidable;
import sprites.LevelIndicator;
import sprites.ScoreIndicator;

import sprites.Sprite;
import sprites.SpriteCollection;

import java.awt.Color;


/**
 * The GameLevel class represents a game level in the game.
 * It manages the game's sprites, environment, and various gameplay elements such as balls, blocks, and scores.
 *
 * @author Tomer Berenstein tomerbrotem@gmail.com
 * @version 19.0.2
 * @since 2023 -01-17
 */
public class GameLevel implements Animation {
    private static final int COUNT_DURATION = 2;
    private static final int START_COUNT = 3;
    private static final int BALL_Y_MARGIN = 10;
    private static final int PADDLE_Y_MARGIN = 35;
    private static final int MARGIN_BLOCK_Y = 18;
    private static final int PADDLE_HEIGHT = 20;
    private static final int BALL_RADIUS = 5;
    private static final int MARGIN_THICKNESS = 25;
    private static final int SCORE_BONUS = 100;
    private BallRemover ballRemover;
    private BlockRemover blockRemover;
    private Counter blocksCounter;
    private Counter ballsCounter;
    private Counter scoreCounter;
    private ScoreTrackingListener scoreTracker;
    private KeyboardSensor keyboardSensor;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private AnimationRunner runner;
    private boolean running;
    private LevelInformation levelInfo;
    private Paddle paddle;


    /**
     * Constructs a new GameLevel.
     *
     * @param levelInfo    the level information for this game level.
     * @param runner       the animation runner for this game level.
     * @param keyboard     the keyboard sensor for this game level.
     * @param scoreCounter the score counter for this game level.
     */
    public GameLevel(LevelInformation levelInfo, AnimationRunner runner, KeyboardSensor keyboard,
                     Counter scoreCounter) {
        this.levelInfo = levelInfo;
        this.keyboardSensor = keyboard;
        this.runner = runner;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.scoreCounter = scoreCounter;
        this.ballsCounter = new Counter();
        this.blocksCounter = new Counter();
        this.blockRemover = new BlockRemover(this, this.blocksCounter);
        this.ballRemover = new BallRemover(this, this.ballsCounter);
        this.scoreTracker = new ScoreTrackingListener(this.scoreCounter);
    }

    /**
     * removes the given Collidable from the environment.
     *
     * @param c the Collidable to be removed
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * removes the given Sprite from the environment.
     *
     * @param s the Sprite to be removed
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Adds the given collidable to the environment.
     *
     * @param c the collidable to be added
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Adds the given sprite to the SpriteCollection.
     *
     * @param s the sprite to be added
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initializes the game by creating the balls, paddle, and blocks.
     */
    public void initialize() {
        createPaddle();
        createBalls();
        createMarginBlocks();
        addBlocks();
        addIndicators();
    }

    /**
     * Adds a ScoreIndicator to the game.
     * The ScoreIndicator displays the current game score on the screen.
     */
    public void addIndicators() {
        ScoreIndicator indicator = new ScoreIndicator(this.scoreCounter);
        this.addSprite(indicator);
        LevelIndicator level = new LevelIndicator(this.levelInfo.levelName());
        this.addSprite(level);
    }

    /**
     * Creates a Paddle object and adds it to the game.
     */
    private void createPaddle() {
        // Calculate the initial position of the paddle based on the game width and paddle width
        double paddleX = (double) this.runner.getGameWidth() / 2 - (double) this.levelInfo.paddleWidth() / 2;
        double paddleY = this.runner.getGameHeight() - PADDLE_Y_MARGIN;

        // Create the paddle
        this.paddle = new Paddle(new Rectangle(new Point(paddleX, paddleY), this.levelInfo.paddleWidth(),
                PADDLE_HEIGHT, Color.ORANGE), this.levelInfo.paddleSpeed(), this.keyboardSensor);

        // Set the range of the paddle's movement within the game area and add it to the game.
        paddle.setRange(MARGIN_THICKNESS, this.runner.getGameWidth() - MARGIN_THICKNESS);
    }

    /**
     * Creates a Ball object with a random velocity.
     */
    private void createBalls() {
        // Calculate the initial position of the balls
        double ballX = this.paddle.getX() + (double) (this.levelInfo.paddleWidth() / 2);
        double ballY = this.paddle.getY() - BALL_Y_MARGIN;
        Point center = new Point(ballX, ballY);

        // Increase the balls counter
        this.ballsCounter.increase(this.levelInfo.numberOfBalls());

        // Create and initialize the balls
        for (int i = 0; i < this.levelInfo.numberOfBalls(); i++) {
            Ball ball = new Ball(center, BALL_RADIUS, Color.WHITE);
            ball.setVelocity(this.levelInfo.initialBallVelocities().get(i));
            ball.setGameEnvironment(this.environment);
            ball.addToGame(this);
        }
        //add the paddle after the balls so that they don't enter the paddle when approaching it.
        paddle.addToGame(this);
    }

    /**
     * Creates margin blocks and adds them to the game.
     */
    private void createMarginBlocks() {
        Block upMargin = createMarginBlock(new Point(0, MARGIN_BLOCK_Y), this.runner.getGameWidth(),
                MARGIN_THICKNESS);
        upMargin.addToGame(this);
        Block deathMargin = createMarginBlock(new Point(0, this.runner.getGameHeight() - 2),
                this.runner.getGameWidth(), 0);
        deathMargin.addToGame(this);
        deathMargin.addHitListener(this.ballRemover);
        Block leftMargin = createMarginBlock(new Point(0, MARGIN_BLOCK_Y), MARGIN_THICKNESS - 1,
                this.runner.getGameHeight());
        leftMargin.addToGame(this);
        Block rightMargin = createMarginBlock(new Point(this.runner.getGameWidth() - MARGIN_THICKNESS,
                MARGIN_BLOCK_Y), MARGIN_THICKNESS, this.runner.getGameHeight());
        rightMargin.addToGame(this);
    }

    /**
     * Creates a margin Block object.
     *
     * @param topLeft the top-left point of the Block
     * @param width   the width of the Block
     * @param height  the height of the Block
     * @return the created Block object
     */
    private Block createMarginBlock(Point topLeft, int width, int height) {
        return new Block(new Rectangle(topLeft, width, height, Color.GRAY));
    }

    /**
     * Adds Blocks as collidable objects and sprites to the game environment.
     */
    private void addBlocks() {
        //adds the blocks as a sprite collidable and there hit listeners.
        for (Block block : this.levelInfo.blocks()) {
            this.addCollidable(block);
            this.addSprite(block);
            block.addHitListener(this.blockRemover);
            block.addHitListener(this.scoreTracker);
        }
        // Increase the blocks counter by the number of blocks to remove for this level
        this.blocksCounter.increase(this.levelInfo.numberOfBlocksToRemove());
    }

    /**
     * Runs the game by starting the animation loop.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(COUNT_DURATION, START_COUNT, this.sprites, this.levelInfo));
        this.running = true;
        this.runner.run(this);
    }

    /**
     * Draws the game environment, including the background, all the collidables, and sprites.
     * Then it shows the game on the GUI.
     *
     * @param d The DrawSurface object on which the game should be drawn.
     */
    private void drawGame(DrawSurface d) {
        // Draw all the collidables and sprites
        this.environment.drawAllCollidable(d);
        this.sprites.drawAllOn(d);
    }


    @Override
    public void doOneFrame(DrawSurface d) {
        //draw the level and move the balls.
        this.levelInfo.getBackground().drawOn(d);
        drawGame(d);
        this.sprites.notifyAllTimePassed();
        // if "p" is pressed pause the game and show the pausescreen.
        if (this.keyboardSensor.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                    new PauseScreen()));
        }
        // Check for end game conditions and handle accordingly
        // If there are no blocks left, increase the score and close the GUI
        if (this.blocksCounter.getValue() == 0) {
            this.scoreCounter.increase(SCORE_BONUS);
            this.running = false;
        }
        // If there are no balls left, close the GUI
        if (this.ballsCounter.getValue() == 0) {
            this.running = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Checks if there are remaining balls in the game.
     *
     * @return {@code true} if there are remaining balls, {@code false} otherwise.
     */
    public boolean hasBallsRemaining() {
        return this.ballsCounter.getValue() > 0;

    }

    /**
     * Checks if there are remaining blocks in the game.
     *
     * @return {@code true} if there are remaining blocks, {@code false} otherwise.
     */
    public boolean hasBlocksRemaining() {
        return this.blocksCounter.getValue() > 0;
    }
}
