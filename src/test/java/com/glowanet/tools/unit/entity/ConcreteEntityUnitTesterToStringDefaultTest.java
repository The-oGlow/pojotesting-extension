package com.glowanet.tools.unit.entity;

import com.glowanet.tools.unit.entity.data.DataEntityUnitTester;
import com.glowanet.tools.unit.entity.data.DataEntityUnitTesterToString;
import com.glowanet.util.junit.TestResultHelper;
import com.glowanet.util.reflect.ReflectionHelper;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * An junit test class, which verifies, that the {@code AbstractEntityUnitTester} is working correctly.
 *
 * @param <T> the type of the entity which will be tested
 */
public class ConcreteEntityUnitTesterToStringDefaultTest<
        T extends DataEntityUnitTester> extends SimulationEntityTesterTest<T> {

    // static fields
    protected static final String ALL_FIELDS_TO_IGNORE_FOR_TO_STRING = "allFieldsToIgnoreForToString";
    protected static final String ALL_FIELDS_DENIED_FOR_TO_STRING    = "allFieldsDeniedForToString";
// end - static fields

    // methods
    @Test
    public void testAllFieldsDeniedForToString_defaultToString_return_emptyList() {
        SimulationEntityTester<T> entityUnitTester = prepareEntityUnitTester((Class<T>) DataEntityUnitTester.class);
        Collection<String> actual = ReflectionHelper.readField(ALL_FIELDS_DENIED_FOR_TO_STRING, entityUnitTester);

        TestResultHelper.verifyNoNull(actual);
        assertThat(actual, TestResultHelper.EMPTY_LIST);
    }

    @Test
    public void testAllFieldsToIgnoreForToString_defaultToString_return_listWithOneElement() {
        SimulationEntityTester<T> entityUnitTester = prepareEntityUnitTester((Class<T>) DataEntityUnitTester.class);
        Collection<String> actual = ReflectionHelper.readField(ALL_FIELDS_TO_IGNORE_FOR_TO_STRING, entityUnitTester);

        TestResultHelper.verifyNoNull(actual);
        assertThat(actual, TestResultHelper.SINGLE_LIST);
    }

    @Test
    public void testFieldsDeniedForToString_defaultToString_return_emptyList() {
        SimulationEntityTester<T> entityUnitTester = prepareEntityUnitTester((Class<T>) DataEntityUnitTester.class);
        List<String> actual = entityUnitTester.fieldsDeniedForToString();

        TestResultHelper.verifyNoNull(actual);
        assertThat(actual, TestResultHelper.EMPTY_LIST);
    }

    @Test
    public void testFieldsToIgnoreForToString_defaultToString_return_emptyList() {
        SimulationEntityTester<T> entityUnitTester = prepareEntityUnitTester((Class<T>) DataEntityUnitTester.class);
        List<String> actual = entityUnitTester.fieldsToIgnoreForToString();

        TestResultHelper.verifyNoNull(actual);
        assertThat(actual, TestResultHelper.EMPTY_LIST);
    }

    @Test
    public void testTestToStringWithValues_defaultToString_raise_oneException() {
        SimulationEntityTester<T> entityUnitTester = prepareEntityUnitTester((Class<T>) DataEntityUnitTester.class);
        entityUnitTester.testToStringWithValues();

        TestResultHelper.verifyCollector(entityUnitTester, TestResultHelper.WITH_ERROR);
    }

    @Test
    public void testTestToString_defaultToString_raise_twoExceptions() {
        SimulationEntityTester<T> entityUnitTester = prepareEntityUnitTester((Class<T>) DataEntityUnitTester.class);
        entityUnitTester.testToString();

        TestResultHelper.verifyCollector(entityUnitTester, TestResultHelper.TWO_ERROR);
    }

    @Override
    protected SimulationEntityTester<T> prepareEntityUnitTester(Class<T> typeOfO2T) {
        return new ConcreteEntityUnitTesterToStringDefault<>(typeOfO2T, prepareTheCreator(typeOfO2T));
    }

    @Override
    protected CallTheCreator<T> prepareTheCreator(Class<T> typeOfO2T) {
        return new CallTheCreator<>() {
            // methods
            /* methods */
            @Override
            public T call() {
                T newO2T = null;
                if (DataEntityUnitTester.class.equals(typeOfO2T)) {
                    newO2T = (T) new DataEntityUnitTester();
                } else if (DataEntityUnitTesterToString.class.equals(typeOfO2T)) {
                    newO2T = (T) new DataEntityUnitTesterToString();
                }
                return newO2T;
            }
// end - methods
        };
    }
// end - methods
}
