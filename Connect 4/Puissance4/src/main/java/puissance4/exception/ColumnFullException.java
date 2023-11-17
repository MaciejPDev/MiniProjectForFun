package puissance4.exception;

public class ColumnFullException extends Exception{

    public ColumnFullException (String errorMessage){
        super(errorMessage);
    }
}
