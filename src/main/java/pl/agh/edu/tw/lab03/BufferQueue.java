package pl.agh.edu.tw.lab03;


import org.apache.log4j.Logger;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Buffer with Queue structure providing access to random sized chunks for producers and consumers.
 *
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class BufferQueue<T> {
    private final static Logger LOGGER = Logger.getLogger(BufferQueue.class.getCanonicalName());
    private final Queue<T> buffer;
    private final int size;
    private final Lock lock = new ReentrantLock();
    private boolean isBigProducerPresent;
    private boolean isBigConsumerPresent;
    private Condition bigProducer = lock.newCondition();
    private Condition bigConsumer = lock.newCondition();

    private Condition restOfProducers = lock.newCondition();
    private Condition restOfConsumers = lock.newCondition();


    public BufferQueue(int size) {
        this.size = size;
        this.buffer = new ArrayDeque<>();
    }

    public void produce(List<T> elements) {
        lock.lock();
        try {
            while (isBigProducerPresent) {
                restOfProducers.await();
            }

            while (elements.size() > size - buffer.size()) {
                isBigProducerPresent = true;
                bigProducer.await();
            }
            buffer.addAll(elements);
            isBigProducerPresent = false;

            restOfProducers.signal();
            bigConsumer.signal();

        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    public List<T> consume(int chunkSize) {

        List<T> result = new ArrayList<>(chunkSize);

        lock.lock();
        try {
            while (isBigConsumerPresent) {
                restOfConsumers.await();
            }

            while (buffer.size() < chunkSize) {
                isBigConsumerPresent = true;
                bigConsumer.await();
            }

            for (int i = 0; i < chunkSize; i++) {
                result.add(buffer.poll());
            }

            isBigConsumerPresent = false;

            restOfConsumers.signal();
            bigProducer.signal();

        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage());
        } finally {
            lock.unlock();
        }

        return result;
    }

    public int getMaxSize() {
        return size;
    }
}
