public class Item {

    String name;
    double square;
    double minSquare;
    double maxSquare;

    public Item(String name, double square) {
        this.name = name;
        this.square = square;
    }

    public Item(String name, double minSquare, double maxSquare) {
        this.name = name;
        this.minSquare = minSquare;
        this.maxSquare = maxSquare;
    }
}
