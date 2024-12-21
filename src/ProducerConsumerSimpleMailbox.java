public class ProducerConsumerSimpleMailbox {
    public static void main(String[] args) {
        SimpleMailbox mailbox = new SimpleMailbox();
        Thread consumer = new Consumer(mailbox,2,6);
        Thread producer = new Producer(mailbox,1,6);

        producer.start();
        consumer.start();

    }
}

interface  Mailbox{
   void put (int num);
   int take ();
}
class SimpleMailbox implements Mailbox{
    private int content;
    private boolean available = false; // false when mailbox is empty and true when mailbox is full



    @Override
    public synchronized void put(int num) {
        while (available){
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e);
            }

        }
        content = num;
        available= true;
        notify();
        System.out.println("Producer has added resource: "+ content);


    }

    @Override
    public synchronized int take() {
        while(!available){
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        available = false;
        System.out.println("Consumer has taken resource: "+content);
        notify();
        return content;
    }
}

class Producer extends Thread{
    private int items;
    private Mailbox mailbox;

    public int getItems() {
        return items;
    }

    public Mailbox getMailbox() {
        return mailbox;
    }

    public void setMailbox(Mailbox mailbox) {
        this.mailbox = mailbox;
    }

    public void setItems(int items) {
        this.items = items;
    }

    public Producer (Mailbox mailbox, int id, int items){
        super("Producer No. "+id);
        this.items = items;
        this.mailbox = mailbox;

    }



    @Override
    public void run() {
        for (int i = 0; i < items ; i++) {

            mailbox.put(i);

        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println(e);
        }

    }
}

class Consumer extends Thread{
    int items;
    Mailbox mailbox;

    public Consumer(Mailbox mailbox,int id, int items){
        super("Customer No. "+id);
        this.items = items;
        this.mailbox = mailbox;

    }



    @Override
    public void run() {
        for (int i = 0; i < items ; i++) {

            int taken = mailbox.take();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }

    }
}
