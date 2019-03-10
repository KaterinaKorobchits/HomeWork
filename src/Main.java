import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        MyThread thread1 = new MyThread();
        MyThread thread2 = new MyThread();
        thread1.setName("Thread_1");
        thread2.setName("Thread_2");
        System.out.print("Чтобы стартануть потоки введите start -> ");
        String start = sc.nextLine();
        if (start.equals("start")) {
            thread1.start();
            thread2.start();
        } else
            return;
    }

    //synchronized static void print10(int firstNumber) {
    static void print10(int firstNumber) {
        for (int j = firstNumber; j < 10 + firstNumber; j++)
            System.out.print(j + " ");
    }
}
