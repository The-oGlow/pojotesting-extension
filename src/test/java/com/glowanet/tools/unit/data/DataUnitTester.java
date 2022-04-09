package com.glowanet.tools.unit.data;

import java.io.Serializable;

public class DataUnitTester implements Serializable {

    // static fields
    public static final int CONST_COUNT  = 11;
    public static final int SETTER_COUNT = 3;
    public static final int GETTER_COUNT = 1 + SETTER_COUNT;

    public static final String FIELD_EXISTS     = "valI";
    public static final String FIELD_NOT_EXISTS = "NOTvalI";

    public static final String     METH_NAME  = "calcVal";
    public static final Class<?>[] METH_PARAM = {String.class, float.class};

    public static final int    VAL_PREV        = 100;
    public static final int    VAL_NEW         = 999;
    public static final String PRIV_CONST_NAME = "PRIV_CONST";
    public static final int    PRIV_CONST      = VAL_PREV;
// end - static fields

    // fields
    private Integer valI;
    private String  valS;
    private float   primJ;
// end - fields

    // methods
    public String calcVal(String sep, float comp) {
        return valS + sep + comp;
    }

    public float getPrimJ() {
        return primJ;
    }

    public Integer getValI() {
        return valI;
    }

    public String getValS() {
        return valS;
    }

    public void setPrimJ(float primJ) {
        this.primJ = primJ;
    }

    public void setValI(Integer valI) {
        this.valI = valI;
    }

    public void setValS(String valS) {
        this.valS = valS;
    }
// end - methods
}
