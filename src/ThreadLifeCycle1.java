/*Write a Java program that illustrates the life cycle of a thread. Include code segments for each thread state (NEW, RUNNABLE, BLOCKED, WAITING, TIMED_WAITING, and TERMINATED). Use appropriate sleep and synchronization methods to control the thread's state transitions. [10 marks, partial marks can be awarded ]*/


public class ThreadLifeCycle1 {
    public static void main(String[] args) {

        Worker worker = new Worker();
        Thread t1 = new Thread(()-> worker.run());// NEW state
        t1.start(); // RUNNABLE State
        // when the output is printed then it is RUNNING state

        Thread t2 = new Thread(()-> worker.run());// NEW state
        t2.start(); // BLOCKED State because t1 has the lock initially

        try{
            t2.join(); // TERMINATED STATE ( main thread waits for t2 to terminate before running)
        }catch (InterruptedException e){
            System.out.println(e);
        }



        Thread t3 = new Thread(()->{ // WAITING STATE
            synchronized (ThreadLifeCycle1.class){
                try {
                    ThreadLifeCycle1.class.wait();

                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        });

        t3.start();

        //Printing the current states
        System.out.println("t1 state: "+t1.getState());
        System.out.println("t2 state: "+t2.getState());
        System.out.println("t3 state: "+t3.getState());


        //MAIN thread delay
        try {
            Thread.sleep(100);
        }catch (InterruptedException e){
            System.out.println(e);
        }


    }
}

class Worker implements Runnable{
    @Override
    public synchronized void run() {
        for(int i =0; i<5; i++){
            System.out.println(Thread.currentThread().getName() + " is running "+i);
            try{
                Thread.sleep(1000); // TIMED_WAITING STATE
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}
