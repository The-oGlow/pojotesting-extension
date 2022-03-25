package com.glowanet.tools.unit.enumobj;

import java.util.List;

/**
 * Container class, which holds the class which will be tested.
 *
 * @param <T> the type of the class to test
 */
public class ConcreteEnumObjectUnitTester<T> extends AbstractEnumObjectUnitTester<T> {

    public ConcreteEnumObjectUnitTester() {
        this((Class<T>) String.class);
    }

    protected ConcreteEnumObjectUnitTester(Class<T> typeOfo2T) {
        super(typeOfo2T);
    }

    @Override
    protected List<String> enumObjectsToIgnoreForCode() {
        return List.of();
    }
}
