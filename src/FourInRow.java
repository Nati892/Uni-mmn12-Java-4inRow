import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FourInRow extends Application {

    public void start(Stage primaryStage) throws Exception {
        Parent root = (Parent) FXMLLoader.load(getClass().getResource("Game_Board.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("4 in a row");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);//disable resizing for screen
        primaryStage.sizeToScene();
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);

    }
}