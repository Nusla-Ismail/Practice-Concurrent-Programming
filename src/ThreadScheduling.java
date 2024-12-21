public class ThreadScheduling {
    public static void main(String[] args) {
         Thread runner1 = new Thread(new Scheduler(1));
         Thread runner2 = new Thread(new Scheduler(2));
         Thread runner3 = new Thread(new Scheduler(3));
         Thread runner4 = new Thread(new Scheduler(4));

        runner1.setPriority(Thread.MAX_PRIORITY);
        runner2.setPriority(Thread.MIN_PRIORITY);
        runner3.setPriority(Thread.NORM_PRIORITY);
        runner4.setPriority(Thread.NORM_PRIORITY);

        // Start the race
        // System.out.println("Starting unfair race with different priorities...");
        //runner1.start();
        //runner2.start();
        System.out.println("Starting fair race with same priorities...");
        runner3.start();
        runner4.start();

        try {
            //runner1.join();
            //runner2.join();
            runner3.join();
            runner4.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}

class Scheduler implements Runnable{

    private  int runnerNumber;

    public Scheduler(int runnerNumber) {
        this.runnerNumber = runnerNumber;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Runner "+ runnerNumber + " is running in position : "+i);
            try{
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(e);
            }

        }
        System.out.println("Runner " + runnerNumber + " has finished!");
    }
}

