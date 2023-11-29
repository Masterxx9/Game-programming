package de.thu.snake.menus;


import de.thu.snake.constants.Config;
import de.thu.snake.entities.Game;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class GameOverMenu {
    private Stage stage = Config.STAGE;


    /**
     * Returns to the Main Menu when called.
     * Loads the FXML file for the Main Menu and sets it as the current scene.
     *
     * @throws IOException If the FXML file for the Main Menu can't be loaded
     */
    @FXML
    void mainMenu() throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("/fxml/menus/Main.fxml"));
        Scene exit = new Scene(menu);
        stage.setScene(exit);
        stage.setTitle("Main Menu");
        stage.show();

    }


    /**
     * Starts a new game of Snake.
     * Creates a new instance of the Game class and starts it.
     */
    @FXML
    void newGame() {
        Game game = new Game();
        stage.setTitle("Game");
        game.startGame();
    }
}
