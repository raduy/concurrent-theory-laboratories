package lab02;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class CounterWithSemaphore implements ICounter{
    private int value;
    private BinarySemaphore semaphore = new BinarySemaphore();

    @Override
    public void increment() {
        try {
            semaphore.down();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        value++;
        semaphore.up();
    }

    public void decrement() {
        try {
            semaphore.down();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        value--;
        semaphore.up();
    }

    @Override
    public String toString() {
        return "CounterWithSemaphore{" +
                "value=" + value +
                '}';
    }
}
