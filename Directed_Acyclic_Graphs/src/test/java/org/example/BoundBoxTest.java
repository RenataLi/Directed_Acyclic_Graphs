package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoundBoxTest {
    BoundBox b;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        b = new BoundBox(-2, 1, 3, -1);
    }


    @Test
    void getLeft() {
        double left = b.getLeft();
        assertEquals(-2, left);
    }

    @Test
    void getUp() {
        assertEquals(b.getUp(), 1); // must be 1
    }

    @Test
    void getRight() {
        assertEquals(b.getRight(), 3);
    }

    @Test
    void getDown() {
        assertEquals(b.getDown(), -1);
    }

    @Test
    void testToString() {
        assertEquals("BoundingBox{left=-2.0, up=1.0, right=3.0, down=-1.0}", b.toString());
    }

    @Test
    void extendedBoundingBox() {
        BoundBox boundBox = new BoundBox(-1, -2, 3, 5);
        assertEquals(boundBox.extendedBoundingBox(boundBox).getRight(), boundBox.getRight());
    }

    @Test
    void movedByOrigin() {
        Origin origin = new Origin(2, 2);
        BoundBox boundBox = new BoundBox(0, 0, -1, 30);
        assertEquals(boundBox.movedByOrigin(origin).getRight(), 1);
    }
}