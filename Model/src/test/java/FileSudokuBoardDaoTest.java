package kompo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class FileSudokuBoardDaoTest {

    private SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();

    private SudokuBoard sudokuBoard;
    private Dao<SudokuBoard> fileSudokuBoardDao;
    private SudokuBoard sudokuBoardSecond;

  /*  @Test
    public void writeReadTest() {
        fileSudokuBoardDao = factory.getFileDao("xxx");
        fileSudokuBoardDao.write(sudokuBoard);


        assertEquals(sudokuBoard, sudokuBoardSecond);
    }
*/
    @Test
    public void readIOExceptionTest() {
        Exception exception = Assertions.assertThrows(RuntimeException.class,()->{
            fileSudokuBoardDao = factory.getFileDao("uwu");
            fileSudokuBoardDao.read();
        });
        Assertions.assertEquals("java.io.FileNotFoundException: uwu.txt (Nie można odnaleźć określonego pliku)",exception.getMessage());

    }



}