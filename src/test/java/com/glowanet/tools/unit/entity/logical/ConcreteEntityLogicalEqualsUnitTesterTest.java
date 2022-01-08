package com.glowanet.tools.unit.entity.logical;

import com.glowanet.tools.unit.entity.AbstractCommonEntityEqualsUnitTester;
import com.glowanet.util.reflect.ReflectionHelper;
import org.junit.Before;
import org.junit.Test;

import java.beans.PropertyDescriptor;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

public class ConcreteEntityLogicalEqualsUnitTesterTest extends AbstractCommonEntityEqualsUnitTester {

    protected ConcreteEntityLogicalEqualsUnitTester<?> entityUnitTester;

    @Before
    public void setUp() {
        initWithEqual();
    }


    public void initWithEqual() {
        entityUnitTester = new ConcreteEntityLogicalEqualsUnitTester(ConcreteEntityLogicalEquals.class);
        entityUnitTester.setUp();
    }

    @Test
    public void test_createEntity_return_entity() {
        Object actual = entityUnitTester.createObject2Test();

        assertThat(actual, notNullValue());
        assertThat(actual, instanceOf(ConcreteEntityLogicalEquals.class));
    }

    @Test
    public void test_getEntity_return_null() {
        entityUnitTester = new ConcreteEntityLogicalEqualsUnitTester(ConcreteEntityLogicalEquals.class);
        Object actual = entityUnitTester.getObject2Test();

        assertThat(actual, nullValue());
    }

    @Test
    public void test_setEntity_return_value() {
        Object before = entityUnitTester.getObject2Test();
        assertThat(before, instanceOf(ConcreteEntityLogicalEquals.class));

        entityUnitTester.setObject2Test(null);
        Object actual = entityUnitTester.getObject2Test();

        assertThat(actual, nullValue());
    }

    @Test
    public void test_findGetter_return_list() {
        List<PropertyDescriptor> actual = entityUnitTester._findGetter();

        assertThat(actual, hasSize(3));
    }

    @Test
    public void test_findSetter_return_emptyList() {
        List<PropertyDescriptor> actual = entityUnitTester._findSetter();

        assertThat(actual, notNullValue());
        assertThat(actual, hasSize(0));
    }

    @Test
    public void test_fieldsDeniedForToString_return_emptyList() {
        List<String> actual = entityUnitTester._fieldsDeniedForToString();

        assertThat(actual, notNullValue());
        assertThat(actual, hasSize(0));
    }

    @Test
    public void test_allFieldsDeniedForToString_return_emptyList() {
        Collection<String> actual = ReflectionHelper.readField("allFieldsDeniedForToString", entityUnitTester);

        assertThat(actual, notNullValue());
        assertThat(actual, hasSize(0));
    }

    @Test
    public void test_fieldsToIgnoreForToString_return_emptyList() {
        List<String> actual = entityUnitTester._fieldsToIgnoreForToString();

        assertThat(actual, notNullValue());
        assertThat(actual, hasSize(0));
    }

    @Test
    public void test_allFieldsToIgnoreForToString_return_oneElement() {
        Collection<String> actual = ReflectionHelper.readField("allFieldsToIgnoreForToString", entityUnitTester);

        assertThat(actual, notNullValue());
        assertThat(actual, hasSize(1));
    }

    @Test
    public void test_testAllGetterAccessiblewith_raise_noException() {
        entityUnitTester.testAllGetterAccessible();

        verifyCollector(entityUnitTester, 0);
    }

    @Test
    public void test_testAllSetterAccessible_raise_noException() {
        entityUnitTester.testAllSetterAccessible();

        verifyCollector(entityUnitTester, 0);
    }

    @Test
    public void test_testGetterSetterCollaboration_raise_noException() {
        entityUnitTester.testGetterSetterCollaboration();

        verifyCollector(entityUnitTester, 0);
    }

    @Test
    public void test_testToString_raise_2Exception() {
        entityUnitTester.testToString();

        verifyCollector(entityUnitTester, 2);
    }

    @Test
    public void test_testToStringWithValues_raise_exception() {
        entityUnitTester.testToStringWithValues();

        verifyCollector(entityUnitTester, 1);
    }

    @Test
    public void test_testHashcodeOtherThan0_raise_exception() {
        verifyException(() -> entityUnitTester.testHashcodeOtherThan0(), AssertionError.class);
    }

    @Test
    public void test_testEqualsLogicalAreTheSame_with_logicalEquals_defaultCompare_raise_exception() {
        assertThat(entityUnitTester._isCheckLogicalEqualsOnly(), is(false));
        verifyException(() -> entityUnitTester.testEqualsLogicalAreTheSame(), AssertionError.class);
    }


    @Test
    public void test_testEqualsLogicalAreTheSame_with_logicalEquals_logicalCompare_raise_noException() {
        entityUnitTester._setCheckLogicalEqualsOnly(true);
        assertThat(entityUnitTester._isCheckLogicalEqualsOnly(), is(true));

        entityUnitTester.testEqualsLogicalAreTheSame();
    }


    @Test
    public void test_testEqualsWithNull_raise_noException() {
        entityUnitTester.testEqualsWithNull();
    }


    @Test
    public void test_testEqualsWithItself_raise_noException() {
        entityUnitTester.testEqualsWithItself();
    }

}
