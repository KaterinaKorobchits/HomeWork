import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Main {

    static Scanner in = new Scanner(System.in);
    static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public static void main(String[] args) {
        Student[] listOfStudents = enterStudents();
        int numberOfStudents = listOfStudents.length;
        Calendar now = Calendar.getInstance();
        int month = 0, avYear = 0, avMonth = 0;
        for(Student st: listOfStudents) {
            Calendar dateOfBirth = Calendar.getInstance();
            dateOfBirth.setTime(st.dateOfBirth);
            int year = now.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);
            int monthNow = now.get(Calendar.MONTH), monthBirthday = dateOfBirth.get(Calendar.MONTH);
            if(monthNow < monthBirthday) {
                year--;
                month += year * 12 + 12 + (monthNow - monthBirthday);
            }
            else
                month += year * 12 + now.get(Calendar.MONTH) - dateOfBirth.get(Calendar.MONTH);
            if(now.get(Calendar.DAY_OF_MONTH) < dateOfBirth.get(Calendar.DAY_OF_MONTH))
                month--;
        }
        avMonth = month/numberOfStudents%12;
        avYear = month/numberOfStudents/12;
        System.out.println("Средний возраст(лет, месяцев): " + avYear + ", " + avMonth);
        //readFromFile();
        //writeToFile();
    }

    public static Student[] enterStudents() {
        System.out.print("Введите количество студентов - > ");
        int numberOfStudents = in.nextInt();
        in.nextLine();
        Student[] listOfStudents = new Student[numberOfStudents];
        for(int i = 0; i < numberOfStudents; i++) {
            System.out.print((i + 1) + " студент: \n\t Имя: ");
            String name = in.nextLine();
            System.out.print("\t Фамилия: ");
            String surName = in.nextLine();
            System.out.print("\t Дата рождения: ");
            Date dateOfBirth = new Date();
            boolean error = true;
            do {
                try {
                    dateOfBirth = dateFormat.parse(in.nextLine());
                    error = false;
                } catch (ParseException e) {
                    System.out.println("*** Неправильно введена дата!!!");
                    System.out.print("\t Дата рождения(dd.mm.yyyy): ");
                }
            } while(error);
            listOfStudents[i] = new Student(name, surName, dateOfBirth);
        }
        return listOfStudents;
    }

    public static void readFromFile() {
        /*try {
            Scanner in = new Scanner(new FileReader("notes.txt"));
            while (in.hasNextLine()) {
                System.out.println(in.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("*** Файл не найден!");
        }*/
        try(FileReader reader = new FileReader("notes.txt"))
        {
            int c;
            while((c=reader.read())!=-1){

                System.out.print((char)c);
            }
        }
        catch(IOException ex){
            System.out.println("*** Файл не найден!");
        }
    }

    public static void writeToFile() {
        System.out.println("Введите текст дял записи в файл(признак окончания строка exit): ");
        try(FileWriter writer = new FileWriter("notes1.txt", false))
        {
            while(true) {
                String temp = in.nextLine();
                if (temp.equals("exit"))
                    break;
                else
                    writer.write(temp + "\n");
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}