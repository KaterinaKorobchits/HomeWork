package appliances;

public class Scale extends MeasuringDevices {
    String weighingLimit;

    public Scale(String brand, String model, double price, String accuracy, String weighingLimit) {
        super("Scale", brand, model, price, accuracy);
        this.weighingLimit = weighingLimit;
    }

    void whatIsItFor() {
        System.out.println("For weight scale any things");
    }

    void howToTurnOn() {
        System.out.println("how to turn on scale: instructions......");
    }
}
