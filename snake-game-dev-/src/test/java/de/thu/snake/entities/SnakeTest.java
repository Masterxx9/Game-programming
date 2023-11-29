package de.thu.snake.entities;
import de.thu.snake.constants.Config;
import de.thu.snake.entities.utils.Position;
import org.junit.jupiter.api.*;
import org.testfx.api.FxToolkit;
import java.util.concurrent.TimeoutException;
import static org.junit.jupiter.api.Assertions.*;
class SnakeTest {
    private Snake snake;
    @BeforeAll
    // setup method to run before all tests
    public static void setupHeadless() {
        // Check if the test is headless and set the required properties accordingly

        if (Config.istHeadlessTest) {
            System.setProperty("java.awt.headless", "true");
            System.setProperty("testfx.robot", "glass");
            System.setProperty("testfx.headless", "true");
            System.setProperty("prism.order", "sw");
            System.setProperty("prism.text", "t2k");

        }
    }

    @BeforeEach
    void setUp() {
        this.snake = new Snake();
        this.snake.addNewBodyPart();

    }

    @Test
    @DisplayName("Test if the snake can move")
    void doMove() {
        Position newPosition = new Position(12, 12);
        this.snake.doMove(newPosition);
        Position headPosition = this.snake.getHeadPosition();
        assertTrue(headPosition.toString().equals(newPosition.toString()));
    }

    @Test
    @DisplayName("Test if the snake has a view")
    void getView() {
        assertNotNull(this.snake.getView());
    }

    @Test
    @DisplayName("Test if the snake can grow")
    void addNewBodyPart() {
        // get the number of body parts the snake has before adding a new one
        int oldSize = this.snake.getView().getChildren().size();
        this.snake.addNewBodyPart();
        int newSize = this.snake.getView().getChildren().size();
        // check if the size of the snake has increased by 1
        assertEquals(oldSize + 1, newSize);
    }

    @Test
    @DisplayName("Test if the snake can shrink after removing the body parts")
    void clear() {
        this.snake.addNewBodyPart();
        this.snake.clear();
        // check if the snake has only one body part left
        assertEquals(1, this.snake.getView().getChildren().size());


    }

}