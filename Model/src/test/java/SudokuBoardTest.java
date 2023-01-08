package kompo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class SudokuBoardTest {
    private SudokuBoard sudokuBoard;

    private SudokuBoard sudokuBoardSecond;
    private SudokuColumn sudokuColumn;
    private SudokuColumn sudokuColumnSecond;
    private SudokuBox sudokuBox;
    private SudokuBox sudokuBoxSecond;
    private SudokuRow sudokuRow;
    private SudokuRow sudokuRowSecond;



    private SudokuBox makeObjectWithValidList1() {
        return new SudokuBox(Arrays.asList(
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9)));
    }

    private SudokuColumn makeObjectWithValidList() {
        return new SudokuColumn(Arrays.asList(
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9)));
    }
    private SudokuRow makeObjectWithValidList2() {
        return new SudokuRow(Arrays.asList(
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9)));
    }


    @BeforeEach
    public void setUp() {
        SudokuSolver solvik = new BacktrackingSudokuSolver();
       sudokuBoard = new SudokuBoard(solvik);
    }




    @Test
    public void getAndSetTest() {
        assertEquals(sudokuBoard.get(0, 0), 0);
        sudokuBoard.set(1, 2, 3 );
        assertEquals(sudokuBoard.get(1, 2), 3);
    }


    @Test
    public void toStringTest() {
        assertNotNull(sudokuBoard.toString());
    }
    @Test
    public void equalsTest() throws WrongFieldException {
        assertTrue(sudokuBoard.equals(sudokuBoard));

        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard board1 = new SudokuBoard(backtrackingSudokuSolver);
        board1.solveGame();

        BacktrackingSudokuSolver backtrackingSudokuSolver1 = new BacktrackingSudokuSolver();
        SudokuBoard board2 = new SudokuBoard(backtrackingSudokuSolver1);
        board1.solveGame();

        assertTrue(board1.equals(board1));

        //porownanie dwoch roznych boardow
        assertFalse(board1.equals(board2));

        assertTrue(board1.equals(board1));

        assertFalse(board1.equals(null));
        assertFalse(board1.equals(backtrackingSudokuSolver));
    }


    @Test
    public void hashCodeTest() throws WrongFieldException {
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
    public void getRowTest() throws WrongFieldException {
        assertNotNull(sudokuBoard.getRow(3));
    }
    @Test
    public void getColumnTest() throws WrongFieldException {
        assertNotNull(sudokuBoard.getColumn(5));
    }

    @Test
    public void getBoxTest() throws WrongFieldException {
        assertNotNull(sudokuBoard.getBox(1, 1));
    }
    @Test
    public void CloneTestColumn() throws WrongFieldException {
        sudokuColumn = makeObjectWithValidList();
        try {
            sudokuColumnSecond = (SudokuColumn) sudokuColumn.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }

        assertTrue(sudokuColumn.equals(sudokuColumnSecond)
                && sudokuColumnSecond.equals(sudokuColumn));
    }

    @Test
    public void CloneTestBox() throws WrongFieldException {
        sudokuBox = makeObjectWithValidList1();
        try {
            sudokuBoxSecond = (SudokuBox) sudokuBox.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }

        assertTrue(sudokuBox.equals(sudokuBoxSecond)
                && sudokuBoxSecond.equals(sudokuBox));
    }

    @Test
    public void CloneTestRow() throws WrongFieldException {
        sudokuRow = makeObjectWithValidList2();
        try {
            sudokuRowSecond = (SudokuRow) sudokuRow.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }

        assertTrue(sudokuRow.equals(sudokuRowSecond)
                && sudokuRowSecond.equals(sudokuRow));
    }

    @Test
    public void cloneTest()  throws WrongFieldException {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(sudokuBoard);
        sudokuBoardSecond = (SudokuBoard) sudokuBoard.clone();

        assertTrue(sudokuBoard.equals(sudokuBoardSecond)
                && sudokuBoardSecond.equals(sudokuBoard));
    }

}
