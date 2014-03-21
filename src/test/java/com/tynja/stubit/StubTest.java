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

import org.junit.Test;

import javax.persistence.Column;

import java.lang.reflect.Field;
import java.util.function.Function;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Tommy Tynj&auml;
 */
public class StubTest {

    @Test
    public void shouldProvidedStubValuesForNonNullFields() {
        Person p = new Person();
        assertThat(p.getFirstName(), nullValue());

        Stub.withProvidedValuesFor(p, nonNullableColumn());
        assertThat(p.getFirstName(), notNullValue());
        assertThat(p.getSurname(), notNullValue());
        assertThat(p.getCity(), notNullValue());
        assertThat(p.getState(), notNullValue());
        assertThat(p.getCountry(), notNullValue());
        assertThat(p.getBornYear(), notNullValue());

        assertThat(p.getId(), nullValue());
        assertThat(p.getMiddleName(), nullValue());
        assertThat(p.getStreet(), nullValue());
        assertThat(p.getZipCode(), nullValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotBeAbleToStubInterfaceReturnType() {
        JpaEntityWithInterfaceTypeProperty e = new JpaEntityWithInterfaceTypeProperty();
        Stub.withProvidedValuesFor(e, nonNullableColumn());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRequireSetterForEachColumnAnnotatedPropertyGetter() {
        JpaEntityWithNonMatchingGetterAndSetter e = new JpaEntityWithNonMatchingGetterAndSetter();
        Stub.withProvidedValuesFor(e, nonNullableColumn());
    }

    private Function<Field, Boolean> nonNullableColumn() {
        return (field -> field.getAnnotation(Column.class) != null && !field.getAnnotation(Column.class).nullable());
    }
}
