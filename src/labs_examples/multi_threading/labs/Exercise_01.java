package labs_examples.multi_threading.labs;



/**
 * Multithreading Exercise 1:
 *
 *      1: Create an application that starts a Thread by implementing the Runnable interface
 *      2: Demonstrate at least two distinct ways of initiating a Thread using the Runnable you just created
 *
 */

class Exerise_01 {

    public static void main( String[] args) {
        MyThread t = new MyThread("Daniel");

        Thread t2 = new Thread(new MyThread2(), "Jennifer");
        t2.start();

        MyThread2 run = new MyThread2();

        Thread t3 = new Thread(run, "Andrea");
        t3.start();

        new Thread(new MyThread2(), "Felicia").start();
    }
}



class MyThread2 implements Runnable {


    @Override
    public void run() {
        try {
            for(int i = 0; i < 5; i++) {
                Thread.sleep(400);
                System.out.println("printing " +   Thread.currentThread().getName());
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyThread implements Runnable {

    Thread t;

    public MyThread (String name) {
        t = new Thread(this, name);
        t.start();
    }

    @Override
    public void run() {
        try {
            for(int i = 0; i < 5; i++) {
                Thread.sleep(400);
                System.out.println("printing " + t.getName());
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
