import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;


public class SudokuBoardTest {
    private SudokuBoard sudokuBoard;

    @BeforeEach
    public void setUp() {
        sudokuBoard = new SudokuBoard();
    }

    //TESTY DLA KOLUMN
    @Test
    public void checkColumnBoardTest() {
        sudokuBoard.fillBoard();
        int[][] board = sudokuBoard.getCopyBoard();


        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 9; i++) {
                for (int i2 = i + 1; i2 < 9; i2++) {
                    if (board[i][j] == board[i2][j]) {
                        fail("There is error in column number " + j );
                    }
                }
            }
        }
    }
    //TESTY DLA RZEDOW
    @Test
    public void checkRowBoardTest() {
        sudokuBoard.fillBoard();
        int[][] board = sudokuBoard.getCopyBoard();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int j2 = j + 1; j2 < 9; j2++) {
                    if (board[i][j] == board[i][j2]) {
                        fail("There is error in column number " + i);
                    }
                }
            }
        }
    }
    //TESTY DLA MALYCH KWADRATOW 3X3
    @Test
    public void checkSmallSquaresBoardTest() {
        sudokuBoard.fillBoard();
        int[][] board = sudokuBoard.getCopyBoard();

        for (int I = 0; I < 3; I++) {
            for (int J = 0; J < 3; J++) {
                for (int checked = 0; checked < 9; checked++) {
                    for (int compared = checked + 1; compared < 9; compared++) {
                        if (board[I * 3 + (checked / 3)][J * 3 + (checked % 3)] ==
                                board[I * 3 + (compared / 3)][J * 3 + (compared % 3)]) {
                            fail("There is error in square(" + I + ", " + J + ")");
                        }
                    }
                }
            }
        }
    }
    //TESTY DLA FILLBOARD, GENEROWANIE INNYCH PLANSZY
    @Test
    public void fillBoardRepeatTest() {
        int[][] testTab1;
        int[][] testTab2;

        sudokuBoard.fillBoard();
        testTab1 = sudokuBoard.getCopyBoard();
        sudokuBoard.fillBoard();
        testTab2 = sudokuBoard.getCopyBoard();

        boolean theSame = true;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (testTab1[i][j] != testTab2[i][j])
                    theSame = false;
            }
        }
        assertTrue(!theSame);
    }
}
