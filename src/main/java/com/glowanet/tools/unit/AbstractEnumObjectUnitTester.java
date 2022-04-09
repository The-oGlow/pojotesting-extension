package com.glowanet.tools.unit;

import com.glowanet.tools.unit.enumobj.EnumObjectUnitTester;

/**
 * @param <T> the type of the entity which will be tested
 *
 * @deprecated Use @{@code com.glowanet.tools.unit.entity.AbstractEnumObjectUnitTester}
 */
@Deprecated(forRemoval = true, since = "1.0")
@SuppressWarnings("java:S2176")
public abstract class AbstractEnumObjectUnitTester<T> extends EnumObjectUnitTester<T> {

    // constructors
    protected AbstractEnumObjectUnitTester(Class<T> typeOfo2T) {
        super(typeOfo2T);
    }
    // end - constructors
}
