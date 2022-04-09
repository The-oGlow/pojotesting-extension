package com.glowanet.tools.unit;

import com.glowanet.tools.unit.data.DataUnitTester;

import java.util.List;

/**
 * Concrete Implementation of {@code  com.glowanet.tools.unit.AbstractEntityUnitTester} for the unit tests.
 *
 * @see com.glowanet.tools.unit.AbstractEntityUnitTester
 * @deprecated Will be removed with {@code com.glowanet.tools.unit.AbstractEntityUnitTester}
 */
@Deprecated(forRemoval = true, since = "1.0")
public class ConcreteEntityUnitTester extends AbstractEntityUnitTester<DataUnitTester> {

    /* constructors */
    public ConcreteEntityUnitTester() {
        super(DataUnitTester.class);
        setCheckSVUID(false);
    }
    /* end - constructors */

    /* methods */
    @Override
    protected DataUnitTester createObject2Test() {
        return new DataUnitTester();
    }

    @Override
    protected List<String> fieldsToIgnoreForToString() {
        return List.of("primJ", "valI", "valS");
    }
    /* end - methods */
}
