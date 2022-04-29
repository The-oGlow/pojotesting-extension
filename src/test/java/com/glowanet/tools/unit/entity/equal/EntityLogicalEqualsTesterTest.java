package com.glowanet.tools.unit.entity.equal;

import com.glowanet.data.entity.equal.DataEntityLogicalEquals;
import com.glowanet.tools.unit.entity.BaseEntityTester;
import com.glowanet.util.junit.TestResultHelper;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * A junit test class, which verifies, that the {@code AbstractEntityUnitTester} is working correctly.
 *
 * @param <T> the type of the entity which will be tested
 */
public class EntityLogicalEqualsTesterTest<T extends DataEntityLogicalEquals> extends EntityGenericEqualsTesterTest<T> {

    // methods
    @Test
    public void testGetObject2Test_return_newCreatedObject() {
        BaseEntityTester<T> entityUnitTester = prepareEntityTesterGeneric();
        Object actual = entityUnitTester.getObject2Test();

        TestResultHelper.verifyInstance(actual, DataEntityLogicalEquals.class);
    }

    @Test
    @Override
    public void testTestEqualsLogicalAreTheSame_with_defaultEquals_defaultCompare_raise_noException() {
        deactivateTest(this.getClass());
    }

    @Test
    @Override
    public void testTestEqualsLogicalAreTheSame_with_defaultEquals_logicalCompare_raise_exception() {
        deactivateTest(this.getClass());
    }

    @Test
    public void testTestEqualsLogicalAreTheSame_with_logicalEquals_defaultCompare_raise_exception() {
        BaseEntityTester<T> entityUnitTester = prepareEntityTesterGeneric();

        entityUnitTester._setCheckLogicalEqualsOnly(false);
        assertThat(entityUnitTester._isCheckLogicalEqualsOnly(), is(false));

        entityUnitTester.testEqualsLogicalAreTheSame();

        TestResultHelper.verifyCollector(entityUnitTester, TestResultHelper.WITH_ERROR);
    }

    @Test
    public void testTestEqualsLogicalAreTheSame_with_logicalEquals_logicalCompare_raise_exception() {
        BaseEntityTester<T> entityUnitTester = prepareEntityTesterGeneric();

        entityUnitTester._setCheckLogicalEqualsOnly(true);
        assertThat(entityUnitTester._isCheckLogicalEqualsOnly(), is(true));

        entityUnitTester.testEqualsLogicalAreTheSame();

        TestResultHelper.verifyCollector(entityUnitTester, TestResultHelper.NO_ERROR);
    }

    @Test
    public void testTestHashcodeOtherThan0_raise_exception() {
        BaseEntityTester<T> entityUnitTester = prepareEntityTesterGeneric();
        entityUnitTester.testHashcodeOtherThan0();

        TestResultHelper.verifyCollector(entityUnitTester, TestResultHelper.WITH_ERROR);
    }

    @Test
    @Override
    public void testTestHashcodeOtherThan0_raise_noException() {
        deactivateTest(this.getClass());
    }

    protected BaseEntityTester<T> prepareEntityTesterGeneric() {
        return prepareEntityUnitTester((Class<T>) DataEntityLogicalEquals.class);
    }

    @Override
    protected BaseEntityTester<T> prepareEntityUnitTester(Class<T> typeOfO2T) {
        return new EntityLogicalEqualsTester<>();
    }
// end - methods
}
