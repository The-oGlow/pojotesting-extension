package com.glowanet.data.entity;

/**
 * The base clazz for all implementation of an entity to use in tests.
 */
public abstract class BaseDataEntity {

    private Integer simInt;
    private String  simString;

    public BaseDataEntity() {
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

    protected boolean _equalsLogical(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseDataEntity that = (BaseDataEntity) o;

        if (getSimInt() != null ? !getSimInt().equals(that.getSimInt()) : that.getSimInt() != null) return false;
        return getSimString() != null ? getSimString().equals(that.getSimString()) : that.getSimString() == null;
    }

    protected int _hashCode() {
        int result = getSimInt() != null ? getSimInt().hashCode() : 0;
        result = 31 * result + (getSimString() != null ? getSimString().hashCode() : 0);
        return result;
    }

    protected String _toString() {
        return this.getClass().getSimpleName() + "{" +
                "simInt=" + simInt +
                ", simString='" + simString + '\'' +
                '}';
    }
}
