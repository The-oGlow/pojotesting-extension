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
@Deprecated(forRemoval = true, since = "1.0")
@Ignore("Do not call this as test class!!")
class ConcreteAbstractEntityUnitTester extends AbstractEntityUnitTester<DataEntitySimple> {

    /* constructors */
    public ConcreteAbstractEntityUnitTester() {
        super(DataEntitySimple.class);
        setCheckSVUID(false);
    }
    /* end - constructors */

    /* methods */
    @Override
    protected List<String> fieldsToIgnoreForToString() {
        return List.of("primJ", "valI", "valS");
    }
    /* end - methods */
}
