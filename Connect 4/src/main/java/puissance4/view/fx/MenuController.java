package puissance4.view.fx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.Objects;

public class MenuController {

    @FXML
    private Button quitButton;

    @FXML
    private Button startButton;

    @FXML
    private void quitButtonAction(){
        System.exit(0);
    }

    @FXML
    private void startButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Board.fxml")));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
