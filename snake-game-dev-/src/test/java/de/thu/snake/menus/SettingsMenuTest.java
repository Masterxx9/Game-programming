package de.thu.snake.menus;

import de.thu.snake.constants.Config;
import de.thu.snake.constants.Difficulty;
import de.thu.snake.entities.Game;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
// This class uses the ApplicationExtension for testing JavaFX applications

@ExtendWith(ApplicationExtension.class)
class SettingsMenuTest {
    private Game game;
    // This method sets up the headless property for the testing environment
    @BeforeAll
    public static void setupHeadless() {
        // Check if the test is a headless test
        if (Config.istHeadlessTest) {
            // Set the properties for a headless test
            System.setProperty("java.awt.headless", "true");
            System.setProperty("testfx.robot", "glass");
            System.setProperty("testfx.headless", "true");
            System.setProperty("prism.order", "sw");
            System.setProperty("prism.text", "t2k");

        }
    }
    // This method starts the JavaFX application for testing
    @Start
    private void start(Stage stage) throws IOException {
        game = new Game();
        Parent menu = FXMLLoader.load(getClass().getResource("/fxml/menus/Settings.fxml"));
        Scene exit = new Scene(menu);
        stage.setScene(exit);
        stage.show();
    }
    // This method tests the easy difficulty button

    @Test
    void easy(FxRobot robot) {
        robot.clickOn("#easy");
        assertEquals(this.game.difficulty, Difficulty.EASY);
    }
    // This method tests the medium difficulty button
    @Test
    void medium(FxRobot robot) {
        robot.clickOn("#medium");
        assertEquals(this.game.difficulty, Difficulty.MEDIUM);
    }
    // etc..
    @Test
    void hard(FxRobot robot) {
        robot.clickOn("#hard");
        assertEquals(this.game.difficulty, Difficulty.HARD);
    }

    @Test
    void returnButton(FxRobot robot) {
        robot.clickOn("#return");
        // This assertion checks if the button with id "play" has text "Play"
        Assertions.assertThat(
                robot.lookup("#play").queryAs(Button.class)
        ).hasText("Play");
    }

    @Test
    void saveButton(FxRobot robot) {
        robot.clickOn("#save");
        // This assertion checks if the button with id "play" has text "Play"
        Assertions.assertThat(
                robot.lookup("#play").queryAs(Button.class)
        ).hasText("Play");

    }

    @AfterEach
    public void tearDown() throws Exception {
        FxToolkit.hideStage();
    }

}