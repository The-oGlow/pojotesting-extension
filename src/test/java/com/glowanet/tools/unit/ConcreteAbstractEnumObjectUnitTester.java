package com.glowanet.tools.unit;

import com.glowanet.tools.unit.enumobj.data.DataEnumObjectUnitTester;
import org.junit.Ignore;

import java.util.List;

/**
 * Concrete Implementation of {@code  com.glowanet.tools.unit.AbstractEnumObjectUnitTester} for the unit tests.
 *
 * @see com.glowanet.tools.unit.AbstractEnumObjectUnitTester
 * @deprecated Will be removed with {@code com.glowanet.tools.unit.AbstractEnumObjectUnitTester}
 */
@Deprecated(forRemoval = true, since = "1.0")
@Ignore("Do not call this as test class!!")
public class ConcreteAbstractEnumObjectUnitTester extends AbstractEnumObjectUnitTester<DataEnumObjectUnitTester> {

    /* constructors */
    public ConcreteAbstractEnumObjectUnitTester() {
        super(DataEnumObjectUnitTester.class);
        setCodeCheckEnabled(false);
    }
    /* end - constructors */

    /* methods */
    @Override
    protected List<String> enumObjectsToIgnoreForCode() {
        return List.of();
    }
    /* end - methods */
}
