package com.glowanet.tools.unit;

import com.glowanet.tools.unit.data.DataUnitTester;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class ConcreteAbstractUnitTester extends AbstractUnitTester<DataUnitTester> {

    public ConcreteAbstractUnitTester() {
        this(DataUnitTester.class);
    }

    /**
     * @param typeOfo2T the class object of {@code T}
     */
    protected ConcreteAbstractUnitTester(Class<DataUnitTester> typeOfo2T) {
        super(typeOfo2T);
    }

    @Override
    protected DataUnitTester createObject2Test() {
        return new DataUnitTester();
    }

    public static void _setFinalStatic(Class<?> clazzA, String fieldName, Object newValue) throws NoSuchFieldException, IllegalAccessException {
        AbstractUnitTester.setFinalStatic(clazzA, fieldName, newValue);
    }

//    public void _setUp() {
//        super.setUp();
//    }

    public List<PropertyDescriptor> _findGetter() {
        return super.findGetter();
    }

    public List<PropertyDescriptor> _findSetter() {
        return super.findSetter();
    }

    public DataUnitTester _createObject2Test() {
        return this.createObject2Test();
    }

    public DataUnitTester _getObject2Test() {
        return super.getObject2Test();
    }

    public void _setObject2Test(DataUnitTester dataUnitTester) {
        super.setObject2Test(dataUnitTester);
    }

    public Class<DataUnitTester> _getTypeOfo2T() {
        return super.getTypeOfo2T();
    }

    public boolean _hasSerializableIF(Class<?> unitTestDataClass) {
        return super.hasSerializableIF(unitTestDataClass);
    }

    public Field _findField(DataUnitTester dataUnitTester, String fieldName) {
        return super.findField(dataUnitTester, fieldName);
    }

    public void _makeFieldAccessible(Field field, DataUnitTester dataUnitTester) {
        super.makeFieldAccessible(field, dataUnitTester);
    }

    public Number _retrieveNumberFromText(String textWithNumber) {
        return super.retrieveNumberFromText(textWithNumber);
    }

    public String _retrieveNumberFromTextSpecialized(String textWithNumber, String numberAsText) {
        return super.retrieveNumberFromTextSpecialized(textWithNumber, numberAsText);
    }

    public Map<Class<?>, Object> _retrieveMethodParameters(Method method) {
        return super.retrieveMethodParameters(method);
    }

    public List<Field> _retrievePublicConstantsfromClass(Class<DataUnitTester> unitTestDataClass) {
        return super.retrievePublicConstantsfromClass(unitTestDataClass);
    }
}
