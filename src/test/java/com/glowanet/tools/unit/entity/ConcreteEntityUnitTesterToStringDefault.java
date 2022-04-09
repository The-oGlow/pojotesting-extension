package com.glowanet.tools.unit.entity;

import com.glowanet.tools.unit.entity.data.DataEntityUnitTester;
import org.junit.Ignore;

/**
 * A concrete tester, which verifies the entity {@code T}.
 *
 * @param <T> the type of the entity which will be tested
 */
@SuppressWarnings("UnconstructableJUnitTestCase")
@Ignore("Do not call this as test class!!")
class ConcreteEntityUnitTesterToStringDefault<
        T extends DataEntityUnitTester> extends SimulationEntityTester<T> {

    // constructors
    protected ConcreteEntityUnitTesterToStringDefault(Class<T> typeOfo2T, CallTheCreator<T> callTheCreatorForT) {
        super(typeOfo2T, callTheCreatorForT);
    }
// end - constructors
}
