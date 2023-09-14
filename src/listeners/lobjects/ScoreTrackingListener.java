// 209625946 Tomer Berenstein
package listeners.lobjects;

import listeners.lhelpers.Counter;
import listeners.lhelpers.HitListener;
import physics.Ball;
import physics.Block;

/**
 * A class that represents a ScoreTrackingListener.
 * ScoreTrackingListener is a HitListener that is responsible for tracking and increasing the game score
 * whenever a block is hit by a ball.
 *
 * @author Tomer Berenstein tomerbrotem@gmail.com
 * @version 19.0.2
 * @since 2023 -01-17
 */
public class ScoreTrackingListener implements HitListener {
    private static final int HIT_SCORE = 5;
    private Counter currentScore;

    /**
     * Constructor for ScoreTrackingListener.
     *
     * @param scoreCounter The counter for the current game score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Method called when the listened-to Block is hit.
     * It increases the game score by a predefined amount (HIT_SCORE).
     *
     * @param beingHit The Block that was hit.
     * @param hitter   The Ball that hit the block.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(HIT_SCORE);
    }
}