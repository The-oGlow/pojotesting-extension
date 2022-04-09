package com.glowanet.tools.unit.entity;

import java.util.List;

/**
 * An abstract class which opens all methods for public access and testing purpoase.
 *
 * @param <T> the type of the class to test
 */
public abstract class SimulationEntityTester<T> extends AbstractEntityUnitTester<T> {

    // fields
    private final CallTheCreator<T> callTheCreatorForT;
// end - fields

    // constructors
    protected SimulationEntityTester(Class<T> typeOfo2T, CallTheCreator<T> callTheCreatorForT) {
        super(typeOfo2T);
        this.callTheCreatorForT = callTheCreatorForT;
    }
// end - constructors

    // methods
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
    public T createObject2Test() {
        return callTheCreatorForT == null ? null : callTheCreatorForT.call();
    }

    @Override
    protected void init() {
        //nothing2do
    }
// end - methods

}
