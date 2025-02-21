package cz.cvut.fel.nss.SaunaStudio.exception;

/**
 * Base for all application-specific exceptions.
 */
public class MyException extends RuntimeException {

    public MyException() {
    }

    public MyException(String message) {
        super(message);
    }

    public MyException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyException(Throwable cause) {
        super(cause);
    }
}
