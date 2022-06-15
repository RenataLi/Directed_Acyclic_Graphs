package org.example;

import java.util.Iterator;

/**
 * Space class.
 */
public class Space extends Origin {
    /**
     * Constructor of space.
     */
    public Space() {
        super();
    }

    /**
     * Method for adding origin.
     *
     * @param origin
     */
    public void addOrigin(Origin origin) {
        this.children.add(origin);
    }

    /**
     * Method for outpur in string form.
     *
     * @param indent - Indentify of origin.
     * @return string.
     */
    public String getOutputStr(String indent) {
        String str = indent + "Space: id=" + id + "(" + position.getX() + ", " + position.getY() + ")\n";
        Iterator<Point> it = children.iterator();
        while (it.hasNext()) {
            str += it.next().getOutputStr(indent + "  ");
        }
        return str;
    }

}

