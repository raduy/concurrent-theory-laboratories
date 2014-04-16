package pl.agh.edu.tw.lab03;

import org.apache.log4j.Logger;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class Runner {
    private final static Logger LOGGER = Logger.getLogger(Runner.class.getCanonicalName());
    private static final Random random = new Random();
    private final int runningTimeInSec;
    private final int countOfConsumers;
    private final int countOfProducers;
    private ExecutorService executorService;
    private final BufferQueue<Integer> bufferQueue;

    public Runner(int runningTimeInSec, int countOfConsumers, int countOfProducers, int bufferSize) {
        this.runningTimeInSec = runningTimeInSec;
        this.countOfConsumers = countOfConsumers;
        this.countOfProducers = countOfProducers;
        this.bufferQueue = new BufferQueue<>(bufferSize);
        this.executorService = Executors.newCachedThreadPool();
    }

    private void submitProducers() {
        for (int i = 0; i < countOfProducers; i++) {
            executorService.submit(new SynchronousProducer(bufferQueue, random.nextInt(3000), random.nextInt(3000)));
        }
    }

    private void submitConsumers() {
        for (int i = 0; i < countOfConsumers; i++) {
            executorService.submit(new SynchronousConsumer(bufferQueue, random.nextInt(3000), random.nextInt(3000)));
        }
    }

    public void showtime() {
        try {
            submitProducers();
            submitConsumers();

            TimeUnit.SECONDS.sleep(runningTimeInSec);
            executorService.shutdown();
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Runner(100, 20, 20, 20).showtime();
    }
}
