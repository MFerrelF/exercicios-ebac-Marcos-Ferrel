package marcos.ferrel.dao.exception;

/**
 * @author marcos.ferrel
 */
public class KeyNotFoundException extends Exception {

    public KeyNotFoundException(String msg) {
        this(msg, null);
    }

    public KeyNotFoundException(String msg, Throwable e) {
        super(msg, e);
    }
}
