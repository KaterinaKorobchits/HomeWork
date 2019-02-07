public abstract class Figure {

    abstract double getArea();
    abstract String infoFigure();
    void print() {
        System.out.println("Фигура: " + infoFigure() + ", площадь = " + getArea());
    }
}
