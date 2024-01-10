package marcos.ferrel.dao.annotation;

import java.lang.annotation.*;

/**
 * @author marcos.ferrel
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface KeyType {

    String value();
}
