package com.glowanet.tools.unit;

import com.glowanet.tools.unit.enumobj.data.DataEnumObjectUnitTester;

import java.util.List;

/**
 * Concrete Implementation of {@code  com.glowanet.tools.unit.AbstractEnumObjectUnitTester} for the unit tests.
 *
 * @see com.glowanet.tools.unit.AbstractEnumObjectUnitTester
 * @deprecated Will be removed with {@code com.glowanet.tools.unit.AbstractEnumObjectUnitTester}
 */
@Deprecated(forRemoval = true, since = "1.0")
public class ConcreteAbstractEnumObjectUnitTester extends AbstractEnumObjectUnitTester<DataEnumObjectUnitTester> {

    /* constructors */
    public ConcreteAbstractEnumObjectUnitTester() {
        super(DataEnumObjectUnitTester.class);
        setCodeCheckEnabled(false);
    }
    /* end - constructors */

    /* methods */
    @Override
    protected DataEnumObjectUnitTester createObject2Test() {
        return null;
    }

    @Override
    protected List<String> enumObjectsToIgnoreForCode() {
        return List.of();
    }
    /* end - methods */

}
