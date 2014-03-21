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
