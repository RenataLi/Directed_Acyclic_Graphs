package org.example;

/**
 * Coord2D class.
 */
public class Coord2D {
    /**
     * Constructor of Coord2D.
     *
     * @param xCoordinate - coordinate of X.
     * @param yCoordinate - coordinate of Y.
     */
    public Coord2D(double xCoordinate, double yCoordinate) {
        this.x = xCoordinate;
        this.y = yCoordinate;
    }

    /**
     * Getter of X.
     *
     * @return x coordinate.
     */
    public double getX() {
        return x;
    }

    /**
     * Getter of Y.
     *
     * @return y coordinate.
     */
    public double getY() {
        return y;
    }

    /**
     * Field of X.
     */
    private final double x;
    /**
     * Field of Y.
     */
    private final double y;
}

