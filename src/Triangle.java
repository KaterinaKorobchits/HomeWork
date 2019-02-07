public class Triangle extends Figure{

    double side1;
    double side2;
    double side3;

    public Triangle(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    @Override
    double getArea() {
        if(side1 < 0 || side2 < 0 || side3 < 0 || (side3 + side1) < side2 || (side2 + side1) < side3 || (side3 + side2) < side1)
            return -1;
        else {
            double semiPer = (side1 + side2 + side3) / 2;
            return Math.sqrt(semiPer * (semiPer - side1) * (semiPer - side2) * (semiPer - side3));
        }
    }

    @Override
    String infoFigure() {
        return "Треугольник ( стороны = " + side1 + ", " + side2 + ", " + side3 + " )";
    }
}
