package com.glowanet.tools.unit.enumobj.data;

public enum DataEnumObjectUnitTesterCorrect {
    A0(0), B1(1), C22(22);

    private final Number code;

    DataEnumObjectUnitTesterCorrect(Number code) {
        this.code = code;
    }

    public Number getCode() {
        return code;
    }
}
