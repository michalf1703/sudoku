package kompo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ResourceBundle;
import java.util.logging.Logger;



public class FileSudokuBoardDao implements Dao<SudokuBoard> {

    private String filename;
    private static final Logger logger = Logger.getLogger(FileSudokuBoardDao.class.getName());


    public FileSudokuBoardDao(String filename) {
        this.filename = filename;
    }

    @Override
    public SudokuBoard read() throws DaoException {
        SudokuBoard obj = null;
        ResourceBundle bundle = ResourceBundle.getBundle("Language");
        try (FileInputStream fileInputSream = new FileInputStream(filename);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputSream)) {
            obj = (SudokuBoard) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new WrongFileException(bundle.getString("NotFoundFile"), e.getCause());
        } catch (IOException e) {
            throw new WrongFileException(bundle.getString("/IOException"), e.getCause());
        }

        return obj;
    }


    @Override
    public void write(SudokuBoard obj) throws DaoException {
        ResourceBundle bundle = ResourceBundle.getBundle("Language");
        try (FileOutputStream fileOutputStream = new FileOutputStream(filename);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(obj);
        } catch (IOException e) {
            throw new WrongFileException(bundle.getString("/IOException"), e.getCause());
        }

    }

    @Override
    public void close() {
        ResourceBundle bundle = ResourceBundle.getBundle("Language");

        logger.info(bundle.getString("FileClose"));

    }
}