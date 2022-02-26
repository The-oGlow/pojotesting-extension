package com.glowanet.tools.unit.enumobj;

import java.util.List;

/**
 * Container class, which holds the class which will be tested.
 *
 * @param <K> the type of the class to test
 */
public class ConcreteEnumObjectUnitTester<K> extends AbstractEnumObjectUnitTester<K> {

    public ConcreteEnumObjectUnitTester() {
        this((Class<K>) String.class);
    }

    protected ConcreteEnumObjectUnitTester(Class<K> typeOfT) {
        super(typeOfT);
    }

    @Override
    protected List<String> enumObjectsToIgnoreForCode() {
        return List.of();
    }
}
