package ecosystem;

/**
 * Represents a point in a 2D space with x and y coordinates.
 */
class Point {
    private int x; // x-coordinate of the point
    private int y; // y-coordinate of the point

    /**
     * Constructs a Point with specified x and y coordinates.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Translates the point by the specified vector (dx, dy).
     *
     * @param dx the change in x-coordinate
     * @param dy the change in y-coordinate
     */
    public void translate(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    /**
     * Gets the x-coordinate of the point.
     *
     * @return the x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the x-coordinate of the point.
     *
     * @param x the new x-coordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets the y-coordinate of the point.
     *
     * @return the y-coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the y-coordinate of the point.
     *
     * @param y the new y-coordinate
     */
    public void setY(int y) {
        this.y = y;
    }
}