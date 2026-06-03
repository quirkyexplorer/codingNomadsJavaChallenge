package labs_examples.multi_threading.labs;

/**
 * Multithreading Exercise 2:
 *
 *      Create an application that creates a Thread using the Thread class
 */


  class Exercise_02 {

    public static void main(String[] args) {

        Thread t = new MyThreadEx2("Daniel's thread", 10);
//        t.start();

        // this one gives the responsibility to the child class of creating the thread

        MyThreadEx2 t2 = new MyThreadEx2("May's thread", 1);
        MyThreadEx2 t3 = new MyThreadEx2("Cass's thread", 5);

    }


}

class MyThreadEx2 extends Thread{

    public MyThreadEx2(String name, int priority) {
        super(name);
        setPriority(priority);
        start();
    }

    public void run() {
        try {
            for(int i = 0; i < 5; i++) {
                Thread.sleep(400);
                System.out.println("printing " +  getName());
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    };
}


