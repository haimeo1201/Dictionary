import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import javafx.event.ActionEvent;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controll implements Initializable {
    @FXML private ListView<String> listCommon;
    @FXML private Button search;
    @FXML private Button listen;
    @FXML private TextField input;
    @FXML private TextArea output;
    Voice voice;
    DictionaryManagement advance;
    Trie trie;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        advance = new DictionaryManagement();
        advance.insertFromFile();
        trie = new Trie();
        trie.init(advance.getList());
        List<String> list = trie.listMatchPrefix("");
        listCommon.setItems(FXCollections.observableList(list));
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
        output.clear();
        String search = input.getText();
        if (trie.meaning(search)==null)  output.setText("khong thay tu ban tim");
        else output.setText(trie.meaning(search));
    }
    @FXML
    public void suggestion(KeyEvent event){
        String prefix = input.getText();
        List<String> list = trie.listMatchPrefix(prefix);
        if(list==null) return;
        listCommon.setItems(FXCollections.observableArrayList(list));
        listCommon.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }
    @FXML
    public void getMeaning(MouseEvent event){
        String search = listCommon.getSelectionModel().getSelectedItem();
        output.setText(trie.meaning(search));
        input.setText(search);
    }
}
