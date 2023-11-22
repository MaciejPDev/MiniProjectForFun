package puissance4.exception;

/**
 * This customised exception will be launched at the moment when a given position will be not situated on the board
 */
public class NonExistentPositionException extends Exception {

    public NonExistentPositionException(String errorMessage) {
        super(errorMessage);
    }
}
