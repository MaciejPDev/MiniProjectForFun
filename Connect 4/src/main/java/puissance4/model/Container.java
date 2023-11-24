package puissance4.model;

import puissance4.exception.ColumnFullException;
import puissance4.exception.NonExistentPositionException;

import java.util.List;

/**
 * This class contains all the methods to handle a Container, which is technically the board
 */
public class Container {

    int height;
    int width;
    private final Box[][] container;
    private boolean isWin;
    private boolean isDraw;

    /**
     * Constructor for the Container
     */
    public Container() {
        this.height = 6;
        this.width = 7;
        this.container = new Box[height][width];
        this.isWin = false;
        this.isDraw = false;
        setUp();
    }

    /**
     * Copy Constructor for the container
     *
     * @param container the container that will be copied
     */
    public Container(Container container) {
        this.height = container.height;
        this.width = container.width;
        this.container = container.container;
        this.isWin = container.isWin;
        this.isDraw = container.isDraw;
    }

    /**
     * This method sets up the container for the usage
     */
    public void setUp() {
        for (int i = 0; i < container.length; i++) {
            for (int j = 0; j < container[i].length; j++) {
                this.container[i][j] = new Box();
            }
        }
    }

    /**
     * Getter for the width attribute
     *
     * @return the value of width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Getter for the height attribute
     *
     * @return the value of height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Getter for the isWin attribute
     *
     * @return the value of isWin
     */
    public boolean isWin() {
        return isWin;
    }

    /**
     * Getter for the isDraw attribute
     *
     * @return the value of isDraw
     */
    public boolean isDraw() {
        return isDraw;
    }

    /**
     * This method verifies if the given position is situated in the container
     *
     * @param position the given position
     * @return true if the position is situated within the boundaries, false otherwise
     */
    public boolean contains(Position position) {
        int x = position.getX();
        int y = position.getY();
        return x >= 0 && x < this.height && y >= 0 && y < this.width;
    }

    /**
     * Checks if the box at the given position is free or does it contain a coin
     *
     * @param position the given position
     * @return true if the position is free, false otherwise
     * @throws NonExistentPositionException if the container does not contain the given position,
     *                                      this exception will be launch
     */
    public boolean isFree(Position position) throws NonExistentPositionException {
        if (!contains(position)) {
            throw new NonExistentPositionException("This column does not exist.");
        }
        return this.container[position.getX()][position.getY()].isFree();
    }

    /**
     * Gets the coin at the given position
     *
     * @param position the given position
     * @return the coin or null if there is no coin at this position
     * @throws NonExistentPositionException if the container does not contain the given position,
     *                                      this exception will be launched
     */
    public Coin getCoin(Position position) throws NonExistentPositionException {
        if (!contains(position)) {
            throw new NonExistentPositionException("This column does not exist.");
        }
        return this.container[position.getX()][position.getY()].getCoin();
    }

    /**
     * This method will place a Coin of the given color in the given column if there is still some space
     *
     * @param y     the column
     * @param color the color of the coin
     * @throws NonExistentPositionException if the container does not contain the given position,
     *                                      this exception will be launched
     * @throws ColumnFullException          if the column has no more free space, this exception will be launched
     */
    public void placeACoin(int y, Color color) throws NonExistentPositionException, ColumnFullException {
        Position position = new Position(0, y);
        if (!this.isFree(position)) {
            throw new ColumnFullException("This column is full.");
        }
        position = getFirstFreePosition(position);
        this.container[position.getX()][position.getY()].setCoin(new Coin(color));
        this.isWin = checkWin(position, color);
        this.isDraw = checkDraw();
    }

    /**
     * This method simulates the "gravity". It returns the first free position in the chosen column starting from
     * the bottom.
     *
     * @param position the position in the column
     * @return the first free position
     * @throws NonExistentPositionException if the container does not contain the given position,
     *                                      this exception will be launched
     */
    private Position getFirstFreePosition(Position position) throws NonExistentPositionException {
        Position newPos = position;
        if (this.contains(position.next(Direction.S)) && this.isFree(position.next(Direction.S))) {
            newPos = getFirstFreePosition(position.next(Direction.S));
        }
        return newPos;
    }

    /**
     * Verifies if the last placed coin caused a victory.
     *
     * @param position the position of the last place coin
     * @param color    the color of the player
     * @return a boolean : true if the coin allow to create a win situation for the player, false otherwise
     * @throws NonExistentPositionException if the container does not contain the given position,
     *                                      this exception will be launched
     */
    private boolean checkWin(Position position, Color color) throws NonExistentPositionException {
        List<Direction> directions = List.of(
                Direction.N,
                Direction.NW,
                Direction.NE,
                Direction.W
        );
        for (Direction dir : directions) {
            if (sameColor(position, dir, color)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifies if from the given position is it possible a line of 4 same colored coin which causes the victory
     *
     * @param position  the position of the last placed coin
     * @param direction the direction in which we have to verify if there are other coins with the same color
     * @param color     the color of the current player
     * @return true if there is a line of 4 same color coins, false otherwise
     * @throws NonExistentPositionException if the container does not contain the given position,
     *                                      this exception will be launched
     */
    private boolean sameColor(Position position, Direction direction, Color color) throws NonExistentPositionException {
        int count = countSameColorCoins(position, direction, 1, color);
        if (count < 4) {
            count += countSameColorCoins(position, direction.getOpposite(), 0, color);
        }
        return count == 4;
    }

    /**
     * Method used by the method just above to achieve its purpose.
     *
     * @param position  a position
     * @param direction a direction
     * @param count     an int
     * @param color     the color of the current player
     * @return an int
     * @throws NonExistentPositionException if the container does not contain the given position,
     *                                      this exception will be launched
     */
    private int countSameColorCoins(Position position, Direction direction, int count, Color color) throws NonExistentPositionException {
        int i = 0;
        boolean sameColor = true;
        while (i < 3 && count != 4 && sameColor) {
            Position next = position.next(direction);
            if (this.contains(next) && (this.getCoin(next) != null && this.getCoin(next).getColor() == color)) {
                count++;
            } else {
                sameColor = false;
            }
            i++;
            position = next;
        }
        return count;
    }

    /**
     * Verify if the game is not in a draw state
     *
     * @return true if there is no possible moves
     * @throws NonExistentPositionException if the container does not contain the given position,
     *                                      this exception will be launched
     */
    private boolean checkDraw() throws NonExistentPositionException {
        for (int i = 0; i < container.length; i++) {
            for (int j = 0; j < container[i].length; j++) {
                if (this.getCoin(new Position(i, j)) == null) {
                    return false;
                }
            }
        }
        return true;
    }
}
