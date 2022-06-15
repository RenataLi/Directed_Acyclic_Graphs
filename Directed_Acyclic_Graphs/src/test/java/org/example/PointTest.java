package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void getPosition() {
        Point point = new Point(1, 2);
        Coord2D c = new Coord2D(1, 2);
        assertEquals(c.getX(), point.position.getX());
        assertEquals(c.getY(), point.position.getY());
    }

    @Test
    void setPosition() {
        Point point = new Point(1, 2);
        point.setPosition(new Coord2D(0, 0));
        assertEquals(point.getPosition().getX(), 0);
    }

    @Test
    void getBounds() {

    }

    @Test
    void getOutputStr() {
        Point point = new Point(1, 2);
        assertEquals("indent Point: (1.0, 2.0)\n", point.getOutputStr("indent "));
    }
}