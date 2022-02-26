package com.glowanet.tools.unit.entity.logical;

import com.glowanet.tools.unit.entity.AbstractEntityUnitTesterCommon;
import com.glowanet.tools.unit.entity.data.DataEntityUnitTesterLogicalEquals;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

public class AbstractEntityUnitTesterConcreteLogicalEqualsTest extends AbstractEntityUnitTesterCommon {

    protected AbstractEntityUnitTesterConcreteLogicalEquals<?> entityUnitTester;

    @Before
    public void setUp() {
        initWithEqual();
    }


    public void initWithEqual() {
        entityUnitTester = new AbstractEntityUnitTesterConcreteLogicalEquals(DataEntityUnitTesterLogicalEquals.class);
        entityUnitTester.setUp();
    }

    @Test
    public void testCreateObject2Test_return_newCreatedObject() {
        Object actual = entityUnitTester.createObject2Test();

        verifyInstance(actual, DataEntityUnitTesterLogicalEquals.class);
    }

    @Test
    public void testGetObject2Test_return_currentUsedObject() {
        Object actual = entityUnitTester.getObject2Test();

        verifyInstance(actual, DataEntityUnitTesterLogicalEquals.class);
    }

    @Test
    public void testSetEntity_return_null() {
        Object before = entityUnitTester.getObject2Test();
        assertThat(before, instanceOf(DataEntityUnitTesterLogicalEquals.class));

        entityUnitTester.setObject2Test(null);
        Object actual = entityUnitTester.getObject2Test();

        verifyNull(actual);
    }


    @Test
    public void testTestEqualsLogicalAreTheSame_with_logicalEquals_defaultCompare_raise_noException() {
        entityUnitTester._setCheckLogicalEqualsOnly(false);
        assertThat(entityUnitTester._isCheckLogicalEqualsOnly(), is(false));

        entityUnitTester.testEqualsLogicalAreTheSame();

        verifyCollector(entityUnitTester, WITH_ERROR);
    }

    @Test
    public void testTestEqualsLogicalAreTheSame_with_logicalEquals_logicalCompare_raise_exception() {
        entityUnitTester._setCheckLogicalEqualsOnly(true);
        assertThat(entityUnitTester._isCheckLogicalEqualsOnly(), is(true));

        entityUnitTester.testEqualsLogicalAreTheSame();

        verifyCollector(entityUnitTester, NO_ERROR);
    }
}
