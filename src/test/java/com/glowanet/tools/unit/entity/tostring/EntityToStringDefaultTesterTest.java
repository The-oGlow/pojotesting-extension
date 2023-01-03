package com.glowanet.tools.unit.entity.tostring;

import com.glowanet.data.entity.tostring.DataEntityToString;
import com.glowanet.tools.unit.entity.BaseEntityTester;
import com.glowanet.tools.unit.entity.BaseEntityTesterTest;
import com.glowanet.util.junit.TestResultHelper;
import com.glowanet.util.reflect.ReflectionHelper;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * A junit test clazz, which verifies, that the {@code AbstractEntityUnitTester} is working correctly.
 *
 * @param <T> the type of the entity which will be tested
 */
public class EntityToStringDefaultTesterTest<T extends DataEntityToString> extends BaseEntityTesterTest<T> {

    protected static final String ALL_FIELDS_TO_IGNORE_FOR_TO_STRING = "allFieldsToIgnoreForToString";
    protected static final String ALL_FIELDS_DENIED_FOR_TO_STRING    = "allFieldsDeniedForToString";

    @Test
    public void testAllFieldsDeniedForToString_defaultToString_return_emptyList() {
        BaseEntityTester<T> entityUnitTester = prepareEntityUnitTester((Class<T>) DataEntityToString.class);
        Collection<String> actual = ReflectionHelper.readField(ALL_FIELDS_DENIED_FOR_TO_STRING, entityUnitTester);

        TestResultHelper.verifyNoNull(actual);
        assertThat(actual, TestResultHelper.EMPTY_LIST);
    }

    @Test
    public void testAllFieldsToIgnoreForToString_defaultToString_return_listWithOneElement() {
        BaseEntityTester<T> entityUnitTester = prepareEntityUnitTester((Class<T>) DataEntityToString.class);
        Collection<String> actual = ReflectionHelper.readField(ALL_FIELDS_TO_IGNORE_FOR_TO_STRING, entityUnitTester);

        TestResultHelper.verifyNoNull(actual);
        assertThat(actual, TestResultHelper.SINGLE_LIST);
    }

    @Test
    public void testFieldsDeniedForToString_defaultToString_return_emptyList() {
        BaseEntityTester<T> entityUnitTester = prepareEntityUnitTester((Class<T>) DataEntityToString.class);
        List<String> actual = entityUnitTester._fieldsDeniedForToString();

        TestResultHelper.verifyNoNull(actual);
        assertThat(actual, TestResultHelper.EMPTY_LIST);
    }

    @Test
    public void testFieldsToIgnoreForToString_defaultToString_return_emptyList() {
        BaseEntityTester<T> entityUnitTester = prepareEntityUnitTester((Class<T>) DataEntityToString.class);
        List<String> actual = entityUnitTester._fieldsToIgnoreForToString();

        TestResultHelper.verifyNoNull(actual);
        assertThat(actual, TestResultHelper.EMPTY_LIST);
    }

    @Test
    public void testTestToStringWithValues_defaultToString_raise_oneException() {
        BaseEntityTester<T> entityUnitTester = prepareEntityUnitTester((Class<T>) DataEntityToString.class);
        entityUnitTester.testToStringWithValues();

        TestResultHelper.verifyCollector(entityUnitTester, TestResultHelper.WITH_ERROR);
    }

    @Test
    public void testTestToString_defaultToString_raise_twoExceptions() {
        BaseEntityTester<T> entityUnitTester = prepareEntityUnitTester((Class<T>) DataEntityToString.class);
        entityUnitTester.testToString();

        TestResultHelper.verifyCollector(entityUnitTester, TestResultHelper.TWO_ERROR);
    }

    @Override
    protected BaseEntityTester<T> prepareEntityUnitTester(Class<T> typeOfO2T) {
        return new EntityToStringDefaultTester<>();
    }
}
