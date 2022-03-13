package com.glowanet.tools.unit;

import com.glowanet.tools.unit.data.DataUnitTester;
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

public class ConcreteUnitTesterTest {

    private static final String VAL_HELLO = "HELLO";
    private static final Number VAL_99    = 99;
    private static final String VAL_1     = "1";

    ConcreteUnitTester o2T;

    @Before
    public void setUp() {
        o2T = new ConcreteUnitTester();
        //o2T._setUp();
    }

    @Test(expected = IllegalAccessException.class)
    public void testSetFinalStatic_throw_IAException() throws NoSuchFieldException, IllegalAccessException {
        DataUnitTester actual = new DataUnitTester();
        Class<?> clazzActual = actual.getClass();
        String fieldName = DataUnitTester.PRIV_CONST_NAME;
        Object oldValue = DataUnitTester.VAL_PREV;
        Object newValue = DataUnitTester.VAL_NEW;

        assertThat(DataUnitTester.VAL_PREV, not(equalTo(DataUnitTester.VAL_NEW)));
        assertThat(DataUnitTester.PRIV_CONST, equalTo(DataUnitTester.VAL_PREV));

        ConcreteUnitTester._setFinalStatic(clazzActual, fieldName, newValue);

        assertThat(DataUnitTester.VAL_PREV, not(equalTo(DataUnitTester.VAL_NEW)));
        assertThat(DataUnitTester.PRIV_CONST, equalTo(DataUnitTester.VAL_NEW));
    }

    @Test
    public void testFindGetter_return_listOfPropertyDescriptor() {
        List<PropertyDescriptor> actual = o2T._findGetter();
        assertThat(actual, isA(List.class));
        assertThat(actual, hasSize(DataUnitTester.GETTER_COUNT));
    }

    @Test
    public void testFindSetter_return_listOfPropertyDesriptor() {
        List<PropertyDescriptor> actual = o2T._findSetter();
        assertThat(actual, isA(List.class));
        assertThat(actual, hasSize(DataUnitTester.SETTER_COUNT));
    }

    @Test
    public void testCreateObject2Test_return_newCreatedObject() {
        Object actual = o2T._createObject2Test();
        Object actual2 = o2T._createObject2Test();
        assertThat(actual, isA(DataUnitTester.class));
        assertThat(actual2, isA(DataUnitTester.class));
        assertThat(actual, not(equalToObject(actual2)));
    }

    @Test
    public void testGetObject2Test_return_currentUsedObject() {
        Object actual = o2T._getObject2Test();
        assertThat(actual, isA(DataUnitTester.class));
    }

    @Test
    public void testSetObject2Test_set_newUsedObject() {
        DataUnitTester expected = new DataUnitTester();
        o2T._setObject2Test(expected);
        Object actual = o2T._getObject2Test();
        assertThat(actual, equalToObject(expected));
    }

    @Test
    public void testGetTypeOfo2T_return_typeOfCurrentUsedObject() {
        Class<DataUnitTester> actual = o2T._getTypeOfo2T();
        assertThat(actual.getTypeName(), equalTo(DataUnitTester.class.getName()));
    }

    @Test
    public void testHasSerializableIF_return_true() {
        boolean actual = o2T._hasSerializableIF(DataUnitTester.class);
        assertThat(actual, equalTo(true));
    }

    @Test
    public void testHasSerializableIF_return_false() {
        boolean actual = o2T._hasSerializableIF(this.getClass());
        assertThat(actual, equalTo(false));
    }

    @Test
    public void testFindField_return_fieldIsFound() {
        DataUnitTester instance = o2T._getObject2Test();
        Field actual = o2T._findField(instance, DataUnitTester.FIELD_EXISTS);
        assertThat(actual, notNullValue());
        assertThat(actual.getName(), equalTo(DataUnitTester.FIELD_EXISTS));
        assertThat(actual.canAccess(instance), equalTo(true));
    }

    @Test
    public void testFindField_throws_fieldNotFoundException() {
        DataUnitTester instance = o2T._getObject2Test();
        Throwable actual = assertThrows(Throwable.class, () -> o2T._findField(instance, DataUnitTester.FIELD_NOT_EXISTS));
        assertThat(actual, notNullValue());
        assertThat(actual, instanceOf(AssertionError.class));
        assertThat(actual.getMessage(), matchesRegex("^No " + DataUnitTester.FIELD_NOT_EXISTS + " defined : .+$"));
    }

    @Test
    public void testMakeFieldAccessible_void_fieldIsAccessibleNow() throws NoSuchFieldException {
        DataUnitTester instance = o2T._getObject2Test();
        Field actual = instance.getClass().getDeclaredField(DataUnitTester.FIELD_EXISTS);
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
        Method method = o2T.getObject2Test().getClass().getDeclaredMethod(DataUnitTester.METH_NAME, DataUnitTester.METH_PARAM);
        Map<Class<?>, Object> actual = o2T._retrieveMethodParameters(method);

        assertThat(actual, notNullValue());
        assertThat(actual.entrySet(), hasSize(DataUnitTester.METH_PARAM.length));
        assertThat(actual.keySet(), containsInAnyOrder(DataUnitTester.METH_PARAM));
    }

    @Test
    public void testRetrievePublicConstantsfromClass_return_listOfPublicConstants() {
        Class<DataUnitTester> unitTestDataClass = DataUnitTester.class;
        List<Field> actual = o2T._retrievePublicConstantsfromClass(unitTestDataClass);

        assertThat(actual, notNullValue());
        assertThat(actual, hasSize(DataUnitTester.CONST_COUNT));
    }
}