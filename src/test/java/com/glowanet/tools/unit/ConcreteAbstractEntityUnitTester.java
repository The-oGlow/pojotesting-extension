package com.glowanet.tools.unit;

import com.glowanet.tools.unit.data.DataUnitTester;

import java.util.List;

/**
 * Concrete Implementation of {@code  com.glowanet.tools.unit.AbstractEntityUnitTester} for the unit tests.
 *
 * @see AbstractEntityUnitTester
 * @deprecated Will be removed with {@code com.glowanet.tools.unit.AbstractEntityUnitTester}
 */
@Deprecated(forRemoval = true, since = "1.0")
public class ConcreteAbstractEntityUnitTester extends AbstractEntityUnitTester<DataUnitTester> {

    /* constructors */
    public ConcreteAbstractEntityUnitTester() {
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
