package com.glowanet.tools.unit.entity.generic;

import com.glowanet.tools.unit.entity.CallTheCreator;
import com.glowanet.tools.unit.entity.SimulationEntityTester;
import com.glowanet.tools.unit.entity.data.DataEntityUnitTesterGenericEquals;
import org.junit.Ignore;

/**
 * A concrete tester, which verifies the entity {@code T}.
 *
 * @param <T> the type of the entity which will be tested
 */
@SuppressWarnings("UnconstructableJUnitTestCase")
@Ignore("Do not call this as test class!!")
public class ConcreteEntityUnitTesterGeneric<
        T extends DataEntityUnitTesterGenericEquals> extends SimulationEntityTester<T> {

    // constructors
    protected ConcreteEntityUnitTesterGeneric(Class<T> typeOfo2T, CallTheCreator<T> callTheCreatorForT) {
        super(typeOfo2T, callTheCreatorForT);
    }
// end - constructors
}
