
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class FileSudokuBoardDaoTest {

    private SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();

    private SudokuBoard sudokuBoard;
    private Dao<SudokuBoard> fileSudokuBoardDao;
    private SudokuBoard sudokuBoardSecond;

    @Test
    public void writeReadTest() {
        fileSudokuBoardDao = factory.getFileDao("xxx");
        fileSudokuBoardDao.write(sudokuBoard);
        sudokuBoardSecond = fileSudokuBoardDao.read();

        assertEquals(sudokuBoard, sudokuBoardSecond);
    }

    @Test
    public void readIOExceptionTest() {
        Exception exception = Assertions.assertThrows(RuntimeException.class,()->{
        fileSudokuBoardDao = factory.getFileDao("uwu");
        fileSudokuBoardDao.read();
        });
        Assertions.assertEquals("java.io.FileNotFoundException: uwu.txt (The system cannot find the file specified)",exception.getMessage());

    }



}