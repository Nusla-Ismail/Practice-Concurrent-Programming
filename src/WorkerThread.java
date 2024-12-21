public class WorkerThread implements Runnable {
    private volatile boolean running = true;

    public void run() {
        while (running) {
            // Thread logic here
            try {
                Thread.sleep(1000); // Simulating some work
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        System.out.println("Thread stopped.");
    }

    public void stop() {
        running = false;
    }
}
