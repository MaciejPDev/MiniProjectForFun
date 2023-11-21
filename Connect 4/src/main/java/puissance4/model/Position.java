package puissance4.model;

import java.util.Objects;

/**
 * This class contains all the methods to handle a Positon
 */
public class Position {

    private final int x;
    private final int y;

    /**
     * Constructor for the Position
     * @param x
     * @param y
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Getter for the x attribute
     * @return the value of x
     */
    public int getX() {
        return x;
    }

    /**
     * Getter for the y attribute
     * @return the value of y
     */
    public int getY() {
        return y;
    }

    /**
     * Returns a new Position which is the old position moved in the given direction
     * @param direction the given direction
     * @return a new Position
     */
    public Position next(Direction direction) {
        return new Position(this.x + direction.getX(), this.y + direction.getY());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
