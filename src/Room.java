import java.util.ArrayList;

public class Room {
    private static final int MaxIlluminance = 4000;
    private static final int IlluminanceOfWindow = 700;

    String name;
    double square;
    int numberOfWindows;
    ArrayList<Lightbulb> listOfLightbulbs = new ArrayList<>();
    ArrayList<Item> listOfItems = new ArrayList<>();

    public Room(String name, double square, int numberOfWindows) throws IllimunanceTooMuchException {
        if(numberOfWindows * IlluminanceOfWindow > MaxIlluminance)
            throw new IllimunanceTooMuchException(name + " *** Превышено количество окон!");
        if(numberOfWindows < 1)
            throw new IllimunanceTooMuchException(name + " *** Должно быть хотя бы 1 окно!");
        this.name = name;
        this.square = square;
        this.numberOfWindows = numberOfWindows;
    }

    public void add(Lightbulb bulb) throws IllimunanceTooMuchException {
        if(getIlluminance() + bulb.illuminance <= MaxIlluminance)
            listOfLightbulbs.add(bulb);
        else
            throw new IllimunanceTooMuchException(name + " *** Уже достаточно света!");
    }

    public void add(Item item) throws SpaceUsageTooMuchException {
        if((getFreeSquare() - (item.square != 0.0 ? item.square : item.maxSquare ))/square >= 0.3) {
            listOfItems.add(item);
        }
        else
            throw new SpaceUsageTooMuchException(name + "/" + item.name + " *** Некуда разместить!");
    }

    private int getIlluminance() {
        int summ = numberOfWindows * IlluminanceOfWindow;
        for(Lightbulb bulb: listOfLightbulbs)
            summ += bulb.illuminance;
        return summ;
    }

    private double getFreeSquare() {
        double occupiedSpace = 0.0;
        if(listOfItems.size() == 0)
            return square;
        else
            for(Item item: listOfItems)
                occupiedSpace += (item.square != 0.0) ? item.square : item.maxSquare;
        return square - occupiedSpace;
    }

    private String squareToString() {
        double occupiedSpace = 0.0;
        double occupiedMinSpace = 0.0;
        if(listOfItems.size() == 0)
            return "свободно 100%";
        else
            for(Item item: listOfItems) {
                occupiedSpace += (item.square != 0.0) ? item.square : item.maxSquare;
                occupiedMinSpace += (item.square != 0.0) ? item.square : item.minSquare;
            }
            return "занято " + (occupiedMinSpace != 0 ? occupiedMinSpace + "-" + occupiedSpace +
                    " м^2, гарантированно " : occupiedSpace + " м2, ") + "свободно " + (square - occupiedSpace) + " м^2 или " +
                    (square - occupiedSpace)/square + "% площади";

    }

    public void print() {
        System.out.println(name);
        System.out.print("\tОсвещенность = " + getIlluminance() + " (" + numberOfWindows + " окон по 700 лк");
        int size = listOfLightbulbs.size();
        if(size != 0) {
            System.out.print(", лампочки ");
            for (int i = 0; i < size - 1; i++)
                System.out.print(listOfLightbulbs.get(i).illuminance + " лк, ");
            System.out.println(listOfLightbulbs.get(size-1).illuminance + " лк)");
        } else
            System.out.println(")");
        System.out.println("\tПлощадь = " + square + " м^2 (" + squareToString() + ")");
        if(listOfItems.size() == 0)
            System.out.println("\tМебели нет");
        else {
            System.out.println("\tМебель:");
            for (Item item: listOfItems)
                System.out.println("\t\t" + item.name + " (площадь " + (item.square != 0 ? item.square : "от " + item.minSquare +
                        " м^2 до " + item.maxSquare) + " м^2)");
        }
    }
}
