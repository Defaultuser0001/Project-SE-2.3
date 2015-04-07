package exceptions;

/**
 * Created by Gerard on 4/7/2015.
 */
public class UsernameAlreadyExistsException extends Exception{

    public UsernameAlreadyExistsException(String msg){
        super(msg);
    }
}
