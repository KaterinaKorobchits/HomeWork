public class Square extends Figure {

    double side;

    public Square(double side) {
        this.side = side;
    }

    @Override
    double getArea() {
        return side < 0 ? -1 : side * side;
    }

    @Override
    String infoFigure() {
        return "Квадрат ( сторона = " + side + " )";
    }
}
