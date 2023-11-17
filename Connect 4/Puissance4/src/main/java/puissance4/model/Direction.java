package puissance4.model;

public enum Direction {
    N(-1, 0), W(0, -1), E(0, 1), S(1, 0), NW(-1, -1), NE(-1, 1), SW(1, -1), SE(1, 1);

    private final int x;
    private final int y;

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
}
