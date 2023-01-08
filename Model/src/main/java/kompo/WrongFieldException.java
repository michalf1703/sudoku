package kompo;

public class WrongFieldException extends SudokuBoardException {
    public WrongFieldException(String message) {
        super(message);
    }
}
