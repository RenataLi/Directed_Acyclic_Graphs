package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class SpaceTest {
    Space space;

    @BeforeEach
    void setUp() {
        space = new Space();
    }

    @Test
    void addOrigin() throws DAGConstraintException {
        Throwable throwable = assertThrows(DAGConstraintException.class,
                new Executable() {
                    @Override
                    public void execute() throws Throwable {
                        Origin or1 = new Origin(4, 4);
                        or1.addPoint(new Point(2, 2));
                        or1.addPoint(new Point(-1, -2));
                        space.addOrigin(or1);
                        Origin or2 = new Origin(-3, -4);
                        Point p1 = new Point(1, 1);
                        or2.addPoint(p1);
                        or2.addPoint(new Point(-1, -2));
                        space.addOrigin(or2);
                        Origin or3 = new Origin(1, -4);
                        or3.addPoint(p1);
                        or3.addPoint(new Point(-1, -2));
                        space.addOrigin(or3);
                        // circle:
                        or1.addPoint(or2);
                        or2.addPoint(or1);
                    }
                }
        );

    }

    @Test
    void getOutputStr() {
        Space space = new Space();
        assertEquals("indent Space: id=1(0.0, 0.0)\n", space.getOutputStr("indent "));
    }
}