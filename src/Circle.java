public class Circle extends Figure {

    double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    double getArea() {
        return radius < 0 ? -1 : Math.PI * radius * radius;
    }

    @Override
    String infoFigure() {
        return "Круг ( радиус = " + radius + " )";
    }
}
