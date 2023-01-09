package kompo;

public class InvalidFieldInputException extends Exception {
    public InvalidFieldInputException(String message) {
        super(message);
    }

    public InvalidFieldInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidFieldInputException(Throwable cause) {
        super(cause);
    }

    public InvalidFieldInputException() {

    }
}