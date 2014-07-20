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

/**
 * @author Tommy Tynj&auml;
 */
public class JpaEntityWithPrimitiveProperties {

    @Column(nullable = false)
    private int intValue;

    @Column(nullable = false)
    private long longValue;

    @Column(nullable = false)
    private double doubleValue;

    @Column(nullable = false)
    private float floatValue;

    @Column(nullable = false)
    private boolean booleanValue;

    @Column(nullable = false)
    private short shortValue;

    @Column(nullable = false)
    private byte byteValue;

    @Column(nullable = false)
    private char charValue;

    @Column(nullable = false)
    private Integer integerObjectValue;

    @Column(nullable = false)
    private Long longObjectValue;

    @Column(nullable = false)
    private Double doubleObjectValue;

    @Column(nullable = false)
    private Float floatObjectValue;

    @Column(nullable = false)
    private Boolean booleanObjectValue;

    @Column(nullable = false)
    private Short shortObjectValue;

    @Column(nullable = false)
    private Byte byteObjectValue;

    @Column(nullable = false)
    private Character characterValue;

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(final int intValue) {
        this.intValue = intValue;
    }

    public long getLongValue() {
        return longValue;
    }

    public void setLongValue(final long longValue) {
        this.longValue = longValue;
    }

    public double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(final double doubleValue) {
        this.doubleValue = doubleValue;
    }

    public float getFloatValue() {
        return floatValue;
    }

    public void setFloatValue(final float floatValue) {
        this.floatValue = floatValue;
    }

    public boolean isBooleanValue() {
        return booleanValue;
    }

    public void setBooleanValue(final boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    public short getShortValue() {
        return shortValue;
    }

    public void setShortValue(final short shortValue) {
        this.shortValue = shortValue;
    }

    public byte getByteValue() {
        return byteValue;
    }

    public void setByteValue(final byte byteValue) {
        this.byteValue = byteValue;
    }

    public char getCharValue() {
        return charValue;
    }

    public void setCharValue(final char charValue) {
        this.charValue = charValue;
    }

    public Integer getIntegerObjectValue() {
        return integerObjectValue;
    }

    public void setIntegerObjectValue(final Integer integerObjectValue) {
        this.integerObjectValue = integerObjectValue;
    }

    public Long getLongObjectValue() {
        return longObjectValue;
    }

    public void setLongObjectValue(final Long longObjectValue) {
        this.longObjectValue = longObjectValue;
    }

    public Double getDoubleObjectValue() {
        return doubleObjectValue;
    }

    public void setDoubleObjectValue(final Double doubleObjectValue) {
        this.doubleObjectValue = doubleObjectValue;
    }

    public Float getFloatObjectValue() {
        return floatObjectValue;
    }

    public void setFloatObjectValue(final Float floatObjectValue) {
        this.floatObjectValue = floatObjectValue;
    }

    public Boolean getBooleanObjectValue() {
        return booleanObjectValue;
    }

    public void setBooleanObjectValue(final Boolean booleanObjectValue) {
        this.booleanObjectValue = booleanObjectValue;
    }

    public Short getShortObjectValue() {
        return shortObjectValue;
    }

    public void setShortObjectValue(final Short shortObjectValue) {
        this.shortObjectValue = shortObjectValue;
    }

    public Byte getByteObjectValue() {
        return byteObjectValue;
    }

    public void setByteObjectValue(final Byte byteObjectValue) {
        this.byteObjectValue = byteObjectValue;
    }

    public Character getCharacterValue() {
        return characterValue;
    }

    public void setCharacterValue(final Character characterValue) {
        this.characterValue = characterValue;
    }
}
