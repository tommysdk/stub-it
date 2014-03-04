package com.tynja.stubit;

import javax.persistence.Column;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author Tommy Tynj&auml;
 */
public class JpaEntityStub {

    public static Object withNullableFieldsFilledIn(final Object o) {
        try {
            for (Method m : o.getClass().getMethods()) {
                if (m.getName().startsWith("get") && m.getParameterTypes().length == 0) {
                    String qualifier = m.getName().replaceFirst("get", "");
                    try {
                        Method setter = o.getClass().getMethod("set" + qualifier, m.getReturnType());
                        setter.invoke(o, provideValueForField(o, fieldCased(qualifier), m.getReturnType()));
                    } catch (NoSuchMethodException nsme) {
                        // getter without corresponding setter, e.g. getClass()
                    }
                }
            }
            return o;
        } catch (Throwable e) {
            throw new IllegalArgumentException("Failed to generate value for stub", e);
        }
    }

    private static String fieldCased(final String name) {
        if (name == null) return null;
        else return name.substring(0, 1).toLowerCase() + name.substring(1);
    }


    private static boolean nullable(final Field field) {
        if (field == null) return true;
        Column column = field.getAnnotation(Column.class);
        return column == null || column.nullable();
    }

    private static <R> R provideValueForField(final Object subject, final String name, final Class<R> returnType) {
        if (!nullable(field(subject, name))) {
            return newInstanceOf(returnType);
        } else return null;
    }

    private static <R> R newInstanceOf(final Class<R> returnType) {
        try {
            return returnType.newInstance();
        } catch (Throwable e) {
            throw new IllegalArgumentException("Could not create new instance of return type " + returnType.getClass(), e);
        }
    }

    private static Field field(final Object subject, final String name) {
        try {
            return subject.getClass().getDeclaredField(name);
        } catch (NoSuchFieldException e) {
            throw new IllegalArgumentException("Could not resolve field '" + name + "' for " + subject, e);
        }
    }
}
