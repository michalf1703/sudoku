package kompo;

public class SudokuBoardDaoFactory {
    public SudokuBoardDaoFactory() {
    }

    public static Dao<SudokuBoard> getFileDao(String filename) {
        return new FileSudokuBoardDao(filename);
    }

    public  static Dao<SudokuBoard> getDatabaseDao(String filename) {
        return new JdbcSudokuBoardDao(filename);
    }
}

