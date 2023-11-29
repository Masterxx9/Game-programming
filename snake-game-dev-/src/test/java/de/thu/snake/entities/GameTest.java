package de.thu.snake.entities;

import de.thu.snake.constants.Config;
import de.thu.snake.entities.utils.Position;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This class tests the Game class.
 * The class tests if the game is over when the snake hits the wall or itself.
 * It also tests if the snake can find the food, if the food can be regenerated after eating it,
 * if the size of the snake is increased after getting the food and if the score is increased after getting the food.
 */
@ExtendWith(ApplicationExtension.class)
class GameTest {

    private Game game;
    private Snake snake;
    private Food food;
    /**
     * This method sets up the headless test.
     */
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
    /**
     * This method sets up the game, snake and food.
     * @param stage The stage used for testing.
     */
    @Start
    private void start(Stage stage) {
        this.snake = new Snake();
        this.food = new Food();
        this.game = new Game(this.food, this.snake);

    }

    @Test
    @DisplayName("Test if is a Game over, when the snake hits the Wall")
    void gameOverByHittingTheWall() {

        Position newPosition = new Position(Config.GAME_WIDTH, Config.GAME_HEIGHT);
        this.snake.doMove(newPosition);
        assertTrue(this.game.isGameOver(this.snake));
    }

    @Test
    @DisplayName("Test if is a Game over, when the snake hits itself")
    void gameOverByHittingItself() {
        this.snake.addNewBodyPart();
        this.snake.addNewBodyPart();
        this.snake.addNewBodyPart();
        this.snake.addNewBodyPart();
        this.snake.doMove(new Position(this.snake.getView().getChildren().get(3).getTranslateX(), this.snake.getView().getChildren().get(3).getTranslateY()));
        assertTrue(this.game.isGameOver(this.snake));
    }

    @Test
    @DisplayName("Test if the snake can finde the food when they are at the same position")
    void gettingFood() {
        Position foodPos = this.food.getPosition();
        this.snake.doMove(foodPos);
        assertTrue(this.game.eatingFood(this.snake.getHeadPosition(), this.food.getPosition()));

    }

    @Test
    @DisplayName("Test if the food can be regenrated after eating it")
    void foodRegenerated() {
        Position oldFoodPos = this.food.getPosition();
        this.snake.doMove(oldFoodPos);
        this.game.eatingFood(this.snake.getHeadPosition(), this.food.getPosition());

        Position newFoodPosition = this.food.getPosition();
        assertNotEquals(oldFoodPos.toString(), newFoodPosition.toString());

    }

    @Test
    @DisplayName("Test if the size of the snake is increased after getting the food")
    void isSnakeBigger() {
        int oldSnakeSize = this.snake.getView().getChildren().size();
        this.snake.doMove(this.food.getPosition());
        this.game.eatingFood(this.snake.getHeadPosition(), this.food.getPosition());
        int newSnakeSize = this.snake.getView().getChildren().size();
        assertNotEquals(oldSnakeSize, newSnakeSize);


    }

    @Test
    @DisplayName("Test if the score is increased after getting the food ")
    void scoreChanged() {
        int oldPoints = this.game.world.getPoints();
        this.snake.doMove(this.food.getPosition());
        this.game.eatingFood(this.snake.getHeadPosition(), this.food.getPosition());
        int newPoints = this.game.world.getPoints();
        assertNotEquals(oldPoints, newPoints);

    }
    /**
     * Tear down the stage after each test.
     */
    @AfterEach
    void tearDown() throws TimeoutException {
        FxToolkit.hideStage();
    }

}