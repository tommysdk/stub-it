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

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.function.Function;

/**
 * The main class that allows stubbing of Java object fields through reflection.
 * This class is not supposed to be instantiated. Calls should be made directly to the exposed static methods.
 *
 * @author Tommy Tynj&auml;
 * @see DefaultValues
 */
public final class Stub {

    private Stub() {
    }

    /**
     * Will create a stub definition, where the specified <tt>Values</tt> implementation will be used to
     * provide values for the stubbed fields. The actual stubbing is performed when the <tt>providedFor</tt>
     * method is called on the returned stub definition.
     *
     * @param values the values to use for this stubbing.
     * @param <T> the object type.
     * @return a stub definition
     */
    public static <T> Definition<T> with(final Values values) {
        return new Definition<>(values);
    }

    /**
     * Will attempt to stub the fields on the specified object, that matches the given predicate function.
     * Only fields that satisfies the following criterion's can be stubbed:
     * <ul>
     *     <li>The field type is not an interface, unless a java.util.Collection or any known sub-interface
     *         according to the official JDK documentation.</li>
     *     <li>The field type provides a no-arg constructor, unless existent in the <tt>DefaultValues</tt>
     *         mapping.</li>
     *     <li>The field has matching getter- and setter methods.</li>
     * </ul>
     * If any exception occurs during stubbing, an <tt>IllegalArgumentException</tt> will be thrown.
     *
     * @param o the object to stub.
     * @param predicate the function that dictates whether a field on the specified object is subject for
     *                  stubbing. The function have to take a field as a parameter and return true for each
     *                  field that should be stubbed.
     * @param <T> the object type.
     * @return the specified object itself, with default values provided for each field that matches the
     *                  specified predicate function.
     */
    public static <T> T withValuesProvidedFor(final T o, final Function<Field, Boolean> predicate) {
        return withValuesProvidedFor(o, predicate, DefaultValues.instance);
    }

    private static <T> T withValuesProvidedFor(final T o, final Function<Field, Boolean> predicate, final Values values) {
        try {
            for (Method m : o.getClass().getMethods()) {
                if (m.getName().startsWith("get") && m.getParameterTypes().length == 0) {
                    String qualifier = m.getName().replaceFirst("get", "");
                    try {
                        Method setter = o.getClass().getMethod("set" + qualifier, m.getReturnType());
                        setter.invoke(o, provideValueForField(o, fieldCased(qualifier), m.getReturnType(), predicate, values));
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

    private static <R> R provideValueForField(final Object subject, final String name, final Class<R> returnType, final Function<Field, Boolean> predicate, final Values defaults) {
        if (predicate.apply(field(subject, name))) {
            if (defaults.valueAvailableForConstructorOf(returnType)) {
                return defaults.valueForClass(returnType);
            }
            else return newInstanceOf(returnType);
        } else return null;
    }

    private static <R> R newInstanceOf(final Class<R> returnType) {
        try {
            return returnType.newInstance();
        } catch (Throwable e) {
            throw new IllegalArgumentException("Could not create new instance of return type " + returnType, e);
        }
    }

    private static Field field(final Object subject, final String name) {
        try {
            return subject.getClass().getDeclaredField(name);
        } catch (NoSuchFieldException e) {
            throw new IllegalArgumentException("Could not resolve field '" + name + "' for " + subject, e);
        }
    }

    /**
     * A stub definition, which allows a user to stub certain fields on a certain object using a custom
     * <tt>Values</tt> implementation which will provide values for the fields subject to stubbing.
     *
     * @param <T> the object type.
     */
    public static class Definition<T> {
        public final Values values;

        private Definition(final Values values) {
            if (values == null) {
                throw new IllegalArgumentException("Expected values to use for stubbing but received null. Use the Stub.withValuesProvidedFor method to let stub-it provide default values for you.");
            }
            this.values = values;
        }

        /**
         * Will attempt to stub the fields on the specified object, that matches the given predicate function.
         * Will only be able to stub fields that are supported by the previously defined <tt>Values</tt>
         * implementation for this stub definition.
         *
         * Every field subject for stubbing is required to have a matching getter- and setter method.
         *
         * If any exception occurs during stubbing, an <tt>IllegalArgumentException</tt> will be thrown.
         *
         * @param subject the object to stub.
         * @param predicate the function that dictates whether a field on the specified object is subject for
         *                  stubbing. The function have to take a field as a parameter and return true for each
         *                  field that should be stubbed.
         * @return the specified object itself, with the previously defined values provided for each field that
         *                  matches the specified predicate function.
         */
        public T providedFor(final T subject, final Function<Field, Boolean> predicate) {
            return Stub.withValuesProvidedFor(subject, predicate, values);
        }
    }
}
