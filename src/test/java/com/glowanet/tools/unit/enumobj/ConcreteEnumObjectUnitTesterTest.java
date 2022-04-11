package com.glowanet.tools.unit.enumobj;

import com.glowanet.tools.unit.enumobj.EnumObjectUnitTester.NAME_CHECK_ENUM;
import com.glowanet.tools.unit.enumobj.data.DataEnumObjectUnitTester;
import com.glowanet.util.junit.TestResultHelper;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.glowanet.tools.unit.enumobj.data.DataEnumObjectUnitTester.ITEMNAME_EXISTS;
import static com.glowanet.tools.unit.enumobj.data.DataEnumObjectUnitTester.ITEM_WITHOUT_NUMBER;
import static com.glowanet.tools.unit.enumobj.data.DataEnumObjectUnitTester.ITEM_WITH_NUMBER;
import static com.glowanet.util.junit.TestResultHelper.TWO_ERROR;
import static com.glowanet.util.junit.TestResultHelper.WITH_ERROR;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

public class ConcreteEnumObjectUnitTesterTest<
        E extends DataEnumObjectUnitTester> extends SimulationEnumObjectTesterTest<E> {

    private SimulationEnumObjectTester<E> o2T;

    // methods
    @Before
    public void setUp() {
        o2T = prepareEnumObjectTester((Class<E>) DataEnumObjectUnitTester.class);
    }

    @Test
    public void testCheckIgnoredFields_existingField_return_false_raise_noException() {
        Field expectedField = prepareFieldExists();
        assertThat(expectedField, notNullValue());
        boolean actual = o2T.checkIgnoredFields(expectedField);
        TestResultHelper.verifyCollector(o2T, WITH_ERROR, false, actual);
    }

    @Test
    public void testCheckIgnoredFields_null_return_false_raise_noException() {
        boolean actual = o2T.checkIgnoredFields(null);
        TestResultHelper.verifyCollectorNoError(o2T, false, actual);
    }

    @Test
    public void testCreateObject2Test_return_null() {
        Object actual = o2T.createObject2Test();
        Object actual2 = o2T.createObject2Test();
        assertThat(actual, nullValue());
        assertThat(actual2, nullValue());
    }

    @Test
    public void testEnumObjectsToIgnoreForCode_return_emptyList() {
        List<String> actual = o2T.enumObjectsToIgnoreForCode();
        assertThat(actual, notNullValue());
        assertThat(actual, hasSize(0));
    }

    @Test
    public void testGetNameCheckType_return_CF() {
        NAME_CHECK_ENUM actual = o2T.getNameCheckType();
        assertThat(actual, equalTo(EnumObjectUnitTester.DEFAULT_NAME_CHECK_TYPE));
    }

    @Test
    public void testGetObject2Test_return_newObject() {
        Object actual = o2T.getObject2Test();
        Object actual2 = o2T.getObject2Test();
        assertThat(actual, nullValue());
        assertThat(actual2, nullValue());
    }

    @Test
    public void testIsCodeCheckEnabled_return_true() {
        boolean actual = o2T.isCodeCheckEnabled();
        assertThat(actual, equalTo(EnumObjectUnitTester.DEFAULT_CODE_CHECK_ENABLED));
    }

    @Test
    public void testIsInIgnoreListForCode_null_return_false_raise_noException() {
        boolean actual = o2T.isInIgnoreListForCode(null);
        TestResultHelper.verifyCollectorNoError(o2T, false, actual);
    }

    @Test
    public void testIsInIgnoreListForCode_existingField_return_false_raise_noException() {
        boolean actual = o2T.isInIgnoreListForCode(ITEMNAME_EXISTS);
        TestResultHelper.verifyCollectorNoError(o2T, false, actual);
    }

    @Test
    public void testSetCodeCheckEnabled_return_false() {
        assertThat(o2T.isCodeCheckEnabled(), equalTo(EnumObjectUnitTester.DEFAULT_CODE_CHECK_ENABLED));
        o2T.setCodeCheckEnabled(false);
        assertThat(o2T.isCodeCheckEnabled(), equalTo(false));
    }

    @Test
    public void testSetNameCheckType_return_CIF() {
        NAME_CHECK_ENUM expected = NAME_CHECK_ENUM.CIF;
        assertThat(o2T.getNameCheckType(), equalTo(EnumObjectUnitTester.DEFAULT_NAME_CHECK_TYPE));
        o2T.setNameCheckType(expected);
        assertThat(o2T.getNameCheckType(), equalTo(expected));
    }

    @Test
    public void testValidateAllEnumObjects_raise_noException() {
        o2T.testValidateAllEnumObjects();
        TestResultHelper.verifyCollector(o2T, DataEnumObjectUnitTester.EXCEPTION_SUM);
    }

    @Test
    public void testValidateEnumObjectCode_itemNameWithNumber_instance_return_true_raise_oneException() {
        Field expectedField = prepareFieldWithNo();
        E actualInstance = (E) ITEM_WITH_NUMBER;
        boolean actual = o2T.validateEnumObjectCode(expectedField, actualInstance);
        TestResultHelper.verifyCollector(o2T, WITH_ERROR, false, actual);
    }

    @Test
    public void testValidateEnumObjectCode_itemNameWithNumber_wrongInstance_return_true_raise_oneException() {
        Field expectedField = prepareFieldWithNo();
        E actualInstance = (E) ITEM_WITHOUT_NUMBER;
        boolean actual = o2T.validateEnumObjectCode(expectedField, actualInstance);
        TestResultHelper.verifyCollector(o2T, WITH_ERROR, false, actual);
    }

    @Test
    public void testValidateEnumObjectName_itemNameNoNumber_instance_return_true_raise_noException() {
        Field expectedField = prepareFieldWithOutNo();
        E actualInstance = (E) ITEM_WITHOUT_NUMBER;
        boolean actual = o2T.validateEnumObjectName(expectedField, actualInstance);
        TestResultHelper.verifyCollectorNoError(o2T, true, actual);
    }

    @Test
    public void testValidateEnumObjectName_itemNameWithNumber_instance_return_true_raise_noException() {
        Field expectedField = prepareFieldWithNo();
        E actualInstance = (E) ITEM_WITH_NUMBER;
        boolean actual = o2T.validateEnumObjectName(expectedField, actualInstance);
        TestResultHelper.verifyCollectorNoError(o2T, true, actual);
    }

    @Test
    public void testValidateEnumObjectName_itemNameWithNumber_wrongInstance_return_true_raise_noException() {
        Field expectedField = prepareFieldWithNo();
        E actualInstance = (E) ITEM_WITHOUT_NUMBER;
        boolean actual = o2T.validateEnumObjectName(expectedField, actualInstance);
        TestResultHelper.verifyCollector(o2T, WITH_ERROR, false, actual);
    }

    @Test
    public void testValidateEnumObject_itemNameNoNumber_clazz_return_true_raise_noException() {
        Field expectedField = prepareFieldWithOutNo();
        Class<E> actualClazz = (Class<E>) DataEnumObjectUnitTester.class;
        boolean actual = o2T.validateEnumObject(expectedField, actualClazz);
        TestResultHelper.verifyCollector(o2T, TWO_ERROR, false, actual);
    }

    @Test
    public void testValidateEnumObject_itemNameNoNumber_null_return_true_raise_noException() {
        Field expectedField = prepareFieldWithOutNo();
        Class<E> actualClazz = null;
        boolean actual = o2T.validateEnumObject(expectedField, actualClazz);
        TestResultHelper.verifyCollector(o2T, WITH_ERROR, false, actual);
    }

    @Test
    public void testValidateEnumObject_itemNameWithNumber_clazz_return_true_raise_noException() {
        Field expectedField = prepareFieldWithNo();
        Class<E> actualClazz = (Class<E>) DataEnumObjectUnitTester.class;
        boolean actual = o2T.validateEnumObject(expectedField, actualClazz);
        TestResultHelper.verifyCollector(o2T, WITH_ERROR, false, actual);
    }

    @Test
    public void testValidateEnumObject_itemNameWithNumber_null_return_true_raise_noException() {
        Field expectedField = prepareFieldWithNo();
        Class<E> actualClazz = null;
        boolean actual = o2T.validateEnumObject(expectedField, actualClazz);
        TestResultHelper.verifyCollector(o2T, WITH_ERROR, false, actual);
    }

    @Test
    public void testValidateEnumObject_null_clazz_return_true_raise_noException() {
        Field expectedField = null;
        Class<E> actualClazz = (Class<E>) DataEnumObjectUnitTester.class;
        boolean actual = o2T.validateEnumObject(expectedField, actualClazz);
        TestResultHelper.verifyCollector(o2T, WITH_ERROR, false, actual);
    }

    @Test
    public void testValidateEnumObject_null_null_return_true_raise_noException() {
        Field expectedField = null;
        Class<E> actualClazz = null;
        boolean actual = o2T.validateEnumObject(expectedField, actualClazz);
        TestResultHelper.verifyCollectorNoError(o2T, true, actual);
    }

    @Override
    protected SimulationEnumObjectTester<E> prepareEnumObjectTester(Class<E> typeOfO2E) {
        return new ConcreteEnumObjectUnitTester<>(typeOfO2E);
    }
// end - methods

}