package com.glowanet.tools.unit.enumobj.data;

public enum DataEnumObjectUnitTesterCode {
    // normal
    A0(0), B1(1), C12(12), //
    // with separator
    D_2(2), E_21(21), //
    // leading zero
    F03(3), G04(04), //
    // no number in text
    XX(null);

    private final Number code;

    DataEnumObjectUnitTesterCode(Number code) {
        this.code = code;
    }

    public Number getCode() {
        return code;
    }
}
