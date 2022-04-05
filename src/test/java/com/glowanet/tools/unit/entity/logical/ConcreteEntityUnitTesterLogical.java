package com.glowanet.tools.unit.entity.logical;

import com.glowanet.tools.unit.entity.CallTheCreator;
import com.glowanet.tools.unit.entity.data.DataEntityUnitTesterLogicalEquals;
import com.glowanet.tools.unit.entity.generic.ConcreteEntityUnitTesterGeneric;
import org.junit.Ignore;

/**
 * Container class, which holds the class which will be tested.
 *
 * @param <T> the type of the class to test
 */
@SuppressWarnings("UnconstructableJUnitTestCase")
@Ignore("Do not call this as test class!!")
public class ConcreteEntityUnitTesterLogical<
        T extends DataEntityUnitTesterLogicalEquals> extends ConcreteEntityUnitTesterGeneric<T> {

    /* constructors */
    protected ConcreteEntityUnitTesterLogical(Class<T> typeOfo2T, CallTheCreator<T> callTheCreatorForT) {
        super(typeOfo2T, callTheCreatorForT);
    }
}
