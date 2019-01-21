import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        maxMatchLettersIn2Names();
        task1();
        task2();
        task3();
        task4();
        task5();
    }

    /*Найти максимальное по длине совпадение букв в 2 именах*/
    static void maxMatchLettersIn2Names() {
        final String[] catNamesArray = {"Рыжик", "Барсик", "Мурзик", "Мурка", "Васька", "Томасина", "Бобик",
                "Кристина","Пушок", "Дымка", "Кузя", "Китти", "Барбос", "Масяня", "Барби"};
        int countMax = 0, count = 0;
        String str;
        int name1 = -1, name2 = -1;
        for (int i = 0; i < catNamesArray.length; i++) {
            for (int j = i+1; j < catNamesArray.length; j++) {
                if (catNamesArray[j].startsWith(catNamesArray[i]) || catNamesArray[i].startsWith(catNamesArray[j]))
                    count = Math.min(catNamesArray[i].length(),catNamesArray[j].length());
                else {
                    str = "";
                    count = 0;
                    for (int k = 0; k < catNamesArray[i].length(); k++) {
                        str = str + catNamesArray[i].charAt(k);
                        if (catNamesArray[j].startsWith(str))
                            count++;
                        else
                            break;
                    }
                }
                if (count >= countMax) {
                    name1 = i;
                    name2 = j;
                    countMax = count;
                }
            }
        }
        System.out.println("-----Максимальное по длине совпадение букв в 2 именах-----");
        if (countMax > 0)
            System.out.println(catNamesArray[name1] + " и " + catNamesArray[name2] +" = " + countMax);
        else
            System.out.println("Совпадений нет!");
    }

    /*1. Создайте массив с 10-ю переменными типа int. Используя оператор "for" найдите и выведите на экран
    наименьшее и наибольшее значение в массиве.
    min value = "значение, которое у вас получилось".
    max value = "значение, которое у вас получилось".
    Далее замените наименьшее значение на 0, а наибольшее на 99 и выведите получившийся массив на экран в виде
    [23, 0, 34, 99, 43534]*/
    static void task1() {
        //long startTime = System.nanoTime();
        int[] array = new int[10];
        for (int i = 0; i < array.length; i++)
            array[i] = new Random().nextInt(100);
        taskPrintFormat(1);
        System.out.println(Arrays.toString(array));
        int maxValue = Integer.MIN_VALUE, minValue = Integer.MAX_VALUE;
        for (int i : array) {
            maxValue = Math.max(i,maxValue);
            minValue = Math.min(i,minValue);
        }
        System.out.println("min value = " + minValue + "\nmax value = " + maxValue);
        for (int i = 0; i < array.length; i++) {
            if(array[i] == maxValue)
                array[i] = 99;
            if(array[i] == minValue)
                array[i] = 0;
        }
        System.out.println("After replacing (min -> 0, max -> 99): " + Arrays.toString(array));
        //long timeSpent = System.nanoTime() - startTime;
        //System.out.println(timeSpent);
    }

    /*2. Создайте массив с 10-ю переменными типа float. Далее найдите дубликаты и выведите их количество.
    Пример: есть массив {2, 3, 5, 7, 6, 5, 7, 3, 7, 20} - в данном массиве цифра 3 и 7 повторяются.
    В результате выполнения программы на экран должно вывести:
        [3] - повторений 2
        [7] - повторений 3*/
    static void task2() {
        float[] array = {3.14f, 1.2f, 453.7f, 3.14f, 5.23f, 6.54f, 1.2f, 6.55f, 3.14f, 1234.67f};
        int[] exclusion = new int[array.length];
        int count = 0;
        taskPrintFormat(2);
        System.out.println(Arrays.toString(array));
        for (int i = 0; i < array.length; i++) {
            count = 1;
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] == array[j] & exclusion[i] != -1) {
                    count++;
                    exclusion[j] = -1;
                }
            }
            if (count > 1)
                System.out.println("[" + array[i] + "] - повторений " + count);
        }
    }

    /*3. Напишите программу, которая печатает массив, затем инвертирует (то есть меняет местами
    первый элемент с последним, второй — с предпоследним и т.д.), и вновь печатает.*/
    static void task3() {
        int[] array = new int[11];
        for (int i = 0; i < array.length; i++)
            array[i] = new Random().nextInt(100);
        taskPrintFormat(3);
        System.out.println(Arrays.toString(array));
        for (int i = 0, j = array.length - 1; i < array.length/2; i++, j--) {
            int temp = array[j];
            array[j] = array[i];
            array[i] = temp;
        }
        System.out.println(Arrays.toString(array));
    }

    /*4. Написать программу, определяющую, образуют ли цифры некоторого числа
    строго возрастающую последовательность. Например: 123 – образуют, 212 – не образуют.*/
    static void task4() {
        long number = 12345677;
        taskPrintFormat(4);
        /*String str = Long.toString(number);       // ----- 1 вариант
        for(int i = 0; i < str.length()-1; i++) {
            if ( str.charAt(i) < str.charAt(i + 1)) {
                if (i == str.length()-2)
                    System.out.println(number + " - строго возрастающая последовательность");
                continue;
            }
            else {
                System.out.println(number + " - НЕ строго возрастающая последовательность");
                break;
            }
        }*/
        long numberCopy = number;
        int length = Long.toString(numberCopy).length(), i = 1;
        int[] mas = new int[length];
        do{
            mas[length - i] = (int)numberCopy%10;
            numberCopy /= 10;
            i++;
        } while (numberCopy > 0);
        for(int k = 0; k < length - 1; k++) {
            if(mas[k+1] > mas[k]) {
                if(k == length - 2)
                    System.out.println(number + " - строго возрастающая последовательность");
                continue;
            }
            else {
                System.out.println(number + " - НЕ строго возрастающая последовательность");
                break;
            }
        }
    }

    /*5.Создайте массив типа int. Отсортируйте массив любым способом (
    по убыванию либо по возрастанию). Результат вывести на экран.*/
    static void task5() {
        //long startTime = System.nanoTime();
        //int[] array = {16, 10, 23, 21, 20, 18, 6, 5, 4, 3};
        int[] array = new int[11];
        for (int i = 0; i < array.length; i++)
            array[i] = new Random().nextInt(100);
        taskPrintFormat(5);
        System.out.println(Arrays.toString(array));
        //Arrays.sort(array);       //----- 1 вариант)
        for (int i = 0; i < array.length ; i++) {
            int min = Integer.MAX_VALUE, index = i;
            for (int j = i + 1; j < array.length; j++){
                if (array[j] < array[index]) {
                    index = j;
                }
            }
            if (index != -1) {
                int temp = array[index];
                array[index] = array[i];
                array[i] = temp;
            }
        }
        System.out.println(Arrays.toString(array));
        //long spentTime = System.nanoTime() - startTime;
        //System.out.println(spentTime);
    }

    static void taskPrintFormat(int k) {
        System.out.println("---------- TASK #" + k + " -------------------------↴");
    }
}
