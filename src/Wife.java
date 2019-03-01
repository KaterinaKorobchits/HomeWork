public class Wife {
    public String name;
    public int age;

    public Wife() {
    }

    public Wife(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "name: " + name + "; age: " + age;
    }
}
