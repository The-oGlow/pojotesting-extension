package com.glowanet.tools.unit;

/**
 * @param <T> the type of the class to test
 *
 * @deprecated Use @{@code com.glowanet.tools.unit.entity.AbstractEntityUnitTester}
 */
@Deprecated(forRemoval = true, since = "1.0")
public abstract class AbstractEntityUnitTester<T> extends com.glowanet.tools.unit.entity.AbstractEntityUnitTester<T> {

    protected AbstractEntityUnitTester(Class<T> typeOfT) {
        super(typeOfT);
    }
}
