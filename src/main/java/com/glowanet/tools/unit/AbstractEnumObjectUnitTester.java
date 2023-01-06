package com.glowanet.tools.unit;

import com.glowanet.tools.unit.entity.enums.EnumUnitTester;

/**
 * @param <T> the type of the entity which will be tested
 *
 * @deprecated Use @{@code com.glowanet.tools.unit.entity.AbstractEnumObjectUnitTester}
 */
@Deprecated(forRemoval = true, since = "0.1.0")
public abstract class AbstractEnumObjectUnitTester<T> extends EnumUnitTester<T> {

    protected AbstractEnumObjectUnitTester(Class<T> typeOfo2T) {
        super(typeOfo2T);
    }
}
