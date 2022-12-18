import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;
import kompo.Dao;
import kompo.DaoException;
import kompo.SudokuBoard;
import kompo.SudokuBoardDaoFactory;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class ChoiceWindow {

    @FXML
    private ComboBox comboBoxSystemLang;
    @FXML
    private ComboBox comboBoxSystemDifficult;

    private PopOutWindow popOutWindow = new PopOutWindow();
    private ResourceBundle bundle = ResourceBundle.getBundle("Language");
    private static final Logger logger = Logger.getLogger(ChoiceWindow.class.getName());
    private SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
    private Dao<SudokuBoard> fileSudokuBoardDao;
    private FileChooser fileChooser;
    private static SudokuBoard sudokuBoardFromSource;
    private static String level;
    private static String language;

    public static String getLevel() {
        return level;
    }

    public static String getLanguage() {
        return language;
    }

    @FXML
    private void initialize() throws IOException {
        comboBoxSystemLang.getItems().addAll(
                bundle.getString("_comboLang1"),
                bundle.getString("_comboLang2")
        );

        comboBoxSystemDifficult.getItems().addAll(
                bundle.getString("_lvlEasy"),
                bundle.getString("_lvlMedium"),
                bundle.getString("_lvlHard")
        );
    }

    public static SudokuBoard getSudokuBoardFromSource() {
        return sudokuBoardFromSource;
    }

    @FXML
    private void onActionButtonStartGame(ActionEvent actionEvent) throws IOException {
        try {
            if (level == null) {
                ChoiceWindow.level = comboBoxSystemDifficult
                        .getSelectionModel().getSelectedItem().toString();
            }
            if (language == null ) {
                ChoiceWindow.language = comboBoxSystemLang
                        .getSelectionModel().getSelectedItem().toString();
            }
            StageSetup.buildStage("boardWindow.fxml", bundle);
            logger.info("Start game");
        } catch (NullPointerException e) {
            logger.warning("Bad game settings!");
            popOutWindow.messageBox(bundle.getString("_warning"),
                    bundle.getString("_startWindow"), Alert.AlertType.WARNING);
        }
    }


    @FXML
    private void onActionButtonConfirmLang(ActionEvent actionEvent) throws IOException {
        try {
            this.language = comboBoxSystemLang
                    .getSelectionModel().getSelectedItem().toString();

            if (language.equals(bundle.getString("_comboLang1"))) {
                Locale.setDefault(new Locale("en"));
                bundle = ResourceBundle.getBundle("Language");
            } else if (language.equals(bundle.getString("_comboLang2"))) {
                Locale.setDefault(new Locale("pl"));
                bundle = ResourceBundle.getBundle("Language");
            }

            StageSetup.buildStage("choiceWindow.fxml",
                    bundle.getString("_windowTitle"), bundle);
            logger.info("Confirm language settings!");
        } catch (NullPointerException e) {
            logger.warning("Bad language settings!");
            popOutWindow.messageBox(bundle.getString("_warning"),
                    bundle.getString("_langWindow"), Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void onActionButtonFile(ActionEvent actionEvent) {
        String filename;
        fileChooser = new FileChooser();

        try {
            filename = fileChooser.showOpenDialog(StageSetup.getStage()).getName();
            fileSudokuBoardDao = factory.getFileDao(filename);
            sudokuBoardFromSource = fileSudokuBoardDao.read();
            ChoiceWindow.level = bundle.getString("_lvlFromExternal");
            comboBoxSystemDifficult.setDisable(true);
        } catch (NullPointerException | DaoException e) {
            logger.warning("Cannot read from file!");
            popOutWindow.messageBox(bundle.getString("_warning"),
                    bundle.getString("_fileWindow"), Alert.AlertType.WARNING);
        }
    }


    @FXML
    private void onActionButtonAuthors(ActionEvent actionEvent) {
        ResourceBundle listBundle = ResourceBundle.getBundle("AuthorsListResourceBundle");
        popOutWindow.messageBox("",
                (listBundle.getObject("1. ") + "\n" + listBundle.getObject("2. ")),
                Alert.AlertType.INFORMATION);
    }

    @FXML
    public void onActionButtonHelp(ActionEvent actionEvent) {
        popOutWindow.messageBox("",bundle.getString("_menuHelpAboutClick")
                , Alert.AlertType.INFORMATION);
    }
}



