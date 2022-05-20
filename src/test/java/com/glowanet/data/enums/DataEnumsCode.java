package com.glowanet.data.enums;

import java.util.List;

public enum DataEnumsCode implements IDataEnums {
    // normal
    A0(0), B1(1), C12(12),
    // with separator
    D_2(2), E_21(21),
    // leading zero
    F03(3), G04(4),
    // no number in text
    XX(null);

    // static fields
    public static final String        ITEMNAME_WITH_NUMBER    = "A0";
    public static final String        ITEMNAME_WITHOUT_NUMBER = "XX";
    public static final String        ITEMNAME_EXISTS         = "B1";
    public static final String        ITEMNAME_NOT_EXISTS     = "NOT_EXISTS";
    public static final DataEnumsCode ITEM_WITH_NUMBER        = DataEnumsCode.A0;
    public static final DataEnumsCode ITEM_WITHOUT_NUMBER     = DataEnumsCode.XX;
    public static final String        ITEMNAME_IGNORED        = "XX";
    public static final List<String>  FIELDS_TO_IGNORE        = List.of(
            "ITEMNAME_WITH_NUMBER", "ITEMNAME_WITHOUT_NUMBER",
            "ITEM_WITH_NUMBER", "ITEM_WITHOUT_NUMBER",
            "ITEMNAME_EXISTS", "ITEMNAME_NOT_EXISTS",
            "COUNT_ITEM_WO_NO", "COUNT_ITEM_W_NO", "COUNT_PSF", "EXCEPTION_SUM",
            "ITEMNAME_IGNORED", "FIELDS_TO_IGNORE"
    );
    public static final int           COUNT_ITEM_WO_NO        = 1;
    public static final int           COUNT_ITEM_W_NO         = DataEnumsCode.values().length - COUNT_ITEM_WO_NO;
    public static final int           COUNT_PSF               = FIELDS_TO_IGNORE.size();
    public static final int           EXCEPTION_SUM           = (COUNT_ITEM_W_NO) * 2 + (COUNT_ITEM_WO_NO) * 3;
    // end - static fields

    private final Number code;

    DataEnumsCode(Number code) {
        this.code = code;
    }

    public Number getCode() {
        return code;
    }
}
