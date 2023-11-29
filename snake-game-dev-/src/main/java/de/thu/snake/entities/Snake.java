package de.thu.snake.entities;

import de.thu.snake.constants.Config;
import de.thu.snake.entities.utils.Position;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
/**
 * Snake class representing the snake in the game
 */
public class Snake {
    // Group to store the body parts of the snake
    private Group snakeBody;
    // List to store the body parts of the snake
    private ObservableList<Node> snakeParts;
    // Rectangle to represent the head of the snake
    private Rectangle head;
    private Position lastTailPosition;

    /**
     * Constructor to initialize the snake
     */
    public Snake() {
        this.snakeBody = new Group();
        snakeParts = snakeBody.getChildren();
        this.lastTailPosition = new Position(0, 0);
        this.init();
    }

    /**
     * Method to initialize the snake
     */
    private void init() {
        // Initialize the head
        this.head = new Rectangle(Config.START_POS_X, Config.START_POS_Y, Config.BLOCK_SIZE, Config.BLOCK_SIZE);
        this.head.setFill(Color.RED);
        // Add the head to the snake body
        this.snakeParts.add(this.head);
    }
    /**
     * Method to move the snake to a given position
     * @param position the position to move the snake to
     */
    public void doMove(Position position) {
        moveBody();
        moveHead(position);


    }

    /**
     * Method to move the head of the snake to a given position
     * @param position the position to move the head to
     */
    private void moveHead(Position position) {
        // Calculate the new position for the head
        double newX = position.getXPos() + this.head.getTranslateX();
        double newY = position.getYPos() + this.head.getTranslateY();
        // Update the position of the head
        this.head.setTranslateY(newY);
        this.head.setTranslateX(newX);

    }
    /**
     * Method to return the snake body parts
     * @return the snake body parts
     */
    public ObservableList<Node> getSnakeBodyParts() {
        return snakeParts;
    }
    /**
     * Method to move the body of the snake
     */
    private void moveBody() {

        ArrayList<Rectangle> tempBody = new ArrayList<>();

        // Deep copy of the snake body parts to tempBody
        for (Node bodyPart : this.snakeParts) {
            Rectangle tempBodyPart = new Rectangle(Config.BLOCK_SIZE, Config.BLOCK_SIZE);
            tempBodyPart.setTranslateX(bodyPart.getTranslateX());
            tempBodyPart.setTranslateY(bodyPart.getTranslateY());

            tempBody.add(tempBodyPart);
        }
        // Store the position of the last tail
        this.lastTailPosition = new Position(this.snakeParts.get(this.snakeParts.size() - 1).getTranslateX(),
                this.snakeParts.get(this.snakeParts.size() - 1).getTranslateY());


        // Move the body of the snake
        for (int i = 1; i < snakeParts.size(); i++) {
            this.snakeParts.get(i).setTranslateX(tempBody.get(i - 1).getTranslateX());
            this.snakeParts.get(i).setTranslateY(tempBody.get(i - 1).getTranslateY());


        }

    }

    public void addNewBodyPart() {
        // create a new body part for the snake
        Rectangle newPart = new Rectangle(Config.BLOCK_SIZE, Config.BLOCK_SIZE);
        this.snakeParts.add(newPart);
        // set the position of the new body part based on the last tail position
        newPart.setTranslateY(this.lastTailPosition.getYPos() + this.head.getTranslateY());
        newPart.setTranslateX(this.lastTailPosition.getXPos() + this.head.getTranslateX() + Config.BLOCK_SIZE);


    }

    public Group getView() {
        return this.snakeBody;
    }

    public Position getHeadPosition() {
        return new Position(this.head.getTranslateX(), this.head.getTranslateY());
    }

    public void clear() {
        this.snakeParts.clear();
        this.init();
    }

}
