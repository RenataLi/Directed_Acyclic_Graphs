package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class OriginTest {
    Origin origin;
    Origin originMain1;

    @BeforeEach
    void setUp() {
        origin = new Origin(0, 0);
        originMain1 = new Origin(2, 2);
    }

    @Test
    void getBounds() throws DAGConstraintException {
        Origin or1 = new Origin(4, 4);
        Origin or2 = new Origin(7, 7);
        Point p1 = new Point(8, 8);
        Point p2 = new Point(10, 23.5);
        Point p3 = new Point(2, 2);
        Point p4 = new Point(-1, -2);
        or2.addPoint(p1);
        or2.addPoint(p2);
        or1.addPoint(p3);
        or1.addPoint(p4);
        or1.addPoint(or2);
        Origin or3 = new Origin(4, 4);
        Origin or4 = new Origin(7, 7);
        or4.addPoint(p1);
        or4.addPoint(p2);
        or3.addPoint(p3);
        or3.addPoint(p4);
        or3.addPoint(or4);
        BoundBox boundBox = or1.getBounds();
        BoundBox boundBox1 = or3.getBounds();
        assertEquals(boundBox.getLeft(), boundBox1.getLeft());
        assertEquals(boundBox.getRight(), boundBox1.getRight());
        assertEquals(boundBox.getDown(), boundBox1.getDown());
        assertEquals(boundBox.getUp(), boundBox1.getUp());
    }

    @Test
    void getChildren() throws DAGConstraintException {
        Origin or1 = new Origin(4, 4);
        Origin or2 = new Origin(7, 7);
        Point p1 = new Point(8, 8);
        Point p2 = new Point(10, 23.5);
        Point p3 = new Point(2, 2);
        Point p4 = new Point(-1, -2);
        or2.addPoint(p1);
        or2.addPoint(p2);
        or1.addPoint(p3);
        or1.addPoint(p4);
        HashSet<Point> set = new HashSet<>();
        set.add(p3);
        set.add(p4);
        set.add(or2);
        or1.addPoint(or2);
        assertEquals(set, or1.getChildren());
    }

    @Test
    void setChildren() throws DAGConstraintException {
        Throwable throwable = assertThrows(DAGConstraintException.class,
                new Executable() {
                    @Override
                    public void execute() throws Throwable {
                        HashSet<Point> set1 = new HashSet<>();
                        HashSet<Point> set2 = new HashSet<>();
                        HashSet<Point> set3 = new HashSet<>();
                        HashSet<Point> set4 = new HashSet<>();
                        Point point1 = new Point(-1, -1);
                        Point point2 = new Point(2, 1);
                        Point point3 = new Point(1, 4);
                        Point point4 = new Point(1, 1);
                        Origin origin1 = new Origin(0, 1);
                        Origin origin2 = new Origin(1, 1);
                        Origin origin3 = new Origin(3, 3);
                        Origin origin4 = new Origin(4, 3);
                        Origin origin5 = new Origin(5, 3);
                        Origin origin6 = new Origin(1.4, 3);
                        origin1.addPoint(point1);
                        origin1.addPoint(point2);
                        origin1.addPoint(point3);
                        origin3.addPoint(point4);
                        origin2.addPoint(origin1);
                        origin2.addPoint(origin3);
                        origin1.addPoint(origin2);
                        set1.add(origin2);
                        origin.setChildren(set1);
                    }
                }
        );

    }

    /**
     * This test shows that the program finds loops and
     * * throws the corresponding exception (as specified in Task).
     * * This test passes because the line "Cycle detected" is output, which means that the cycle is found.
     *
     * @throws DAGConstraintException
     */
    @Test
    void addPoint() throws DAGConstraintException {
        Throwable throwable = assertThrows(DAGConstraintException.class,
                new Executable() {
                    @Override
                    public void execute() throws Throwable {
                        origin.addPoint(new Point(1, 23));
                        origin.addPoint(new Point(2, 2));
                        Origin origin1 = new Origin(5, 5);
                        Origin origin2 = new Origin(10, 5);
                        Origin origin3 = new Origin(25, 5);
                        Origin origin4 = new Origin(45, 5);
                        origin1.addPoint(origin2);
                        origin2.addPoint(origin3);
                        origin3.addPoint(origin4);
                        origin4.addPoint(origin2);
                    }
                }
        );
    }

    @Test
    void getOutputStr() {
        Origin or = new Origin(1, 2);
        assertEquals("indent Origin: id=2(1.0, 2.0)\n", or.getOutputStr("indent "));
    }
}