package pl.agh.edu.tw.lab01.simple;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class SimpleRunner implements Runnable {

    @Override
    public void run() {
        System.out.println("New thread implementing Runnable interface");
    }
}
