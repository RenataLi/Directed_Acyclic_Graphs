package org.example;

/**
 * BoundBox class.
 */
public class BoundBox {

    /**
     * Coordinates defining the BoundBox.
     *
     * @param left  - min x.
     * @param up    - max y.
     * @param right - max x.
     * @param down  - min y.
     */
    public BoundBox(double left, double up, double right, double down) {
        this.left = left;
        this.up = up;
        this.right = right;
        this.down = down;
    }

    /**
     * Standard constructor.
     *
     * @param boundBox - bound Box.
     */
    public BoundBox(BoundBox boundBox) {
        left = boundBox.left;
        right = boundBox.right;
        up = boundBox.up;
        down = boundBox.down;
    }

    /**
     * Get MinX.
     *
     * @return MinX.
     */
    public double getLeft() {
        return left;
    }

    /**
     * Getting MaxY.
     *
     * @return MaxY.
     */
    public double getUp() {
        return up;
    }

    /**
     * Getting MaxX.
     *
     * @return
     */
    public double getRight() {
        return right;
    }

    /**
     * Getting MinY.
     *
     * @return
     */
    public double getDown() {
        return down;
    }

    /**
     * Expand the scope to include new ones
     * does not change the object, returns new frames.
     *
     * @param boundBox new framework.
     * @return returns new frames.
     */
    public BoundBox extendedBoundingBox(BoundBox boundBox) {
        BoundBox newBoundBox = new BoundBox(this);
        if (boundBox.left < newBoundBox.left) newBoundBox.left = boundBox.left;
        if (boundBox.right > newBoundBox.right) newBoundBox.right = boundBox.right;
        if (boundBox.up > newBoundBox.up) newBoundBox.up = boundBox.up;
        if (boundBox.down < newBoundBox.down) newBoundBox.down = boundBox.down;
        return newBoundBox;
    }


    /**
     * Is called by Origin in case there are other Origin's in the tree
     * * to shift their Bounding Boxes to their Position, for correct calculation.
     * BoundingBox of the current Origin.
     *
     * @param origin - Relative to whom to shift
     * @return - Offset Origin.
     */
    public BoundBox movedByOrigin(Origin origin) {
        BoundBox newBoundBox = new BoundBox(this);
        newBoundBox.left += origin.getPosition().getX();
        newBoundBox.right += origin.getPosition().getX();
        newBoundBox.up += origin.getPosition().getY();
        newBoundBox.down += origin.getPosition().getY();
        return newBoundBox;
    }


    private double left;
    private double up;
    private double right;
    private double down;

    @Override
    public String toString() {
        return "BoundingBox{" +
                "left=" + left +
                ", up=" + up +
                ", right=" + right +
                ", down=" + down +
                '}';
    }
}
