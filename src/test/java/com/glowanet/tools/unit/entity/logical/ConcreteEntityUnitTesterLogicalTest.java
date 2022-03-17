package com.glowanet.tools.unit.entity.logical;

import com.glowanet.tools.unit.entity.AbstractEntityUnitTesterCommon;
import com.glowanet.tools.unit.entity.data.DataEntityUnitTesterLogicalEquals;
import com.glowanet.util.junit.TestResultHelper;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ConcreteEntityUnitTesterLogicalTest extends AbstractEntityUnitTesterCommon {

    protected ConcreteEntityUnitTesterLogical<?> entityUnitTester;

    @Before
    public void setUp() {
        initWithEqual();
    }


    public void initWithEqual() {
        entityUnitTester = new ConcreteEntityUnitTesterLogical(DataEntityUnitTesterLogicalEquals.class);
        entityUnitTester.setUp();
    }

    @Test
    public void testCreateObject2Test_return_newCreatedObject() {
        Object actual = entityUnitTester.createObject2Test();

        TestResultHelper.verifyInstance(actual, DataEntityUnitTesterLogicalEquals.class);
    }

    @Test
    public void testGetObject2Test_return_currentUsedObject() {
        Object actual = entityUnitTester.getObject2Test();

        TestResultHelper.verifyInstance(actual, DataEntityUnitTesterLogicalEquals.class);
    }

//    @Test
//    public void testSetEntity_return_null() {
//        Object before = entityUnitTester.getObject2Test();
//        assertThat(before, instanceOf(DataEntityUnitTesterLogicalEquals.class));
//
//        entityUnitTester.setObject2Test(null);
//        Object actual = entityUnitTester.getObject2Test();
//
//        TestResultHelper.verifyNull(actual);
//    }


    @Test
    public void testTestEqualsLogicalAreTheSame_with_logicalEquals_defaultCompare_raise_noException() {
        entityUnitTester._setCheckLogicalEqualsOnly(false);
        assertThat(entityUnitTester._isCheckLogicalEqualsOnly(), is(false));

        entityUnitTester.testEqualsLogicalAreTheSame();

        TestResultHelper.verifyCollector(entityUnitTester, TestResultHelper.WITH_ERROR);
    }

    @Test
    public void testTestEqualsLogicalAreTheSame_with_logicalEquals_logicalCompare_raise_exception() {
        entityUnitTester._setCheckLogicalEqualsOnly(true);
        assertThat(entityUnitTester._isCheckLogicalEqualsOnly(), is(true));

        entityUnitTester.testEqualsLogicalAreTheSame();

        TestResultHelper.verifyCollector(entityUnitTester, TestResultHelper.NO_ERROR);
    }
}
