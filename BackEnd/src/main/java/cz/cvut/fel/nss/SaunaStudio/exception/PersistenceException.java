package cz.cvut.fel.nss.SaunaStudio.exception;

public class PersistenceException extends MyException {

    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersistenceException(Throwable cause) {
        super(cause);
    }
}
