package com.glowanet.tools.unit.entity.generic;

import com.glowanet.tools.unit.entity.AbstractEntityUnitTester;
import com.glowanet.tools.unit.entity.AbstractEntityUnitTesterCommon;
import com.glowanet.tools.unit.entity.data.DataClazzSerializable;
import com.glowanet.tools.unit.entity.data.DataEntityUnitTesterGenericEquals;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

public class AbstractEntityUnitTesterConcreteGenericEqualsTest extends AbstractEntityUnitTesterCommon {

    //protected AbstractEntityUnitTesterConcreteGenericEquals<?> entityUnitTester;

    public AbstractEntityUnitTesterConcreteGenericEquals<?> prepareEntityUnitTester() {
        AbstractEntityUnitTesterConcreteGenericEquals<DataEntityUnitTesterGenericEquals> entityUnitTester = new AbstractEntityUnitTesterConcreteGenericEquals<>(DataEntityUnitTesterGenericEquals.class);
//        entityUnitTester.setUp();
        return entityUnitTester;
    }

    @Test
    public void testFieldsDeniedForToString_return_emptyList() {
        AbstractEntityUnitTesterConcreteGenericEquals<?> entityUnitTester = prepareEntityUnitTester();

        List<String> actual = entityUnitTester._fieldsDeniedForToString();

        assertThat(actual, EMPTY_LIST);
    }

    @Test
    public void testIsCheckSVUID_return_true() {
        AbstractEntityUnitTesterConcreteGenericEquals<?> entityUnitTester = prepareEntityUnitTester();

        boolean actual = entityUnitTester._isCheckSVUID();

        assertThat(actual, equalTo(AbstractEntityUnitTester.DEFAULT_CHECK_SVUID));
    }

    @Test
    public void testSetCheckSVUID_return_false() {
        AbstractEntityUnitTesterConcreteGenericEquals<?> entityUnitTester = prepareEntityUnitTester();

        boolean actual = entityUnitTester._isCheckSVUID();
        assertThat(actual, equalTo(AbstractEntityUnitTester.DEFAULT_CHECK_SVUID));

        entityUnitTester._setCheckSVUID(!AbstractEntityUnitTester.DEFAULT_CHECK_SVUID);
        boolean actual2 = entityUnitTester._isCheckSVUID();

        assertThat(actual2, equalTo(!AbstractEntityUnitTester.DEFAULT_CHECK_SVUID));
    }

    @Test
    public void testIsCheckLogicalEqualsOnly_return_true() {
        AbstractEntityUnitTesterConcreteGenericEquals<?> entityUnitTester = prepareEntityUnitTester();

        boolean actual = entityUnitTester._isCheckLogicalEqualsOnly();

        assertThat(actual, equalTo(AbstractEntityUnitTester.DEFAULT_CHECK_LOGICAL_EQUALS_ONLY));
    }

    @Test
    public void testIsCheckLogicalEqualsOnly_return_false() {
        AbstractEntityUnitTesterConcreteGenericEquals<?> entityUnitTester = prepareEntityUnitTester();

        boolean actual = entityUnitTester._isCheckLogicalEqualsOnly();
        assertThat(actual, equalTo(AbstractEntityUnitTester.DEFAULT_CHECK_LOGICAL_EQUALS_ONLY));

        entityUnitTester._setCheckLogicalEqualsOnly(!AbstractEntityUnitTester.DEFAULT_CHECK_LOGICAL_EQUALS_ONLY);
        boolean actual2 = entityUnitTester._isCheckLogicalEqualsOnly();

        assertThat(actual2, equalTo(!AbstractEntityUnitTester.DEFAULT_CHECK_LOGICAL_EQUALS_ONLY));
    }

    @Test
    public void testValidateSerialVersionUID_simplePojo_raise_noException() {
        AbstractEntityUnitTesterConcreteGenericEquals<?> entityUnitTester = prepareEntityUnitTester();

        Object instance = new DataClazzSerializable.ClazzNoSerializable();
        entityUnitTester._validateSerialVersionUID();

        verifyCollector(entityUnitTester, NO_ERROR);
    }

    @Test
    public void testValidateSerialVersionUID_serialPojoWithoutId_raise_twoException() {
        AbstractEntityUnitTesterConcreteGenericEquals<?> entityUnitTester = prepareEntityUnitTester();

        Object instance = new DataClazzSerializable.ClazzWithSerializableNoSerialVersionUid();

        verifyException(() -> entityUnitTester._validateSerialVersionUID(), AssertionError.class);
    }

    @Test
    public void testValidateSerialVersionUID_serialPojoWrongId_raise_oneException() {
        AbstractEntityUnitTesterConcreteGenericEquals<?> entityUnitTester = prepareEntityUnitTester();

        Object instance = new DataClazzSerializable.ClazzWithWrongSerialVersionUid();
        entityUnitTester._validateSerialVersionUID();

        verifyCollector(entityUnitTester, WITH_ERROR);
    }

    @Test
    public void testValidateSerialVersionUID_serialPojo_raise_noException() {
        AbstractEntityUnitTesterConcreteGenericEquals<?> entityUnitTester = prepareEntityUnitTester();

        Object instance = new DataClazzSerializable.ClazzWithSerialVersionUid();
        entityUnitTester._validateSerialVersionUID();

        verifyCollector(entityUnitTester, NO_ERROR);
    }

    @Test
    public void testCreateObject2Test_return_newCreatedObject() {
        AbstractEntityUnitTesterConcreteGenericEquals<?> entityUnitTester = prepareEntityUnitTester();

        Object actual = entityUnitTester.createObject2Test();

        verifyInstance(actual, DataEntityUnitTesterGenericEquals.class);
    }

    @Test
    public void testGetObject2Test_return_currentUsedObject() {
        AbstractEntityUnitTesterConcreteGenericEquals<?> entityUnitTester = prepareEntityUnitTester();

        Object actual = entityUnitTester.getObject2Test();

        verifyInstance(actual, DataEntityUnitTesterGenericEquals.class);
    }

    @Test
    public void testSetEntity_return_null() {
        AbstractEntityUnitTesterConcreteGenericEquals<?> entityUnitTester = prepareEntityUnitTester();

        Object before = entityUnitTester.getObject2Test();
        assertThat(before, instanceOf(DataEntityUnitTesterGenericEquals.class));

        entityUnitTester.setObject2Test(null);
        Object actual = entityUnitTester.getObject2Test();

        verifyNull(actual);
    }

    @Test
    public void testTestAllGetterAccessiblewith_raise_noException() {
        AbstractEntityUnitTesterConcreteGenericEquals<?> entityUnitTester = prepareEntityUnitTester();

        entityUnitTester.testAllGetterAccessible();

        verifyCollector(entityUnitTester, NO_ERROR);
    }

    @Test
    public void testTestAllSetterAccessible_raise_noException() {
        AbstractEntityUnitTesterConcreteGenericEquals<?> entityUnitTester = prepareEntityUnitTester();

        entityUnitTester.testAllSetterAccessible();

        verifyCollector(entityUnitTester, NO_ERROR);
    }

    @Test
    public void testTestGetterSetterCollaboration_raise_noException() {
        AbstractEntityUnitTesterConcreteGenericEquals<?> entityUnitTester = prepareEntityUnitTester();

        entityUnitTester.testGetterSetterCollaboration();

        verifyCollector(entityUnitTester, NO_ERROR);
    }

    @Test
    public void testTestHashcodeOtherThan0_raise_noException() {
        AbstractEntityUnitTesterConcreteGenericEquals<?> entityUnitTester = prepareEntityUnitTester();

        entityUnitTester.testHashcodeOtherThan0();

        verifyCollector(entityUnitTester, NO_ERROR);
    }

    @Test
    public void testTestEqualsWithNull_raise_noException() {
        AbstractEntityUnitTesterConcreteGenericEquals<?> entityUnitTester = prepareEntityUnitTester();

        entityUnitTester.testEqualsWithNull();

        verifyCollector(entityUnitTester, NO_ERROR);
    }

    @Test
    public void testTestEqualsWithItself_raise_noException() {
        AbstractEntityUnitTesterConcreteGenericEquals<?> entityUnitTester = prepareEntityUnitTester();

        entityUnitTester.testEqualsWithItself();

        verifyCollector(entityUnitTester, NO_ERROR);
    }

    @Test
    public void testTestEqualsLogicalAreTheSame_with_defaultEquals_defaultCompare_raise_noException() {
        AbstractEntityUnitTesterConcreteGenericEquals<?> entityUnitTester = prepareEntityUnitTester();

        entityUnitTester._setCheckLogicalEqualsOnly(false);
        assertThat(entityUnitTester._isCheckLogicalEqualsOnly(), is(false));

        entityUnitTester.testEqualsLogicalAreTheSame();

        verifyCollector(entityUnitTester, NO_ERROR);
    }

    @Test
    public void testTestEqualsLogicalAreTheSame_with_defaultEquals_logicalCompare_raise_exception() {
        AbstractEntityUnitTesterConcreteGenericEquals<?> entityUnitTester = prepareEntityUnitTester();

        entityUnitTester._setCheckLogicalEqualsOnly(true);
        assertThat(entityUnitTester._isCheckLogicalEqualsOnly(), is(true));

        entityUnitTester.testEqualsLogicalAreTheSame();

        verifyCollector(entityUnitTester, WITH_ERROR);
    }
}
