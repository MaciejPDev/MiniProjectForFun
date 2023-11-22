package puissance4.model;

/**
 * This enumeration contains all the methods to handle a Direction
 */
public enum Direction {
    N(-1, 0), W(0, -1), E(0, 1), S(1, 0), NW(-1, -1), NE(-1, 1), SW(1, -1), SE(1, 1);

    private final int x;
    private final int y;
    private Direction opposite;

    static {
        N.opposite = S;
        S.opposite = N;
        W.opposite = E;
        E.opposite = W;
        NW.opposite = SE;
        NE.opposite = SW;
        SW.opposite = NE;
        SE.opposite = NW;
    }

    /**
     * Private Constructor for a Direcion
     *
     * @param x the movement on the x-axe
     * @param y the movement on the y-axe
     */
    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Getter for the X attribute
     *
     * @return the value of x
     */
    public int getX() {
        return x;
    }

    /**
     * Getter for the Y attribute
     *
     * @return the value of y
     */
    public int getY() {
        return y;
    }

    /**
     * Getter for the Opposite attribute
     *
     * @return the value of opposite (the opposite direction)
     */
    public Direction getOpposite() {
        return opposite;
    }
}
