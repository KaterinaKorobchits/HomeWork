package appliances;

public class WashingMachine extends Appliances {
    double capacity;

    public WashingMachine(String type, String brand, String model, double price, double capacity) {
        super("Washing machine", brand, model, price);
        this.capacity = capacity;
    }

    void whatIsItFor() {
        System.out.println("For washing, rinsing and spinning any things");
    }

    void howToTurnOn() {
        System.out.println("how to turn on washing machine: instructions......");
    }
}
