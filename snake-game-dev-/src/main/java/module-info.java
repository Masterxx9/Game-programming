module snakegame {
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.controls;

    requires javafx.fxml;
    opens de.thu.snake;
    opens de.thu.snake.menus;
    opens de.thu.snake.entities;
    opens de.thu.snake.constants;


}