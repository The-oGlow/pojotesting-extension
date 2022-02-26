package com.glowanet.tools.unit;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class AbstractUnitTesterConcrete extends AbstractUnitTester<DataUnitTesterConcrete> {

    public AbstractUnitTesterConcrete() {
        this(DataUnitTesterConcrete.class);
    }

    /**
     * @param typeOfo2T the class object of {@code T}
     */
    protected AbstractUnitTesterConcrete(Class<DataUnitTesterConcrete> typeOfo2T) {
        super(typeOfo2T);
    }

    @Override
    protected DataUnitTesterConcrete createObject2Test() {
        return new DataUnitTesterConcrete();
    }

    public static void _setFinalStatic(Class<?> clazzA, String fieldName, Object newValue) throws NoSuchFieldException, IllegalAccessException {
        AbstractUnitTester.setFinalStatic(clazzA, fieldName, newValue);
    }

    public void _setUp() {
        super.setUp();
    }

    public List<PropertyDescriptor> _findGetter() {
        return super.findGetter();
    }

    public List<PropertyDescriptor> _findSetter() {
        return super.findSetter();
    }

    public DataUnitTesterConcrete _createObject2Test() {
        return this.createObject2Test();
    }

    public DataUnitTesterConcrete _getObject2Test() {
        return super.getObject2Test();
    }

    public void _setObject2Test(DataUnitTesterConcrete dataUnitTesterConcrete) {
        super.setObject2Test(dataUnitTesterConcrete);
    }

    public Class<DataUnitTesterConcrete> _getTypeOfo2T() {
        return super.getTypeOfo2T();
    }

    public boolean _hasSerializableIF(Class<?> unitTestDataClass) {
        return super.hasSerializableIF(unitTestDataClass);
    }

    public Field _findField(DataUnitTesterConcrete dataUnitTesterConcrete, String fieldName) {
        return super.findField(dataUnitTesterConcrete, fieldName);
    }

    public void _makeFieldAccessible(Field field, DataUnitTesterConcrete dataUnitTesterConcrete) {
        super.makeFieldAccessible(field, dataUnitTesterConcrete);
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

    public List<Field> _retrievePublicConstantsfromClass(Class<DataUnitTesterConcrete> unitTestDataClass) {
        return super.retrievePublicConstantsfromClass(unitTestDataClass);
    }
}
