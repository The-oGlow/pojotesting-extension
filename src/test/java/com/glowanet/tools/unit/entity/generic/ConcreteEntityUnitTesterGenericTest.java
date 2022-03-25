package com.glowanet.tools.unit.entity.generic;

import com.glowanet.tools.unit.entity.AbstractEntityUnitTester;
import com.glowanet.tools.unit.entity.CallTheCreator;
import com.glowanet.tools.unit.entity.SimulationEntityTester;
import com.glowanet.tools.unit.entity.SimulationEntityTesterCommon;
import com.glowanet.tools.unit.entity.data.DataEntityUnitTester;
import com.glowanet.tools.unit.entity.data.DataEntityUnitTesterGenericEquals;
import com.glowanet.tools.unit.entity.data.DataEntityUnitTesterLogicalEquals;
import com.glowanet.tools.unit.entity.data.DataEntityUnitTesterSerializable;
import com.glowanet.util.junit.TestResultHelper;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ConcreteEntityUnitTesterGenericTest<
        T extends DataEntityUnitTester> extends SimulationEntityTesterCommon<T> {

    @Override
    protected SimulationEntityTester<T> prepareEntityUnitTester(Class<T> typeOfO2T) {
        return new ConcreteEntityUnitTesterGeneric(typeOfO2T, prepareTheCreator(typeOfO2T));
    }

    @Override
    protected CallTheCreator<T> prepareTheCreator(Class<T> typeOfO2T) {
        return new CallTheCreator<>() {
            @SuppressWarnings("unchecked")
            @Override
            public T call() {
                T newO2T = null;
                if (DataEntityUnitTesterSerializable.ClazzWithSerialVersionUid.class.equals(typeOfO2T)) {
                    newO2T = (T) new DataEntityUnitTesterSerializable.ClazzWithSerialVersionUid();
                } else if (DataEntityUnitTesterSerializable.ClazzWithWrongSerialVersionUid.class.equals(typeOfO2T)) {
                    newO2T = (T) new DataEntityUnitTesterSerializable.ClazzWithWrongSerialVersionUid();
                } else if (DataEntityUnitTesterSerializable.ClazzWithSerializableNoSerialVersionUid.class.equals(typeOfO2T)) {
                    newO2T = (T) new DataEntityUnitTesterSerializable.ClazzWithSerializableNoSerialVersionUid();
                } else if (DataEntityUnitTesterSerializable.ClazzNoSerializable.class.equals(typeOfO2T)) {
                    newO2T = (T) new DataEntityUnitTesterSerializable.ClazzNoSerializable();
                } else if (DataEntityUnitTesterLogicalEquals.class.equals(typeOfO2T)) {
                    newO2T = (T) new DataEntityUnitTesterLogicalEquals();
                } else if (DataEntityUnitTesterGenericEquals.class.equals(typeOfO2T)) {
                    newO2T = (T) new DataEntityUnitTesterGenericEquals();
                } else if (DataEntityUnitTester.class.equals(typeOfO2T)) {
                    newO2T = (T) new DataEntityUnitTester();
                }
                return newO2T;
            }
        };
    }

    protected SimulationEntityTester<T> prepareEntityTesterGeneric() {
        return prepareEntityUnitTester((Class<T>) DataEntityUnitTesterGenericEquals.class);
    }

    protected SimulationEntityTester<?> prepareEntityTesterGenericForSerializable(Class<?> typeOfQ) {
        return prepareEntityUnitTester((Class<T>) typeOfQ);
    }

    @Test
    public void testCreateObject2Test_return_newCreatedObject() {
        SimulationEntityTester<T> entityUnitTester = prepareEntityTesterGeneric();
        Object actual = entityUnitTester.createObject2Test();

        TestResultHelper.verifyInstance(actual, DataEntityUnitTesterGenericEquals.class);
    }

    @Test
    public void testGetObject2Test_return_currentUsedObject() {
        SimulationEntityTester<T> entityUnitTester = prepareEntityTesterGeneric();
        Object actual = entityUnitTester.getObject2Test();

        TestResultHelper.verifyInstance(actual, DataEntityUnitTesterGenericEquals.class);
    }

    @Test
    public void testFieldsDeniedForToString_return_emptyList() {
        SimulationEntityTester<T> entityUnitTester = prepareEntityTesterGeneric();
        List<String> actual = entityUnitTester._fieldsDeniedForToString();

        assertThat(actual, TestResultHelper.EMPTY_LIST);
    }

    @Test
    public void testIsCheckSVUID_return_true() {
        SimulationEntityTester<T> entityUnitTester = prepareEntityTesterGeneric();
        boolean actual = entityUnitTester._isCheckSVUID();

        assertThat(actual, equalTo(AbstractEntityUnitTester.DEFAULT_CHECK_SVUID));
    }

    @Test
    public void testSetCheckSVUID_return_false() {
        SimulationEntityTester<T> entityUnitTester = prepareEntityTesterGeneric();

        boolean actual = entityUnitTester._isCheckSVUID();
        assertThat(actual, equalTo(AbstractEntityUnitTester.DEFAULT_CHECK_SVUID));

        entityUnitTester._setCheckSVUID(!AbstractEntityUnitTester.DEFAULT_CHECK_SVUID);
        boolean actual2 = entityUnitTester._isCheckSVUID();

        assertThat(actual2, equalTo(!AbstractEntityUnitTester.DEFAULT_CHECK_SVUID));
    }

    @Test
    public void testIsCheckLogicalEqualsOnly_return_true() {
        SimulationEntityTester<T> entityUnitTester = prepareEntityTesterGeneric();
        boolean actual = entityUnitTester._isCheckLogicalEqualsOnly();

        assertThat(actual, equalTo(AbstractEntityUnitTester.DEFAULT_CHECK_LOGICAL_EQUALS_ONLY));
    }

    @Test
    public void testIsCheckLogicalEqualsOnly_return_false() {
        SimulationEntityTester<T> entityUnitTester = prepareEntityTesterGeneric();
        boolean actual = entityUnitTester._isCheckLogicalEqualsOnly();
        assertThat(actual, equalTo(AbstractEntityUnitTester.DEFAULT_CHECK_LOGICAL_EQUALS_ONLY));

        entityUnitTester._setCheckLogicalEqualsOnly(!AbstractEntityUnitTester.DEFAULT_CHECK_LOGICAL_EQUALS_ONLY);
        boolean actual2 = entityUnitTester._isCheckLogicalEqualsOnly();

        assertThat(actual2, equalTo(!AbstractEntityUnitTester.DEFAULT_CHECK_LOGICAL_EQUALS_ONLY));
    }

    @Test
    public void testValidateSerialVersionUID_simplePojo_raise_noException() {
        SimulationEntityTester<T> entityUnitTester = prepareEntityTesterGeneric();
        entityUnitTester._validateSerialVersionUID();

        TestResultHelper.verifyCollector(entityUnitTester, TestResultHelper.NO_ERROR);
    }

    @Test
    public void testValidateSerialVersionUID_serialPojoWithoutId_raise_twoException() {
        SimulationEntityTester<?> entityUnitTester = prepareEntityTesterGenericForSerializable(DataEntityUnitTesterSerializable.ClazzWithWrongSerialVersionUid.class);
        entityUnitTester._validateSerialVersionUID();

        TestResultHelper.verifyCollector(entityUnitTester, TestResultHelper.WITH_ERROR);
    }

    @Test
    public void testValidateSerialVersionUID_serialPojoWrongId_raise_oneException() {
        SimulationEntityTester<?> entityUnitTester = prepareEntityTesterGenericForSerializable(DataEntityUnitTesterSerializable.ClazzWithWrongSerialVersionUid.class);
        entityUnitTester._validateSerialVersionUID();

        TestResultHelper.verifyCollector(entityUnitTester, TestResultHelper.WITH_ERROR);
    }

    @Test
    public void testValidateSerialVersionUID_serialPojo_raise_noException() {
        SimulationEntityTester<?> entityUnitTester = prepareEntityTesterGenericForSerializable(DataEntityUnitTesterSerializable.ClazzWithSerialVersionUid.class);
        entityUnitTester._validateSerialVersionUID();

        TestResultHelper.verifyCollector(entityUnitTester, TestResultHelper.NO_ERROR);
    }

    @Test
    public void testTestHashcodeOtherThan0_raise_noException() {
        SimulationEntityTester<T> entityUnitTester = prepareEntityTesterGeneric();
        entityUnitTester.testHashcodeOtherThan0();

        TestResultHelper.verifyCollector(entityUnitTester, TestResultHelper.NO_ERROR);
    }

    @Test
    public void testTestEqualsWithNull_raise_noException() {
        SimulationEntityTester<T> entityUnitTester = prepareEntityTesterGeneric();
        entityUnitTester.testEqualsWithNull();

        TestResultHelper.verifyCollector(entityUnitTester, TestResultHelper.NO_ERROR);
    }

    @Test
    public void testTestEqualsWithItself_raise_noException() {
        SimulationEntityTester<T> entityUnitTester = prepareEntityTesterGeneric();
        entityUnitTester.testEqualsWithItself();

        TestResultHelper.verifyCollector(entityUnitTester, TestResultHelper.NO_ERROR);
    }

    @Test
    public void testTestEqualsLogicalAreTheSame_with_defaultEquals_defaultCompare_raise_noException() {
        SimulationEntityTester<T> entityUnitTester = prepareEntityTesterGeneric();

        entityUnitTester._setCheckLogicalEqualsOnly(false);
        assertThat(entityUnitTester._isCheckLogicalEqualsOnly(), is(false));

        entityUnitTester.testEqualsLogicalAreTheSame();

        TestResultHelper.verifyCollector(entityUnitTester, TestResultHelper.NO_ERROR);
    }

    @Test
    public void testTestEqualsLogicalAreTheSame_with_defaultEquals_logicalCompare_raise_exception() {
        SimulationEntityTester<T> entityUnitTester = prepareEntityTesterGeneric();

        entityUnitTester._setCheckLogicalEqualsOnly(true);
        assertThat(entityUnitTester._isCheckLogicalEqualsOnly(), is(true));

        entityUnitTester.testEqualsLogicalAreTheSame();

        TestResultHelper.verifyCollector(entityUnitTester, TestResultHelper.WITH_ERROR);
    }
}
