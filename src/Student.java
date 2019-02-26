public class Student extends Person{

    private int course;
    private String specialization;

    public Student(String name) {
        super(name);
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getCourse() {
        return course;
    }

    public String getSpecialization() {
        return specialization;
    }

    @Override
    public String toString() {
        return "Голодный студент " + name + " " + course + "-го курса, обучающийся по специальности " + specialization;
    }
}
