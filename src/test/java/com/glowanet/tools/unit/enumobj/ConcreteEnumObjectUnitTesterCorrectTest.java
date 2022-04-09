package com.glowanet.tools.unit.enumobj;

import com.glowanet.tools.unit.enumobj.data.DataEnumObjectUnitTesterCorrect;

import java.util.List;

public class ConcreteEnumObjectUnitTesterCorrectTest<
        E extends DataEnumObjectUnitTesterCorrect> extends EnumObjectUnitTester<E> {

    public ConcreteEnumObjectUnitTesterCorrectTest() {
        super((Class<E>) DataEnumObjectUnitTesterCorrect.class);
    }

    @Override
    protected List<String> enumObjectsToIgnoreForCode() {
        return List.of();
    }
}
