package puissance4.model;


import puissance4.exception.ColumnFullException;
import puissance4.exception.NonExistentPositionException;

/**
 * This interface gives all the methods that can interact with a model. It's purpose is to respect
 * the encapsulation.
 */
public interface Model {

    /**
     * Launches the game
     */
    void start();

    /**
     * Gets you a copy of the container which contains all the coins
     *
     * @return the container
     */
    Container getContainer();

    /**
     * Returns the current Player, which has to play
     *
     * @return the current player
     */
    Player getCurrentPlayer();

    /**
     * Places a coin in the chosen column
     *
     * @param column the chosen column
     * @throws ColumnFullException          if the column has no more free space, this exception will be launched
     * @throws NonExistentPositionException if the container does not contain the given position,
     *                                      this exception will be launch
     */
    void placeACoin(int column) throws ColumnFullException, NonExistentPositionException;

    /**
     * Check is the state of the game/model is set to Play state
     *
     * @return true if that's the case, false otherwise
     */
    boolean play();

    /**
     * Check is the state of the game/model is set to Win state
     *
     * @return true if that's the case, false otherwise
     */
    boolean win();

    /**
     * Check is the state of the game/model is set to Draw state
     *
     * @return true if that's the case, false otherwise
     */
    boolean draw();
}
