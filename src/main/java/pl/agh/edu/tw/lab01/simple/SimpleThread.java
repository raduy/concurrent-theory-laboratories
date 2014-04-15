package pl.agh.edu.tw.lab01.simple;

/**
 * @author Lukasz Raduj <raduj.lukasz@gmail.com>
 */
public class SimpleThread extends Thread {

    @Override
    public void run() {
        System.out.println("New thread extending thread class");
    }
}
