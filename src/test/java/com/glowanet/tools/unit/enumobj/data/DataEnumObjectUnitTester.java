package com.glowanet.tools.unit.enumobj.data;

import java.util.List;

public enum DataEnumObjectUnitTester {
    // normal
    A0, B1, C12, //
    // with separator
    D_2, E_21,  //
    // leading zero
    F03, G04, H05,
    // no number in text
    XX;

    // static fields
    public static final String                   ITEMNAME_WITH_NUMBER    = "A0";
    public static final String                   ITEMNAME_WITHOUT_NUMBER = "XX";
    public static final DataEnumObjectUnitTester ITEM_WITH_NUMBER        = DataEnumObjectUnitTester.A0;
    public static final DataEnumObjectUnitTester ITEM_WITHOUT_NUMBER     = DataEnumObjectUnitTester.XX;
    public static final List<String>             FIELDS_TO_IGNORE        = List.of(
            "ITEMNAME_WITH_NUMBER", "ITEMNAME_WITHOUT_NUMBER", "ITEM_WITH_NUMBER", "ITEM_WITHOUT_NUMBER",
            "COUNT_ITEM_WO_NO", "COUNT_ITEM_W_NO", "COUNT_PSF", "EXCEPTION_SUM", "FIELDS_TO_IGNORE"
    );
    public static final int                      COUNT_ITEM_WO_NO        = 1;
    public static final int                      COUNT_ITEM_W_NO         = DataEnumObjectUnitTester.values().length - COUNT_ITEM_WO_NO;
    public static final int                      COUNT_PSF               = FIELDS_TO_IGNORE.size();
    public static final int                      EXCEPTION_SUM           = (COUNT_ITEM_W_NO) * 2 + (COUNT_ITEM_WO_NO) * 3;
    // end - static fields
}
