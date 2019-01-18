import java.util.Random;

public class Main {

    public static void main(String[] args) {
        taskFirst();
        taskSecond();
        taskThird();
        taskFourth();
        taskFifth();
        taskPrintFormat("FINISH");
        //System.out.println(taskFinish(new Random().nextDouble()*10, new Random().nextDouble()*10, new Random().nextDouble()*10));
        System.out.println("Result = " + taskFinish(0, 2, 3));
        System.out.println("---------- ❗❗❗❗ IN ONE STRING [number days in month] ❗❗❗ ------------");
        int year = 1900;
        for(int i = 1; i < 13; i++) {
            System.out.printf("%02d.%d%10d days\n", i, year, numberDaysInMonth(i, year));
        }
    }

    /*1.  Создайте переменную типа String c любым текстом (не сильно короткое).
    Далее выведите на экран количество символов в данной строке. Далее разделите строку пополам
    если ровно поделить не выходит то +-1 не страшно), в результате у вас должно быть 2-е новых
    переменных типа String с частями из изначальной строки. Полученные строки выведите на экран.*/
    static void taskFirst() {
        taskPrintFormat("1");
        String text = "Cat and dog grooming is about more than just looking nice — pet grooming is an " +
                "essential part of keeping your furry friends healthy.";
        System.out.println(text + "\n\u2BC8Length of string: " + text.length());
        String firstPartOfText = text.substring(0, text.length() / 2);
        String secondPartOfText = text.substring(text.length() / 2);
        System.out.println("\u2BC8First part of string: \"" + firstPartOfText + "\"\n\u2BC8Second part of string: \"" + secondPartOfText + "\"");
    }

    /*2. Создайте любое число. Определите, является ли последняя цифра числа семеркой.*/
    static void taskSecond() {
        taskPrintFormat("2");
        int randomNumber = new Random().nextInt(1000);
        //int randomNumber = 77;
        System.out.print("Number " + randomNumber + " has the last digit");
        System.out.println(randomNumber % 10 == 7 ? " 7" : " NOT 7");
    }

    /*3. Имеется прямоугольное отверстие размерами a и b (размеры задать любые), определить, можно
    ли его полностью закрыть круглой картонкой радиусом r (тоже подставляем любое значение).*/
    static void taskThird() {
        taskPrintFormat("3");
        int a = new Random().nextInt(20), b = new Random().nextInt(20), r = new Random().nextInt(20);
        //int a = 5, b = 6, r = 3;
        System.out.printf("Rectangle with sides %d and %d %s be closed by circle with a radius %d\n", a, b,
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
        System.out.println(anyText);
        anyText = anyText.replaceAll("\\?", "HELLO").replace("#","");
        System.out.println("\u2BC8After formatting: \"" + anyText + "\"");
    }

    /* x, s, t - параметры*/
    static double taskFinish(double x, double t, double s) {
        return Math.pow(Math.sin(Math.pow(x, t)), 2) / Math.sqrt(1 + Math.pow(x, s));
    }

    static int numberDaysInMonth(int m, int y) {
        return m < 8 ? (m == 2 ? (y % 4 == 0 ? (y % 100 != 0 ? 29 : (y % 400 == 0 ? 29 : 28)) : 28) : (30 + m % 2)) : (31 - m % 2);
    }

    static void taskPrintFormat(String k) {
        System.out.println("---------- TASK #" + k + " -------------------------↴");
    }

}
