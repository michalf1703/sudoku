import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import kompo.BacktrackingSudokuSolver;
import kompo.FileSudokuBoardDao;
import kompo.SudokuBoard;
import kompo.SudokuSolver;

public class BoardWindowControl {

    @FXML
    private GridPane sudokuBoardGrid;

    private PopOutWindow popOutWindow = new PopOutWindow();
    private SudokuSolver solver1 = new BacktrackingSudokuSolver();
    private SudokuBoard sudokuBoard = new SudokuBoard(solver1);
    private SudokuBoard sudokuBoardCopy = new SudokuBoard();
    private BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
    private Levels difficultyLevel = new Levels();

    /*------------------------ METHODS REGION ------------------------*/
    @FXML
    private void initialize() throws CloneNotSupportedException, EmptyBoardException {
        solver.solve(sudokuBoard);
        sudokuBoardCopy = (SudokuBoard) sudokuBoard.clone();
        difficultyLevel.chooseLevel(sudokuBoard, ChoiceWindowControl.getLevel());
        fillGrid();
    }


    private void checkFieldInput(TextField textField) {
        if (!textField.getText().matches("[1-9]")) {
            popOutWindow.messageBox("Error",
                    "Permitted numbers are integers from range 1 - 9", Alert.AlertType.ERROR);
        }
    }


    private void fillGrid() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField textField = new TextField();
                textField.setMinSize(50, 50);
                textField.setFont(Font.font(18));
                if (sudokuBoard.get(i, j) != 0) {
                    textField.setDisable(true);
                    textField.setText(String.valueOf(sudokuBoard.get(i, j)));
                }
                sudokuBoardGrid.add(textField, i, j);
            }
        }
    }


    @FXML
    public void onActionButtonCheck(ActionEvent actionEvent) {
        boolean isWon = true;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

            }
        }

        if (isWon) {
            popOutWindow.messageBox("", "You WON", Alert.AlertType.INFORMATION);
        } else {
            popOutWindow.messageBox("", "You LOST", Alert.AlertType.INFORMATION);
        }
    }


    @FXML
    public void onActionButtonDatabase(ActionEvent actionEvent) {
    }


    @FXML
    public void onActionButtonFile(ActionEvent actionEvent) {
        FileSudokuBoardDao fileSudokuBoardDao = new FileSudokuBoardDao("abc");
        fileSudokuBoardDao.write(sudokuBoard);
    }
}
