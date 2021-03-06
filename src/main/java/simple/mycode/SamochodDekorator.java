package com.in28minutes.java.mycode;
interface CarOrder {
    int getPrice();
    String getDescription();
}

class BasicCarOrder implements CarOrder {
    @Override
    public int getPrice() {
        return 45000;
    }
    @Override
    public String getDescription() {
        return "Basic car ";
    }
}

abstract class AbstractBasicConfigurator implements CarOrder {
    CarOrder carOrder;
    public AbstractBasicConfigurator(CarOrder carOrder) {
        this.carOrder = carOrder;
    }
    @Override
    public int getPrice() {
        return carOrder.getPrice();
    }
    @Override
    public String getDescription() {
        return carOrder.getDescription();
    }
}

class AirConditionDecorator extends AbstractBasicConfigurator {
    public AirConditionDecorator(CarOrder carOrder) {
        super(carOrder);
    }
    @Override
    public int getPrice() {
        return super.getPrice() + 1000;
    }
    @Override
    public String getDescription() {
        return super.getDescription() + " Z Klimatyzacją";
    }
}

class TiresDecorator extends AbstractBasicConfigurator {
    public TiresDecorator(CarOrder carOrder) {
        super(carOrder);
    }
    @Override
    public int getPrice() {
        return super.getPrice() + 800;
    }
    @Override
    public String getDescription() {
        return super.getDescription() + " Z Oponami Zimowymi";
    }
}

public class SamochodDekorator {
    public static void main(String args[]) {
        CarOrder carOrder = new BasicCarOrder();
        carOrder = new AirConditionDecorator(carOrder);
        carOrder = new TiresDecorator(carOrder);
        System.out.println(carOrder.getDescription());
        System.out.println(carOrder.getPrice());
    }
}
