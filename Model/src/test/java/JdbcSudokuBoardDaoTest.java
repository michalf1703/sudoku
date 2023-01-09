import kompo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class JdbcSudokuBoardDaoTest {

    private SudokuBoard sudokuBoard;

    private SudokuBoard secondSudokuBoard;

    @BeforeEach
    public void setUp() {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        sudokuBoard = new SudokuBoard(solver);
        sudokuBoard.solveGame();

    }


    @Test
    public void writeExceptionTest(){

        assertThrows(DaoException.class, () -> SudokuBoardDaoFactory.getDatabaseDao("?").write(sudokuBoard));
        assertThrows(DaoException.class, () -> SudokuBoardDaoFactory.getDatabaseDao("SudokuTest").write(null));
    }

    @Test
    public void closeTest() throws Exception{

        SudokuBoardDaoFactory.getDatabaseDao("SudokuTest").close();

    }
    private SudokuBoard generateBoard() throws InvalidFieldInputException {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();
        return board;
    }

    @Test
    public void testDatabaseReadWrite() throws IOException, InvalidFieldInputException {

        Files.deleteIfExists(Paths.get("./SudokuDatabase"));
        SudokuBoard boardA = generateBoard();
        SudokuBoard boardB = generateBoard();


        try (Dao<SudokuBoard> dbDao = SudokuBoardDaoFactory.getDatabaseDao("Test1")) {
            dbDao.write(boardA);
            System.out.println("Saved to database");
        } catch (DaoException e) {
            System.out.println("Cannot save to database");
        } catch (Exception e) {
            System.out.println("Unknown error.");
        }

        try (Dao<SudokuBoard> dbDao = SudokuBoardDaoFactory.getDatabaseDao("Test1");) {
            SudokuBoard board1 = dbDao.read();
            //assertEquals(boardA, board1);
            System.out.println("Loaded from database");
        } catch (DaoException ex) {
            System.out.println("Cannot load from database");
        } catch (Exception e) {
            System.out.println("Unknown error.");
        }

    }

}
