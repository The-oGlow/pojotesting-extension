package com.glowanet.tools.unit.entity.equal;

import com.glowanet.data.entity.equal.DataEntityGenericEquals;
import com.glowanet.tools.unit.entity.BaseEntityTester;
import com.glowanet.tools.unit.entity.BaseEntityTesterTest;
import com.glowanet.tools.unit.entity.EntityUnitTester;
import com.glowanet.util.junit.TestResultHelper;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * A junit test class, which verifies, that the {@code AbstractEntityUnitTester} is working correctly.
 *
 * @param <T> the type of the entity which will be tested
 */
public class EntityGenericEqualsTesterTest<T extends DataEntityGenericEquals> extends BaseEntityTesterTest<T> {

    // methods
    @Test
    public void testFieldsDeniedForToString_return_emptyList() {
        BaseEntityTester<T> entityUnitTester = prepareEntityTesterGeneric();
        List<String> actual = entityUnitTester._fieldsDeniedForToString();

        assertThat(actual, TestResultHelper.EMPTY_LIST);
    }

    @Test
    public void testGetObject2Test_return_newCreatedObject() {
        BaseEntityTester<T> entityUnitTester = prepareEntityTesterGeneric();
        Object actual = entityUnitTester.getObject2Test();

        TestResultHelper.verifyInstance(actual, DataEntityGenericEquals.class);
    }

    @Test
    public void testIsCheckLogicalEqualsOnly_return_false() {
        BaseEntityTester<T> entityUnitTester = prepareEntityTesterGeneric();
        boolean actual = entityUnitTester._isCheckLogicalEqualsOnly();
        assertThat(actual, equalTo(EntityUnitTester.DEFAULT_CHECK_LOGICAL_EQUALS_ONLY));

        entityUnitTester._setCheckLogicalEqualsOnly(!EntityUnitTester.DEFAULT_CHECK_LOGICAL_EQUALS_ONLY);
        boolean actual2 = entityUnitTester._isCheckLogicalEqualsOnly();

        assertThat(actual2, equalTo(!EntityUnitTester.DEFAULT_CHECK_LOGICAL_EQUALS_ONLY));
    }

    @Test
    public void testIsCheckLogicalEqualsOnly_return_true() {
        BaseEntityTester<T> entityUnitTester = prepareEntityTesterGeneric();
        boolean actual = entityUnitTester._isCheckLogicalEqualsOnly();

        assertThat(actual, equalTo(EntityUnitTester.DEFAULT_CHECK_LOGICAL_EQUALS_ONLY));
    }

    @Test
    public void testIsCheckSVUID_return_true() {
        BaseEntityTester<T> entityUnitTester = prepareEntityTesterGeneric();
        boolean actual = entityUnitTester._isCheckSVUID();

        assertThat(actual, equalTo(EntityUnitTester.DEFAULT_CHECK_SVUID));
    }

    @Test
    public void testSetCheckSVUID_return_false() {
        BaseEntityTester<T> entityUnitTester = prepareEntityTesterGeneric();

        boolean actual = entityUnitTester._isCheckSVUID();
        assertThat(actual, equalTo(EntityUnitTester.DEFAULT_CHECK_SVUID));

        entityUnitTester._setCheckSVUID(!EntityUnitTester.DEFAULT_CHECK_SVUID);
        boolean actual2 = entityUnitTester._isCheckSVUID();

        assertThat(actual2, equalTo(!EntityUnitTester.DEFAULT_CHECK_SVUID));
    }

    @Test
    public void testTestEqualsLogicalAreTheSame_with_defaultEquals_defaultCompare_raise_noException() {
        BaseEntityTester<T> entityUnitTester = prepareEntityTesterGeneric();

        entityUnitTester._setCheckLogicalEqualsOnly(false);
        assertThat(entityUnitTester._isCheckLogicalEqualsOnly(), is(false));

        entityUnitTester.testEqualsLogicalAreTheSame();

        TestResultHelper.verifyCollector(entityUnitTester, TestResultHelper.NO_ERROR);
    }

    @Test
    public void testTestEqualsLogicalAreTheSame_with_defaultEquals_logicalCompare_raise_exception() {
        BaseEntityTester<T> entityUnitTester = prepareEntityTesterGeneric();

        entityUnitTester._setCheckLogicalEqualsOnly(true);
        assertThat(entityUnitTester._isCheckLogicalEqualsOnly(), is(true));

        entityUnitTester.testEqualsLogicalAreTheSame();

        TestResultHelper.verifyCollector(entityUnitTester, TestResultHelper.WITH_ERROR);
    }

    @Test
    public void testTestEqualsWithItself_raise_noException() {
        BaseEntityTester<T> entityUnitTester = prepareEntityTesterGeneric();
        entityUnitTester.testEqualsWithItself();

        TestResultHelper.verifyCollector(entityUnitTester, TestResultHelper.NO_ERROR);
    }

    @Test
    public void testTestEqualsWithNull_raise_noException() {
        BaseEntityTester<T> entityUnitTester = prepareEntityTesterGeneric();
        entityUnitTester.testEqualsWithNull();

        TestResultHelper.verifyCollector(entityUnitTester, TestResultHelper.NO_ERROR);
    }

    @Test
    public void testTestHashcodeOtherThan0_raise_noException() {
        BaseEntityTester<T> entityUnitTester = prepareEntityTesterGeneric();
        entityUnitTester.testHashcodeOtherThan0();

        TestResultHelper.verifyCollector(entityUnitTester, TestResultHelper.NO_ERROR);
    }

    protected BaseEntityTester<T> prepareEntityTesterGeneric() {
        return prepareEntityUnitTester((Class<T>) DataEntityGenericEquals.class);
    }

    @Override
    protected BaseEntityTester<T> prepareEntityUnitTester(Class<T> typeOfO2T) {
        return new EntityGenericEqualsTester<>();
    }
// end - methods
}
