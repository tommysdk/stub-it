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

        Stub.withProvidedValuesFor(p, nullableColumn());
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
        Stub.withProvidedValuesFor(e, nullableColumn());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRequireSetterForEachColumnAnnotatedPropertyGetter() {
        JpaEntityWithNonMatchingGetterAndSetter e = new JpaEntityWithNonMatchingGetterAndSetter();
        Stub.withProvidedValuesFor(e, nullableColumn());
    }

    private Function<Field, Boolean> nullableColumn() {
        return (field -> field.getAnnotation(Column.class) != null && !field.getAnnotation(Column.class).nullable());
    }
}
