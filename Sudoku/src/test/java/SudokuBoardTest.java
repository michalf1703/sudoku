import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;


public class SudokuBoardTest {
    private SudokuBoard sudokuBoard;

    @BeforeEach
    public void setUp() {
        sudokuBoard = new SudokuBoard();
    }

    @Test
    public void getAndSetTest() {
        assertEquals(sudokuBoard.get(0, 0), 0);
        sudokuBoard.set(1, 2, 3 );
        assertEquals(sudokuBoard.get(1, 2), 3);
    }

    @Test
    public void equalsTest() {
        assertTrue(sudokuBoard.equals(sudokuBoard));
    }
    //TESTY DLA KOLUMN

    @Test
    public void toStringTest() {
        String tmp = "";
        for(int i = 0; i < 9; i++)
        {
            tmp += "0 0 0 0 0 0 0 0 0 \n";
        }
        assertTrue(sudokuBoard.toString().equals(tmp));
    }

    @Test
    public void hashCodeTest() {
        assertEquals(sudokuBoard.hashCode(), 0);
    }
}
