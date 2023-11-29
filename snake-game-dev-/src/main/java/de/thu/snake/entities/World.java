package de.thu.snake.entities;

import de.thu.snake.constants.Config;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
/**
 * Class that represents the game world, including the playing field and score.
 */
public class World {
    private Pane field;
    private Label score;
    private int points;


    /**
     * Creates a new World instance with a default score label.
     */
    public World() {
        this.points = 0;
        this.score = new Label("  Score : " + points + "   ");
        this.field = new Pane();
        this.initGui();
    }
    /**
     * Creates a new World instance with a custom score label.
     * @param field The playing field.
     * @param score The score label.
     */
    public World(Pane field, Label score) {
        this.points = 0;
        this.score = score;
        this.field = field;
        this.initGui();
    }

    /**
     * Initializes the GUI elements.
     */
    private void initGui() {
        // Translate the score label to the center of the stage.
        this.score.setTranslateX(Config.STAGE.getWidth() / 2 - Config.BLOCK_SIZE);
        // Set the background color of the score label.
        Color col = Color.rgb(205, 205, 205);
        CornerRadii corn = new CornerRadii(10);
        Background background = new Background(new BackgroundFill(col, corn, Insets.EMPTY));
        this.score.setBackground(background);
        this.score.setId("score");
        // Set the preferred size of the playing field.
        this.field.setPrefSize(Config.GAME_WIDTH, Config.GAME_HEIGHT);
    }

    /**
     * Gets the number of points earned.
     * @return The number of points earned.
     */
    public int getPoints() {
        return points;
    }
    /**
     * Sets the number of points earned.
     * @param points The number of points to set.
     */
    public void setPoints(int points) {
        this.points = points;
        this.score.setText("  Score : " + points + "   ");
    }
    /**
     * Generates the playing field and adds the snake and food to it.
     * @param snake The snake instance.
     * @param food The food instance.
     * @return The playing field.
     */
    public Pane generate(Snake snake, Food food) {
        // Add the snake, food, and score label to the playing field.
        this.field.getChildren().addAll(snake.getView(), food.getView(), this.score);

        return this.field;
    }
}
