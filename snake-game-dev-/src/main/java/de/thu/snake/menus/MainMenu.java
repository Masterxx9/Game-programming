package de.thu.snake.menus;

import de.thu.snake.constants.Config;
import de.thu.snake.entities.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
public class MainMenu {

    private Stage stage;

    public MainMenu() {
        this.stage = Config.STAGE;
    }

    @FXML
    void exit(ActionEvent event) throws IOException {
        System.exit(0);
    }

    // play method starts a new game
    @FXML
    void play(ActionEvent event) throws IOException {
        Game game = new Game();
        this.stage.setTitle("Game");
        game.startGame();

    }
    // settings method opens the settings menu
    @FXML
    void settings(ActionEvent event) throws IOException {
        Parent settings = FXMLLoader.load(getClass().getResource("/fxml/menus/Settings.fxml"));
        Scene settingScene = new Scene(settings);
        stage.setScene(settingScene);
        this.stage.setTitle("Settings");
        stage.show();
    }
}
