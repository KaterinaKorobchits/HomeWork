import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Student implements Serializable {
    String name;
    String surName;
    Date dateOfBirth;
    final private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public Student(String name, String surName, Date dateOfBirth) {
        this.name = name;
        this.surName = surName;
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Имя: " + name + "; Фамилия: " + surName + "; Дата рождения: " + dateFormat.format(dateOfBirth) + "\n";
    }


}
