package com.glowanet.tools.unit.entity;

import com.glowanet.tools.unit.entity.data.DataEntityUnitTesterToString;

@SuppressWarnings("UnconstructableJUnitTestCase")
class ConcreteEntityUnitTesterToStringImplemented extends AbstractEntityUnitTester<DataEntityUnitTesterToString> {
    protected ConcreteEntityUnitTesterToStringImplemented(Class<DataEntityUnitTesterToString> typeOfT) {
        super(typeOfT);
    }

    @Override
    protected DataEntityUnitTesterToString createObject2Test() {
        return new DataEntityUnitTesterToString();
    }
}
