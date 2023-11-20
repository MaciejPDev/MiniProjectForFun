package puissance4.model;


import puissance4.exception.ColumnFullException;
import puissance4.exception.NonExistentPositionException;

/**
 * @TODO JAVADOC
 */
public interface Model {

    void start();

    Container getContainer();

    Player getCurrentPlayer();

    void placeACoin(int column) throws ColumnFullException, NonExistentPositionException;

    boolean play();

    boolean win();

    boolean draw();
}
