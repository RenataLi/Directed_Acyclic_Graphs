package org.example;

/**
 * Class of Point.
 */
public class Point {
    /**
     * Constructor of the Point class.
     */
    protected Point() {
        position = new Coord2D(0, 0);
    }

    /**
     * Constructor of the Point class as  in task.
     *
     * @param xCoordinate - coordinate x.
     * @param yCoordinate - coordinate y.
     */
    public Point(double xCoordinate, double yCoordinate) {
        position = new Coord2D(xCoordinate, yCoordinate);
    }

    /**
     * Getter of Position.
     *
     * @return - position.
     */
    public Coord2D getPosition() {
        return position;
    }

    /**
     * Setter of Position.
     *
     * @param position - object of class Coord2D.
     */
    public void setPosition(Coord2D position) {
        this.position = position;
    }

    /**
     * Getter of Bounds as in task.
     *
     * @return
     */
    public BoundBox getBounds() {
        return new BoundBox(position.getX(), position.getY(), position.getX(), position.getY());
    }

    /**
     * Field position.
     */
    protected Coord2D position;

    /**
     * @return возвращает строку с отступом для вывода на экран
     */
    public String getOutputStr(String indent) {
        String str = indent + "Point: (" + position.getX() + ", " + position.getY() + ")\n";
        return str;
    }
}

