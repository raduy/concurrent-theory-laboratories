package pl.agh.edu.tw.counter;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class NonThreadSaveCounter implements ICounter {
    private int counter;

    public NonThreadSaveCounter() {}

    public NonThreadSaveCounter(int counter) {
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
                "pl.agh.edu.tw.counter=\t\t\t" + counter +
                '}';
    }
}
