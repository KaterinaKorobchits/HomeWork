public class Professor extends Person{

    private String experience;
    private String discipline;

    public Professor(String name) {
        super(name);
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getExperience() {
        return experience;
    }

    public String getDiscipline() {
        return discipline;
    }

    @Override
    public String toString() {
        return "Профессор " + name + ", обладающий опытом: \"" + experience + "\", преподает дисциплину " + discipline;
    }
}
