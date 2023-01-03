package com.glowanet.tools.unit.enums;

import com.glowanet.data.enums.DataEnumsCode;
import com.glowanet.util.junit.TestResultHelper;
import org.junit.Test;

import java.lang.reflect.Field;

import static com.glowanet.data.enums.DataEnumsCode.ITEMNAME_EXISTS;
import static com.glowanet.data.enums.DataEnumsCode.ITEMNAME_NOT_EXISTS;
import static com.glowanet.data.enums.DataEnumsCode.ITEMNAME_WITHOUT_NUMBER;
import static com.glowanet.data.enums.DataEnumsCode.ITEMNAME_WITH_NUMBER;
import static com.glowanet.util.junit.TestResultHelper.NO_ERROR;
import static com.glowanet.util.junit.TestResultHelper.TWO_ERROR;
import static com.glowanet.util.junit.TestResultHelper.WITH_ERROR;

/**
 * An abstract clazz which operates as base clazz for the junit tests.
 *
 * @param <E> the type of the enum which will be tested
 */
public abstract class BaseEnumCodeTesterTest<E extends DataEnumsCode> extends CommonEnumTesterTest<E> {

    protected BaseEnumCodeTesterTest(Class<E> typeOfE) {
        super(typeOfE);
    }

    @Test
    public void testValidateEnumObject_itemNameNoNumber_clazz_return_false_raise_twoException() {
        if (assumeParameterCodeCheckEnabledIsTrue()) {
            BaseEnumTester<E> o2T = prepareEnumTester();
            Field expectedField = prepareFieldWithOutNoInName();
            Class<E> actualClazz = (Class<E>) DataEnumsCode.class;
            boolean actual = o2T._validateEnumObject(expectedField, actualClazz);
            TestResultHelper.verifyCollector(o2T, TWO_ERROR, false, actual);
        }
    }

    @Test
    public void testValidateEnumObject_itemNameNoNumber_clazz_return_true_raise_noException() {
        if (assumeParameterCodeCheckEnabledIsFalse()) {
            BaseEnumTester<E> o2T = prepareEnumTester();
            Field expectedField = prepareFieldWithOutNoInName();
            Class<E> actualClazz = (Class<E>) DataEnumsCode.class;
            boolean actual = o2T._validateEnumObject(expectedField, actualClazz);
            TestResultHelper.verifyCollectorNoError(o2T, true, actual);
        }
    }

    @Test
    public void testValidateEnumObject_itemNameNoNumber_null_return_false_raise_oneException() {
        BaseEnumTester<E> o2T = prepareEnumTester();
        Field expectedField = prepareFieldWithOutNoInName();
        Class<E> actualClazz = null;
        boolean actual = o2T._validateEnumObject(expectedField, actualClazz);
        TestResultHelper.verifyCollector(o2T, WITH_ERROR, false, actual);
    }

    @Test
    public void testValidateEnumObject_itemNameWithNumber_clazz_return_true_raise_noException() {
        BaseEnumTester<E> o2T = prepareEnumTester();
        Field expectedField = prepareFieldWithNoInName();
        Class<E> actualClazz = (Class<E>) DataEnumsCode.class;
        boolean actual = o2T._validateEnumObject(expectedField, actualClazz);
        TestResultHelper.verifyCollector(o2T, NO_ERROR, true, actual);
    }

    @Test
    public void testValidateEnumObject_itemNameWithNumber_null_return_false_raise_oneException() {
        BaseEnumTester<E> o2T = prepareEnumTester();
        Field expectedField = prepareFieldWithNoInName();
        Class<E> actualClazz = null;
        boolean actual = o2T._validateEnumObject(expectedField, actualClazz);
        TestResultHelper.verifyCollector(o2T, WITH_ERROR, false, actual);
    }

    @Test
    public void testValidateEnumObject_null_clazz_return_false_raise_oneException() {
        BaseEnumTester<E> o2T = prepareEnumTester();
        Field expectedField = null;
        Class<E> actualClazz = (Class<E>) DataEnumsCode.class;
        boolean actual = o2T._validateEnumObject(expectedField, actualClazz);
        TestResultHelper.verifyCollector(o2T, WITH_ERROR, false, actual);
    }

    @Test
    public void testValidateEnumObject_null_null_return_true_raise_noException() {
        BaseEnumTester<E> o2T = prepareEnumTester();
        Field expectedField = null;
        Class<E> actualClazz = null;
        boolean actual = o2T._validateEnumObject(expectedField, actualClazz);
        TestResultHelper.verifyCollectorNoError(o2T, true, actual);
    }

    protected Field prepareFieldExistsInClazz() {
        return prepareField(ITEMNAME_EXISTS);
    }

    protected Field prepareFieldNotExistsInClazz() {
        return prepareField(ITEMNAME_NOT_EXISTS);
    }

    protected Field prepareFieldWithNoInName() {
        return prepareField(ITEMNAME_WITH_NUMBER);
    }

    protected Field prepareFieldWithOutNoInName() {
        return prepareField(ITEMNAME_WITHOUT_NUMBER);
    }
}
