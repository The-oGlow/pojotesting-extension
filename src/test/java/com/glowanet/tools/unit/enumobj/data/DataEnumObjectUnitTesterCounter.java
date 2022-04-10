package com.glowanet.tools.unit.enumobj.data;

public enum DataEnumObjectUnitTesterCounter {
    // normal
    A0(0), B1(1), C12(12), //
    // with separator
    D_2(2), E_21(21), //
    // leading zero
    F03(3), G04(04), //
    // no number in text
    XX(null);

    private final Number counter;

    DataEnumObjectUnitTesterCounter(Number counter) {
        this.counter = counter;
    }

    public Number getCounter() {
        return counter;
    }
}
