import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ResourceBundle;

import static javafx.fxml.FXMLLoader.load;

public class ApiSearch implements Initializable {
    @FXML private Button Search;
    @FXML private TextArea in;
    @FXML private TextArea out;
    @FXML private Button listen;
    Voice voice;
    public String translate(String text) throws IOException {
        // INSERT YOU URL HERE
        String urlStr = "https://script.google.com/macros/s/AKfycbwPtcJzvHqOJCxRdZ18b4V6xrk-IRjbfYFNwGrIyidWx1PN8To/exec" +
                "?q=" + URLEncoder.encode(text, "UTF-8")
                + "&target=" + "vi" +
                "&source=" + "en";
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        // con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        voice = VoiceManager.getInstance().getVoice("kevin");
        voice.allocate();
        Image img1 = new Image("C:\\Users\\Hoang Thang\\Desktop\\DictionaryHai\\data\\glass.jpg");
        Search.setGraphic(new ImageView(img1));
        Image img = new Image("C:\\Users\\Hoang Thang\\Desktop\\DictionaryHai\\data\\audio_40px.png");
        listen.setGraphic(new ImageView(img));
    }
    @FXML
    public void translate(ActionEvent event) throws IOException {
        out.setText(translate(in.getText()));
    }
    @FXML
    public void swtichtoMain(ActionEvent event) throws IOException {
        Parent root = load(getClass().getResource("App.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void speakup(ActionEvent event){
        voice.speak(in.getText());
    }
}
