import java.util.HashMap;

public class Dairy {
    private static Dairy ourInstance = new Dairy();
    HashMap<Integer, Day> dairy;

    public static Dairy getInstance() {
        return ourInstance;
    }

    private Dairy() {
        dairy = new HashMap<>();
    }
}
