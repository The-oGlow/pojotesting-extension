package com.glowanet.tools.unit.entity;

import java.util.List;

public abstract class SimulationEntityTester<T> extends AbstractEntityUnitTester<T> {

    private final CallTheCreator<T> callTheCreatorForT;

    protected SimulationEntityTester(Class<T> typeOfo2T, CallTheCreator<T> callTheCreatorForT) {
        super(typeOfo2T);
        this.callTheCreatorForT = callTheCreatorForT;
    }

    @Override
    protected void init() {
        //nothing2do
    }

    @Override
    public T createObject2Test() {
        return callTheCreatorForT == null ? null : callTheCreatorForT.call();
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
