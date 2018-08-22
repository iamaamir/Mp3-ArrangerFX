package mp3a.arrangerfx;

import java.io.File;
import java.net.URL;
import java.time.Year;
import javafx.fxml.FXML;
import javafx.util.Duration;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.stage.DirectoryChooser;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.scene.control.ProgressBar;
import javafx.collections.ObservableList;
import javafx.beans.value.ObservableValue;
import static mp3a.arrangerfx.Util.getResource;

/**
 *
 * @author Aamir khan
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField path;
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
    
    private final String DEFAULT_MUSIC_DIRECTORY;
    private DirectoryChooser dirChooser;
    private Timeline dummyAnimation;

    public FXMLDocumentController() {
        this.DEFAULT_MUSIC_DIRECTORY = System.getProperty("user.home") + File.separatorChar + "Music";
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        selectFolder.setTooltip(new Tooltip(getResource("BROWSE")));
        choice.setTooltip(new Tooltip(getResource("CHOICE_TIP")));


        dirChooser = new DirectoryChooser();
        dirChooser.setTitle(getResource("CHOOSER"));
        dirChooser.setInitialDirectory(new File(DEFAULT_MUSIC_DIRECTORY));

        dummyAnimation = new Timeline(
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
        copyrightLabel.setText(getResource("COPYRIGHT").replace("%YEAR%", currentYear()));

        final ObservableList<String> CHOICE_LIST;
        CHOICE_LIST = FXCollections.observableArrayList(getResource("CHOICE_LIST").split(","));
        choice.getItems().addAll(CHOICE_LIST);
        choice.getSelectionModel().selectFirst();

        path.setText(DEFAULT_MUSIC_DIRECTORY);
        path.setEditable(false);

        copyrightLabel.visibleProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
//            if (newValue == true) {
//                progressBar.setVisible(false);
//            } else {
//                progressBar.setVisible(true);
//            }
              progressBar.setVisible(!newValue);
        });
    }

    @FXML
    public void browseButtonClick(ActionEvent e) {
        File selectedDirectory = dirChooser.showDialog(null);
        path.setText(selectedDirectory.getAbsolutePath());

    }

    @FXML
    public void goButtonClick(ActionEvent e) {
        copyrightLabel.setVisible(false);
        dummyAnimation.playFromStart();
        dummyAnimation.setOnFinished((event) -> {
            copyrightLabel.setVisible(true);
        });
        
    }

    private String currentYear() {
        final int CURRENT_YEAR = Year.now().getValue();
        return (CURRENT_YEAR < 2018) ? "2018" : String.valueOf(CURRENT_YEAR);
    }

}
