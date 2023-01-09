import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;
import kompo.Dao;
import kompo.DaoException;
import kompo.SudokuBoard;
import kompo.SudokuBoardDaoFactory;
import org.apache.log4j.Logger;




public class ChoiceWindow {

    @FXML
    private ComboBox comboBoxSystemLang;
    @FXML
    private ComboBox comboBoxSystemDifficult;

    private PopOutWindow popOutWindow = new PopOutWindow();
    private ResourceBundle bundle = ResourceBundle.getBundle("Language");
    private static final Logger logger = Logger.getLogger(ChoiceWindow.class.getName());
    private SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
    private FileChooser fileChooser;
    private static SudokuBoard sudokuBoardFromSource;
    private static String level;
    private Dao<SudokuBoard> jdbcSudokuBoardDao;
    private static String language;
    private static SudokuBoard sudokuBoardFromDatabase;

    /*--------*/

    public static String getLevel() {
        return level;
    }


    public static String getLanguage() {
        return language;
    }

    public static SudokuBoard getSudokuBoardFromDatabase() {
        return sudokuBoardFromDatabase;
    }

    public static void setSudokuBoardFromDatabase() {
        sudokuBoardFromDatabase = null;
    }

    public static SudokuBoard getSudokuBoardFromSource() {
        return sudokuBoardFromSource;
    }

    @FXML
    public void initialize() throws IOException {
        comboBoxSystemLang.getItems().addAll(
                bundle.getString("LanEng"),
                bundle.getString("LanPl")
        );

        comboBoxSystemDifficult.getItems().addAll(
                bundle.getString("LevelEasy"),
                bundle.getString("LevelMedium"),
                bundle.getString("LevelHard")
        );
    }



    @FXML
    public void onActionButtonStartGame() throws IOException {
        try {
            if (level == null) {
                ChoiceWindow.level = comboBoxSystemDifficult
                        .getSelectionModel().getSelectedItem().toString();
            }
            if (language == null) {
                ChoiceWindow.language = comboBoxSystemLang
                        .getSelectionModel().getSelectedItem().toString();
            }
            StageSetup.buildStage("boardWindow.fxml", bundle);
            logger.info("Start game");
        } catch (NullPointerException e) {
            logger.info("Bad game settings!");
            popOutWindow.messageBox(bundle.getString("Warning"),
                    bundle.getString("WindowLanFileNotChosen"), Alert.AlertType.WARNING);
        }
    }


    @FXML
    public void onActionButtonConfirmLang() throws IOException {
        try {
            this.language = comboBoxSystemLang
                    .getSelectionModel().getSelectedItem().toString();

            if (language.equals(bundle.getString("LanEng"))) {
                Locale.setDefault(new Locale("en"));
                bundle = ResourceBundle.getBundle("Language");
            } else if (language.equals(bundle.getString("LanPl"))) {
                Locale.setDefault(new Locale("pl"));
                bundle = ResourceBundle.getBundle("Language");
            }

            StageSetup.buildStage("choiceWindow.fxml",
                    bundle.getString("Title"), bundle);
            logger.info("Confirm language settings!");
        } catch (NullPointerException e) {
            logger.info("Bad language settings!");
            popOutWindow.messageBox(bundle.getString("Warning"),
                    bundle.getString("WindowLanNotChosen"), Alert.AlertType.WARNING);
        }
    }



    public void onActionButtonFile() {
        String filename;
        FileChooser fileChooser = new FileChooser();

        try {
            filename = fileChooser.showOpenDialog(StageSetup.getStage()).getAbsolutePath();
            sudokuBoardFromSource = SudokuBoardDaoFactory.getFileDao(filename).read();
            StageSetup.buildStage("boardWindow.fxml", bundle);
        } catch (NullPointerException | IOException e) {
            logger.info(bundle.getString("_cannotReadFile"));
        }



    }

    @FXML
    public void onActionButtonAuthors() {
        ResourceBundle listBundle = ResourceBundle.getBundle("AuthorsListResourceBundle");
        popOutWindow.messageBox("",
                listBundle.getObject("1. ") + "\n" + listBundle.getObject("2. "),
                Alert.AlertType.INFORMATION);
    }

    @FXML
    public void onActionButtonHelp(ActionEvent actionEvent) {
        popOutWindow.messageBox("",bundle.getString("MenuHelpAboutClick"),
                Alert.AlertType.INFORMATION);
    }

    @FXML
   public void inactionbuttondatabase() {

        String filename;
        fileChooser = new FileChooser();

        try {
            filename = fileChooser.showOpenDialog(StageSetup.getStage()).getName();
            jdbcSudokuBoardDao = factory.getDatabaseDao(filename);
            sudokuBoardFromSource = jdbcSudokuBoardDao.read();
            ChoiceWindow.level = bundle.getString("default_level");
            comboBoxSystemDifficult.setDisable(true);
        } catch (NullPointerException | DaoException e) {
            logger.info("Cannot read from database!");
            popOutWindow.messageBox(bundle.getString("Error_database"),
                    bundle.getString("Error_database"), Alert.AlertType.WARNING);
        }

    }



}



