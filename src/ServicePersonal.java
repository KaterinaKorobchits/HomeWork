public class ServicePersonal extends Person{

    private String position;

    public ServicePersonal(String name) {
        super(name);
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "Сотрудник обслуживающего персонала " + name + ", должность: " + position;
    }
}
