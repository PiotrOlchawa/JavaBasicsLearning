package exception;

public class MyArithmeticException extends ArithmeticException {

    // Parameterless Constructor
    public MyArithmeticException() {}

    // Constructor that accepts a message
    public MyArithmeticException(String message)
    {
        super(message);
    }
}
