import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;



public class BacktrackingSudokuTest {

    private SudokuBoard sudokuBoard;
    private BacktrackingSudokuSolver solve;

    @BeforeEach
    public void setUp() {

        SudokuSolver solverek = new BacktrackingSudokuSolver();
        sudokuBoard = new SudokuBoard(solverek);
    }

    @Test
    public boolean checkColumnBoardTest(SudokuBoard board) {

        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 9; i++) {
                for (int i2 = i + 1; i2 < 9; i2++) {
                    if (board.get(i, j) == board.get(i2, j)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    //TESTY DLA RZEDOW
    @Test
    public boolean checkRowBoardTest(SudokuBoard board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int j2 = j + 1; j2 < 9; j2++) {
                    if (board.get(i,j) == board.get(i, j2)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    //TESTY DLA MALYCH KWADRATOW 3X3
    @Test
    public boolean checkSmallSquaresBoardTest(SudokuBoard board) {

        for (int I = 0; I < 3; I++) {
            for (int J = 0; J < 3; J++) {
                for (int checked = 0; checked < 9; checked++) {
                    for (int compared = checked + 1; compared < 9; compared++) {
                        if (board.get(I * 3 + (checked / 3), J * 3 + (checked % 3)) ==
                                board.get(I * 3 + (compared / 3), J * 3 + (compared % 3))) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    //TESTY DLA FILLBOARD, GENEROWANIE INNYCH PLANSZY

    @Test
    public void solveRepeatBoardTest() {

        SudokuSolver solverek1 = new BacktrackingSudokuSolver();
        SudokuBoard object1 = new SudokuBoard(solverek1);
        SudokuBoard object2 = new SudokuBoard(solverek1);
        object1.solveGame();
        object2.solveGame();

        assertTrue(!object1.equals(object2));
    }
}
