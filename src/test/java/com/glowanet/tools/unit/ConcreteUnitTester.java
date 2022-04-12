package com.glowanet.tools.unit;

import com.glowanet.tools.unit.data.DataUnitTester;

/**
 * Concrete Implementation of {@code  com.glowanet.tools.unit.AbstractUnitTester} for the unit tests.
 *
 * @see com.glowanet.tools.unit.AbstractUnitTester
 */
public class ConcreteUnitTester extends AbstractUnitTester<DataUnitTester> {

    /* constructors */
    public ConcreteUnitTester() {
        super(DataUnitTester.class);
    }
    /* end - constructors */

    /* static method */
    public static void _setFinalStatic(Class<?> clazzA, String fieldName, Object newValue) throws IllegalAccessException {
        AbstractUnitTester.setFinalStatic(clazzA, fieldName, newValue);
    }
    /* end - static method */
}
