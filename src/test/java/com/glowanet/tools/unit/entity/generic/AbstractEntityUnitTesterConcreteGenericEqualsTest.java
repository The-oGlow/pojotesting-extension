package com.glowanet.tools.unit.entity.generic;

import com.glowanet.tools.unit.entity.AbstractEntityUnitTesterCommon;
import com.glowanet.tools.unit.entity.data.DataEntityUnitTesterGenericEquals;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

public class AbstractEntityUnitTesterConcreteGenericEqualsTest extends AbstractEntityUnitTesterCommon {

    protected AbstractEntityUnitTesterConcreteGenericEquals<?> entityUnitTester;

    @Before
    public void setUp() {
        entityUnitTester = new AbstractEntityUnitTesterConcreteGenericEquals<>(DataEntityUnitTesterGenericEquals.class);
        entityUnitTester.setUp();
    }

    @Test
    public void testCreateObject2Test_return_newCreatedObject() {
        Object actual = entityUnitTester.createObject2Test();

        verifyInstance(actual, DataEntityUnitTesterGenericEquals.class);
    }

    @Test
    public void testGetObject2Test_return_currentUsedObject() {
        Object actual = entityUnitTester.getObject2Test();

        verifyInstance(actual, DataEntityUnitTesterGenericEquals.class);
    }

    @Test
    public void testSetEntity_return_null() {
        Object before = entityUnitTester.getObject2Test();
        assertThat(before, instanceOf(DataEntityUnitTesterGenericEquals.class));

        entityUnitTester.setObject2Test(null);
        Object actual = entityUnitTester.getObject2Test();

        verifyNull(actual);
    }

    @Test
    public void testTestAllGetterAccessiblewith_raise_noException() {
        entityUnitTester.testAllGetterAccessible();

        verifyCollector(entityUnitTester, NO_ERROR);
    }

    @Test
    public void testTestAllSetterAccessible_raise_noException() {
        entityUnitTester.testAllSetterAccessible();

        verifyCollector(entityUnitTester, NO_ERROR);
    }

    @Test
    public void testTestGetterSetterCollaboration_raise_noException() {
        entityUnitTester.testGetterSetterCollaboration();

        verifyCollector(entityUnitTester, NO_ERROR);
    }

    @Test
    public void testTestHashcodeOtherThan0_raise_noException() {
        entityUnitTester.testHashcodeOtherThan0();

        verifyCollector(entityUnitTester, NO_ERROR);
    }

    @Test
    public void testTestEqualsWithNull_raise_noException() {
        entityUnitTester.testEqualsWithNull();

        verifyCollector(entityUnitTester, NO_ERROR);
    }

    @Test
    public void testTestEqualsWithItself_raise_noException() {
        entityUnitTester.testEqualsWithItself();

        verifyCollector(entityUnitTester, NO_ERROR);
    }

    @Test
    public void testTestEqualsLogicalAreTheSame_with_defaultEquals_defaultCompare_raise_noException() {
        entityUnitTester._setCheckLogicalEqualsOnly(false);
        assertThat(entityUnitTester._isCheckLogicalEqualsOnly(), is(false));

        entityUnitTester.testEqualsLogicalAreTheSame();

        verifyCollector(entityUnitTester, NO_ERROR);
    }

    @Test
    public void testTestEqualsLogicalAreTheSame_with_defaultEquals_logicalCompare_raise_exception() {
        entityUnitTester._setCheckLogicalEqualsOnly(true);
        assertThat(entityUnitTester._isCheckLogicalEqualsOnly(), is(true));

        entityUnitTester.testEqualsLogicalAreTheSame();

        verifyCollector(entityUnitTester, WITH_ERROR);
    }

}
