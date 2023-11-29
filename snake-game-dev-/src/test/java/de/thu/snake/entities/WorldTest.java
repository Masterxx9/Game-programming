package de.thu.snake.entities;

import de.thu.snake.constants.Config;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.util.WaitForAsyncUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
/**
 * Class for testing the World class in the de.thu.snake.entities package.
 */
@ExtendWith(ApplicationExtension.class)
class WorldTest {

    private static World world;

    /**
     * Method that sets up the headless test environment.
     */
    @BeforeAll
    public static void setupHeadless() {

        if (Config.istHeadlessTest) {
            // Set system properties for headless testing
            System.setProperty("java.awt.headless", "true");
            System.setProperty("testfx.robot", "glass");
            System.setProperty("testfx.headless", "true");
            System.setProperty("prism.order", "sw");
            System.setProperty("prism.text", "t2k");

        }
    }
    /**
     * Method to start the stage and initialize the World instance.
     * @param stage the stage to be used
     */
    @Start
    private void start(Stage stage) {
        // Use Platform.runLater to avoid any exception when accessing JavaFX components
        Platform.runLater(() -> {
            this.world = new World();
        });
        WaitForAsyncUtils.waitForFxEvents();
    }
    /**
     * Test to verify if the points are set to 0 at the beginning of the game.
     * @param robot the robot for testing
     */
    @DisplayName("Test if the Game world has a 0 points at the beginning")
    @Test
    void getPoints(FxRobot robot) {
        // Assert that the points are 0 at the beginning of the game
        assertEquals(0, this.world.getPoints());
    }
    /**
     * Test to verify if the points can be changed.
     * @param robot the robot for testing
     */
    @Test
    @DisplayName("Test if the Game world's points can be changed")
    void setPoints(FxRobot robot) {
        // Platform.runLater to avoid running on the main thread, which it causes som exception in some situations
        Platform.runLater(() -> {
            this.world.setPoints(10);
            assertEquals(10, this.world.getPoints());
        });

    }

    @Test
    @DisplayName("Test if the Game world can generate a pane")
    void generate(FxRobot robot) {

        Platform.runLater(() -> {
            assertNotNull(this.world.generate(new Snake(), new Food()));

        });

    }
// Hide the stage after each test case
    @AfterEach
    public void tearDown() throws Exception {
        FxToolkit.hideStage();
    }

}