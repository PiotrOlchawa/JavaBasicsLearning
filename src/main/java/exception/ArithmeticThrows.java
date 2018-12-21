package exception;

public class ArithmeticThrows {

    public static void main(String[] args) {
        int a = 1;
        int b = 0;
        ArithmeticThrows arithmeticThrows = new ArithmeticThrows();
        //arithmeticThrows.divideTwoNumbers(a,b);
        System.out.println(arithmeticThrows.divideTwoNumbersThrows(a, b));
    }

    public Double divideTwoNumbersThrows(int a, int b) {
        return divide(a,b);
    }

    private Double divide(int a, int b) throws MyArithmeticException {
        double value = 0;

        if (b == 0) {
            throw new MyArithmeticException("Dzielenie przez zero !");
        }

        return (double) (a / b);
    }

    public Double divideTwoNumbers(int a, int b) throws ArithmeticException { //throws ArithmeticException nie jest konieczne

        if (b == 0) {
            throw new MyArithmeticException("Pamiętaj nie dziel przez zero");
        }
        return (double) (a / b);
    }

}
