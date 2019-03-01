import java.util.ArrayList;

public class Person {
    public String name;
    public int age;
    public boolean isStudent;
    public Wife wife;
    public ArrayList<String> pet;

    public Person() {
    }

    public Person(String name, int age, boolean isStudent, Wife wife, ArrayList<String> pet) {
        this.name = name;
        this.age = age;
        this.isStudent = isStudent;
        this.wife = wife;
        this.pet = pet;
    }

    @Override
    public String toString() {
        return "Name: " + name + "; age: " + age + "; is student?: " + isStudent +
                (wife == null ? "" : "; wife (" + wife.toString() + ")") + (pet == null ? "" : "; pets: " + pet.toString());
    }
}
