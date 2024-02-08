package annotations;

import java.lang.annotation.*;

/**
 * @author marcos.ferrel
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {

    String value();

}
