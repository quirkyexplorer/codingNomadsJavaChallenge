package labs_examples.multi_threading.labs;
/**
 * Multithreading Exercise 6:
 *
 *      Write a program that will print 1-100 sequentially from at least two distinct threads. For instance, thread1 will
 *      print "1", then thread2 will print "2", then thread1 will print "3", then thread2 will print "4" and so on.
 */
class Excercise_06 {
    public static void main(String[] args) {
        Counter c = new Counter();
        boolean t1 = true;
        boolean t2 = false;
        // thread 1
        MyCountThread thread1 = new MyCountThread("thread1", c );
        // thread 2
        MyCountThread thread2 = new MyCountThread("thread2", c);
    }
}
class Counter {
    int counter;
    String curthread;
    public Counter() {
        this.counter = 0;
        curthread = "thread1";
    }
    public synchronized void increment() {

        while (!curthread.equals(Thread.currentThread().getName())) {
            try {
                wait();
            }
            catch(InterruptedException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        counter++;
        System.out.println(getCounter() + " from thread: " + curthread);
        curthread = curthread.equals("thread1")? "thread2" : "thread1";
        notify();
    }
    public int getCounter() {
        return counter;
    }
}

class MyCountThread implements Runnable {
    Thread t;
    Counter c;

    public MyCountThread(String name, Counter c ) {
        t = new Thread(this, name);
        this.c = c;

        t.start();
    }
    @Override
    public void run() {
        int i = 0;
        while(i < 50)  {
            c.increment();
            i++;
        }
    }
}

