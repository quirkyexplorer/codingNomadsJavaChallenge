package labs_examples.multi_threading.labs;
/**
 * Multithreading Exercise 4:
 *
 *      Demonstrate the use of a synchronized block and a synchronized method - ensure that the synchronization is
 *      working as expected
 */
class Excercise_04 {
    public static void main(String[] args) {
        MythreadEx4 myThread = new MythreadEx4();
        MythreadEx4 myThread2 = new MythreadEx4("Daniel");
        MythreadEx4 myThread3 = new MythreadEx4("Luis", true);
        // synchronization
        SharedCounter ct = new SharedCounter();
        SyncRun sr  = new SyncRun("thread 1", ct);
        SyncRun sr2  = new SyncRun("thread 2", ct);
        SyncRun sr3 = new SyncRun("thread 3", ct);
    }
}
class SharedCounter {
    private int counter = 0;
    public SharedCounter() {
    }
    public void increment() {
        this.counter++;
    }
    public int getCounter() {
        return this.counter;
    }
    public void setCounter() {
        this.counter = 0;
    };
}
class SyncRun extends Thread {
    SharedCounter ct;
    String name;
    public SyncRun(String name, SharedCounter ct) {
        this.ct = ct;
        this.name = name;
        start();
    }
    public void run() {

        synchronized (ct) {
            for (int i = 0; i < 5; i++) {
                ct.increment();
                System.out.println("counter is: " + ct.getCounter() + " " + this.name + " " + getName() + " updated resource");
            }
            System.out.println("Thread finished" + this.name + "total count: " + ct.getCounter());
            ct.setCounter();
        }
    }
}
// demonstrates synchronization
class MythreadEx4 extends Thread {
    public MythreadEx4 (){
        super("Jonathan");
        start();
    }
    public MythreadEx4 (String name){
        super(name);
        start();
    }
    public MythreadEx4 (String name, boolean bool){
        super(name);
        if (bool == true) {
            start();
        }
    }
    public void run() {
        try {
            for(int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(400);
                System.out.println("printing " + getName());
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


