import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.scene.paint.Paint;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import static javafx.fxml.FXMLLoader.load;

public class Controll implements Initializable {
    @FXML private ListView<String> listCommon;
    @FXML private Button search;
    @FXML private Button listen;
    @FXML private Button add;
    @FXML private Button edit;
    @FXML private Button transOnl;
    @FXML private Button imgLabel;
    @FXML private TextField input;
    @FXML private TextArea output;
    @FXML private Label nofi;
    @FXML private Button commitChange;


    DictionaryManagement dicM = new DictionaryManagement();
    DictionaryCommandline dicC = new DictionaryCommandline();
    Voice voice;
    DictionaryManagement advance;
    Trie trie;

    private Stage stage;
    private Scene scene;
    private Parent root;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        commitChange.setVisible(false);
        advance = new DictionaryManagement();
        advance.insertFromFile();
        advance.sortDictionary();
        trie = new Trie();
        trie.init(advance.getList());
        List<String> list = trie.listMatchPrefix("");
        listCommon.setItems(FXCollections.observableList(list));
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        voice = VoiceManager.getInstance().getVoice("kevin");
        voice.allocate();
        Image img = new Image("C:\\Users\\Hoang Thang\\Desktop\\DictionaryHai\\data\\audio_40px.png");
        listen.setGraphic(new ImageView(img));
        Image img1 = new Image("C:\\Users\\Hoang Thang\\Desktop\\DictionaryHai\\data\\search_30px.png");
        search.setGraphic(new ImageView(img1));
        Image img2 = new Image("C:\\Users\\Hoang Thang\\Desktop\\DictionaryHai\\data\\add_40px.png");
        add.setGraphic(new ImageView(img2));
        Image img3 = new Image("C:\\Users\\Hoang Thang\\Desktop\\DictionaryHai\\data\\edit_property_40px.png");
        edit.setGraphic(new ImageView(img3));
        Image img4 = new Image("C:\\Users\\Hoang Thang\\Desktop\\DictionaryHai\\data\\translation_40px.png");
        transOnl.setGraphic(new ImageView(img4));
        Image img5 = new Image("C:\\Users\\Hoang Thang\\Desktop\\DictionaryHai\\data\\books_38px.png");
        imgLabel.setGraphic(new ImageView(img5));
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

    @FXML
    public void swtichtoAddPanes(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddPanes.fxml"));
        root = load(getClass().getResource("AddPanes.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void searchApi(ActionEvent event) throws IOException {
        root = load(getClass().getResource("Api.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void erase(ActionEvent event) throws IOException {
        int index = listCommon.getSelectionModel().getSelectedIndex();
        if(index!=-1) {
            advance.removeFromCommandline(listCommon.getSelectionModel().getSelectedItem());
            listCommon.getItems().remove(index);
            advance.insertFromFile();
            trie = new Trie();
            trie.init(advance.getList());
            input.clear();
            output.clear();
        }
    }
    @FXML
    public void commit(ActionEvent event) throws IOException {
        Word word = new Word(input.getText(), output.getText());
        advance.replaceFromCommandline(word);
        advance.insertFromFile();
        trie = new Trie();
        trie.init(advance.getList());
        commitChange.setVisible(false);
        input.editableProperty().set(true);
        output.editableProperty().set(false);
    }
    @FXML
    public void edit(ActionEvent event){
        output.editableProperty().set(true);
        commitChange.setVisible(true);
        input.editableProperty().set(false);
    }
}
