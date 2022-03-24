package com.glowanet.tools.unit.entity;

import com.glowanet.tools.unit.entity.data.DataEntityUnitTesterSerializable.ClazzNoSerializable;
import org.junit.Ignore;

@SuppressWarnings("UnconstructableJUnitTestCase")
@Ignore("Do not call this as test class!!")
class ConcreteEntityUnitTester<T extends ClazzNoSerializable> extends AbstractEntityUnitTester<T> implements IConcreteEntityUnitTester {

    private final Callback<T> callbackForT;

    protected ConcreteEntityUnitTester(Class<T> typeOfT) {
        this(typeOfT, null);
    }

    protected ConcreteEntityUnitTester(Class<T> typeOfT, Callback<T> callbackForT) {
        super(typeOfT);
        this.callbackForT = callbackForT;
    }

    @Override
    public T createObject2Test() {
        return callbackForT == null ? null : callbackForT.call();
    }
}
