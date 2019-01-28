import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("------- task1:");
        //int[] array = {4,3,7,1,5,2,8,3,0,1};
        int[] array = new int[10];
        for(int i = 0; i < array.length; i++) {
            System.out.print(i + 1 + " элемент: ");
            array[i] = keyboardInput();
        }
        int[] array1 = Arrays.copyOf(array,10);
        int[] array2 = Arrays.copyOf(array,10);
        int[] array3 = Arrays.copyOf(array,10);
        arrayOutput(array);
        System.out.println("------- Selection Sort:");
        long startTime = System.nanoTime();
        arrayOutput(selectionSort(array));
        long timeSpent = System.nanoTime() - startTime;
        System.out.println("Time spent: " + timeSpent);
        System.out.println(Arrays.toString(array));
        System.out.println("------- Insertion Sort:");
        startTime = System.nanoTime();
        arrayOutput(insertionSort(array1));
        timeSpent = System.nanoTime() - startTime;
        System.out.println("Time spent: " + timeSpent);
        System.out.println("------- Arrays.sort():");
        startTime = System.nanoTime();
        arrayOutput(insertionSort(array2));
        timeSpent = System.nanoTime() - startTime;
        System.out.println("Time spent: " + timeSpent);
        System.out.println("------- Bubble sort:");
        System.out.println(Arrays.toString(array3));
        startTime = System.nanoTime();
        arrayOutput(bubbleSort(array3));
        timeSpent = System.nanoTime() - startTime;
        System.out.println("Time spent: " + timeSpent);
        System.out.println("------- task2:");
        int n = new Random().nextInt(100000);
        System.out.printf("Для вывода %d новостей необходимо %d страниц\n",n ,task2(n));
        System.out.println("------- Find missing number:");
        int[] findMis = {9,10,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27};
        System.out.println(Arrays.toString(findMis) + "\nMissing number: " + lessonFindMissingNumber(findMis));
        System.out.println("------- Arithmetic operations for long numbers:");
        String num1 = "900000000";
        String num2 = "900000000";
        System.out.println(num1 + " + " + num2 + " = " + lessonAddition(num1, num2));
        System.out.println(num1 + " - " + num2 + " = " + lessonSubtraction(num1, num2));
        System.out.println("------- Find the longets word, consisting of other array words:");
        String[] str = {"dog", "cat", "dogcat", "mouse", "dogandcat"};
        System.out.println(Arrays.toString(str) + " ---> " + lessonFindLongestWord(str));
    }

    static int keyboardInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    static void arrayOutput(int[] mas) {
        for (int i = 0; i < mas.length; i++)
            System.out.printf("\"%d\" %s ",mas[i], i == (mas.length-1) ? "" : "|");
        System.out.println();
    }

    static int[] selectionSort(int[] mas) {
        for (int i = 0; i < mas.length ; i++) {
            int min = mas[i], index = i;
            for (int j = i + 1; j < mas.length; j++){
                if (mas[j] < mas[index]) {
                    index = j;
                }
            }
            if (index != i) {
                int temp = mas[index];
                mas[index] = mas[i];
                mas[i] = temp;
            }
        }
        return mas;
    }

    static int[] insertionSort(int[] mas) {
        for(int i = 1; i < mas.length; i++) {
            int curr = mas[i];
            int j = i - 1 ;
            while (j >= 0 && mas[j] > curr) {
                mas[j+1] = mas[j];
                j--;
            }
            mas[j+1] = curr;
        }
        return mas;
    }

    static int[] bubbleSort(int[] mas) {
        for (int i = 0; i < mas.length - 1; i++) {
            boolean b = true;
            while (b) {
                b = false;
                for (int j = 0; j < mas.length - i - 1; j++) {
                    if (mas[j + 1] < mas[j]) {
                        int temp = mas[j];
                        mas[j] = mas[j + 1];
                        mas[j + 1] = temp;
                        b = true;
                    }
                }
            }
        }
        return mas;
    }

    static int task2(int n) {
        return (int)Math.ceil(n*1.0/10);
    }

    static int lessonFindMissingNumber(int[] mas) {
        int sum = 0;
        for(int i: mas)
            sum += i;
        return (int)((mas[0] + mas[mas.length-1]) * 1.0 / 2 * (mas.length + 1) - sum);
    }

    static String lessonAddition(String num1, String num2) {
        String result = "";
        int num1Length = num1.length();
        int num2Length = num2.length();
        int max = num1Length >= num2Length ? num1Length : num2Length;
        int help = 0, temp = 0;
        for (int i = 0; i < max; i++) {
            if (num1Length > i & num2Length > i)
                temp = Integer.parseInt("" + num1.charAt(num1Length - 1 - i)) +
                        Integer.parseInt("" + num2.charAt(num2Length - 1 - i)) + help;
            else
                temp = Integer.parseInt("" + (num1Length > num2Length ? num1 : num2).charAt(max - 1 - i)) + help;
            result = temp % 10 + result;
            help = temp / 10;
        }
        if(help != 0 )
            result = help + result;
        return result;
    }

    static String lessonSubtraction(String number1, String number2) {
        String result = "";
        String num1 = "", num2 = "";
        boolean isNegative = false;
        if(number2.length() > number1.length()) {
            num1 = number2;
            num2 = number1;
            isNegative = true;
        }
        else if (number2.length() == number1.length()) {
            for (int i = 0; i < number1.length(); i++) {
                if (number2.charAt(i) > number1.charAt(i)) {
                    num1 = number2;
                    num2 = number1;
                    isNegative = true;
                    break;
                }
                else if (number1.charAt(i) > number2.charAt(i)) {
                    num1 = number1;
                    num2 = number2;
                    break;
                }
                else
                    return "0";
            }
        }
        else{
            num1 = number1;
            num2 = number2;
        }
        int num1Length = num1.length();
        int num2Length = num2.length();
        int max = num1Length >= num2Length ? num1Length : num2Length;
        int help = 0, temp = 0;
        for (int i = 0; i < max; i++) {
            if (num1Length > i & num2Length > i) {
                int temp1 = Integer.parseInt("" + num1.charAt(num1Length - 1 - i));
                int temp2 = Integer.parseInt("" + num2.charAt(num2Length - 1 - i));
                temp = temp1 - temp2 - help;
            }
            else
                temp = Integer.parseInt("" + (num1Length > num2Length ? num1 : num2).charAt(max - 1 - i)) - help;
            if(temp < 0) {
                temp += 10;
                help = 1;
            }
            else
                help = 0;
            result = temp + result;
        }
        result = isNegative ? "-" + result : result;
        return result.replaceFirst ("^0*", "");
    }

    static String lessonFindLongestWord(String[] list) {
        Arrays.sort(list,new Comparator<String>() {
            @Override
            public int compare(String s1,String s2) {
                if (s1.length() < s2.length())
                    return 1;
                else if (s1.length() > s2.length())
                    return -1;
                else
                    return 0;
            }
        });
        for(int i = 0; i < list.length; i++) {
            String temp = list[i];
            String temp1 = list[i];
            for (int j = i + 1; j < list.length; j++) {
                if (temp.contains(list[j]))
                    temp = temp.replaceAll(list[j], "");
            }
            if (temp.equals(""))
                return list[i];
        }
        return "";
    }
}
