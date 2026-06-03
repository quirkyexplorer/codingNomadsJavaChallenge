package labs_examples.multi_threading.labs;

/**
 * Multithreading Exercise 5:
 *
 *      Demonstrate the use of a wait() and notify()
 */

public class Exercise_05 {
    public static void main(String[] args) {
        SharedResource sharedResource
                = new SharedResource();

        // Producer thread
        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                sharedResource.produce(i);
            }
        }).start();

        // Consumer thread
        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                sharedResource.consume();
            }
        }).start();
    }
}


 class SharedResource {
    private int item;
    private boolean hasItem = false;

    public synchronized void produce(int newItem) {
        while (hasItem) {
            try {
                // Producer waits if the item is already present
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        item = newItem;
        hasItem = true;
        System.out.println("Produced: " + item);
        // Notify the consumer that a new item is available
        notify();
    }

    public synchronized void consume() {
        while (!hasItem) {
            try {
                // Consumer waits if there is no item
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Consumed: " + item);
        hasItem = false;
        // Notify the producer that the item has been consumed
        notify();
    }
}