/*
* Copyright 2014 Tommy Tynjä
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.tynja.stubit;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * Provides default values for a certain set of predefined classes,
 * e.g. primitive types, as these lacks a no-arg constructor.
 *
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

    /**
     * Checks whether there is a provided default value for the specified class.
     *
     * @param cls the class to compare against available default values.
     * @return true if a default value can be provided for the specified class.
     */
    public static boolean valueAvailableForConstructorOf(final Class cls) {
        return MAPPINGS.containsKey(cls);
    }

    /**
     * Get a default value for the specified class, if such is provided by this implementation.
     * This method will only provide a value in return if the <tt>valueAvailableForConstructorOf</tt>
     * method returns true.
     *
     * @param cls the class to get a default value for.
     * @param <T> the class type.
     * @return a default value for the specified class, if such can be provided.
     */
    @SuppressWarnings("unchecked")
    public static <T> T valueForClass(final Class<T> cls) {
        return (T) MAPPINGS.get(cls);
    }
}
