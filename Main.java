import java.util.Random;

public class Main {

    public static void main(String[] args) {
        taskFirst();
        taskSecond();
        taskThird();
        taskFourth();
        taskFifth();
        taskPrintFormat("FINISH");
        System.out.println(taskFinish(new Random().nextDouble()*10, new Random().nextDouble()*10, new Random().nextDouble()*10));
        //System.out.println(taskFinish(0, 2, 3));
    }

    /*1.  Создайте переменную типа String c любым текстом (не сильно короткое).
    Далее выведите на экран количество символов в данной строке. Далее разделите строку пополам
    если ровно поделить не выходит то +-1 не страшно), в результате у вас должно быть 2-е новых
    переменных типа String с частями из изначальной строки. Полученные строки выведите на экран.*/
    static void taskFirst() {
        taskPrintFormat("1");
        String text = "Cat and dog grooming is about more than just looking nice — pet grooming is an " +
                "essential part of keeping your furry friends healthy.";
        System.out.println("STRING:\n" + text + "\n\u2BC8LENGTH OF STRING: " + text.length());
        String firstPartOfText = text.substring(0, text.length() / 2 + 1);
        String secondPartOfText = text.substring(text.length() / 2 + 1);
        System.out.println("\u2BC8FIRST PART OF STRING: \"" + firstPartOfText + "\"\n\u2BC8SECOND PART OF STRING: \"" + secondPartOfText + "\"");
    }

    /*2. Создайте любое число. Определите, является ли последняя цифра числа семеркой.*/
    static void taskSecond() {
        taskPrintFormat("2");
        int randomNumber = new Random().nextInt(1000);
        //int randomNumber = 77;
        System.out.print("NUMBER " + randomNumber + " HAS THE LAST DIGIT");
        System.out.println(randomNumber % 10 == 7 ? " 7" : " NOT 7");
    }

    /*3. Имеется прямоугольное отверстие размерами a и b (размеры задать любые), определить, можно
    ли его полностью закрыть круглой картонкой радиусом r (тоже подставляем любое значение).*/
    static void taskThird() {
        taskPrintFormat("3");
        int a = new Random().nextInt(20), b = new Random().nextInt(20), r = new Random().nextInt(20);
        //int a = 5, b = 6, r = 3;
        System.out.printf("RECTANGLE WITH SIDES %d AND %d %s BE CLOSED BY CIRCLE WITH A RADIUS %d\n", a, b,
                2 * r >= Math.sqrt(a * a + b * b) ? "CAN" : "CAN NOT", r);
    }


    /*4. Имеется целое число (любое), это число — сумма денег в рублях. Вывести это число,
    добавив к нему слово «рублей» в правильном падеже.*/
    static void taskFourth() {
        taskPrintFormat("4");
        int money = new Random().nextInt(10000);
        switch (money % 10) {
            case 1:
                System.out.println(money + " рубль");
                break;
            case 2:
            case 3:
            case 4:
                System.out.println(money + " рубля");
                break;
            default:
                System.out.println(money + " рублей");
        }
    }

    /*5. Имеется строка, которая содержит символы ? и символы #.  Замените все символы ? на HELLO,
    а # - удалите. Результат вывести на экран.*/
    static void taskFifth() {
        taskPrintFormat("5");
        String anyText = "dkldgg ?chc#v ?##d?g ?#fg3f#dgb # fgdfh";
        System.out.println("String: \n" + anyText);
        anyText = anyText.replaceAll("\\?", "HELLO").replace("#","");
        System.out.println("\u2BC8AFTER FORMATTING: \"" + anyText + "\"");
    }

    /* x, s, t - параметры*/
    static double taskFinish(double x, double t, double s) {
        return Math.pow(Math.sin(Math.pow(x, t)), 2) / Math.sqrt(1 + Math.pow(x, s));
    }

    static int numberDaysInMonth(int m, int y) {
        return day < 8 ? (m == 2 ? (y % 4 == 0 ? 1 : 0) : 30 + m % 2) : 31 - m % 2;
    }

    static void taskPrintFormat(String k) {
        System.out.println("TASK #" + k + "---------------------------------↴");
    }

}
