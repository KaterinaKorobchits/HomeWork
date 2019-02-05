package appliances;

public abstract class MeasuringDevices extends Appliances {
    String accuracy;

    public MeasuringDevices(String type, String brand, String model, double price, String accuracy) {
        super(type, brand, model, price);
        this.accuracy = accuracy;
    }


}
