import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ATM atm1 = new ATM(0,0,0);
        System.out.println("0 - Набор купюр в банкомате");
        System.out.println("1 - Добавить деньги в автомат");
        System.out.println("2 - Снять деньги");
        Scanner in = new Scanner(System.in);
        System.out.print("----- Выберите действие: ");
        while(in.hasNextInt()) {
            int temp = Integer.parseInt(in.nextLine());
            if(temp == 0) {
                atm1.print();
            }
            else if(temp== 1) {
                System.out.print("Введите сумму: ");
                if(atm1.add(Integer.parseInt(in.nextLine())))
                    System.out.println("Сумма успешно добавлена");
                else
                    System.out.println("Номинал валют не совпадает!");
            }
            else if(temp== 2) {
                System.out.print("Введите сумму: ");
                System.out.println(atm1.cash(Integer.parseInt(in.nextLine())));
            }
            else
                return;
            System.out.print("----- Выберите действие: ");
        }
    }
}
