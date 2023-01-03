package com.glowanet.tools.unit.entity;

import com.glowanet.data.entity.BaseDataEntity;

import java.util.List;

/**
 * An abstract clazz which opens all methods from the tester for public access and testing purpose.
 *
 * @param <T> the type of the entity which will be tested
 */
public abstract class BaseEntityTester<T extends BaseDataEntity> extends EntityUnitTester<T> {

    protected BaseEntityTester(Class<T> typeOfo2T) {
        super(typeOfo2T);
    }

    public List<String> _fieldsDeniedForToString() {
        return super.fieldsDeniedForToString();
    }

    public List<String> _fieldsToIgnoreForToString() {
        return super.fieldsToIgnoreForToString();
    }

    public boolean _isCheckLogicalEqualsOnly() {
        return super.isCheckLogicalEqualsOnly();
    }

    public boolean _isCheckSVUID() {
        return super.isCheckSVUID();
    }

    public void _setCheckLogicalEqualsOnly(boolean checkLogicalEqualsOnly) {
        super.setCheckLogicalEqualsOnly(checkLogicalEqualsOnly);
    }

    public void _setCheckSVUID(boolean checkSVUID) {
        super.setCheckSVUID(checkSVUID);
    }

    public void _validateSerialVersionUID() {
        super.validateSerialVersionUID();
    }

    public void _verifyAllGetterSetterCollaboration(boolean verifyValue) {
        super.verifyAllGetterSetterCollaboration(verifyValue);
    }

    @Override
    protected void init() {
        //nothing2do
    }
}
