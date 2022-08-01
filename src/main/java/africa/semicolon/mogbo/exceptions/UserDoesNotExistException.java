package africa.semicolon.mogbo.exceptions;

public class UserDoesNotExistException extends EventBriteException{
    public UserDoesNotExistException(String message){
        super(message);
    }
}
