import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.stage.Stage;




public class App extends Application {

    private static final Logger logger = Logger.getLogger(App.class.getName());

    static {
        String configPath = App.class.getClassLoader().getResource("logging.properties").getFile();
        System.setProperty("java.util.logging.config.file", configPath);
    }

    private ResourceBundle bundle = ResourceBundle.getBundle("Language");

    @Override
    public void start(Stage stage) throws IOException {
        StageSetup.buildStage(stage, "choiceWindow.fxml",
                bundle.getString("Title"), bundle);
    }

    public static void main(String[] args) {
        logger.info("Start application!");
        launch();
    }
}