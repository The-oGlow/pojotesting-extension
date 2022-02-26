package com.glowanet.tools.unit;

import org.junit.Before;
import org.junit.Test;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToObject;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.matchesRegex;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThrows;

public class AbstractUnitTesterConcreteTest {

    private static final String VAL_HELLO = "HELLO";
    private static final Number VAL_99    = 99;
    private static final String VAL_1     = "1";

    AbstractUnitTesterConcrete o2T;

    @Before
    public void setUp() {
        o2T = new AbstractUnitTesterConcrete();
        o2T._setUp();
    }

    @Test(expected = IllegalAccessException.class)
    public void testSetFinalStatic_throw_IAException() throws NoSuchFieldException, IllegalAccessException {
        DataUnitTesterConcrete actual = new DataUnitTesterConcrete();
        Class<?> clazzActual = actual.getClass();
        String fieldName = DataUnitTesterConcrete.PRIV_CONST_NAME;
        Object oldValue = DataUnitTesterConcrete.VAL_PREV;
        Object newValue = DataUnitTesterConcrete.VAL_NEW;

        assertThat(DataUnitTesterConcrete.VAL_PREV, not(equalTo(DataUnitTesterConcrete.VAL_NEW)));
        assertThat(DataUnitTesterConcrete.PRIV_CONST, equalTo(DataUnitTesterConcrete.VAL_PREV));

        AbstractUnitTesterConcrete._setFinalStatic(clazzActual, fieldName, newValue);

        assertThat(DataUnitTesterConcrete.VAL_PREV, not(equalTo(DataUnitTesterConcrete.VAL_NEW)));
        assertThat(DataUnitTesterConcrete.PRIV_CONST, equalTo(DataUnitTesterConcrete.VAL_NEW));
    }

    @Test
    public void testFindGetter_return_listOfPropertyDescriptor() {
        List<PropertyDescriptor> actual = o2T._findGetter();
        assertThat(actual, isA(List.class));
        assertThat(actual, hasSize(DataUnitTesterConcrete.GETTER_COUNT));
    }

    @Test
    public void testFindSetter_return_listOfPropertyDesriptor() {
        List<PropertyDescriptor> actual = o2T._findSetter();
        assertThat(actual, isA(List.class));
        assertThat(actual, hasSize(DataUnitTesterConcrete.SETTER_COUNT));
    }

    @Test
    public void testCreateObject2Test_return_newCreatedObject() {
        Object actual = o2T._createObject2Test();
        Object actual2 = o2T._createObject2Test();
        assertThat(actual, isA(DataUnitTesterConcrete.class));
        assertThat(actual2, isA(DataUnitTesterConcrete.class));
        assertThat(actual, not(equalToObject(actual2)));
    }

    @Test
    public void testGetObject2Test_return_currentUsedObject() {
        Object actual = o2T._getObject2Test();
        assertThat(actual, isA(DataUnitTesterConcrete.class));
    }

    @Test
    public void testSetObject2Test_set_newUsedObject() {
        DataUnitTesterConcrete expected = new DataUnitTesterConcrete();
        o2T._setObject2Test(expected);
        Object actual = o2T._getObject2Test();
        assertThat(actual, equalToObject(expected));
    }

    @Test
    public void testGetTypeOfo2T_return_typeOfCurrentUsedObject() {
        Class<DataUnitTesterConcrete> actual = o2T._getTypeOfo2T();
        assertThat(actual.getTypeName(), equalTo(DataUnitTesterConcrete.class.getName()));
    }

    @Test
    public void testHasSerializableIF_return_true() {
        boolean actual = o2T._hasSerializableIF(DataUnitTesterConcrete.class);
        assertThat(actual, equalTo(true));
    }

    @Test
    public void testHasSerializableIF_return_false() {
        boolean actual = o2T._hasSerializableIF(this.getClass());
        assertThat(actual, equalTo(false));
    }

    @Test
    public void testFindField_return_fieldIsFound() {
        DataUnitTesterConcrete instance = o2T._getObject2Test();
        Field actual = o2T._findField(instance, DataUnitTesterConcrete.FIELD_EXISTS);
        assertThat(actual, notNullValue());
        assertThat(actual.getName(), equalTo(DataUnitTesterConcrete.FIELD_EXISTS));
        assertThat(actual.canAccess(instance), equalTo(true));
    }

    @Test
    public void testFindField_throws_fieldNotFoundException() {
        DataUnitTesterConcrete instance = o2T._getObject2Test();
        Throwable actual = assertThrows(Throwable.class, () -> o2T._findField(instance, DataUnitTesterConcrete.FIELD_NOT_EXISTS));
        assertThat(actual, notNullValue());
        assertThat(actual, instanceOf(AssertionError.class));
        assertThat(actual.getMessage(), matchesRegex("^No " + DataUnitTesterConcrete.FIELD_NOT_EXISTS + " defined : .+$"));
    }

    @Test
    public void testMakeFieldAccessible_void_fieldIsAccessibleNow() throws NoSuchFieldException {
        DataUnitTesterConcrete instance = o2T._getObject2Test();
        Field actual = instance.getClass().getDeclaredField(DataUnitTesterConcrete.FIELD_EXISTS);
        assertThat(actual.canAccess(instance), equalTo(false));
        o2T._makeFieldAccessible(actual, instance);
        assertThat(actual.canAccess(instance), equalTo(true));
    }

    @Test
    public void testRetrieveNumberFromText_return_correctNumber() {
        Number expected = VAL_99;
        Number actual = o2T._retrieveNumberFromText(expected.toString());
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void testRetrieveNumberFromText_return_null() {
        Number actual = o2T._retrieveNumberFromText(VAL_HELLO);
        assertThat(actual, nullValue());
    }

    @Test
    public void testRetrieveNumberFromTextSpecialized_return_specializedNumber() {
        String expected = VAL_99.toString();
        String actual = o2T._retrieveNumberFromTextSpecialized(VAL_1, expected);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void testRetrieveMethodParameters_return_mapOfMethodParameter() throws NoSuchMethodException {
        Method method = o2T.getObject2Test().getClass().getDeclaredMethod(DataUnitTesterConcrete.METH_NAME, DataUnitTesterConcrete.METH_PARAM);
        Map<Class<?>, Object> actual = o2T._retrieveMethodParameters(method);

        assertThat(actual, notNullValue());
        assertThat(actual.entrySet(), hasSize(DataUnitTesterConcrete.METH_PARAM.length));
        assertThat(actual.keySet(), containsInAnyOrder(DataUnitTesterConcrete.METH_PARAM));
    }

    @Test
    public void testRetrievePublicConstantsfromClass_return_listOfPublicConstants() {
        Class<DataUnitTesterConcrete> unitTestDataClass = DataUnitTesterConcrete.class;
        List<Field> actual = o2T._retrievePublicConstantsfromClass(unitTestDataClass);

        assertThat(actual, notNullValue());
        assertThat(actual, hasSize(DataUnitTesterConcrete.CONST_COUNT));
    }
}