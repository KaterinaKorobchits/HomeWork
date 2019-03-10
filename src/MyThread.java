public class MyThread extends Thread {

    @Override
    public void run() {
        for (int i = 1; i <= 100; i += 10) {
            System.out.print(getName() + ": ");
            Main.print10(i);
            System.out.println(getName() + "-end");
        }
    }
}
