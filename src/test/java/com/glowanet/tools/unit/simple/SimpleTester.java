package com.glowanet.tools.unit.simple;

import com.glowanet.data.simple.DataSimple;
import com.glowanet.tools.unit.AbstractUnitTester;
import com.glowanet.tools.unit.ClazzAdapter;
import org.junit.Ignore;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * Concrete Implementation of {@code com.glowanet.tools.unit.AbstractUnitTester} for the unit tests.
 *
 * @see com.glowanet.tools.unit.AbstractUnitTester
 */
@Ignore("Do not call this as test class!!")
class SimpleTester extends AbstractUnitTester<DataSimple> {

    public SimpleTester() {
        super(DataSimple.class);
    }

    public static void _setFinalStatic(Class<?> clazzA, String fieldName, Object newValue) throws UnsupportedOperationException {
        AbstractUnitTester.setFinalStatic(clazzA, fieldName, newValue);
    }

    public Field _findField(DataSimple instance, String fieldName) {
        return super.findField(instance, fieldName);
    }

    public List<PropertyDescriptor> _findGetter() {
        return super.findGetter();
    }

    public List<PropertyDescriptor> _findSetter() {
        return super.findSetter();
    }

    public Class<DataSimple> _getTypeOfo2T() {
        return super.getTypeOfo2T();
    }

    protected boolean _hasSerializableIF(Class<?> clazzA) {
        return super.hasSerializableIF(clazzA);
    }

    protected void _makeFieldAccessible(Field field, DataSimple instance) {
        super.makeFieldAccessible(field, instance);
    }

    protected List<Field> _retrievePublicConstantsfromClass(Class<?> clazzA) {
        return super.retrievePublicConstantsfromClass(clazzA);
    }

    protected Map<ClazzAdapter, Object> _retrieveMethodParameters(Method method) {
        return super.retrieveMethodParameters(method);
    }

    protected Number _retrieveNumberFromText(String textWithNumber) {
        return super.retrieveNumberFromText(textWithNumber);
    }

    protected String _retrieveNumberFromTextSpecialized(String textWithNumber, String numberAsText) {
        return super.retrieveNumberFromTextSpecialized(textWithNumber, numberAsText);
    }
}
