package marcos.ferrel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @author marcos.ferrel
 */

@Target(ElementType.TYPE)
public @interface Table {
    String value();

}
