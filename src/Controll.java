import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Controll implements Initializable {
    @FXML private Button search;
    @FXML private Button listen;
    @FXML private TextField input;
    @FXML private TextArea output;
    Voice voice;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        voice = VoiceManager.getInstance().getVoice("kevin");
        voice.allocate();
        Image img = new Image("C:\\Users\\Hoang Thang\\Documents\\GitHub\\Dictionary\\data\\speaker.jpg");
        listen.setGraphic(new ImageView(img));
        Image img1 = new Image("C:\\Users\\Hoang Thang\\Documents\\GitHub\\Dictionary\\data\\glass.jpg");
        search.setGraphic(new ImageView(img1));
    }
    @FXML
    public void speakup(ActionEvent event){
        voice.speak(input.getText());
    }
    @FXML
    public void translate(ActionEvent event){

    }
}
