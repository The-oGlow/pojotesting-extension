package com.glowanet.tools.unit.entity.logical;

import com.glowanet.tools.unit.entity.data.DataEntityUnitTesterLogicalEquals;
import com.glowanet.tools.unit.entity.generic.AbstractEntityUnitTesterConcreteGenericEquals;
import org.junit.Ignore;

/**
 * Container class, which holds the class which will be tested.
 *
 * @param <T> the type of the class to test
 */
@Ignore("Do not call this call directly!")
public class AbstractEntityUnitTesterConcreteLogicalEquals<T extends DataEntityUnitTesterLogicalEquals> extends AbstractEntityUnitTesterConcreteGenericEquals<T> {

    public AbstractEntityUnitTesterConcreteLogicalEquals() {
        this((Class<T>) DataEntityUnitTesterLogicalEquals.class);
    }

    protected AbstractEntityUnitTesterConcreteLogicalEquals(Class<T> typeOfT) {
        super(typeOfT);
    }

    @Override
    protected T createObject2Test() {
        return (T) new DataEntityUnitTesterLogicalEquals();
    }
}
