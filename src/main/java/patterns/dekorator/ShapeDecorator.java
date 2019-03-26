package patterns.dekorator;

interface Shape {

    void draw();

}

class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Drawing pure rectandle");
    }

}

abstract class RectangleDecorator implements Shape {

    Shape shape;

    public RectangleDecorator(Shape shape) {
        this.shape = shape;
    }

    @Override
    public void draw() {
        shape.draw();
    }

}

class EnhancedRectangleDecorator extends RectangleDecorator {


    public EnhancedRectangleDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void draw() {
        shape.draw();
        setBorder();
    }

    private void setBorder() {
        System.out.println("Printing Border");
    }
}

class XEnhancedRectangleDecorator extends EnhancedRectangleDecorator{

    public XEnhancedRectangleDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void draw(){
        super.draw();
        System.out.println("With Outline");
    }

}

public class ShapeDecorator {

    public static void main(String [] args){

        Shape shape = new Rectangle();
        //shape.draw();

        Shape decoratedShape = new XEnhancedRectangleDecorator(shape);

        decoratedShape.draw();
    }

}



















