package appliances;

public abstract class Appliances {
    final String type;
    String brand;
    String model;
    double price;

    public Appliances(String type, String brand, String model, double price) {
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.price = price;
    }

    abstract void whatIsItFor();

    abstract  void howToTurnOn();
}
