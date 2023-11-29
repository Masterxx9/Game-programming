package de.thu.snake.entities;

import de.thu.snake.constants.Config;
import de.thu.snake.constants.Difficulty;
import de.thu.snake.constants.Direction;
import de.thu.snake.entities.utils.Position;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
/**
 * The `Game` class is the main game class of the snake game.
 * It contains all the necessary methods and variables to run the game.
 */
public class Game {
    static public Difficulty difficulty = Difficulty.EASY;
    // Static field to keep track of the current direction of the snake
    static public Direction direction;
    // Static field to keep track of the speed of the snake
    static private Duration speed = Duration.seconds(0.0);
    // A timeline object to handle the animation of the snake
    private final Timeline timeline = new Timeline();
    World world = new World();
    Food food = new Food();
    Snake snake = new Snake();
    /**
     * Constructor to create a new Game object with the provided food and snake objects.
     *
     * @param food   The food object for this game.
     * @param snake  The snake object for this game.
     */
    public Game(Food food, Snake snake) {
        this.food = food;
        this.snake = snake;
    }

    /**
     * Default constructor for the Game class.
     */
    public Game() {

    }
    /**
     * Method to start the game.
     * It sets up the scene and stage, adds a KeyEvent filter to handle the movement of the snake,
     * sets the initial direction of the snake, and starts the timeline to handle the animation.
     */
    public void startGame() {
        Stage stage = Config.STAGE;
        Scene scene = new Scene(game());
        // Add a KeyEvent filter to handle the movement of the snake based on the key pressed
        scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
            if ((key.getCode().equals(KeyCode.W) || (key.getCode().equals(KeyCode.UP))) && direction != Direction.DOWN) {
                direction = Direction.UP;
            } else if ((key.getCode().equals(KeyCode.S) || key.getCode().equals(KeyCode.DOWN)) && direction != Direction.UP) {
                direction = Direction.DOWN;
            } else if ((key.getCode().equals(KeyCode.A) || key.getCode().equals(KeyCode.LEFT)) && direction != Direction.RIGHT) {
                direction = Direction.LEFT;
            } else if ((key.getCode().equals(KeyCode.D) || key.getCode().equals(KeyCode.RIGHT)) && direction != Direction.LEFT) {
                direction = Direction.RIGHT;
            }
        });
        stage.setScene(scene);
        stage.show();
        direction = Direction.RIGHT;
        timeline.play();

    }


    public Parent game() {

        switch (difficulty) {
            case EASY -> speed = Duration.seconds(0.2);
            case MEDIUM -> speed = Duration.seconds(0.06);
            case HARD -> speed = Duration.seconds(0.03);
        }

        KeyFrame frame = new KeyFrame(speed, event -> {
            switch (direction) {
                case UP -> {
                    snake.doMove(new Position(0, -Config.BLOCK_SIZE));
                }
                case DOWN -> {

                    snake.doMove(new Position(0, +Config.BLOCK_SIZE));
                }
                case LEFT -> {

                    snake.doMove(new Position(-Config.BLOCK_SIZE, 0));

                }
                case RIGHT -> {
                    snake.doMove(new Position(+Config.BLOCK_SIZE, 0));

                }
            }
            // Check if the game is over by calling isGameOver method

            if (isGameOver(snake)) {

                try {
                    this.gameOver();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            // Check if the snake is eating the food by calling eatingFood method

            this.eatingFood(snake.getHeadPosition(), food.getPosition());


        });
        timeline.getKeyFrames().add(frame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        return world.generate(snake, food);
    }

    // Method to check if the snake is eating the food
    public boolean eatingFood(Position snakeHeadPos, Position foodPos) {
        // If the snake head position and the food position match, add a new body part to the snake and regenerate food
        if (snakeHeadPos.getXPos() == foodPos.getXPos() && snakeHeadPos.getYPos() == foodPos.getYPos()) {
            snake.addNewBodyPart();
            world.setPoints(world.getPoints() + food.getPoints());
            food.regenerate();
            return true;
        }
        return false;
    }
    // Method to check if the game is over
    public boolean isGameOver(Snake snake) {
        Position snakeHeadPos = snake.getHeadPosition();
        // If the snake head hits the wall, game is over
        if (snakeHeadPos.getXPos() < 0 || snakeHeadPos.getXPos() >= Config.GAME_WIDTH || snakeHeadPos.getYPos() < 0 || snakeHeadPos.getYPos() >= Config.GAME_HEIGHT) {
            return true;
        }
        // If the snake head hits its own body, game is over
        else if (snake.getSnakeBodyParts().size() > 1) {
            for (Node part : snake.getSnakeBodyParts()
            ) {
                if (part.getTranslateX() == snakeHeadPos.getXPos() && part.getTranslateY() == snakeHeadPos.getYPos() && part != snake.getSnakeBodyParts().get(0)) {
                    return true;
                }
            }


        }

        return false;
    }

    // Method to handle the game over event
    public void gameOver() throws IOException {
        world.setPoints(0);
        this.stopGame();
        // Load the Game Over FXML file
        Parent settings = FXMLLoader.load(getClass().getResource("/fxml/menus/GameOver.fxml"));
        Scene settingScene = new Scene(settings);
        Config.STAGE.setScene(settingScene);
        Config.STAGE.setTitle("Game Over");
        Config.STAGE.show();

    }
// The `stopGame` method stops the game.
    public void stopGame() {
        timeline.stop();
        snake.clear();

    }

}
