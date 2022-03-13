package com.glowanet.tools.unit.entity;

import com.glowanet.tools.unit.entity.data.DataEntityUnitTester;
import com.glowanet.tools.unit.entity.data.DataEntityUnitTesterToString;
import com.glowanet.util.junit.TestResultHelper;
import com.glowanet.util.reflect.ReflectionHelper;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class ConcreteEntityUnitTesterToStringTest extends AbstractEntityUnitTesterCommon {

    protected ConcreteEntityUnitTesterToStringDefault prepareEntityTesterWithClazzDefaultToString() {
        ConcreteEntityUnitTesterToStringDefault entityUnitTester = new ConcreteEntityUnitTesterToStringDefault(DataEntityUnitTester.class);
        entityUnitTester.setUp();
        return entityUnitTester;
    }

    protected ConcreteEntityUnitTesterToStringImplemented prepareEntityTesterWithClazzImplementedToString() {
        ConcreteEntityUnitTesterToStringImplemented entityUnitTester = new ConcreteEntityUnitTesterToStringImplemented(DataEntityUnitTesterToString.class);
        entityUnitTester.setUp();
        return entityUnitTester;
    }

    @Test
    public void testTestToString_defaultToString_raise_twoExceptions() {
        ConcreteEntityUnitTesterToStringDefault entityUnitTester = prepareEntityTesterWithClazzDefaultToString();
        entityUnitTester.testToString();

        TestResultHelper.verifyCollector(entityUnitTester, TestResultHelper.TWO_ERROR);
    }

    @Test
    public void testTestToString_withToString_raise_noException() {
        ConcreteEntityUnitTesterToStringImplemented entityUnitTester = prepareEntityTesterWithClazzImplementedToString();
        entityUnitTester.testToString();

        TestResultHelper.verifyCollector(entityUnitTester, TestResultHelper.NO_ERROR);
    }

    @Test
    public void testTestToStringWithValues_defaultToString_raise_oneException() {
        ConcreteEntityUnitTesterToStringDefault entityUnitTester = prepareEntityTesterWithClazzDefaultToString();
        entityUnitTester.testToStringWithValues();

        TestResultHelper.verifyCollector(entityUnitTester, TestResultHelper.WITH_ERROR);
    }

    @Test
    public void testTestToStringWithValues_withToString_raise_oneException() {
        ConcreteEntityUnitTesterToStringImplemented entityUnitTester = prepareEntityTesterWithClazzImplementedToString();
        entityUnitTester.testToStringWithValues();

        TestResultHelper.verifyCollector(entityUnitTester, TestResultHelper.WITH_ERROR);
    }

    @Test
    public void testFieldsDeniedForToString_defaultToString_return_emptyList() {
        ConcreteEntityUnitTesterToStringDefault entityUnitTester = prepareEntityTesterWithClazzDefaultToString();
        List<String> actual = entityUnitTester.fieldsDeniedForToString();

        TestResultHelper.verifyNoNull(actual);
        assertThat(actual, TestResultHelper.EMPTY_LIST);
    }

    @Test
    public void testAllFieldsDeniedForToString_defaultToString_return_emptyList() {
        ConcreteEntityUnitTesterToStringDefault entityUnitTester = prepareEntityTesterWithClazzDefaultToString();
        Collection<String> actual = ReflectionHelper.readField("allFieldsDeniedForToString", entityUnitTester);

        TestResultHelper.verifyNoNull(actual);
        assertThat(actual, TestResultHelper.EMPTY_LIST);
    }

    @Test
    public void testFieldsToIgnoreForToString_defaultToString_return_emptyList() {
        ConcreteEntityUnitTesterToStringDefault entityUnitTester = prepareEntityTesterWithClazzDefaultToString();
        List<String> actual = entityUnitTester.fieldsToIgnoreForToString();

        TestResultHelper.verifyNoNull(actual);
        assertThat(actual, TestResultHelper.EMPTY_LIST);
    }

    @Test
    public void testAllFieldsToIgnoreForToString_defaultToString_return_listWithOneElement() {
        ConcreteEntityUnitTesterToStringDefault entityUnitTester = prepareEntityTesterWithClazzDefaultToString();
        Collection<String> actual = ReflectionHelper.readField("allFieldsToIgnoreForToString", entityUnitTester);

        TestResultHelper.verifyNoNull(actual);
        assertThat(actual, TestResultHelper.SINGLE_LIST);
    }

}
