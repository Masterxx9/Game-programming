package de.thu.snake.menus;

import de.thu.snake.constants.Config;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

@ExtendWith(ApplicationExtension.class)
class GameOverMenuTest {
    Stage stage;

// Method to set up headless test (test without GUI)
    @BeforeAll
    public static void setupHeadless() {

        // Check if headless test is set
        if (Config.istHeadlessTest) {
            // Set system properties for headless test
            System.setProperty("java.awt.headless", "true");
            System.setProperty("testfx.robot", "glass");
            System.setProperty("testfx.headless", "true");
            System.setProperty("prism.order", "sw");
            System.setProperty("prism.text", "t2k");

        }
    }

    @Start
    private void start(Stage stage) throws IOException {
        // Load the Game Over menu
        Parent menu = FXMLLoader.load(getClass().getResource("/fxml/menus/GameOver.fxml"));
        Scene exit = new Scene(menu);
        stage.setScene(exit);

        this.stage = stage;
        stage.show();
    }

    // Test if the main menu button returns to the main menu
    @Test
    @DisplayName("Test if returns to main menu, when main menu button is clicked")
    void mainMenu(FxRobot robot) {
        robot.clickOn("#main-menu");
        // Assert that the play button in the main menu has the correct text
        Assertions.assertThat(
                robot.lookup("#play").queryAs(Button.class)
        ).hasText("Play");

    }
    // Method to tear down the GUI
    @AfterEach
    public void tearDown() throws Exception {
        FxToolkit.hideStage();
    }


}