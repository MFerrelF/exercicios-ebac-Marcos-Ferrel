package marcos.ferrel;

import java.lang.annotation.*;

/**
 * @author marcos.ferrel
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Table {
    String value();

}
