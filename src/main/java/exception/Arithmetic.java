package exception;

public class Arithmetic {


    public int divideTwoDoubleNumbers(int a, int b) {
        try {
            if (b == 0) {
                throw new MyArithmeticException("Dzielisz przez zero");
            }
            return a / b;
        } catch (MyArithmeticException e) {
            System.out.println(e.getMessage());
            return 10;
        }
    }

    public static void main(String[] args) {
        Arithmetic arithmetic = new Arithmetic();
        System.out.println(arithmetic.divideTwoDoubleNumbers(1, 0));

    }
}

