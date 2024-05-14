package co.uos.customer.util.exception;

public class CustomException extends RuntimeException {

    public CustomException(String message) {
        super(message);
    }

    public String getMessage() {
        return super.getMessage();
    }
}