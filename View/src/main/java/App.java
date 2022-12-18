import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;


public class App extends Application {

    private StageSetup stageSetup;

    @Override
    public void start(Stage stage) throws IOException {
        StageSetup.buildStage(stage, "choiceWindow.fxml", "Sudoku Game");
    }

    public static void main(String[] args) {
        launch();
    }
}