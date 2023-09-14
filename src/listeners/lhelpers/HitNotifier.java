// 209625946 Tomer Berenstein
package listeners.lhelpers;


/**
 * Interface for classes that can notify HitListeners of hit events.
 *
 * @author Tomer Berenstein tomerbrotem@gmail.com
 * @version 19.0.2
 * @since 2023 -01-17
 */
public interface HitNotifier {
    /**
     * Registers a HitListener to receive hit events.
     *
     * @param hl The HitListener to add.
     */
    void addHitListener(HitListener hl);

    /**
     * Unregisters a HitListener, so it will no longer receive hit events.
     *
     * @param hl The HitListener to remove.
     */
    void removeHitListener(HitListener hl);
}