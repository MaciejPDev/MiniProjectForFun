package puissance4.model;

import puissance4.exception.ColumnFullException;
import puissance4.exception.NonExistentPositionException;
import java.util.List;

/**
 * @TODO Finir la javadoc
 */

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
     * @param container the container that will be copied
     */
    public Container(Container container){
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
     * @return the value of width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Getter for the height attribute
     * @return the value of height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Getter for the isWin attribute
     * @return the value of isWin
     */
    public boolean isWin() {
        return isWin;
    }
    /**
     * Getter for the isDraw attribute
     * @return the value of isDraw
     */
    public boolean isDraw() {
        return isDraw;
    }

    /**
     * This method verifies if the given position is situated in the container
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
     * @param y the column
     * @param color the color of the coin
     * @throws NonExistentPositionException if the container does not contain the given position,
     *                                      this exception will be launched
     * @throws ColumnFullException if the column has no more free space, this exception will be launched
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
     * This
     * @param position
     * @return
     * @throws NonExistentPositionException
     */
    private Position getFirstFreePosition(Position position) throws NonExistentPositionException {
        Position newPos = position;
        if (this.contains(position.next(Direction.S)) && this.isFree(position.next(Direction.S))) {
            newPos = getFirstFreePosition(position.next(Direction.S));
        }
        return newPos;
    }

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

    private boolean sameColor(Position position, Direction direction, Color color) throws NonExistentPositionException {
        int count = countSameColorCoins(position,direction,1,color);
        if (count < 4) {
            count += countSameColorCoins(position,direction.getOpposite(),0,color);
        }
        return count == 4;
    }

    private int countSameColorCoins(Position position, Direction direction, int count, Color color) throws NonExistentPositionException {
        int i = 0;
        boolean sameColor = true;
        while (i < 3 && count != 4 && sameColor){
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

    private boolean checkDraw() throws NonExistentPositionException {
        for (int i = 0; i < container.length; i ++){
            for (int j = 0; j < container[i].length; j++){
                if (this.getCoin(new Position(i,j)) == null) {
                    return false;
                }
            }
        }
        return true;
    }
}
