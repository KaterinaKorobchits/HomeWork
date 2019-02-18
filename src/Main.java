import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Main {

    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        //firsPart();
        try {
            secondPart();
        }catch (IllimunanceTooMuchException ex) {
            System.out.println(ex.getMessage());
        } catch (SpaceUsageTooMuchException ex) {
            System.out.println(ex.getMessage());
        }
    }

    static void firsPart() {
        ArrayList<String> list = new ArrayList<>();
        System.out.println("Введите массив строк (для окончания ввода введите EXIT) - >");
        while (true){
            String st = in.nextLine();
            if(st.equals("EXIT"))
                break;
            else
                list.add(st);
        }
        /*for(ListIterator<String> i = list.listIterator(); i.hasNext();) {
            i.set(i.next().replaceAll("a", ""));
        }*/
        for(int i = 0; i < list.size(); i++){
            list.set(i,list.get(i).replaceAll("a", ""));
        }
        System.out.println(list.toString());
    }

    static void secondPart() throws IllimunanceTooMuchException, SpaceUsageTooMuchException {
        Building building1 = new Building("Здание 1");
        Item item1 = new Item("Кровать", 13);
        Item item2 = new Item("Стол письменный", 4,5.2);
        Lightbulb bulb1 = new Lightbulb(150);
        Lightbulb bulb2 = new Lightbulb(250);
        building1.addRoom("Комната 1",40.6, 1);
        building1.addRoom("Комната 2",70.6, 4);
        building1.getRoom("Комната 1").add(item1);
        building1.getRoom("Комната 1").add(item2);
        building1.getRoom("Комната 1").add(bulb1);
        building1.print();
    }
}
