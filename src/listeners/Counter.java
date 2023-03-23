//208001677 Shahar Moshonov
package listeners;

/**
 * Counter class- has reference of int- can increase\decrease the counter,
 * can return the value of the counter.
 */
public class Counter {
    private int count;

    /**
     * constructor with configurables - int count.
     * @param number - int.
     */
    public Counter(int number) {
        this.count = number;
    }

    /**
     * add number to current count.
     * @param number to add.
     */
    public void increase(int number) {
        count = count + number;
    }

    /**
     * subtract number from current count.
     * @param number to subtract.
     */
    void decrease(int number) {
        count = count - number;
    }

    /**
     * @return current count.
     */
    public int getValue() {
        return count;
    }
}