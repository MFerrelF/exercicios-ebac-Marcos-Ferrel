package marcos.ferrel;

import java.lang.annotation.Annotation;

/**
 * @author marcos.ferrel
 */

public class AnnotationReading {

    public static void main(String[] args) {

        executeAnnotationReading();

    }

    private static void executeAnnotationReading() {
        UsingAnnotation using = new UsingAnnotation();
        Annotation[] annotations = using.getClass().getAnnotations();
        for (Annotation an : annotations) {
            System.out.println("Annotation full name: " + an.toString());
        }

        if (using.getClass().isAnnotationPresent(Table.class)) {
            Table an = using.getClass().getAnnotation(Table.class);
            System.out.println("Annotation value: " + an.value());
        }
    }
}
