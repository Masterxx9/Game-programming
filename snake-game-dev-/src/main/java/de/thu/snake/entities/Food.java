package de.thu.snake.entities;

import de.thu.snake.constants.Config;
import de.thu.snake.entities.utils.Position;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Food {
    // Rectangle that represents the food
    private Rectangle rectangle;
    // Value of the food in points
    private int value;
    // Position of the food on the game board
    private Position position;


    public Food() {
        // Get a random position for the food
        this.getRandomPos();
        this.rectangle = new Rectangle(0, 0, Config.BLOCK_SIZE, Config.BLOCK_SIZE);
        this.regenerate();
    }

    public Position getPosition() {
        return this.position;
    }
    // Get a random position for the food
    private void getRandomPos() {
        int newX = (int) (Math.random() * Config.GAME_WIDTH) / Config.BLOCK_SIZE * Config.BLOCK_SIZE;
        int newY = (int) (Math.random() * Config.GAME_HEIGHT) / Config.BLOCK_SIZE * Config.BLOCK_SIZE;
        this.position = new Position(newX, newY);
    }
    // Regenerate the food
    public void regenerate() {
        Random random = new Random();
        int randomColor = random.nextInt(4);
        this.value = 1;
        // Set the fill color of the rectangle based on the random color
        switch (randomColor) {
            case 0 -> rectangle.setFill(Color.GREEN);
            case 1 -> rectangle.setFill(Color.GREENYELLOW);
            case 2 -> {
                rectangle.setFill(Color.PURPLE);
                this.value = 2;
            }
            case 3 -> {
                rectangle.setFill(Color.GOLD);
                this.value = 4;
            }
        }
        // Get a random position for the food
        this.getRandomPos();
        this.rectangle.setTranslateX(this.position.getXPos());
        this.rectangle.setTranslateY(this.position.getYPos());
    }
    // Get the value of the food in points
    public int getPoints() {
        return this.value;
    }
    // Get the rectangle that represents the food
    public Rectangle getView() {
        return this.rectangle;
    }
}
