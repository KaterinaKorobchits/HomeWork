import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        firstPartATM();
        //secondPart();
        //thirdPart();
    }

    static public void firstPartATM() {
        CityATM atm1 = new CityATM("ABCD");
        System.out.println("0 - Инфо, 1 - Набор валют в банкомате, 2 - Добавить деньги в банкомат, 3 - Снять деньги");
        Scanner in = new Scanner(System.in);
        System.out.print("----- Выберите действие: ");
        while(in.hasNextInt()) {
            int temp = Integer.parseInt(in.nextLine());
            switch (temp) {
                case 0:
                    System.out.println(atm1.getInfoAtm());
                    break;
                case 1:
                    atm1.print();
                    break;
                case 2:
                    System.out.print("Введите сумму: ");
                    if (atm1.add(Integer.parseInt(in.nextLine())))
                        System.out.println("Сумма успешно добавлена");
                    else
                        System.out.println("Номинал валют не совпадает!");
                    break;
                case 3:
                    System.out.print("Введите сумму: ");
                    atm1.printWithdrawal(atm1.withdrawal(Integer.parseInt(in.nextLine())));
                    break;
                default:
                    return;
            }
            System.out.println("0 - Инфо, 1 - Набор валют в банкомате, 2 - Добавить деньги в банкомат, 3 - Снять деньги");
            System.out.print("----- Выберите действие: ");
        }
    }

    static public void secondPart() {
        int mas1[] = new int[10];
        int mas2[] = new int[20];
        for(int i = 0; i < mas1.length; i++)
            mas1[i] = i+1;
        System.arraycopy(mas1, 0, mas2, (mas2.length - mas1.length)/2, mas1.length);
        System.out.println(Arrays.toString(mas2));
    }

    static public void thirdPart() {
        ArrayList<Figure> list = new ArrayList<>();
        list.add(new Circle(2.0));
        list.add(new Triangle(3.0, 3.7, 6.9));
        list.add(new Square(4.7));
        list.add(new Rectangle(3.7, 8.9));
        for(Figure f : list)
            f.print();
    }
}
