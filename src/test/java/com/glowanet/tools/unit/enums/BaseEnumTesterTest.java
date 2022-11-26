package com.glowanet.tools.unit.enums;

import com.glowanet.data.enums.DataEnums;
import com.glowanet.util.junit.TestResultHelper;
import com.glowanet.util.junit.rules.ErrorCollectorExt;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.glowanet.data.enums.DataEnums.ITEMNAME_EXISTS;
import static com.glowanet.data.enums.DataEnums.ITEMNAME_NOT_EXISTS;
import static com.glowanet.data.enums.DataEnums.ITEMNAME_WITHOUT_NUMBER;
import static com.glowanet.data.enums.DataEnums.ITEMNAME_WITH_NUMBER;
import static com.glowanet.data.enums.DataEnums.ITEM_WITHOUT_NUMBER;
import static com.glowanet.data.enums.DataEnums.ITEM_WITH_NUMBER;
import static com.glowanet.util.junit.TestResultHelper.WITH_ERROR;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

/**
 * An abstract clazz which operates as base clazz for the junit tests.
 *
 * @param <E> the type of the enum which will be tested
 */
public abstract class BaseEnumTesterTest<E> extends CommonEnumTesterTest<E> {

    protected BaseEnumTesterTest(Class<E> typeOfE) {
        super(typeOfE);
    }

    // methods
    @Test
    public void testCheckIgnoredFields_existingField_return_false_raise_noException() {
        BaseEnumTester<E> o2T = prepareEnumTester();
        Field expectedField = prepareFieldExistsInClazz();
        assertThat(expectedField, notNullValue());
        boolean actual = o2T._checkIgnoredFields(expectedField);
        TestResultHelper.verifyCollector(o2T, WITH_ERROR, false, actual);
    }

    @Test
    public void testCheckIgnoredFields_null_return_false_raise_noException() {
        BaseEnumTester<E> o2T = prepareEnumTester();
        boolean actual = o2T._checkIgnoredFields(null);
        TestResultHelper.verifyCollectorNoError(o2T, false, actual);
    }

    @Test
    public void testEnumObjectsToIgnoreForCode_return_emptyList() {
        BaseEnumTester<E> o2T = prepareEnumTester();
        List<String> actual = o2T._enumObjectsToIgnoreForCode();
        assertThat(actual, notNullValue());
        assertThat(actual, hasSize(0));
    }

    @Test
    public void testGetNameCheckType_return_CF() {
        BaseEnumTester<E> o2T = prepareEnumTester();
        NameCheckTypeEnum actual = o2T._getNameCheckType();

        ErrorCollectorExt verifyCollector = new ErrorCollectorExt();
        verifyCollector.checkThat(actual, equalTo(EnumUnitTester.DEFAULT_NAME_CHECK_TYPE));
        TestResultHelper.verifyCollector(verifyCollector, lessThanOrEqualTo(1));
    }

    @Test
    public void testGetObject2Test_return_null() {
        BaseEnumTester<E> o2T = prepareEnumTester();
        Object actual = o2T.getObject2Test();
        Object actual2 = o2T.getObject2Test();
        assertThat(actual, nullValue());
        assertThat(actual2, nullValue());
    }

    @Test
    public void testIsCodeCheckEnabled_return_true() {
        if (assumeParameterCodeCheckEnabledIsTrue()) {
            BaseEnumTester<E> o2T = prepareEnumTester();
            boolean actual = o2T._isCodeCheckEnabled();
            assertThat(actual, equalTo(EnumUnitTester.DEFAULT_CODE_CHECK_ENABLED));
        }
    }

    @Test
    public void testIsInIgnoreListForCode_existingField_return_false_raise_noException() {
        BaseEnumTester<E> o2T = prepareEnumTester();
        boolean actual = o2T._isInIgnoreListForCode(ITEMNAME_EXISTS);
        TestResultHelper.verifyCollectorNoError(o2T, false, actual);
    }

    @Test
    public void testIsInIgnoreListForCode_null_return_false_raise_noException() {
        BaseEnumTester<E> o2T = prepareEnumTester();
        boolean actual = o2T._isInIgnoreListForCode(null);
        TestResultHelper.verifyCollectorNoError(o2T, false, actual);
    }

    @Test
    public void testSetCodeCheckEnabled_return_false() {
        if (assumeParameterCodeCheckEnabledIsTrue()) {
            BaseEnumTester<E> o2T = prepareEnumTester();
            assertThat(o2T._isCodeCheckEnabled(), equalTo(EnumUnitTester.DEFAULT_CODE_CHECK_ENABLED));
            o2T._setCodeCheckEnabled(false);
            assertThat(o2T._isCodeCheckEnabled(), equalTo(false));
        }
    }

    @Test
    public void testSetNameCheckType_return_CIF() {
        BaseEnumTester<E> o2T = prepareEnumTester();
        NameCheckTypeEnum expected = NameCheckTypeEnum.CIF;

        ErrorCollectorExt verifyCollector = new ErrorCollectorExt();
        verifyCollector.checkThat(o2T._getNameCheckType(), equalTo(EnumUnitTester.DEFAULT_NAME_CHECK_TYPE));

        o2T._setNameCheckType(expected);
        assertThat(o2T._getNameCheckType(), equalTo(expected));
        TestResultHelper.verifyCollector(verifyCollector, lessThanOrEqualTo(1));
    }

    @Test
    public void testValidateAllEnumObjects_raise_noException() {
        if (assumeParameterCodeCheckEnabledIsTrue()) {
            BaseEnumTester<E> o2T = prepareEnumTester();
            o2T.testValidateAllEnumObjects();
            TestResultHelper.verifyCollector(o2T, DataEnums.EXCEPTION_SUM);
        }
    }

    @Test
    public void testValidateAllEnumObjects_raise_exception() {
        if (assumeParameterCodeCheckEnabledIsFalse()) {
            BaseEnumTester<E> o2T = prepareEnumTester();
            o2T.testValidateAllEnumObjects();
            TestResultHelper.verifyCollectorNoError(o2T);
        }
    }

    @Test
    public void testValidateEnumObjectCode_itemNameWithNumber_instance_return_true_raise_oneException() {
        BaseEnumTester<E> o2T = prepareEnumTester();
        Field expectedField = prepareFieldWithNoInName();
        E actualInstance = (E) ITEM_WITH_NUMBER;
        boolean actual = o2T._validateEnumObjectCode(expectedField, actualInstance);
        TestResultHelper.verifyCollector(o2T, WITH_ERROR, false, actual);
    }

    @Test
    public void testValidateEnumObjectCode_itemNameWithNumber_wrongInstance_return_true_raise_oneException() {
        BaseEnumTester<E> o2T = prepareEnumTester();
        Field expectedField = prepareFieldWithNoInName();
        E actualInstance = (E) ITEM_WITHOUT_NUMBER;
        boolean actual = o2T._validateEnumObjectCode(expectedField, actualInstance);
        TestResultHelper.verifyCollector(o2T, WITH_ERROR, false, actual);
    }

    @Test
    public void testValidateEnumObjectName_itemNameNoNumber_instance_return_true_raise_noException() {
        BaseEnumTester<E> o2T = prepareEnumTester();
        Field expectedField = prepareFieldWithOutNoInName();
        E actualInstance = (E) ITEM_WITHOUT_NUMBER;
        boolean actual = o2T._validateEnumObjectName(expectedField, actualInstance);
        TestResultHelper.verifyCollectorNoError(o2T, true, actual);
    }

    @Test
    public void testValidateEnumObjectName_itemNameWithNumber_instance_return_true_raise_noException() {
        BaseEnumTester<E> o2T = prepareEnumTester();
        Field expectedField = prepareFieldWithNoInName();
        E actualInstance = (E) ITEM_WITH_NUMBER;
        boolean actual = o2T._validateEnumObjectName(expectedField, actualInstance);
        TestResultHelper.verifyCollectorNoError(o2T, true, actual);
    }

    @Test
    public void testValidateEnumObjectName_itemNameWithNumber_wrongInstance_return_true_raise_noException() {
        BaseEnumTester<E> o2T = prepareEnumTester();
        Field expectedField = prepareFieldWithNoInName();
        E actualInstance = (E) ITEM_WITHOUT_NUMBER;
        boolean actual = o2T._validateEnumObjectName(expectedField, actualInstance);
        TestResultHelper.verifyCollector(o2T, WITH_ERROR, false, actual);
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
// end - methods
}
