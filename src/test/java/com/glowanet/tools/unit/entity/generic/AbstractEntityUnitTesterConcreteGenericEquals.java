package com.glowanet.tools.unit.entity.generic;

import com.glowanet.tools.unit.entity.AbstractEntityUnitTester;
import com.glowanet.tools.unit.entity.data.DataEntityUnitTesterGenericEquals;
import org.junit.Ignore;

import java.util.List;

/**
 * Container class, which holds the class which will be tested.
 *
 * @param <T> the type of the class to test
 */
@Ignore("Do not call this call directly!")
public class AbstractEntityUnitTesterConcreteGenericEquals<T extends DataEntityUnitTesterGenericEquals> extends AbstractEntityUnitTester<T> {

    public AbstractEntityUnitTesterConcreteGenericEquals() {
        this((Class<T>) DataEntityUnitTesterGenericEquals.class);
    }

    protected AbstractEntityUnitTesterConcreteGenericEquals(Class<T> typeOfT) {
        super(typeOfT);
    }

    @Override
    protected T createObject2Test() {
        return (T) new DataEntityUnitTesterGenericEquals();
    }

    public List<String> _fieldsDeniedForToString() {
        return super.fieldsDeniedForToString();
    }

    public List<String> _fieldsToIgnoreForToString() {
        return super.fieldsToIgnoreForToString();
    }

    public boolean _isCheckSVUID() {
        return super.isCheckSVUID();
    }

    public void _setCheckSVUID(boolean checkSVUID) {
        super.setCheckSVUID(checkSVUID);
    }

    public boolean _isCheckLogicalEqualsOnly() {
        return super.isCheckLogicalEqualsOnly();
    }

    public void _setCheckLogicalEqualsOnly(boolean checkLogicalEqualsOnly) {
        super.setCheckLogicalEqualsOnly(checkLogicalEqualsOnly);
    }

    public void _validateSerialVersionUID(Object instance) {
        super.validateSerialVersionUID(instance);
    }

    public void _verifyAllGetterSetterCollaboration(boolean verifyValue) {
        super.verifyAllGetterSetterCollaboration(verifyValue);
    }
}
