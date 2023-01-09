package kompo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JdbcSudokuBoardDao implements Dao<SudokuBoard> {

    public static final String dbName = "SudokuDatabase";
    private final String recordName;

    public JdbcSudokuBoardDao(String name) {
        this.recordName = name;
    }

    private Connection startConnection(String jdbcUrl) throws DaoException {
        Connection connection;
        try {
            connection = DriverManager.getConnection(jdbcUrl);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return connection;
    }

    @Override
    public SudokuBoard read() throws DaoException {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        String jdbcUrl = "jdbc:sqlite:./" + dbName;
        Connection connection = startConnection(jdbcUrl);
        String dataReceived;
        ResultSet results = null;
        String dataSelect = "SELECT tablename, fields FROM "
                + dbName + " WHERE tablename=?";
        try (PreparedStatement statement = connection.prepareStatement(dataSelect)) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            statement.setString(1, recordName);
            results = statement.executeQuery();
            dataReceived = results.getString(2);
            board.stringToBoard(dataReceived);
            connection.commit();
        } catch (SQLException | InvalidFieldInputException e) {
            throw new DaoException(e);
        }
        try {
            results.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return board;
    }



    @Override
    public void write(SudokuBoard board) throws DaoException {
        String jdbcUrl = "jdbc:sqlite:./" + dbName;
        Connection connection = startConnection(jdbcUrl);

        String createTable = "CREATE TABLE IF NOT EXISTS " + dbName
                + " (tableName text PRIMARY KEY NOT NULL, fields text)";

        String insertData = "INSERT INTO " + dbName + "(tableName,fields) values (?,?)";

        try (Statement statement = connection.createStatement()) {
            statement.execute(createTable);
        } catch (SQLException e) {
            throw new DaoException(e);
        }



        try (PreparedStatement statement = connection.prepareStatement(insertData)) {

            statement.setString(1, recordName);
            statement.setString(2, board.boardToString());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        try {
            connection.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    @Override
    public void close() throws DaoException {
    }
}