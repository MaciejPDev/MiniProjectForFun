package puissance4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class MainFx extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Menu.fxml")));
            Scene scene = new Scene(parent);
            stage.setTitle("Connect 4");
            stage.setScene(scene);
            stage.show();
        } catch (NullPointerException e) {
            System.err.println("The fxml file has not be found");
            System.exit(0);
        }
    }
}
