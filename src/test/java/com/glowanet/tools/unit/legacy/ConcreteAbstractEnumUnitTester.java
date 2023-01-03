package com.glowanet.tools.unit.legacy;

import com.glowanet.data.enums.DataEnums;
import com.glowanet.tools.unit.AbstractEnumObjectUnitTester;
import org.junit.Ignore;

import java.util.List;

/**
 * Concrete Implementation of {@code com.glowanet.tools.unit.AbstractEnumObjectUnitTester} for the unit tests.
 *
 * @see com.glowanet.tools.unit.AbstractEnumObjectUnitTester
 * @deprecated Will be removed with {@code com.glowanet.tools.unit.AbstractEnumObjectUnitTester}
 */
@Deprecated(forRemoval = true, since = "0.1.0")
@Ignore("Do not call this as test class!!")
class ConcreteAbstractEnumUnitTester extends AbstractEnumObjectUnitTester<DataEnums> {

    public ConcreteAbstractEnumUnitTester() {
        super(DataEnums.class);
        setCodeCheckEnabled(false);
    }

    @Override
    protected List<String> enumObjectsToIgnoreForCode() {
        return List.of();
    }
}
