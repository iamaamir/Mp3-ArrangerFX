package mp3a.arrangerfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static mp3a.arrangerfx.Util.getResource;

/**
 *
 * @author Aamir khan
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
  

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle(getResource("TITLE"));
        stage.setMaximized(false);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
