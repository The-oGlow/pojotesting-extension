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
@SuppressWarnings("UnconstructableJUnitTestCase")
@Ignore("Do not call this call directly!")
public class ConcreteEntityUnitTesterGeneric<T extends DataEntityUnitTester> extends AbstractEntityUnitTester<T> {

    public abstract static class Callback<T> implements Runnable {
        protected T newO2T;

        public T getNewO2T() {
            this.run();
            return newO2T;
        }
    }

    private final Callback<T> callbackForT;

    protected ConcreteEntityUnitTesterGeneric(Class<T> typeOfT) {
        this(typeOfT, null);
    }

    protected ConcreteEntityUnitTesterGeneric(Class<T> typeOfT, Callback<T> callbackForT) {
        super(typeOfT);
        this.callbackForT = callbackForT;
    }

    @Override
    protected void init() {
        //
    }

    @Override
    protected T createObject2Test() {
        return callbackForT == null ? null : callbackForT.getNewO2T();
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
