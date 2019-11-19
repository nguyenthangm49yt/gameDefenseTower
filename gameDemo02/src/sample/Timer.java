package sample;

public class Timer extends Thread {
    @Override
    public void run() {
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}
