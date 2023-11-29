package de.thu.snake.constants;

import javafx.stage.Stage;

public class Config {

    public static final int HEIGHT = 700;
    public static final int WIDTH = 400;
    public static final int BLOCK_SIZE = 25;
    public static final int GAME_WIDTH = 28 * BLOCK_SIZE;
    public static final int GAME_HEIGHT = 16 * BLOCK_SIZE;
    public static final int START_POS_X = 0;
    public static final int START_POS_Y = 0;

    public static final String TITLE = "SnakeFX";
    public static final boolean istHeadlessTest = true;
    public static Stage STAGE = new Stage();

    public static void init() {
        STAGE.setTitle(TITLE);
        STAGE.setResizable(false);

    }
}
