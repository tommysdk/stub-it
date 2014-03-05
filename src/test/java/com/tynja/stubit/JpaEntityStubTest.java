package com.tynja.stubit;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Tommy Tynj&auml;
 */
public class JpaEntityStubTest {

    @Test
    public void shouldProvidedStubValuesForNonNullFields() {
        Person p = new Person();
        assertThat(p.getFirstName(), nullValue());

        JpaEntityStub.withNullableFieldsFilledIn(p);
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
        JpaEntityStub.withNullableFieldsFilledIn(e);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRequireSetterForEachColumnAnnotatedPropertyGetter() {
        JpaEntityWithNonMatchingGetterAndSetter e = new JpaEntityWithNonMatchingGetterAndSetter();
        JpaEntityStub.withNullableFieldsFilledIn(e);
    }
}
