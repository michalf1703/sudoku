package kompo;

public class WrongFileException extends DaoException {
    public WrongFileException(String message, Throwable cause) {
        super(message, cause);
    }
}

