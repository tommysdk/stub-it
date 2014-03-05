package com.tynja.stubit;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Tommy Tynj&auml;
 */
public class Default<T> {

    private static final Map<Class, Object> MAPPINGS;
    static {
        MAPPINGS = new HashMap<>();
        MAPPINGS.put(Integer.class, 0);
        MAPPINGS.put(Long.class, 0L);
        MAPPINGS.put(Double.class, 0.0);
        MAPPINGS.put(Float.class, 0.0F);
        MAPPINGS.put(BigInteger.class, new BigInteger("0"));
        MAPPINGS.put(BigDecimal.class, new BigDecimal(0));
    }

    public static boolean valueAvailableForConstructorOf(final Class cls) {
        return MAPPINGS.containsKey(cls);
    }

    @SuppressWarnings("unchecked")
    public static <T> T valueForClass(final Class<T> cls) {
        return (T) MAPPINGS.get(cls);
    }
}
