package annotation.validator;

import java.lang.reflect.Field;

public class Validator {

    public static void validate(Object obj) throws IllegalAccessException {
        Field[] declaredFields = obj.getClass().getDeclaredFields();

        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);

            if (declaredField.isAnnotationPresent(NotEmpty.class)) {
                String value = (String) declaredField.get(obj);
                NotEmpty annotation = declaredField.getAnnotation(NotEmpty.class);
                if (value == null || value.isEmpty()) {
                    throw new RuntimeException(annotation.message());
                }
            }

            if (declaredField.isAnnotationPresent(Range.class)) {
                long value =   declaredField.getLong(obj);
                Range annotation = declaredField.getAnnotation(Range.class);
                if (value < annotation.min() || value > annotation.max()) {
                    throw new RuntimeException(annotation.message());
                }
            }
        }

    }
}
