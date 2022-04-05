package com.glowanet.tools.unit.entity.data;

/**
 * Test Object with
 * <ul>
 *     <li>a default {@code #equals()}, {@code #hashCode()}-method.</li>
 *     <li>an implemented {@code #toString()}-method.</li>
 * </ul>
 */
public class DataEntityUnitTesterToString extends DataEntityUnitTester {
    /* methods */
    @Override
    public String toString() {
        return "DataEntityUnitTesterGenericEqualsToString{" +
                "simInt=" + getSimInt() +
                ", simString='" + getSimString() + '\'' +
                '}';
    }
}
