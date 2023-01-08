package kompo;

import kompo.SudokuBoardException;

public class WrongFieldsException extends SudokuBoardException {
    public WrongFieldsException(String message) {
        super(message);
    }

}
