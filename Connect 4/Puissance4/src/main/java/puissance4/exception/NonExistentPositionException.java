package puissance4.exception;

public class NonExistentPositionException extends Exception{

    public NonExistentPositionException (String errorMessage) {
        super(errorMessage);
    }
}
