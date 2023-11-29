package de.thu.snake.menus;

import de.thu.snake.constants.Config;
import de.thu.snake.constants.Difficulty;
import de.thu.snake.entities.Game;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
public class SettingsMenu {
    private static HashMap<String, Button> buttons = new HashMap<>();
    private Stage stage = Config.STAGE;
    @FXML
    private Button easy;
    @FXML
    private Button medium;
    @FXML
    private Button hard;


    // Initialize the buttons and their text color.
    @FXML
    public void initialize() {

        easy.setTextFill(Color.BLACK);
        this.buttons.put("easy", easy);
        this.buttons.put("medium", medium);
        this.buttons.put("hard", hard);

    }
    // The action event when the easy button is clicked.

    @FXML
    void easy(ActionEvent event) {
        this.selectMode("easy", event);
        Game.difficulty = Difficulty.EASY;
    }
    // The action event when the medium button is clicked.
    @FXML
    void medium(ActionEvent event) {
        this.selectMode("medium", event);
        Game.difficulty = Difficulty.MEDIUM;

    }
    // The action event when the hard button is clicked.
    @FXML
    void hard(ActionEvent event) {
        this.selectMode("hard", event);
        Game.difficulty = Difficulty.HARD;
    }
    // The action event when the return button is clicked.
    @FXML
    void returnPush(ActionEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("/fxml/menus/Main.fxml"));
        Scene exit = new Scene(menu);
        stage.setScene(exit);
        stage.setTitle("Main Menu");
        stage.show();
    }
    // The action event when the save button is clicked.
    @FXML
    void savePush(ActionEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("/fxml/menus/Main.fxml"));
        Scene exit = new Scene(menu);
        stage.setScene(exit);
        stage.setTitle("Main Menu");
        stage.show();

    }
    // A helper method to update the text color of the buttons when a button is selected.
    private void selectMode(String mode, Event event) {

        Button bt = (Button) event.getTarget();
        bt.setTextFill(Color.BLACK);
        this.buttons.put(mode, bt);

        for (Map.Entry<String, Button> entry : this.buttons.entrySet()) {
            if (!entry.getKey().equals(mode)) {
                entry.getValue().setTextFill(Color.WHITE);
            }

        }

    }

}
