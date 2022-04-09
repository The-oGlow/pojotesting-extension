package com.glowanet.tools.unit.entity.generic;

import com.glowanet.tools.unit.entity.CallTheCreator;
import com.glowanet.tools.unit.entity.SimulationEntityTester;
import com.glowanet.tools.unit.entity.data.DataEntityUnitTesterGenericEquals;
import org.junit.Ignore;

/**
 * Container class, which holds the class which will be tested.
 *
 * @param <T> the type of the class to test
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
