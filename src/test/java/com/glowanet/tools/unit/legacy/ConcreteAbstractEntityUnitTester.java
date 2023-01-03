package com.glowanet.tools.unit.legacy;

import com.glowanet.data.entity.simple.DataEntitySimple;
import com.glowanet.tools.unit.AbstractEntityUnitTester;
import org.junit.Ignore;

import java.util.List;

/**
 * Concrete Implementation of {@code com.glowanet.tools.unit.AbstractEntityUnitTester} for the unit tests.
 *
 * @see AbstractEntityUnitTester
 * @deprecated Will be removed with {@code com.glowanet.tools.unit.AbstractEntityUnitTester}
 */
@Deprecated(forRemoval = true, since = "0.1.0")
@Ignore("Do not call this as test class!!")
class ConcreteAbstractEntityUnitTester extends AbstractEntityUnitTester<DataEntitySimple> {

    public ConcreteAbstractEntityUnitTester() {
        super(DataEntitySimple.class);
        setCheckSVUID(false);
    }

    @Override
    protected List<String> fieldsToIgnoreForToString() {
        return List.of("primJ", "valI", "valS");
    }
}
