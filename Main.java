package HomeWork4;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int count;
        System.out.print("Введите количетсво пациентов -> ");
        count = in.nextInt();
        in.nextLine();
        Patient[] array = new Patient[count];
        for(int i = 0; i < count; i++) {
            System.out.println((i + 1) + " Пациент: ");
            System.out.print("\t⬪ Введите ФИО: ");
            String fio = in.nextLine();
            System.out.print("\t⬪ Введите возраст: ");
            int age = in.nextInt();
            in.nextLine();
            System.out.print("\t⬪ Введите адрес: ");
            String address = in.nextLine();
            System.out.print("\t⬪ Введите диагноз: ");
            String diagnosis = in.nextLine();
            System.out.print("\t⬪ Резидент?(Y/N): ");
            boolean isResident = in.nextLine().charAt(0) == 'Y' ? true : false;
            array[i] = new Patient(fio, age, address, diagnosis, isResident);
        }
        System.out.println(Arrays.toString(array));
        System.out.print("\n\uD83E\uDC46 Найти пациента(ов) по возрасту?(Y/N)");
        if(in.nextLine().charAt(0) == 'Y') {
            System.out.print("Введи возраст для поиска: ");
            int ageForSearch = in.nextInt();
            in.nextLine();
            System.out.printf("%n%-45s%-9s%-45s%-20s%-8s%n", "ФИО", "Возраст", "Адрес", "Диагноз", "Резидент");
            System.out.printf("-------------------------------------------------------------------------------------------------------------------------------\n");
            for (Patient patient : array) {
                if (patient.age == ageForSearch)
                    System.out.println(patient.printTable());
            }
        }
        System.out.print("\n\uD83E\uDC46 Найти пациента(ов) по имени?(Y/N)");
        if(in.nextLine().charAt(0) == 'Y') {
            System.out.print("Введите имя для поиска: ");
            String nameForSearch = in.nextLine();
            System.out.printf("%n%-45s%-9s%-45s%-20s%-8s%n","ФИО","Возраст","Адрес","Диагноз","Резидент");
            System.out.printf("-------------------------------------------------------------------------------------------------------------------------------\n");
            for(Patient patient : array) {
                if(patient.fio.contains(nameForSearch))
                    System.out.println(patient.printTable());
            }
        }
    }
}
