package com.glowanet.tools.unit.entity.logical;

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
public class ConcreteEntityUnitTesterLogical<T extends DataEntityUnitTesterLogicalEquals> extends ConcreteEntityUnitTesterGeneric<T> {

    public ConcreteEntityUnitTesterLogical() {
        this((Class<T>) DataEntityUnitTesterLogicalEquals.class);
    }

    protected ConcreteEntityUnitTesterLogical(Class<T> typeOfT) {
        super(typeOfT);
    }

    @Override
    protected T createObject2Test() {
        return (T) new DataEntityUnitTesterLogicalEquals();
    }
}
