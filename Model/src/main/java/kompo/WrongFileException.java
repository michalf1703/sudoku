package kompo;

public class WrongFileException extends DaoException {
    public WrongFileException(String messagee, Throwable cause) {
        super(messagee,cause);
    }
}

