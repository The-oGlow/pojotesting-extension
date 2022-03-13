package com.glowanet.tools.unit.entity.generic;

import com.glowanet.tools.unit.entity.AbstractEntityUnitTester;
import com.glowanet.tools.unit.entity.data.DataEntityUnitTester;
import org.junit.Ignore;

import java.util.List;

/**
 * Container class, which holds the class which will be tested.
 *
 * @param <T> the type of the class to test
 */
@Ignore("Do not call this call directly!")
public class ConcreteEntityUnitTesterGeneric<T extends DataEntityUnitTester> extends AbstractEntityUnitTester<T> {

    public ConcreteEntityUnitTesterGeneric() {
        this((Class<T>) DataEntityUnitTester.class);
    }

    protected ConcreteEntityUnitTesterGeneric(Class<T> typeOfT) {
        super(typeOfT);
    }

    @Override
    protected T createObject2Test() {
        return (T) new DataEntityUnitTester();
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

    public void _validateSerialVersionUID() {
        super.validateSerialVersionUID();
    }

    public void _verifyAllGetterSetterCollaboration(boolean verifyValue) {
        super.verifyAllGetterSetterCollaboration(verifyValue);
    }
}
