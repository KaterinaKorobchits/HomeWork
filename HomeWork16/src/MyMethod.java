public class MyMethod {

    String name;
    String value;

    public MyMethod(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return name + (value == null ? "" : ": " + value);
    }
}
