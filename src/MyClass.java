import java.util.ArrayList;

public class MyClass {

    String name;
    ArrayList<MyMethod> listMethod = new ArrayList<MyMethod>();

    public MyClass(String name) {
        this.name = name;
    }

    public void addMethod(MyMethod myMethod) {
        listMethod.add(myMethod);
    }

    @Override
    public String toString() {
        return "class " + name + (listMethod.size() > 0 ? (" has method" + (listMethod.size() == 1 ? " " : "s ") + listMethod.toString()) : " hasn't methods");
    }
}
