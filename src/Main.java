import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
      /*  System.out.println("Hello world!");
        Cat myCat = new Cat("Tom",6);
        myCat.meow();
        Scanner sc = new Scanner(System.in);
        System.out.println("what is the name of your cat?");
        myCat.setName(sc.nextLine());

        Animal myAnimal = new Dog();
        myAnimal.sounds();

        Animal myBirds = new Bird();
        myBirds.sounds();

        Animal myReptile = new Reptile();
        myReptile.sounds();

        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();

        t1.start();
        t2.start();

        MyRunnable myRunnable = new MyRunnable();

        Thread t1 = new Thread(myRunnable);
        Thread t2 = new Thread(myRunnable);

        t1.start();
        t2.start();
        Thread t1 = new Thread(() -> {
            try {
                synchronized (Thread.currentThread()) {
                    Thread.currentThread().wait();
                }
            } catch (InterruptedException e) {
                System.out.println(e);;
            }
        });
        t1.start();

        try {
            // Wait for a short time to ensure t1 has time to reach the wait() call
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        System.out.println(t1.getState()); // Output: WAITING

        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(2000); // TIMED WAITING for 2 seconds
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        });
        t1.start();
        try {
            // Wait for a short time to ensure t1 has time to reach the wait() call
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.out.println(t1.getState()); // Output: TIMED WAITING*/

        Thread t1 = new Thread(() -> System.out.println("Thread has run"));
        t1.start();
        try { t1.join(); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println(t1.getState()); // Output: TERMINATED








    }
}