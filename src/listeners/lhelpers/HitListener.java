// 209625946 Tomer Berenstein
package listeners.lhelpers;

import physics.Ball;
import physics.Block;

/**
 * Interface for classes that want to be notified of hit events.
 *
 * @author Tomer Berenstein tomerbrotem@gmail.com
 * @version 19.0.2
 * @since 2023 -01-17
 */
public interface HitListener {
    /**
     * Method that is called whenever the beingHit object is hit by a ball.
     *
     * @param beingHit The Block that was hit.
     * @param hitter   The Ball that hit the block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}