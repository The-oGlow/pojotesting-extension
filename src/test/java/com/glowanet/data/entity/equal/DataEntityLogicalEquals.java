package com.glowanet.data.entity.equal;

/**
 * Test Object with
 * <ul>
 *     <li>an {@code #equals()}-method, which compares only logically equality</li>
 *     <li>an implemented {@code #hashCode()}-method.</li>
 *     <li>an implemented {@code #toString()}-method.</li>
 * </ul>
 */
public class DataEntityLogicalEquals extends DataEntityGenericEquals {

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    @Override
    public boolean equals(Object obj) {
        return _equalsLogical(obj);
    }

    @Override
    public int hashCode() {
        return _hashCode();
    }
}
