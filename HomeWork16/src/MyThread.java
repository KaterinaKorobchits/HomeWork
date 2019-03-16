public class MyThread extends Thread {

    volatile boolean temp = true;

    @Override
    public void run() {
        while (temp){}
        System.out.println("ПОБОЧНЫЙ: я уже в конце");
    }
}
