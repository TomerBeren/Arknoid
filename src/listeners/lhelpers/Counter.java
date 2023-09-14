// 209625946 Tomer Berenstein
package listeners.lhelpers;

/**
 * A simple counter class. Can increase, decrease, and return its current value.
 *
 * @author Tomer Berenstein tomerbrotem@gmail.com
 * @version 19.0.2
 * @since 2023 -01-17
 */
public class Counter {
    private int counter;

    /**
     * Default constructor that initializes the counter to zero.
     */
    public Counter() {
        this.counter = 0;
    }

    /**
     * Increases the counter by a specific amount.
     *
     * @param number the amount to increase the counter by.
     */
    public void increase(int number) {
        this.counter += number;
    }


    /**
     * Decreases the counter by a specific amount.
     *
     * @param number the amount to decrease the counter by.
     */
    public void decrease(int number) {
        this.counter -= number;
    }

    /**
     * Gets the current value of the counter.
     *
     * @return the current count.
     */
    public int getValue() {
        return this.counter;
    }
}