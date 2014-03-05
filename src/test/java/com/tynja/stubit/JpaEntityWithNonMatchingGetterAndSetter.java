package com.tynja.stubit;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Tommy Tynj&auml;
 */
public class JpaEntityWithNonMatchingGetterAndSetter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Integer value1;

    @Column
    private Integer value2;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Integer getMandatoryValue() {
        return value1;
    }

    public void setMandatoryValue(final Integer mandatoryValue) {
        this.value1 = mandatoryValue;
    }

    public Integer getOptionalValue() {
        return value2;
    }

    public void setOptionalValue(final Integer optionalValue) {
        this.value2 = optionalValue;
    }
}
