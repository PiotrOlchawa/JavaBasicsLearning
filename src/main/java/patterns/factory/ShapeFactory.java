package patterns.factory;

public class ShapeFactory {
    public static final String CIRCLE = "CIRCLE";
    public static final String SQUARE = "SQUARE";
    public static final String RECTANGLE = "RECTANGLE";

    public final Shape makeShape(final String shapeClass) {
        switch (shapeClass) {
            case CIRCLE:
                return new Circle("The rounded circle", 4.50);
            case SQUARE:
                return new Square("The angular square", 7.0);
            case RECTANGLE:
                return new Rectangle("The long rectangle", 15.0, 2.50);
            default:
                return null;
        }
    }
}

class Shape {

}

class Circle extends Shape {
    String name;
    double diameter;

    public Circle(String name, double diameter) {
        this.name = name;
        this.diameter = diameter;
    }
}

class Square extends Shape {
    String name;
    double lenght;

    public Square(String name, double lenght) {
        this.name = name;
        this.lenght = lenght;
    }
}

class Rectangle extends Shape {
    String name;
    double lenghta;
    double getLenghtab;

    public Rectangle(String name, double lenghta, double getLenghtab) {
        this.name = name;
        this.lenghta = lenghta;
        this.getLenghtab = getLenghtab;
    }
}