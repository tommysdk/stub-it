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
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static org.hamcrest.CoreMatchers.is;
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

        Stub.withValuesProvidedFor(p, nonNullableColumn());
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

    @Test
    public void shouldProvideStubValuesForCollectionTypeFields() {
        JpaEntityWithCollectionsInterfaces e = new JpaEntityWithCollectionsInterfaces();
        Stub.withValuesProvidedFor(e, nonNullableColumn());
        assertThat(e.getCollection(), notNullValue());
        assertThat(e.getList(), notNullValue());
        assertThat(e.getSet(), notNullValue());
        assertThat(e.getSortedSet(), notNullValue());
        assertThat(e.getBlockingDeque(), notNullValue());
        assertThat(e.getBlockingQueue(), notNullValue());
        assertThat(e.getDeque(), notNullValue());
        assertThat(e.getQueue(), notNullValue());
        assertThat(e.getNavigableSet(), notNullValue());
        assertThat(e.getTransferQueue(), notNullValue());
        assertThat(e.getIterable(), notNullValue());
        assertThat(e.getCollection().isEmpty(), is(true));
        assertThat(e.getList().isEmpty(), is(true));
        assertThat(e.getSet().isEmpty(), is(true));
        assertThat(e.getSortedSet().isEmpty(), is(true));
        assertThat(e.getBlockingDeque().isEmpty(), is(true));
        assertThat(e.getBlockingQueue().isEmpty(), is(true));
        assertThat(e.getDeque().isEmpty(), is(true));
        assertThat(e.getQueue().isEmpty(), is(true));
        assertThat(e.getNavigableSet().isEmpty(), is(true));
        assertThat(e.getTransferQueue().isEmpty(), is(true));
        assertThat(e.getIterable().iterator().hasNext(), is(false));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotBeAbleToStubArbitraryInterfaceReturnType() {
        JpaEntityWithInterfaceTypeProperty e = new JpaEntityWithInterfaceTypeProperty();
        Stub.withValuesProvidedFor(e, nonNullableColumn());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRequireSetterForEachColumnAnnotatedPropertyGetter() {
        JpaEntityWithNonMatchingGetterAndSetter e = new JpaEntityWithNonMatchingGetterAndSetter();
        Stub.withValuesProvidedFor(e, nonNullableColumn());
    }

    @Test
    public void shouldBeAbleToUseUserProvidedValuesForStubbedFields() {
        final String expectedMapValue = "user-specified-value";
        ObjectWithMapProperty subject = new ObjectWithMapProperty();
        Values userDefinedValues = new Values() {
            @Override
            public boolean valueAvailableForConstructorOf(final Class cls) {
                return Map.class.equals(cls);
            }
            @Override
            @SuppressWarnings("unchecked")
            public <T> T valueForClass(final Class<T> cls) {
                if (valueAvailableForConstructorOf(cls)) {
                    Map<String, String> map = new HashMap<>();
                    map.put("user-specified-key", expectedMapValue);
                    return (T) map;
                } else {
                    return null;
                }
            }
        };
        Stub.with(userDefinedValues).providedFor(subject, field -> field.getType().equals(Map.class));
        assertThat(subject, notNullValue());
        assertThat(userDefinedValues.valueForClass(Map.class), notNullValue());
        assertThat(subject.getMapping(), notNullValue());
        assertThat(subject.getMapping().isEmpty(), is(false));
        assertThat(subject.getMapping().size(), is(1));
        assertThat(subject.getMapping().values().iterator().next(), is(expectedMapValue));
    }

    @Test
    public void shouldBeAbleToStubPrimitiveTypedFields() {
        JpaEntityWithPrimitiveProperties e = new JpaEntityWithPrimitiveProperties();
        Stub.withValuesProvidedFor(e, field -> true);
    }

    private Function<Field, Boolean> nonNullableColumn() {
        return (field -> field.getAnnotation(Column.class) != null && !field.getAnnotation(Column.class).nullable());
    }
}
