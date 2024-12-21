import java.util.ArrayList;

public class ReaderWriter {
    public static void main(String[] args) {

        SharedResource resource = new SharedResource();
        Thread writer = new Writer(resource, 1);
        Thread writer1 = new Writer(resource, 2);
        Thread reader = new Reader(resource, 1);
        Thread reader1 = new Reader(resource, 2);
        Thread reader2 = new Reader(resource, 3);

        writer.start();
        writer1.start();
        reader.start();
        reader1.start();
        reader2.start();
    }
}

class SharedResource {
    private final ArrayList<Integer> content = new ArrayList<>();
    private int readersCount = 0;  // Keeps track of the number of active readers
    private boolean writerActive = false;  // Indicates if a writer is active

    // Reader method
    public synchronized void read() {
        // Wait if there is an active writer
        while (writerActive) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }

        readersCount++;  // Increment the readers count
        System.out.println(Thread.currentThread().getName() + " has started reading.");

        // Simulate reading the content
        for (Integer integer : content) {
            System.out.println(Thread.currentThread().getName() + " has read " + integer + " in content.");
        }

        readersCount--;  // Decrement the readers count
        if (readersCount == 0) {
            notifyAll();  // Notify writers if there are no readers left
        }

        System.out.println(Thread.currentThread().getName() + " has finished reading.");
    }

    // Writer method
    public synchronized void write(int contents) {
        // Wait if there are any readers or if a writer is already active
        while (readersCount > 0 || writerActive) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }

        writerActive = true;  // Indicate that the writer is active
        System.out.println(Thread.currentThread().getName() + " has started writing.");

        // Simulate writing to the content
        for (int i = 0; i <= contents; i++) {
            content.add(i);
            System.out.println(Thread.currentThread().getName() + " has written " + i + " in content.");
        }

        writerActive = false;  // Indicate that the writer is no longer active
        notifyAll();  // Notify both readers and writers

        System.out.println(Thread.currentThread().getName() + " has finished writing.");
    }
}

class Writer extends Thread {
    private final SharedResource resource;

    public Writer(SharedResource resource, int id) {
        super("Writer #" + id);
        this.resource = resource;
    }

    @Override
    public void run() {
        resource.write(5);
        try {
            sleep(100);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}

class Reader extends Thread {
    private final SharedResource resource;

    public Reader(SharedResource resource, int id) {
        super("Reader #" + id);
        this.resource = resource;
    }

    @Override
    public void run() {
        resource.read();
        try {
            sleep(100);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}
