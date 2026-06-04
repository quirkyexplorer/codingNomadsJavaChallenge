package labs_examples.multi_threading.examples;

//sequential pipeline

// Use a synchronized block to control access to SumArray.
class SumArray2 {
    private int sum;
    int sumArray2(int nums[]) {
        sum = 0; // reset sum
        for(int i=0; i<nums.length; i++) {
            sum += nums[i];
            System.out.println("Running total for " +
                    Thread.currentThread().getName() +
                    " is " + sum);
            try {
                Thread.sleep(400); // allow task-switch
            }
            catch(InterruptedException exc) {
                System.out.println("Thread interrupted.");
            }
        }
        return sum;
    }
}

class gfMyThread7 implements Runnable {
    Thread thrd;
    // using a new SumArray2
    static SumArray2 sa = new SumArray2();
    int a[];
    int answer;
    // Construct a new thread.
    gfMyThread7(String name, int nums[], int priority) {
        thrd = new Thread(this, name);
        thrd.setPriority(priority);
        a = nums;
        thrd.start(); // start the thread

    }
    // Begin execution of new thread.
    public void run() {
        int sum;
        System.out.println(thrd.getName() + " starting.");
        // synchronize calls to sumArray()
        synchronized(sa) {
            answer = sa.sumArray2(a);
            System.out.println("Sum for " + thrd.getName() +
                    " is " + answer);
            System.out.println(thrd.getName() + " terminating.");

        }
  //      answer = sa.sumArray2(a);

    }
}

// may have to rename to "Sync"
class Sync2 {
    public static void main(String args[]) {
        int a[] = {1, 2, 3, 4, 5};
        int b[] = {5, 6, 7, 8, 9};
        gfMyThread7 mt1 = new gfMyThread7("Child #1", a, 1);
        gfMyThread7 mt2 = new gfMyThread7("Child #2", a, 10);
        try {
            mt1.thrd.join();
            // here main thread is waiting for mt1 to finish runningn
            mt2.thrd.join();
        } catch(InterruptedException exc) {
            System.out.println("Main thread interrupted.");
        }
    }
}
