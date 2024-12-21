//Implement a Java program using Thread class to demonstrate the concept of different ways of implementing threads. [5 marks , partial marks are possible]

public class Threads extends Thread{

    @Override
    public void run() {
        super.run();
        System.out.println(Thread.currentThread().getName()+ "is now running");
    }
}

/*
public class Runnables implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+ "is now running");
    }
}

class Main{
    public static void main(String[] args) {
        Threads t1 = new Threads();
        Runnables run = new Runnables();
        Thread t2 = new Thread(run);
        t1.start();
        t2.start();
    }
}*/
