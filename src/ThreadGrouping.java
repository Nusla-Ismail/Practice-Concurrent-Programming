import java.util.Arrays;

public class ThreadGrouping {
    public static void main(String[] args) {
        final int maxGroup = 6;
        final int maxThread = 7;
        ThreadGroup[] group = new ThreadGroup[maxGroup];
        Thread[] threads = new Thread[maxThread];


        group[0] = Thread.currentThread().getThreadGroup();
        group[1] = new ThreadGroup(group[0],"Group A");
        group[2] = new ThreadGroup(group[0],"Group B");
        group[3] = new ThreadGroup(group[2],"Group B1");

        threads[0]= new Thread(group[0],new ThreadGroups(), "Thread 1");
        threads[1]= new Thread(group[1], new ThreadGroups(), "Thread 2");
        threads[2]= new Thread(group[1], new ThreadGroups(), "Thread 3");

        int actNum = group[0].activeCount();

        Thread[] listOfThreads = new Thread[actNum*2];

        int count = group[0].enumerate(listOfThreads);

        System.out.println(count);

        threads[0].start();
        threads[1].start();

        try {
            Thread.sleep(1000);
            group[0].interrupt();
            System.out.println(group[0].getMaxPriority());
        }catch (InterruptedException e){
            System.out.println(e);
        }

        System.out.println(Arrays.toString(group));


    }


}

class ThreadGroups implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " is running at: "+i);
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }

        }
    }
}