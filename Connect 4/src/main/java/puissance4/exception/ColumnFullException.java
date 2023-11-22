package puissance4.exception;

/**
 * This customised exception will be launched if the player will try to put a coin in a column which is full;
 */
public class ColumnFullException extends Exception {

    public ColumnFullException(String errorMessage) {
        super(errorMessage);
    }
}
