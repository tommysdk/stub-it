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
