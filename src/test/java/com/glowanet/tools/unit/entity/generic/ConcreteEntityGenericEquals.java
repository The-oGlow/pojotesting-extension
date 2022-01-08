package com.glowanet.tools.unit.entity.generic;

/**
 * Test Object with
 * <ul>
 *     <li>a default {@code #equals()}, {@code #hashCode()}, {@code #testToString()}-method.</li>
 * </ul>
 */
public class ConcreteEntityGenericEquals {
    private Integer simInt;
    private String  simString;

    public ConcreteEntityGenericEquals() {
    }

    public Integer getSimInt() {
        return simInt;
    }

    public String getSimString() {
        return simString;
    }
}
