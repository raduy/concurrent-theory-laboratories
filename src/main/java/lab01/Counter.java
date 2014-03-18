package lab01;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class Counter {
    private int counter;

    public Counter() {}

    public Counter(int counter) {
        this.counter = counter;
    }

    public void increment() {
        counter++;
    }

    public void decrement() {
        counter--;
    }

    @Override
    public String toString() {
        return "Counter{" +
                "counter=" + counter +
                '}';
    }
}
