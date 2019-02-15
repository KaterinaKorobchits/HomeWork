import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Struct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    static Scanner in = Calculator.in;
    static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public static void main(String[] args) {
        // калькулятор
        do{
            calculator();
            System.out.print("Еще? (Y/N)");
        } while(!Calculator.in.next().equals("N"));
        //Модернизация задания со Студентами
        //menuForStudent();
    }

    public static void calculator() {
        double num1 = Calculator.getNumber();
        double num2 = Calculator.getNumber();
        char operation = Calculator.getOperation();
        double result = 0;
        try {
            result = Calculator.getResult(num1, num2, operation);
            System.out.println(num1 + " " + operation + " " + num2 + " = " + result);
        } catch (Exception ex) {
            if(ex instanceof CalculationMyException)
                System.out.println(((CalculationMyException) ex).getRussianMessage());
            else
                System.out.println("*** Неизвестная ошибка ***");
        }
    }

    public static ArrayList<Student> enterStudents(ArrayList<Student> listOfStudents) {
        int i = 0;
        do {
            System.out.print((i + 1) + " студент: \n\t Имя: ");
            String name = in.nextLine();
            System.out.print("\t Фамилия: ");
            String surName = in.nextLine();
            System.out.print("\t Дата рождения(dd.mm.yyyy): ");
            Date dateOfBirth = null;
            while(dateOfBirth == null) {
                try {
                    dateOfBirth = dateFormat.parse(in.nextLine());
                } catch (ParseException e) {
                    System.out.println("*** Неправильно введена дата!!!");
                    System.out.print("\t Дата рождения(dd.mm.yyyy): ");
                }
            }
            Student a = new Student(name, surName, dateOfBirth);
            listOfStudents.add(a);
            System.out.print("Продолжить ввод студентов? (Y/N) -> ");
            i++;
        } while(in.nextLine().charAt(0) == 'Y');
        return listOfStudents;
    }

    public static void menuForStudent() {
        ArrayList<Student> list = new ArrayList<>();
        if(readFromFileStudents() != null) {
            list = readFromFileStudents();
            System.out.print("Студенты в базе уже есть ");
        }
        else
            System.out.print("Студентов в базе НЕТ ");
        do{
            if(list.size() > 0) {
                System.out.print("[1 - просмотр студентов; 2 - добавить студентов; 0 - выход] -> ");
            }
            else
                System.out.print("[2 - добавить студентов; 0 - выход] -> ");
            char addStudents = in.nextLine().charAt(0);
            switch (addStudents) {
                case '1':
                    if(list.size() > 0)
                        for (Student st: list)
                            System.out.print(st.toString());
                    else
                        System.out.println("База пуста");
                    break;
                case '2':
                    list = enterStudents(list);
                    break;
                case '0':
                    writeToFileStudents(list);
                    return;
                default:
                    System.out.println("Неверный ввод дествия");
                    return;
            }

        } while(true);
    }

    public static boolean writeToFileStudents(ArrayList<Student> list) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Students.txt"))) {
            oos.writeObject(list);
            return true;
        } catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public static ArrayList<Student> readFromFileStudents() {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Students.txt"))) {
            ArrayList<Student> list = (ArrayList<Student>) ois.readObject();
            return list;
        } catch(Exception ex){
            System.out.println("Нет файла такого(((");
            return null;
        }
    }
}
