import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import kompo.*;


public class BoardWindow {

    @FXML
    private GridPane sudokuBoardGrid;

    private PopOutWindow popOutWindow = new PopOutWindow();
    private ResourceBundle bundle = ResourceBundle.getBundle("Language");
    private static final Logger logger = Logger.getLogger(BoardWindow.class.getName());
    private SudokuSolver solver1 = new BacktrackingSudokuSolver();
    private SudokuBoard sudokuBoard = new SudokuBoard(solver1);
    private SudokuBoard sudokuBoardCopy = new SudokuBoard();
    private BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
    private Levels difficultyLevel = new Levels();

    private SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
    private Dao<SudokuBoard> fileSudokuBoardDao;
    private FileChooser fileChooser;
    private File file;


    @FXML
    private void initialize() throws EmptyBoardException {
        if (ChoiceWindow.getSudokuBoardFromSource() != null) {
            sudokuBoard = ChoiceWindow.getSudokuBoardFromSource();
        } else {
            solver.solve(sudokuBoard);
            difficultyLevel.chooseLevel(sudokuBoard, ChoiceWindow.getLevel());
        }

        fillGrid();
    }


    private void fillGrid() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField textField = new TextField();
                textField.setMinSize(50, 50);
                textField.setFont(Font.font(18));

                if (!(sudokuBoard.get(i, j) == 0 || sudokuBoard.isEditableField(i, j))) {
                    textField.setDisable(true);
                    textField.setText(String.valueOf(sudokuBoard.get(i, j)));
                } else if (sudokuBoard.get(i, j) != 0 && sudokuBoard.isEditableField(i, j)) {
                    textField.setText(String.valueOf(sudokuBoard.get(i, j)));
                }

                sudokuBoardGrid.add(textField, j, i);
            }
        }
    }


    private boolean isInputValid() {
        boolean isValid = true;

        for (int i = 0; i < 81; i++) {
            String fieldValue = ((TextField) sudokuBoardGrid.getChildren().get(i)).getText();
            if (!((fieldValue.matches("[1-9]")) || (fieldValue.equals("")))) {
                isValid = false;
            }
        }

        return isValid;
    }


    private void updateBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String fieldValue = ((TextField) sudokuBoardGrid
                        .getChildren().get(i * 9 + j)).getText();
                if (!fieldValue.equals("")) {
                    sudokuBoard.set(i, j, Integer.parseInt(fieldValue));
                } else {
                    sudokuBoard.set(i, j, 0);
                }
            }
        }
    }


    @FXML
    private void onActionButtonCheck(ActionEvent actionEvent) {
        if (!isInputValid()) {
            popOutWindow.messageBox(bundle.getString("Warning"),
                    bundle.getString("WindowValid"), Alert.AlertType.WARNING);
        } else {
            updateBoard();
            if (sudokuBoard.checkBoard()) {
                popOutWindow.messageBox("",
                        bundle.getString("WindowWonGame"), Alert.AlertType.INFORMATION);
            } else {
                popOutWindow.messageBox("",
                        bundle.getString("WindowLostGame"), Alert.AlertType.INFORMATION);
            }
        }
    }


    @FXML
    private void onActionButtonFile(ActionEvent actionEvent) {
        fileChooser = new FileChooser();

        if (isInputValid()) {
            updateBoard();
            try {
                file = fileChooser.showSaveDialog(StageSetup.getStage());
                fileSudokuBoardDao = factory.getFileDao(file.getName());
                fileSudokuBoardDao.write(sudokuBoard);
            } catch (NullPointerException | DaoException e) {
                logger.warning("Cannot save to file!");
                popOutWindow.messageBox(bundle.getString("Warning"),
                        bundle.getString("WindowFileNotChosen"), Alert.AlertType.WARNING);
            }
        } else {
            popOutWindow.messageBox(bundle.getString("Warning"),
                    bundle.getString("WindowValid"), Alert.AlertType.WARNING);
        }
    }

    @FXML
    public void onActionButtonBackToMenu(ActionEvent actionEvent) throws IOException {
        StageSetup.buildStage("choiceWindow.fxml",
                bundle.getString("Title"), bundle);

    }
}
