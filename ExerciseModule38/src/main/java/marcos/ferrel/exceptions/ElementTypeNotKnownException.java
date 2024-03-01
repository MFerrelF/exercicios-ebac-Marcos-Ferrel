/**
 * 
 */
package marcos.ferrel.exceptions;

/**
 * @author marcos.ferrel
 * 
 */
public class ElementTypeNotKnownException extends Exception {
	
	private static final long serialVersionUID = -2268140970978666251L;

    public ElementTypeNotKnownException(String msg) {
        this(msg, null);
    }

    public ElementTypeNotKnownException(String msg, Throwable e) {
        super(msg, e);
    }

}
