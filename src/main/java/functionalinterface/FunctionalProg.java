package functionalinterface;

public class FunctionalProg {

    public static void main(String args[]) {
        Try tryy = new Try(1, 2);
        System.out.println(tryy.tryToOperate( (a, b) -> (a + b) )) ;
    }
}


class Try {

    int a;
    int b;

    public Try(int a, int b) {
        this.a = a;
        this.b = b;
    }

    int tryToOperate(Operation operation) {
        return operation.operate(a, b);
    }

}


interface Operation {

    int operate(int a, int b);
}