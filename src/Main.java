import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;

public class Main {

    public static void main(String[] args) throws IllegalAccessException {
        Class myPerson = Person.class;
        Person person = new Person();
        Field[] fields = myPerson.getDeclaredFields();
        Method[] methods = myPerson.getDeclaredMethods();
        System.out.print("Поля: ");
        for (Field f: fields)
            System.out.print(f.getName() + " (" + f.getType() + ")    ");
        System.out.print("\nМетоды: ");
        for (Method m: methods)
            System.out.print(m.toString() + "    ");
        try {
            Field name = myPerson.getDeclaredField("name");
            name.setAccessible(true);
            name.set(person, "Babi");
            Field age = myPerson.getDeclaredField("age");
            age.setAccessible(true);
            age.set(person, 29);
            Field height = myPerson.getDeclaredField("height");
            height.setAccessible(true);
            height.set(person, 178.9f);
            Method getName = myPerson.getMethod("getName", null);
            Method getAge = myPerson.getMethod("getAge", null);
            Method getHeight = myPerson.getMethod("getHeight", null);
            System.out.println("\nName: " + getName.invoke(person) + ", age: " + getAge.invoke(person) + ", height: " + getHeight.invoke(person));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
