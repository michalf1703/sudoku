import javafx.beans.property.adapter.JavaBeanIntegerProperty;
import javafx.beans.property.adapter.JavaBeanIntegerPropertyBuilder;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;
import kompo.*;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;


public class BoardWindow {

    @FXML
    private GridPane sudokuBoardGrid;

    private final StringConverter converter = new CustomConverter();
    private JavaBeanIntegerProperty[][] fieldValueProperty = new JavaBeanIntegerProperty[9][9];
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
        logger.info("Loading board...");
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
            for (int k = 0; k < 9; k++) {
                TextField label = new TextField();
                label.setMinSize(50,65);
                label.setFont(Font.font(20));
                if (sudokuBoard.get(i, k) != 0) {
                    label.setDisable(true);
                }
                try {
                    fieldValueProperty[i][k] = JavaBeanIntegerPropertyBuilder.create()
                            .bean(new SudokuBoardAdapter(sudokuBoard, i, k))
                            .name("Field")
                            .build();
                }
                catch (NoSuchMethodException e){
                    logger.info("Cannot build required property");

                }
                label.textProperty().bindBidirectional(fieldValueProperty[i][k], converter);
                label.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String previous, String current) {
                        if (!((current.matches("[1-9]")) || (current.equals("")))) {
                            label.setText(previous);
                        }
                    }
                });
                if (sudokuBoard.get(i, k) == 0) {
                    label.clear();
                }
                sudokuBoardGrid.add(label, k, i);
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
    public void onActionButtonFile() throws DaoException {
        FileChooser fileChooser = new FileChooser();
        ResourceBundle bundle = ResourceBundle.getBundle("Language");
        try {
            File file = fileChooser.showSaveDialog(StageSetup.getStage());
            Dao<SudokuBoard> fileSudokuBoardDao = SudokuBoardDaoFactory.getFileDao(file.getAbsolutePath());
            fileSudokuBoardDao.write(sudokuBoard);
        } catch (NullPointerException e) {
            throw new WrongFileException(bundle.getString("IOException"), e.getCause());
        }

    }

    @FXML
    public void onActionButtonBackToMenu(ActionEvent actionEvent) throws IOException {
        StageSetup.buildStage("choiceWindow.fxml",
                bundle.getString("Title"), bundle);
        logger.info("Back to menu");

    }
}
