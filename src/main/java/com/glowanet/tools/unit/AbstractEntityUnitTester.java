package com.glowanet.tools.unit;

/**
 * @param <T> the type of the entity which will be tested
 *
 * @deprecated Use @{@code com.glowanet.tools.unit.entity.EntityUnitTester}
 */
@Deprecated(forRemoval = true, since = "0.1.0")
public abstract class AbstractEntityUnitTester<T> extends com.glowanet.tools.unit.entity.EntityUnitTester<T> {

    // constructors
    protected AbstractEntityUnitTester(Class<T> typeOfo2T) {
        super(typeOfo2T);
    }
    // end - constructors
}
