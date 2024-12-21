public class ThreadLifeCycle {
    public static void main(String[] args) {
        sharedResources sr = new sharedResources();

        // Create two threads targeting the shared synchronized resource
        Thread t1 = new Thread(() -> sr.access());
        Thread t2 = new Thread(() -> sr.access());
        Thread t3 = new Thread(() -> sr.access());
        System.out.println("State of t1 before start: " + t1.getState());
        System.out.println("State of t2 before start: " + t2.getState());
        System.out.println("State of t3 before start: " + t3.getState());

        t1.start();
        t2.start();
        t3.start();

        // Create and start t5, which will repeatedly check the "running" flag
        Thread t5 = new Thread(sr);
        t5.start();

        // Delay to ensure t1 gets the lock first
        try {
            t3.join();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        // Monitor the state of the threads every 100ms for 5000ms
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < 5000) {
            System.out.println("State of t1: " + t1.getState());
            System.out.println("State of t2: " + t2.getState());
            System.out.println("State of t3: " + t3.getState());
            System.out.println("State of t5: " + t5.getState()); // Monitor t5's state as well
            System.out.println("-----------------------------------");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Sleep interrupted");
            }
        }

        System.out.println("Loop ended after 5000 milliseconds.");

        // Call the close() method on sharedResources to signal t5 to stop
        System.out.println("Calling close() to stop t5...");
        sr.close();

        try {
            t5.join(); // Wait for t5 to terminate
        } catch (InterruptedException e) {
            System.out.println("Thread t5 interrupted while waiting for join");
        }

        System.out.println("t5 has stopped.");

        Thread t4 = new Thread(() -> {
            try {
                synchronized (Thread.currentThread()) {
                    Thread.currentThread().wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t4.start();
        System.out.println(t4.getState()); // Output: WAITING
    }
}

class sharedResources implements Runnable {
    private volatile boolean running = true; // Volatile to ensure visibility across threads

    synchronized void access() {
        System.out.println(Thread.currentThread().getName() + " has got the lock");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public void close() {
        running = false; // Signal to the thread to stop
    }

    @Override
    public void run() {
        while (running) {
            try {
                System.out.println(Thread.currentThread().getName() + " is running...");
                Thread.sleep(1000); // Simulate some work in the thread
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " was interrupted");
            }
        }
        System.out.println(Thread.currentThread().getName() + " has stopped.");
    }
}
