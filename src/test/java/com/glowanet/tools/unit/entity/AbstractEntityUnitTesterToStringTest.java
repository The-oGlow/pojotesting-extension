package com.glowanet.tools.unit.entity;

import com.glowanet.tools.unit.entity.data.DataEntityUnitTesterGenericEquals;
import com.glowanet.tools.unit.entity.data.DataEntityUnitTesterGenericEqualsToString;
import com.glowanet.util.reflect.ReflectionHelper;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class AbstractEntityUnitTesterToStringTest extends AbstractEntityUnitTesterCommon {

    @SuppressWarnings("UnconstructableJUnitTestCase")
    static class EntityTesterWithClazzDefaultToString extends AbstractEntityUnitTester<DataEntityUnitTesterGenericEquals> {
        protected EntityTesterWithClazzDefaultToString(Class<DataEntityUnitTesterGenericEquals> typeOfT) {
            super(typeOfT);
        }

        @Override
        protected DataEntityUnitTesterGenericEquals createObject2Test() {
            return new DataEntityUnitTesterGenericEquals();
        }
    }

    @SuppressWarnings("UnconstructableJUnitTestCase")
    static class EntityTesterWithClazzImplementedToString extends AbstractEntityUnitTester<DataEntityUnitTesterGenericEqualsToString> {
        protected EntityTesterWithClazzImplementedToString(Class<DataEntityUnitTesterGenericEqualsToString> typeOfT) {
            super(typeOfT);
        }

        @Override
        protected DataEntityUnitTesterGenericEqualsToString createObject2Test() {
            return new DataEntityUnitTesterGenericEqualsToString();
        }
    }

    protected EntityTesterWithClazzDefaultToString prepareEntityTesterWithClazzDefaultToString() {
        EntityTesterWithClazzDefaultToString entityUnitTester = new EntityTesterWithClazzDefaultToString(DataEntityUnitTesterGenericEquals.class);
        entityUnitTester.setUp();
        return entityUnitTester;
    }

    protected EntityTesterWithClazzImplementedToString prepareEntityTesterWithClazzImplementedToString() {
        EntityTesterWithClazzImplementedToString entityUnitTester = new EntityTesterWithClazzImplementedToString(DataEntityUnitTesterGenericEqualsToString.class);
        entityUnitTester.setUp();
        return entityUnitTester;
    }

    @Test
    public void testTestToString_defaultToString_raise_twoExceptions() {
        EntityTesterWithClazzDefaultToString entityUnitTester = prepareEntityTesterWithClazzDefaultToString();
        entityUnitTester.testToString();

        verifyCollector(entityUnitTester, TWO_ERROR);
    }

    @Test
    public void testTestToString_withToString_raise_noException() {
        EntityTesterWithClazzImplementedToString entityUnitTester = prepareEntityTesterWithClazzImplementedToString();
        entityUnitTester.testToString();

        verifyCollector(entityUnitTester, NO_ERROR);
    }

    @Test
    public void testTestToStringWithValues_defaultToString_raise_oneException() {
        EntityTesterWithClazzDefaultToString entityUnitTester = prepareEntityTesterWithClazzDefaultToString();
        entityUnitTester.testToStringWithValues();

        verifyCollector(entityUnitTester, WITH_ERROR);
    }

    @Test
    public void testTestToStringWithValues_withToString_raise_oneException() {
        EntityTesterWithClazzImplementedToString entityUnitTester = prepareEntityTesterWithClazzImplementedToString();
        entityUnitTester.testToStringWithValues();

        verifyCollector(entityUnitTester, WITH_ERROR);
    }

    @Test
    public void testFieldsDeniedForToString_defaultToString_return_emptyList() {
        EntityTesterWithClazzDefaultToString entityUnitTester = prepareEntityTesterWithClazzDefaultToString();
        List<String> actual = entityUnitTester.fieldsDeniedForToString();

        verifyNoNull(actual);
        assertThat(actual, EMPTY_LIST);
    }

    @Test
    public void testAllFieldsDeniedForToString_defaultToString_return_emptyList() {
        EntityTesterWithClazzDefaultToString entityUnitTester = prepareEntityTesterWithClazzDefaultToString();
        Collection<String> actual = ReflectionHelper.readField("allFieldsDeniedForToString", entityUnitTester);

        verifyNoNull(actual);
        assertThat(actual, EMPTY_LIST);
    }

    @Test
    public void testFieldsToIgnoreForToString_defaultToString_return_emptyList() {
        EntityTesterWithClazzDefaultToString entityUnitTester = prepareEntityTesterWithClazzDefaultToString();
        List<String> actual = entityUnitTester.fieldsToIgnoreForToString();

        verifyNoNull(actual);
        assertThat(actual, EMPTY_LIST);
    }

    @Test
    public void testAllFieldsToIgnoreForToString_defaultToString_return_listWithOneElement() {
        EntityTesterWithClazzDefaultToString entityUnitTester = prepareEntityTesterWithClazzDefaultToString();
        Collection<String> actual = ReflectionHelper.readField("allFieldsToIgnoreForToString", entityUnitTester);

        verifyNoNull(actual);
        assertThat(actual, SINGLE_LIST);
    }

}
