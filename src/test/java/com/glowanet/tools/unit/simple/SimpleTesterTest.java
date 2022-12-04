package com.glowanet.tools.unit.simple;

import com.glowanet.data.entity.serial.DataEntityNotSerialVersionUid;
import com.glowanet.data.entity.serial.DataEntityNotSerializable;
import com.glowanet.data.simple.DataSimple;
import com.glowanet.tools.unit.ClazzAdapter;
import com.glowanet.util.junit.TestResultHelper;
import org.junit.Before;
import org.junit.Test;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
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

/**
 * Unit Tests to verify {@code com.glowanet.tools.unit.simple.SimpleTester}.
 *
 * @see SimpleTester
 */
public class SimpleTesterTest {

    /* static fields */
    private static final String VAL_HELLO = "HELLO";
    private static final Number VAL_99    = 99;
    private static final String VAL_1     = "1";
    /* end - static fields */

    /* fields */
    private SimpleTester o2T;
    /* end - fields */

    /* methods */
    @Before
    public void setUp() {
        o2T = new SimpleTester();
    }

    @Test
    public void testGetObject2Test_return_newCreatedObject() {
        Object actual = o2T.getObject2Test();
        Object actual2 = o2T.getObject2Test();
        assertThat(actual, isA(DataSimple.class));
        assertThat(actual2, isA(DataSimple.class));
        assertThat(actual, not(equalToObject(actual2)));
    }

    @Test
    public void testFindField_return_fieldIsFound() {
        DataSimple instance = o2T.getObject2Test();
        Field actual = o2T._findField(instance, DataSimple.FIELD_EXISTS);
        assertThat(actual, notNullValue());
        assertThat(actual.getName(), equalTo(DataSimple.FIELD_EXISTS));
        assertThat(actual.canAccess(instance), equalTo(true));
    }

    @Test
    public void testFindField_throws_fieldNotFoundException() {
        DataSimple instance = o2T.getObject2Test();

        Throwable actual = TestResultHelper.verifyException(() -> o2T._findField(instance, DataSimple.FIELD_NOT_EXISTS), Throwable.class);
        assertThat(actual, notNullValue());
        assertThat(actual, instanceOf(AssertionError.class));
        assertThat(actual.getMessage(), matchesRegex(String.format("^No '%s' defined : .+$", DataSimple.FIELD_NOT_EXISTS)));
    }

    @Test
    public void testFindGetter_return_listOfPropertyDescriptor() {
        List<PropertyDescriptor> actual = o2T._findGetter();
        assertThat(actual, isA(List.class));
        assertThat(actual, hasSize(DataSimple.GETTER_COUNT));
    }

    @Test
    public void testFindSetter_return_listOfPropertyDesriptor() {
        List<PropertyDescriptor> actual = o2T._findSetter();
        assertThat(actual, isA(List.class));
        assertThat(actual, hasSize(DataSimple.SETTER_COUNT));
    }

    @Test
    public void testGetObject2Test_return_currentUsedObject() {
        Object actual = o2T.getObject2Test();
        assertThat(actual, isA(DataSimple.class));
    }

    @Test
    public void testGetTypeOfo2T_return_typeOfCurrentUsedObject() {
        Class<DataSimple> actual = o2T._getTypeOfo2T();
        assertThat(actual.getTypeName(), equalTo(DataSimple.class.getName()));
    }

    @Test
    public void testHasSerializableIF_return_false() {
        boolean actual = o2T._hasSerializableIF(DataEntityNotSerializable.class);
        assertThat(actual, equalTo(false));
    }

    @Test
    public void testHasSerializableIF_return_true() {
        boolean actual = o2T._hasSerializableIF(DataEntityNotSerialVersionUid.class);
        assertThat(actual, equalTo(true));
    }

    @Test
    public void testMakeFieldAccessible_void_fieldIsAccessibleNow() throws NoSuchFieldException {
        DataSimple instance = o2T.getObject2Test();
        Field actual = instance.getClass().getDeclaredField(DataSimple.FIELD_EXISTS);
        assertThat(actual.canAccess(instance), equalTo(false));
        o2T._makeFieldAccessible(actual, instance);
        assertThat(actual.canAccess(instance), equalTo(true));
    }

    @Test
    public void testRetrieveMethodParameters_return_mapOfMethodParameter() throws NoSuchMethodException {
        Method method = o2T.getObject2Test().getClass().getDeclaredMethod(DataSimple.METH_NAME, DataSimple.METH_PARAM);
        Map<ClazzAdapter, Object> actual = o2T._retrieveMethodParameters(method);

        assertThat(actual, notNullValue());
        assertThat(actual.entrySet(), hasSize(DataSimple.METH_PARAM.length));
        assertThat(actual.keySet(), containsInAnyOrder(
                Arrays.stream(DataSimple.METH_PARAM).map(ClazzAdapter::newI).toArray()
        ));
    }

    @Test
    public void testRetrieveNumberFromTextSpecialized_return_specializedNumber() {
        String expected = VAL_99.toString();
        String actual = o2T._retrieveNumberFromTextSpecialized(VAL_1, expected);
        assertThat(actual, equalTo(expected));
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
    public void testRetrievePublicConstantsfromClass_return_listOfPublicConstants() {
        Class<DataSimple> unitTestDataClass = DataSimple.class;
        List<Field> actual = o2T._retrievePublicConstantsfromClass(unitTestDataClass);

        assertThat(actual, notNullValue());
        assertThat(actual, hasSize(DataSimple.CONST_COUNT));
    }

    @Test
    public void testSetFinalStatic_method_throw_USException() {
        DataSimple actual = new DataSimple();
        Class<?> clazzActual = actual.getClass();
        String fieldName = DataSimple.PRIV_CONST_NAME;
        Object oldValue = DataSimple.VAL_PREV;
        Object newValue = DataSimple.VAL_NEW;

        assertThat(DataSimple.VAL_PREV, not(equalTo(DataSimple.VAL_NEW)));
        assertThat(DataSimple.PRIV_CONST, equalTo(DataSimple.VAL_PREV));

        TestResultHelper.verifyException(() -> SimpleTester._setFinalStatic(clazzActual, fieldName, newValue), UnsupportedOperationException.class);
        assertThat(DataSimple.VAL_PREV, not(equalTo(DataSimple.VAL_NEW)));
        assertThat(DataSimple.PRIV_CONST, not(equalTo(DataSimple.VAL_NEW)));
    }
    /* end - methods */
}