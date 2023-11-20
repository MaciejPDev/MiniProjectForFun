package puissance4.model;

import puissance4.exception.ColumnFullException;
import puissance4.exception.NonExistentPositionException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @TODO Finir la javadoc
 */

/**
 * This class contains all the methods to handle a container, which is technically the board
 */
public class Container {

    private final Box[][] container;
    private boolean isWin;
    private boolean isDraw;

    /**
     * Constructor for the container
     */
    public Container() {
        this.container = new Box[6][7];
        this.isWin = false;
        this.isDraw = false;
        setUp();
    }

    /**
     * Copy Constructor for the container
     * @param container the container that will be copied
     */
    public Container(Container container){
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

    public Box[][] getContainer() {
        return container;
    }

    public boolean isWin() {
        return isWin;
    }

    public boolean isDraw() {
        return isDraw;
    }

    public boolean contains(Position position) {
        int x = position.getX();
        int y = position.getY();
        return x >= 0 && x < 6 && y >= 0 && y < 7;
    }

    public boolean isFree(Position position) throws NonExistentPositionException {
        if (!contains(position)) {
            throw new NonExistentPositionException("This column does not exist.");
        }
        return this.container[position.getX()][position.getY()].isFree();
    }

    public Coin getCoin(Position position) throws NonExistentPositionException {
        if (!contains(position)) {
            throw new NonExistentPositionException("This column does not exist.");
        }
        return this.container[position.getX()][position.getY()].getCoin();
    }

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
        Map<Direction, Direction> opposites = createOpposites();
        int count = countSameColorCoins(position,direction,1,color);
        if (count < 4) {
            count += countSameColorCoins(position,opposites.get(direction),0,color);
        }
        return count == 4;
    }

    private Map<Direction,Direction> createOpposites() {
        Map<Direction,Direction> map = new HashMap<>();
        map.put(Direction.N, Direction.S);
        map.put(Direction.S,Direction.N);
        map.put(Direction.E, Direction.W);
        map.put(Direction.W,Direction.E);
        map.put(Direction.NW, Direction.SE);
        map.put(Direction.NE,Direction.SW);
        map.put(Direction.SW, Direction.SE);
        map.put(Direction.SE,Direction.SW);

        return map;
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
