import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.fxml.FXMLLoader.load;


public class PanesControll implements Initializable {
    @FXML TextField textExplain;
    @FXML TextField textTarget;
    @FXML Button save;
    @FXML Label nofi;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    public void swtichtoMain(ActionEvent event) throws IOException {
        root = load(getClass().getResource("App.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void saveAddButton(ActionEvent event) {
        try {
            String userNewWord = textTarget.getText();
            String userDefinition = textExplain.getText();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("App.fxml"));
            root = loader.load();
            Controll controll = loader.getController();
            if (controll.trie.meaning(userNewWord)==null) {
                controll.dicM.addFromCommandline(new Word(userNewWord,userDefinition));
                nofi.setText("Done!");
            }
            else {
                textTarget.clear();
                textExplain.clear();
                nofi.setText("This word is already existed fucker!");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image img = new Image("C:\\Users\\Hoang Thang\\Desktop\\DictionaryHai\\data\\save_40px.png");
        save.setGraphic(new ImageView(img));
    }
}
