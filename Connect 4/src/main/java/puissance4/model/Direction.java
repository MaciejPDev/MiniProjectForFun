package puissance4.model;

/**
 * @TODO JAVADOC
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

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getOpposite() {
        return opposite;
    }
}
