package de.thu.snake;

import de.thu.snake.constants.Config;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;


@ExtendWith(ApplicationExtension.class)
class MainTest {
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

    @Start
    private void start(Stage stage) throws Exception {
        stage.show();
    }

    @Test
    @DisplayName("Test if the game launch correctly")
    void main(FxRobot robot) throws Exception {
        ApplicationTest.launch(Main.class);
    }

    @AfterEach
    public void tearDown() throws Exception {
        FxToolkit.hideStage();
    }


}