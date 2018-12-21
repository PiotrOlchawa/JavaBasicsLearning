package patterns.factory;

enum ChocolateType {
    MILK, DARK
}

interface ChocolateFactory {
    Chocolate produceChocolate(ChocolateType type);
}

class WedelFactory implements ChocolateFactory {

    @Override
    public Chocolate produceChocolate(ChocolateType type) {

        Chocolate chocolate = null;

        switch (type) {
            case MILK: {
                chocolate = new MilkChocolate();
                break;
            }
            case DARK: {
                chocolate = new DarkChocolate();
                break;
            }
        }
        return chocolate;
    }
}

abstract class Chocolate {

    protected ChocolateType type;

    public abstract Chocolate getChocolate();
}

class MilkChocolate extends Chocolate {

    public MilkChocolate() {
        type = ChocolateType.MILK;
    }
    @Override
    public Chocolate getChocolate() {
        return this;
    }
}

class DarkChocolate extends Chocolate {

    public DarkChocolate() {
        type = ChocolateType.DARK;
    }

    @Override
    public Chocolate getChocolate() {
        return this;
    }
}

public class Czekolada {

    public static void main(String args[]) {
        ChocolateFactory factory = new WedelFactory();
        Chocolate milk = factory.produceChocolate(ChocolateType.MILK);
        Chocolate dark = factory.produceChocolate(ChocolateType.DARK);
    }
}