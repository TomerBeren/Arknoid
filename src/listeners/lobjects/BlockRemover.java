// 209625946 Tomer Berenstein
package listeners.lobjects;

import game.GameLevel;
import listeners.lhelpers.Counter;
import listeners.lhelpers.HitListener;
import physics.Ball;
import physics.Block;

/**
 * A class that represents a BlockRemover.
 * BlockRemover is a HitListener that is responsible for removing blocks from the game
 * and keeping track of the remaining number of blocks when they are hit by a ball.
 *
 * @author Tomer Berenstein tomerbrotem@gmail.com
 * @version 19.0.2
 * @since 2023 -01-17
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * Constructor for BlockRemover.
     *
     * @param game          The game object
     * @param removedBlocks The counter for removed blocks
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * Method called when the listened-to Block is hit.
     * It removes the block from the game, removes this as a listener from the block,
     * and decreases the count of remaining blocks.
     *
     * @param beingHit The Block that was hit.
     * @param hitter   The Ball that hit the block.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.game);
        beingHit.removeHitListener(this);
        this.remainingBlocks.decrease(1);
    }
}
