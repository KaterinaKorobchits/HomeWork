package appliances;

public class VacuumCLeaner extends Appliances {
    int power;
    public VacuumCLeaner(String brand, String model, double price, int power) {
        super("Vacuum Cleaner", brand, model, price);
        this.power = power;
    }

    void whatIsItFor() {
        System.out.println("For cleaning dust and dirt from surfaces due to suction by air flow");
    }

    void howToTurnOn() {
        System.out.println("how to turn on vacuum cleaner: instructions......");
    }
}
