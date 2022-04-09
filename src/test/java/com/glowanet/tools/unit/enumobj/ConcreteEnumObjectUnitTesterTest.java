package com.glowanet.tools.unit.enumobj;

import com.glowanet.tools.unit.enumobj.data.DataEnumObjectUnitTesterIncorrect;

import java.util.List;

public class ConcreteEnumObjectUnitTesterTest<
        E extends DataEnumObjectUnitTesterIncorrect> extends EnumObjectUnitTester<E> {

    public ConcreteEnumObjectUnitTesterTest() {
        super((Class<E>) DataEnumObjectUnitTesterIncorrect.class);
    }

    @Override
    protected List<String> enumObjectsToIgnoreForCode() {
        return List.of();
    }
}
