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

/**
 * Defines whether values can be provided for a field of the specified
 * type (class) and if so, provides a value for that field during a
 * given ongoing stubbing.
 *
 * @author Tommy Tynj&auml;
 */
public interface Values {

    /**
     * Returns whether this Values implementation will be able to provide
     * a value suitable for stubbing for a field of the specified type (class).
     * If this method returns <tt>true</tt>, the <tt>valueForClass</tt> method
     * is expected to yield a non-null result.
     *
     * @param cls the field type which is subject for stubbing.
     * @return true if this implementation can provide a value for the specified
     *         class.
     */
    public boolean valueAvailableForConstructorOf(final Class cls);

    /**
     * Provides a value suitable for stubbing for the specified class. An
     * implementation is expected to return a non-null result for classes
     * returning <tt>true</tt> from the <tt>valueAvailableForConstructorOf</tt>,
     * otherwise <tt>null</tt>.
     *
     * @param cls the field type which is subject for stubbing.
     * @param <T> the field instance type.
     * @return the value to use for stubbing fields of this type.
     */
    public <T> T valueForClass(final Class<T> cls);

}
