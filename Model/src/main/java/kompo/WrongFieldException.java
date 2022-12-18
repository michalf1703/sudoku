package kompo;

public class WrongFieldException extends IllegalArgumentException {
    public WrongFieldException(final String message) {
        super(message);
    }
}
