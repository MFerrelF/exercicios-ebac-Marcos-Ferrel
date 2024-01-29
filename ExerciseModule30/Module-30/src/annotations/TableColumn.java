package annotations;

import java.lang.annotation.*;

/**
 * @author marcos.ferrel
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TableColumn {

    String dbName();

    String setJavaName();

}
