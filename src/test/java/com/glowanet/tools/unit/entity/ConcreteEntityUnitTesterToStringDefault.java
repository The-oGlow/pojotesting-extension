package com.glowanet.tools.unit.entity;

import com.glowanet.tools.unit.entity.data.DataEntityUnitTester;

@SuppressWarnings("UnconstructableJUnitTestCase")
class ConcreteEntityUnitTesterToStringDefault extends AbstractEntityUnitTester<DataEntityUnitTester> implements IConcreteEntityUnitTester {
    protected ConcreteEntityUnitTesterToStringDefault(Class<DataEntityUnitTester> typeOfT) {
        super(typeOfT);
    }

    @Override
    protected DataEntityUnitTester createObject2Test() {
        return new DataEntityUnitTester();
    }
}
