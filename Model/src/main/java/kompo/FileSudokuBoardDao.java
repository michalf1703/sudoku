package kompo;

import java.io.*;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {

    private String filename;

    public FileSudokuBoardDao(String filename) {
        this.filename = filename;
    }

    @Override
    public SudokuBoard read() throws WrongFileException {
        SudokuBoard obj = null;

        try (FileInputStream fileInputStream = new FileInputStream(filename);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            obj = (SudokuBoard) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new WrongFileException(e);
        } catch (IOException e) {
            throw new WrongFileException(e);
        }

        return obj;
    }

    @Override
    public void write(SudokuBoard obj) throws WrongFileException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filename);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(obj);
        } catch (IOException e) {
            throw new WrongFileException(e);
        }
    }

    @Override
    public void close() {

    }
}
