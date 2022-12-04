package com.glowanet.tools.unit;

import java.io.Serializable;

public class ClazzAdapter implements Serializable, Comparable<Class<?>> {

    private static final long serialVersionUID = -3435518656258112660L;

    private final Class<?> clazz;

    private ClazzAdapter(Class<?> clazz) {
        this.clazz = clazz;
    }

    public static ClazzAdapter newI(Class<?> clazz) {
        return new ClazzAdapter(clazz);
    }

    @Override
    public int compareTo(Class<?> o) {
        if (o == null) {
            return 1;
        } else {
            if (this.clazz.equals(o)) {
                return 0;
            } else {
                return Integer.compare(this.clazz.hashCode(), o.hashCode());
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ClazzAdapter that = (ClazzAdapter) o;
        return clazz.equals(that.clazz);
    }

    @Override
    public int hashCode() {
        return getClazz() != null ? getClazz().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ClazzAdapter{" + "clazz=" + clazz + '}';
    }

    public Class<?> getClazz() {
        return clazz;
    }
}
