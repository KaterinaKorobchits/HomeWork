package appliances;

public class RobotVacuumCleaner extends VacuumCLeaner {
    int batteryCapacity;

    public RobotVacuumCleaner(String brand, String model, double price, int power, int batteryCapacity) {
        super(brand, model, price, power);
        this.batteryCapacity = batteryCapacity;
    }

    void howToTurnOn() {
        System.out.println("how to turn on robot vacuum cleaner: instructions......");
    }
}
