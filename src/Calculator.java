import java.util.Scanner;

public class Calculator {
    static Scanner in = new Scanner(System.in);
    private static final char PLUS = '+';
    private static final char MINUS = '-';
    private static final char TIMES = '*';
    private static final char DIVIDED = '/';

    public static double getNumber() {
        System.out.print("Введите число -> ");
        double num;
        try {
            if (in.hasNextDouble()) {
                num = in.nextDouble();
            } else {
                in.next();
                throw new InputMyException(" *** Неверный ввод! Еще одна попытка");
            }
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            num = getNumber();
        }
        return num;
    }

    public static char getOperation() {
        System.out.print("Введите операцию (+ - * /) -> ");
        char operation = in.next().charAt(0);
        try {
            if (operation != PLUS && operation != MINUS && operation != TIMES && operation != DIVIDED)
                throw new InputMyException(" *** Неверный тип операции! Еще одна попытка");
        } catch (InputMyException ex) {
            System.out.println(ex.getMessage());
            operation = getOperation();
        }
        return operation;
    }

    public static double getResult(double num1, double num2, char operation) throws CalculationMyException {
        double result = 0;
        switch (operation) {
            case PLUS:
                result = num1 + num2;
                break;
            case MINUS:
                result = num1 - num2;
                break;
            case TIMES:
                result = num1 * num2;
                break;
            case DIVIDED:
                if (num2 == 0)
                    throw new CalculationMyException("CalculationMyException");
                result = num1 / num2;
                break;
        }
        return result;
    }
}
