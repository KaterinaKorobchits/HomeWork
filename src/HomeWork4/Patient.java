package HomeWork4;

public class Patient {
    String fio;
    int age;
    String address;
    String diagnosis;
    boolean isResident;

    public Patient() {
    }

    public Patient(String fio, int age, String address, String diagnosis, boolean isResident) {
        this.fio = fio;
        this.age = age;
        this.address = address;
        this.diagnosis = diagnosis;
        this.isResident = isResident;
    }

    @Override
    public String toString() {
        return "Пациент \"" + fio + "\" - Возраст = " + age;
    }

    public String printTable() {
        return String.format("%-45s%-9d%-45s%-20s%-8s",fio,age,address,diagnosis,isResident ? "Да" : "Нет");
    }
}
