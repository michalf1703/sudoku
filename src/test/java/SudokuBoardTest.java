import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


public class SudokuBoardTest {
    private SudokuBoard sudokuBoard;
    private SudokuBoard sudokuBoardSecond;
    public int [][] exampleBoardGood = {
            {5,3,4,6,7,8,9,1,2},
            {6,7,2,1,9,5,3,4,8},
            {1,9,8,3,4,2,5,6,7},
            {8,5,9,7,6,1,4,2,3},
            {4,2,6,8,5,3,7,9,1},
            {7,1,3,9,2,4,8,5,6},
            {9,6,1,5,3,7,2,8,4},
            {2,8,7,4,1,9,6,3,5},
            {3,4,5,2,8,6,1,7,9}
    };

    public int [][] exampleBoardGood2 = {
            {5,3,4,6,7,8,9,1,2},
            {6,7,2,1,9,5,3,4,8},
            {1,9,8,3,4,2,5,6,7},
            {8,5,9,7,6,1,4,2,3},
            {4,2,6,8,5,3,7,9,1},
            {7,1,3,9,2,4,8,5,6},
            {9,6,1,5,3,7,2,8,4},
            {2,8,7,4,1,9,6,3,5},
            {3,4,5,2,8,6,1,7,9}
    };
    public int [][] exampleBoardWrongRow = {
            {5,5,4,6,7,8,9,1,2},
            {6,7,2,1,9,5,3,4,8},
            {1,9,8,3,4,2,5,6,7},
            {8,5,9,7,6,1,4,2,3},
            {4,2,6,8,5,3,7,9,1},
            {7,1,3,9,2,4,8,5,6},
            {9,6,1,5,3,7,2,8,4},
            {2,8,7,4,1,9,6,3,5},
            {3,4,5,2,8,6,1,7,9}
    };
    public int [][] exampleBoardWrongColumn = {
            {6,3,4,5,7,8,9,1,2},
            {6,7,2,1,9,5,3,4,8},
            {1,9,8,3,4,2,5,6,7},
            {8,5,9,7,6,1,4,2,3},
            {4,2,6,8,5,3,7,9,1},
            {7,1,3,9,2,4,8,5,6},
            {9,6,1,5,3,7,2,8,4},
            {2,8,7,4,1,9,6,3,5},
            {3,4,5,2,8,6,1,7,9}
    };
    public int [][] exampleBoardWrongSquare = {
            {0,3,4,6,7,8,9,1,2},
            {6,0,2,1,9,5,3,4,8},
            {1,9,8,3,4,2,5,6,7},
            {8,5,9,7,6,1,4,2,3},
            {4,2,6,8,5,3,7,9,1},
            {7,1,3,9,2,4,8,5,6},
            {9,6,1,5,3,7,2,8,4},
            {2,8,7,4,1,9,6,3,5},
            {3,4,5,2,8,6,1,7,9}
    };

    SudokuBoard boardzik = new SudokuBoard(exampleBoardGood);
    SudokuBoard boardzik1 = new SudokuBoard(exampleBoardGood2);
    @BeforeEach
    public void setUp() {
        SudokuSolver solvik = new BacktrackingSudokuSolver();
        sudokuBoard = new SudokuBoard(solvik);
    }


   @Test
    public void checkValidTest(){
        assertTrue(sudokuBoard.checkBoard(exampleBoardGood));
        assertFalse(sudokuBoard.checkBoard(exampleBoardWrongRow));
        assertFalse(sudokuBoard.checkBoard(exampleBoardWrongColumn));
        assertFalse(sudokuBoard.checkBoard(exampleBoardWrongSquare));
    }


    @Test
    public void getAndSetTest() {
        assertEquals(sudokuBoard.get(0, 0), 0);
        sudokuBoard.set(1, 2, 3 );
        assertEquals(sudokuBoard.get(1, 2), 3);
    }

    @Test
    public void ConstructorTest() {
        boolean z = true;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++){
                if (boardzik.get(i,j) != boardzik1.get(i,j)) {
                    z = false;
                    break;
                }
            }
        }
    }


    @Test
    public void toStringTest() {
        assertNotNull(sudokuBoard.toString());
    }
    @Test
    public void equalsTest() {
        assertTrue(sudokuBoard.equals(sudokuBoard));

        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard board1 = new SudokuBoard(backtrackingSudokuSolver);
        board1.solveGame();

        BacktrackingSudokuSolver backtrackingSudokuSolver1 = new BacktrackingSudokuSolver();
        SudokuBoard board2 = new SudokuBoard(backtrackingSudokuSolver1);
        board1.solveGame();

        //reflexive: an object must equal itself
        assertTrue(board1.equals(board1));


        //porownanie dwoch roznych boardow
        assertFalse(board1.equals(board2));

        assertTrue(board1.equals(board1));
    }
    @Test
    public void hashCodeTest() {
        List<List<SudokuField>> board = Arrays.asList(new List[9]);

        for (int i = 0; i < 9; i++) {
            board.set(i, Arrays.asList(new SudokuField[9]));
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board.get(i).set(j, new SudokuField());
            }
        }

        assertTrue(sudokuBoard.hashCode() != board.hashCode());
    }


    @Test
    public void getRowTest() {
        assertNotNull(sudokuBoard.getRow(3));
    }
    @Test
    public void getColumnTest() {
        assertNotNull(sudokuBoard.getColumn(5));
    }

    @Test
    public void getBoxTest() {
        assertNotNull(sudokuBoard.getBox(1, 1));
    }


}
