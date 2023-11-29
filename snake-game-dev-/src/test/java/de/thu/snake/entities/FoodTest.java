package de.thu.snake.entities;

import de.thu.snake.constants.Config;
import de.thu.snake.entities.utils.Position;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FoodTest {

    private Food food;

    @BeforeAll
    public static void setupHeadless() {
        // This code is for running the tests on a headless server (Jenkins)
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
        this.food = new Food();
    }


    @Test
    @DisplayName("Test if the food has an initial position on the game board after the game starts")
    void getPosition() {
        //Tests if the food's position is not null when the game starts.
        assertNotNull(this.food.getPosition());
    }

    @Test
    @DisplayName("Test if the food is regenerated in a different position after calling the regenerate method")
    void regenerate() {
        //Tests if the food is regenerated in a different position after calling the regenerate method.
        Position oldPosition = new Position(this.food.getPosition().getXPos(), this.food.getPosition().getYPos());
        this.food.regenerate();
        assertFalse(oldPosition.toString().equals(this.food.getPosition().toString()));
    }

    @Test
    @DisplayName("Test if the food could return the correct points, which is 1, 2 or 4")
    void getPoints() {
        //Tests if the food's points are 1, 2, or 4.
        assertTrue(this.food.getPoints() == 1 || this.food.getPoints() == 2 || this.food.getPoints() == 4);
    }

    @Test
    @DisplayName("Test if the food has a view")
    void getView() {
        //Tests if the food has a view.
        assertNull(this.food.getView());
    }
}
