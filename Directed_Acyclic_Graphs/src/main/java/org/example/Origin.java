package org.example;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

/**
 * Class origin.
 */
public class Origin extends Point {
    /**
     * All vertices are numbered.
     */
    static protected Vector<Origin> vertices;

    /**
     * Static Constructor.
     */
    static {
        vertices = new Vector<Origin>();
    }

    /**
     * Origin number - in the order of creation.
     */
    protected int id;

    /**
     * This origin is always in vertices[id].
     */
    protected Origin() {
        super();
        id = vertices.size();
        vertices.add(this);
        children = new HashSet<Point>();
    }

    /**
     * Constructor of Origin.
     *
     * @param xCoordinate - coordinate of x.
     * @param yCoordinate - coordinate of y.
     */
    public Origin(double xCoordinate, double yCoordinate) {
        super(xCoordinate, yCoordinate);
        id = vertices.size();
        vertices.add(this);
        children = new HashSet<Point>();
    }

    /**
     * Getter of Bounds.
     *
     * @return BoundBox.
     */
    public BoundBox getBounds() {
        assert (hasCircles() == null);
        /**
         * We do not add the origin of coordinates!
         */
        BoundBox boundBox = null;
        Iterator<Point> pointIterator = children.iterator();
        while (pointIterator.hasNext()) {
            Point point = pointIterator.next();
            String className = point.getClass().getSimpleName();
            /**
             * If you haven't added any -- assign BB from the first element.
             */
            if (boundBox == null) {
                boundBox = point.getBounds();
                if (point.getClass().equals(Origin.class) && boundBox != null)
                    // если первый элемент - Origin - сдвигаем его BB Относительно this
                    boundBox = boundBox.movedByOrigin((Origin) point);
            } else {
                if (point.getClass().equals(Origin.class)) {
                    BoundBox newBound = ((Origin) point).getBounds();
                    newBound = newBound.movedByOrigin((Origin) point);
                    boundBox = boundBox.extendedBoundingBox(newBound);
                } else if (point.getClass().equals(Point.class)) {
                    BoundBox newBound = ((Point) point).getBounds();
                    boundBox = boundBox.extendedBoundingBox(newBound);
                } else {
                    System.out.println("Error - wrong class!");
                }
            }
        }
        return boundBox;
    }

    /**
     * Getter of children.
     *
     * @return set of children.
     */
    public Set<Point> getChildren() {
        return children;
    }

    /**
     * Setter of children.
     *
     * @param set set of children.
     * @throws DAGConstraintException if there is cycles.
     */
    public void setChildren(Set<Point> set) throws DAGConstraintException {
        Set<Point> pointSet = Set.copyOf(children);
        children.clear();
        children = Set.copyOf(set);
        String msg = hasCircles();
        if (msg != null) {
            children = pointSet;
            throw new DAGConstraintException(msg);
        }
    }

    /**
     * Add a point or Origin to the tree to this node.
     *
     * @param point - point or Origin to add.
     *              If a loop is created as a result of adding -- DAGConstraintException is thrown.
     */
    public void addPoint(Point point) throws DAGConstraintException {
        children.add(point);
        String str = hasCircles();
        if (str != null) {
            children.remove(point);
            throw new DAGConstraintException(str);
        }
    }

    /**
     * Set of children.
     */
    protected Set<Point> children;

    /**
     * String format of Origin.
     *
     * @param indent - Indentify of origin.
     * @return - String form of origin.
     */
    public String getOutputStr(String indent) {
        assert (hasCircles() == null);
        String stringForm = indent + "Origin: id=" + id + "(" + position.getX() + ", " + position.getY() + ")\n";
        Iterator<Point> it = children.iterator();
        while (it.hasNext()) {
            stringForm += it.next().getOutputStr(indent + "  ");
        }
        return stringForm;
    }

    /**
     * Checking for circles.
     *
     * @return Null if there are no cycles, or a string with the path of the first cycle found.
     */
    public static String hasCircles() {
        // Analyzing the vertices, somehow building the entire graph between them.
        // Space is also one of the vertices of the graph.
        Vector<int[]> pairs = new Vector<int[]>();
        for (int i = 0; i < vertices.size(); i++) {
            int from = i;
            Iterator<Point> it = vertices.elementAt(i).children.iterator();
            while (it.hasNext()) {
                Point p = it.next();
                if (p.getClass().equals(Origin.class) || p.getClass().equals(Space.class)) {
                    int to = vertices.indexOf(p);
                    int[] rib = new int[2];
                    rib[0] = from;
                    rib[1] = to;
                    pairs.add(rib);
                }
            }
        }
        System.out.println("graph:");
        for (int i = 0; i < pairs.size(); i++) {
            System.out.println(" " + pairs.elementAt(i)[0] + " --> " + pairs.elementAt(i)[1]);
        }

        Graph G = new Graph(pairs, vertices.size());
        if (G.dFS()) {
            System.out.println("Loop detected!");
            return G.getPath();
        }
        return null;
    }

}

