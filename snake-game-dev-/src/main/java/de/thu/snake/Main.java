package de.thu.snake;
import de.thu.snake.constants.Config;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
*Main class extends Application from javafx framework.
*The main method launches the Application by calling the launch method.
*The start method sets up the main menu UI by loading the Main.fxml file using FXMLLoader.
*The root node of the UI is set as the scene of the main stage with the dimensions of 700x400.
*The title of the main stage is set to "Main Menu". Finally, the main stage is shown.
*/
public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage mainStage = Config.STAGE;
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/menus/Main.fxml"));
        Config.init();
        mainStage.setScene(new Scene(root, 700, 400));
        mainStage.setTitle("Main Menu");
        mainStage.show();


    }
}