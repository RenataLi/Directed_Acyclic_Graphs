package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TestClass for Coord2D.
 */
class Coord2DTest {
    Coord2D coord2D;

    @BeforeEach
    void setUp() {
        coord2D = new Coord2D(30, 23.4);
    }

    @Test
    void getX() {
        assertEquals(30, coord2D.getX());
    }

    @Test
    void getY() {
        assertEquals(23.4, coord2D.getY());
    }
}