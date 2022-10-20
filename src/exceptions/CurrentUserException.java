package exceptions;

/**
 * A class which defines a custom exception to prevent the ListItSystem from running certain
 * features without having a current user, which represents a logged-in user.
 */
public class CurrentUserException extends Exception{

    /**
     * A method which displays an error message to the console when the exception is thrown.
     * @param message A String which represents an error message for the exception.
     */
    public CurrentUserException(String message) {
        super(message);
    }
}