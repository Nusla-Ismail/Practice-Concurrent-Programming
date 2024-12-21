public class MyThread extends Thread{

    @Override
    public void run() {
        super.run();
        for(int i =0 ; i<5;i++) {
            System.out.println(Thread.currentThread().getName() + " is currently running "+ i);
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                System.out.println(e);
            }
        }
    }
}
