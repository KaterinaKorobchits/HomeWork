package appliances;

public class Thermometer extends MeasuringDevices {
    boolean containsMercury;

    public Thermometer(String brand, String model, double price, String accuracy, boolean containsMercury) {
        super("Thermometer", brand, model, price, accuracy);
        this.containsMercury = containsMercury;
    }

    void whatIsItFor() {
        System.out.println("For measure temperature");
    }

    void howToTurnOn() {
        System.out.println("how to turn on thermometer: instructions......");
    }
}
