package de.thu.snake.menus;

import de.thu.snake.constants.Config;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

@ExtendWith(ApplicationExtension.class)
class MainMenuTest {
    private Stage stage;
    @BeforeAll
    public static void setupHeadless() {

        if (Config.istHeadlessTest) {
            System.setProperty("java.awt.headless", "true");
            System.setProperty("testfx.robot", "glass");
            System.setProperty("testfx.headless", "true");
            System.setProperty("prism.order", "sw");
            System.setProperty("prism.text", "t2k");

        }
    }
    // Start method to initialize stage and load Main menu.
    @Start
    private void start(Stage stage) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("/fxml/menus/Main.fxml"));
        Scene exit = new Scene(menu);
        this.stage = stage;
        stage.setScene(exit);

        stage.show();

    }


    @Test
    @DisplayName("Test if it returns to main menu, when main menu button is clicked")
    void settings(FxRobot robot) {
        robot.clickOn("#settings");

        Assertions.assertThat(
                robot.lookup("#easy").queryAs(Button.class)
        ).hasText("Easy");

    }


}