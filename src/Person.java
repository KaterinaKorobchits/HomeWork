public class Person {

    String name;
    String surname;
    String patronymic;

    public Person(String surname, String name, String patronymic) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
    }

    @Override
    public String toString() {
        return "Фамилия: " + surname + "; Имя: " + name + "; Отчество: " + patronymic;
    }
}
