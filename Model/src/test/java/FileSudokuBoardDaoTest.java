package kompo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class FileSudokuBoardDaoTest {

    private SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();

    private SudokuBoard sudokuBoard;
    private Dao<SudokuBoard> fileSudokuBoardDao;
    private SudokuBoardDaoFactory daoFactory;


    @BeforeEach
    public void setUp() {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        sudokuBoard = new SudokuBoard(solver);
        daoFactory = new SudokuBoardDaoFactory();

    }


    @Test
    public void readExceptionTest() throws DaoException, WrongFileException, WrongFieldException {
        fileSudokuBoardDao = daoFactory.getFileDao("nieznany.txt");
        assertThrows(WrongFileException.class, () -> fileSudokuBoardDao.read());
    }

    @Test
    public void writeExceptionTest() throws DaoException, WrongFileException, WrongFieldException {
        fileSudokuBoardDao = daoFactory.getFileDao("?");
        assertThrows(WrongFileException.class, () -> fileSudokuBoardDao.write(sudokuBoard));
    }

    @Test
    public void closeTest() throws Exception {

        fileSudokuBoardDao = daoFactory.getFileDao("file.txt");
        fileSudokuBoardDao.close();

    }

    }
