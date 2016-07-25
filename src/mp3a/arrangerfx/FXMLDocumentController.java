package mp3a.arrangerfx;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ProgressBar;
import javafx.util.Duration;
import static mp3a.arrangerfx.Util.getResource;

/**
 *
 * @author Aamir khan
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField path;
    @FXML
    private Button browse;

    @FXML
    private Button go;
    @FXML
    private ChoiceBox choice;
    @FXML
    private Label copyrightLabel;
    @FXML
    private Button selectFolder;
    @FXML
    private ProgressBar progressBar;
    private final String MUSIC_DIRECTORY_PATH = 
            System.getProperty("user.home") + File.separatorChar + "Music";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Timeline task = new Timeline(
                new KeyFrame(
                        Duration.ZERO,
                        new KeyValue(progressBar.progressProperty(), 0)
                ),
                new KeyFrame(
                        Duration.seconds(2),
                        new KeyValue(progressBar.progressProperty(), 1)
                )
        );

        selectFolder.setText(getResource("BROWSE"));
        go.setText(getResource("GO"));
        copyrightLabel.setText(getResource("COPYRIGHT"));
        
        final ObservableList<String> CHOICE_LIST;
        CHOICE_LIST = FXCollections.observableArrayList(getResource("CHOICE_LIST").split(","));
        choice.getItems().addAll(CHOICE_LIST);
        choice.getSelectionModel().selectFirst();
        
        path.setText(MUSIC_DIRECTORY_PATH);

        copyrightLabel.visibleProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (newValue == true) {
                System.out.println(newValue);
                progressBar.setVisible(false);
            } else {
                progressBar.setVisible(true);
                task.playFromStart();
            }
        });
    }

    @FXML
    public void browseButtonClick(ActionEvent e) {
        copyrightLabel.setVisible(false);
    }

    @FXML
    public void goButtonClick(ActionEvent e) {
        copyrightLabel.setVisible(true);
    }

}
