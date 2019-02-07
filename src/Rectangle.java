public class Rectangle extends Figure {

    double width;
    double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    double getArea() {
        return (width < 0 ||  height < 0) ? -1 : width * height;
    }

    @Override
    String infoFigure() {
        return "Прямоугольник ( стороны =  " + width + ", " + height + " )";
    }
}
