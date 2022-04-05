package com.glowanet.tools.unit;

/**
 * @param <T> the type of the class to test
 *
 * @deprecated Use @{@code com.glowanet.tools.unit.entity.AbstractEnumObjectUnitTester}
 */
@Deprecated(forRemoval = true, since = "1.0")
@SuppressWarnings({"java:S2176", "java:S1133"})
public abstract class AbstractEnumObjectUnitTester<T> extends com.glowanet.tools.unit.enumobj.AbstractEnumObjectUnitTester<T> {

    /* constructors */
    protected AbstractEnumObjectUnitTester(Class<T> typeOfo2T) {
        super(typeOfo2T);
    }
}
