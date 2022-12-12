import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuBoardRepositoryTest {

    @Test
    public void cloneTest() {
    SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();
        SudokuBoard board1 = board.clone();
        assertFalse(board1.equals(null));
        assertTrue(board1.equals(board));
        board.set(0,0,board.get(1,1));
        assertFalse(board1.equals(board));
    }

    @Test
    public void createInstanceTest() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        SudokuBoardRepository boardx = new SudokuBoardRepository(board);
        SudokuBoard board1 = boardx.createInstance();
        assertFalse(board1.equals(null));
        assertTrue(board1.equals(board));
        board.set(0,0,board.get(1,1));
        assertFalse(board1.equals(board));
    }
}
