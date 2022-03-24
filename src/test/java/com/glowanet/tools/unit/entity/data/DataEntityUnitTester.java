package com.glowanet.tools.unit.entity.data;

/**
 * Test Object with
 * <ul>
 *     <li>a default {@code #equals()}, {@code #hashCode()}, {@code #toString()}-method.</li>
 * </ul>
 */
public class DataEntityUnitTester {
    private Integer simInt;
    private String  simString;

    public DataEntityUnitTester() {
    }

    public Integer getSimInt() {
        return simInt;
    }

    public String getSimString() {
        return simString;
    }

    public void setSimInt(Integer simInt) {
        this.simInt = simInt;
    }

    public void setSimString(String simString) {
        this.simString = simString;
    }
}
