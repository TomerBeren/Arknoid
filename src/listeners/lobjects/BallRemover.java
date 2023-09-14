// 209625946 Tomer Berenstein
package listeners.lobjects;

import game.GameLevel;
import listeners.lhelpers.Counter;
import listeners.lhelpers.HitListener;
import physics.Ball;
import physics.Block;

/**
 * A class that represents a BallRemover.
 * BallRemover is a HitListener that is responsible for removing balls from the game
 * when they hit certain blocks.
 *
 * @author Tomer Berenstein tomerbrotem@gmail.com
 * @version 19.0.2
 * @since 2023 -01-17
 */
public class BallRemover implements HitListener {

    private Counter ballCounter;
    private GameLevel game;


    /**
     * Constructor for BallRemover.
     *
     * @param game        the game object
     * @param ballCounter ball's counter
     */
    public BallRemover(GameLevel game, Counter ballCounter) {
        this.game = game;
        this.ballCounter = ballCounter;

    }

    /**
     * Method called when the listened-to Block is hit.
     * It removes the ball from the game and decreases the count of balls.
     *
     * @param beingHit The Block that was hit.
     * @param hitter   The Ball that hit the block.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.ballCounter.decrease(1);
    }


}