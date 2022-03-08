package com.glowanet.tools.unit.entity.data;

/**
 * Test Object with
 * <ul>
 *     <li>an {@code #equals()}-method, which compares only logically equalness</li>
 *     <li>an implemented {@code #hashCode()}, {@code #toString()}-method.</li>
 * </ul>
 */
public class DataEntityUnitTesterLogicalEquals extends DataEntityUnitTesterGenericEqualsToString {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataEntityUnitTesterGenericEquals that = (DataEntityUnitTesterGenericEquals) o;

        if (getSimInt() != null ? !getSimInt().equals(that.getSimInt()) : that.getSimInt() != null) return false;
        return getSimString() != null ? getSimString().equals(that.getSimString()) : that.getSimString() == null;
    }

    @Override
    public int hashCode() {
        int result = getSimInt() != null ? getSimInt().hashCode() : 0;
        result = 31 * result + (getSimString() != null ? getSimString().hashCode() : 0);
        return result;
    }
}