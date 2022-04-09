package com.glowanet.tools.unit;

import com.glowanet.tools.unit.data.DataUnitTester;

import java.util.List;

/**
 * Concrete Implementation of {@code  com.glowanet.tools.unit.AbstractEnumObjectUnitTester} for the unit tests.
 *
 * @see com.glowanet.tools.unit.AbstractEnumObjectUnitTester
 * @deprecated Will be removed with {@code com.glowanet.tools.unit.AbstractEnumObjectUnitTester}
 */
@Deprecated(forRemoval = true, since = "1.0")
public class ConcreteAbstractEnumObjectUnitTester extends AbstractEnumObjectUnitTester<DataUnitTester> {

    /* constructors */
    public ConcreteAbstractEnumObjectUnitTester() {
        super(DataUnitTester.class);
    }
    /* end - constructors */

    /* methods */
    @Override
    protected DataUnitTester createObject2Test() {
        return new DataUnitTester();
    }

    @Override
    protected List<String> enumObjectsToIgnoreForCode() {
        return List.of();
    }
    /* end - methods */

}
