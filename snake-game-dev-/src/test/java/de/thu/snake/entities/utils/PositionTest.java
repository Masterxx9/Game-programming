package de.thu.snake.entities.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PositionTest {
    @Test
    void getXPos() {
        // creating a new instance of the `Position` class with x=1, y=2
        Position position = new Position(1, 2);
        // asserting that the x position of the created instance is equal to 1
        assertEquals(1, position.getXPos());

    }

    @Test
    void setXPos() {
        // creating a new instance of the `Position` class with x=1, y=2
        Position position = new Position(1, 2);
        // setting the x position to 3
        position.setXPos(3);
        // asserting that the x position of the created instance is now equal to 3
        assertEquals(3, position.getXPos());
    }

    @Test
    void getYPos() {
        // creating a new instance of the `Position` class with x=1, y=2
        Position position = new Position(1, 2);
        // asserting that the y position of the created instance is equal to 2
        assertEquals(2, position.getYPos());
    }

    @Test
    void setYPos() {
        // creating a new instance of the `Position` class with x=1, y=2
        Position position = new Position(1, 2);
        // setting the y position to 3
        position.setYPos(3);
        // asserting that the y position of the created instance is now equal to 3
        assertEquals(3, position.getYPos());
    }

    @Test
    void testToString() {
        // creating a new instance of the `Position` class with x=1, y=2
        Position position = new Position(1, 2);
        // asserting that the string representation of the created instance is equal to "Position{xPos=1.0, yPos=2.0}"
        assertTrue("Position{xPos=1.0, yPos=2.0}".equals(position.toString()));

    }
}