import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

import java.io.IOException;

public class ChoiceWindowControl {

    @FXML
    private ComboBox comboBoxSystemLang;
    @FXML
    private ComboBox comboBoxSystemDifficult;

    private PopOutWindow popOutWindow = new PopOutWindow();
    private static String level;
    private static String language;

    public static String getLevel() {
        return level;
    }

    public static String getLanguage() {
        return language;
    }

    @FXML
    public void onActionButtonStartGame(ActionEvent actionEvent) throws IOException {
        if (!(level == null || language == null)) {
            StageSetup.buildStage("boardWindow.fxml");
        } else {
            popOutWindow.messageBox("Warning",
                    "Check if language and level has not been chosen", Alert.AlertType.WARNING);
        }
    }


    @FXML
    public void onActionButtonConfirmLevel(ActionEvent actionEvent) {
        try {
            this.level = comboBoxSystemDifficult.getSelectionModel().getSelectedItem().toString();
        } catch (NullPointerException e) {
            popOutWindow.messageBox("Warning",
                    "Level of difficulty has not been chosen", Alert.AlertType.WARNING);
        }
    }


    @FXML
    public void onActionButtonConfirmLang(ActionEvent actionEvent) {
        try {
            this.language = comboBoxSystemLang.getSelectionModel().getSelectedItem().toString();
        } catch (NullPointerException e) {
            popOutWindow.messageBox("Warning",
                    "Language has not been chosen", Alert.AlertType.WARNING);
        }
    }

    @FXML
    public void onActionButtonDatabase(ActionEvent actionEvent) {
        //TODO
    }


    @FXML
    public void onActionButtonFile(ActionEvent actionEvent) {
        //TODO
    }


    @FXML
    public void onActionButtonAuthors(ActionEvent actionEvent) {
        popOutWindow.messageBox("",
                "Michal Ferdzyn ||| Artur Grzybek", Alert.AlertType.INFORMATION);
    }
}



