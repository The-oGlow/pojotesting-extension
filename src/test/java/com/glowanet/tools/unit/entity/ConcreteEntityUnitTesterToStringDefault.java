package com.glowanet.tools.unit.entity;

import com.glowanet.tools.unit.entity.data.DataEntityUnitTester;
import org.junit.Ignore;

/**
 * This simulates a class which will act as an unit-test to check {@code T}.
 *
 * @param <T> the type of the pojo which will be tested
 */
@SuppressWarnings("UnconstructableJUnitTestCase")
@Ignore("Do not call this as test class!!")
class ConcreteEntityUnitTesterToStringDefault<
        T extends DataEntityUnitTester> extends SimulationEntityTester<T> {

    /* constructors */
    protected ConcreteEntityUnitTesterToStringDefault(Class<T> typeOfo2T, CallTheCreator<T> callTheCreatorForT) {
        super(typeOfo2T, callTheCreatorForT);
    }
}
