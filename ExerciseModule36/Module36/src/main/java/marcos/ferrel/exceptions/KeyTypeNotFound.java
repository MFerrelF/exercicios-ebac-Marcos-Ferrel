package marcos.ferrel.exceptions;

/**
 * @author marcos.ferrel
 */
public class KeyTypeNotFound extends Exception {

    private static final long serialVersionUID = -1389494676398525746L;

    public KeyTypeNotFound(String msg) {
        this(msg, null);
    }

    public KeyTypeNotFound(String msg, Throwable e) {
        super(msg, e);
    }

}