package com.glowanet.tools.unit.entity.generic;

import com.glowanet.tools.unit.AbstractEntityUnitTester;

import java.beans.PropertyDescriptor;
import java.util.List;

/**
 * Container class, which holds the class which will be tested.
 *
 * @param <T> the type of the class to test
 */
public class ConcreteEntityGenericEqualsUnitTester<T extends ConcreteEntityGenericEquals> extends AbstractEntityUnitTester<T> {

    public ConcreteEntityGenericEqualsUnitTester() {
        this((Class<T>) ConcreteEntityGenericEquals.class);
    }

    protected ConcreteEntityGenericEqualsUnitTester(Class<T> typeOfT) {
        super(typeOfT);
    }

    @Override
    protected T createObject2Test() {
        return (T) new ConcreteEntityGenericEquals();
    }

    public List<PropertyDescriptor> _findGetter() {
        return super.findGetter();
    }

    protected List<PropertyDescriptor> _findSetter() {
        return super.findSetter();
    }

    public List<String> _fieldsDeniedForToString() {
        return super.fieldsDeniedForToString();
    }

    public List<String> _fieldsToIgnoreForToString() {
        return super.fieldsToIgnoreForToString();
    }

    protected boolean _isCheckLogicalEqualsOnly() {
        return super.isCheckLogicalEqualsOnly();
    }

    protected void _setCheckLogicalEqualsOnly(boolean checkLogicalEqualsOnly) {
        super.setCheckLogicalEqualsOnly(checkLogicalEqualsOnly);
    }
}
