package com.glowanet.tools.unit.entity.generic;

import com.glowanet.tools.unit.entity.AbstractEntityUnitTester;
import com.glowanet.tools.unit.entity.AbstractEntityUnitTesterCommon;
import com.glowanet.tools.unit.entity.data.DataEntityUnitTester;
import com.glowanet.tools.unit.entity.data.DataEntityUnitTesterSerializable.ClazzWithSerializableNoSerialVersionUid;
import com.glowanet.tools.unit.entity.generic.ConcreteEntityUnitTesterGeneric.Callback;
import com.glowanet.util.junit.TestResultHelper;
import org.junit.Test;

import java.util.List;

import static com.glowanet.tools.unit.entity.data.DataEntityUnitTesterSerializable.ClazzNoSerializable;
import static com.glowanet.tools.unit.entity.data.DataEntityUnitTesterSerializable.ClazzWithSerialVersionUid;
import static com.glowanet.tools.unit.entity.data.DataEntityUnitTesterSerializable.ClazzWithWrongSerialVersionUid;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ConcreteEntityUnitTesterGenericTest<T extends DataEntityUnitTester> extends AbstractEntityUnitTesterCommon {

    public ConcreteEntityUnitTesterGeneric<T> prepareEntityUnitTester(Class<?> typeOfO2T) {
        ConcreteEntityUnitTesterGeneric<T> entityUnitTester = new ConcreteEntityUnitTesterGeneric<T>((Class<T>) typeOfO2T, prepareCallback(typeOfO2T));
        return entityUnitTester;
    }

    @SuppressWarnings("unchecked")
    public Callback<T> prepareCallback(Class<?> typeOfO2T) {
        return new Callback<T>() {
            @Override
            public void run() {
                T newO2T;
                if (ClazzWithSerialVersionUid.class.equals(typeOfO2T)) {
                    newO2T = (T) new ClazzWithSerialVersionUid();
                } else if (ClazzWithWrongSerialVersionUid.class.equals(typeOfO2T)) {
                    newO2T = (T) new ClazzWithWrongSerialVersionUid();
                } else if (ClazzWithSerializableNoSerialVersionUid.class.equals(typeOfO2T)) {
                    newO2T = (T) new ClazzWithSerializableNoSerialVersionUid();
                } else if (ClazzNoSerializable.class.equals(typeOfO2T)) {
                    newO2T = (T) new ClazzNoSerializable();
                } else {
                    newO2T = (T) new DataEntityUnitTester();
                }
                this.newO2T = newO2T;
            }
        };
    }

    @Test
    public void testFieldsDeniedForToString_return_emptyList() {
        ConcreteEntityUnitTesterGeneric<T> entityUnitTester = prepareEntityUnitTester(DataEntityUnitTester.class);

        List<String> actual = entityUnitTester._fieldsDeniedForToString();

        assertThat(actual, TestResultHelper.EMPTY_LIST);
    }

    @Test
    public void testIsCheckSVUID_return_true() {
        ConcreteEntityUnitTesterGeneric<?> entityUnitTester = prepareEntityUnitTester(DataEntityUnitTester.class);

        boolean actual = entityUnitTester._isCheckSVUID();

        assertThat(actual, equalTo(AbstractEntityUnitTester.DEFAULT_CHECK_SVUID));
    }

    @Test
    public void testSetCheckSVUID_return_false() {
        ConcreteEntityUnitTesterGeneric<?> entityUnitTester = prepareEntityUnitTester(DataEntityUnitTester.class);

        boolean actual = entityUnitTester._isCheckSVUID();
        assertThat(actual, equalTo(AbstractEntityUnitTester.DEFAULT_CHECK_SVUID));

        entityUnitTester._setCheckSVUID(!AbstractEntityUnitTester.DEFAULT_CHECK_SVUID);
        boolean actual2 = entityUnitTester._isCheckSVUID();

        assertThat(actual2, equalTo(!AbstractEntityUnitTester.DEFAULT_CHECK_SVUID));
    }

    @Test
    public void testIsCheckLogicalEqualsOnly_return_true() {
        ConcreteEntityUnitTesterGeneric<?> entityUnitTester = prepareEntityUnitTester(DataEntityUnitTester.class);

        boolean actual = entityUnitTester._isCheckLogicalEqualsOnly();

        assertThat(actual, equalTo(AbstractEntityUnitTester.DEFAULT_CHECK_LOGICAL_EQUALS_ONLY));
    }

    @Test
    public void testIsCheckLogicalEqualsOnly_return_false() {
        ConcreteEntityUnitTesterGeneric<?> entityUnitTester = prepareEntityUnitTester(DataEntityUnitTester.class);

        boolean actual = entityUnitTester._isCheckLogicalEqualsOnly();
        assertThat(actual, equalTo(AbstractEntityUnitTester.DEFAULT_CHECK_LOGICAL_EQUALS_ONLY));

        entityUnitTester._setCheckLogicalEqualsOnly(!AbstractEntityUnitTester.DEFAULT_CHECK_LOGICAL_EQUALS_ONLY);
        boolean actual2 = entityUnitTester._isCheckLogicalEqualsOnly();

        assertThat(actual2, equalTo(!AbstractEntityUnitTester.DEFAULT_CHECK_LOGICAL_EQUALS_ONLY));
    }

    @Test
    public void testValidateSerialVersionUID_simplePojo_raise_noException() {
        ConcreteEntityUnitTesterGeneric<?> entityUnitTester = prepareEntityUnitTester(DataEntityUnitTester.class);

        Object instance = new ClazzNoSerializable();
        entityUnitTester._validateSerialVersionUID();

        TestResultHelper.verifyCollector(entityUnitTester, TestResultHelper.NO_ERROR);
    }

    @Test
    public void testValidateSerialVersionUID_serialPojoWithoutId_raise_twoException() {
        ConcreteEntityUnitTesterGeneric<?> entityUnitTester = prepareEntityUnitTester(ClazzWithWrongSerialVersionUid.class);

        entityUnitTester._validateSerialVersionUID();

        TestResultHelper.verifyCollector(entityUnitTester, TestResultHelper.WITH_ERROR);
    }

    @Test
    public void testValidateSerialVersionUID_serialPojoWrongId_raise_oneException() {
        ConcreteEntityUnitTesterGeneric<?> entityUnitTester = prepareEntityUnitTester(ClazzWithWrongSerialVersionUid.class);

        entityUnitTester._validateSerialVersionUID();

        TestResultHelper.verifyCollector(entityUnitTester, TestResultHelper.WITH_ERROR);
    }

    @Test
    public void testValidateSerialVersionUID_serialPojo_raise_noException() {
        ConcreteEntityUnitTesterGeneric<?> entityUnitTester = prepareEntityUnitTester(ClazzWithSerialVersionUid.class);

        entityUnitTester._validateSerialVersionUID();

        TestResultHelper.verifyCollector(entityUnitTester, TestResultHelper.NO_ERROR);
    }

    @Test
    public void testCreateObject2Test_return_newCreatedObject() {
        ConcreteEntityUnitTesterGeneric<?> entityUnitTester = prepareEntityUnitTester(DataEntityUnitTester.class);

        Object actual = entityUnitTester.createObject2Test();

        TestResultHelper.verifyInstance(actual, DataEntityUnitTester.class);
    }

    @Test
    public void testGetObject2Test_return_currentUsedObject() {
        ConcreteEntityUnitTesterGeneric<?> entityUnitTester = prepareEntityUnitTester(DataEntityUnitTester.class);

        Object actual = entityUnitTester.getObject2Test();

        TestResultHelper.verifyInstance(actual, DataEntityUnitTester.class);
    }

    @Test
    public void testTestAllGetterAccessiblewith_raise_noException() {
        ConcreteEntityUnitTesterGeneric<?> entityUnitTester = prepareEntityUnitTester(DataEntityUnitTester.class);

        entityUnitTester.testAllGetterAccessible();

        TestResultHelper.verifyCollector(entityUnitTester, TestResultHelper.NO_ERROR);
    }

    @Test
    public void testTestAllSetterAccessible_raise_noException() {
        ConcreteEntityUnitTesterGeneric<?> entityUnitTester = prepareEntityUnitTester(DataEntityUnitTester.class);

        entityUnitTester.testAllSetterAccessible();

        TestResultHelper.verifyCollector(entityUnitTester, TestResultHelper.NO_ERROR);
    }

    @Test
    public void testTestGetterSetterCollaboration_raise_noException() {
        ConcreteEntityUnitTesterGeneric<?> entityUnitTester = prepareEntityUnitTester(DataEntityUnitTester.class);

        entityUnitTester.testGetterSetterCollaboration();

        TestResultHelper.verifyCollector(entityUnitTester, TestResultHelper.NO_ERROR);
    }

    @Test
    public void testTestHashcodeOtherThan0_raise_noException() {
        ConcreteEntityUnitTesterGeneric<?> entityUnitTester = prepareEntityUnitTester(DataEntityUnitTester.class);

        entityUnitTester.testHashcodeOtherThan0();

        TestResultHelper.verifyCollector(entityUnitTester, TestResultHelper.NO_ERROR);
    }

    @Test
    public void testTestEqualsWithNull_raise_noException() {
        ConcreteEntityUnitTesterGeneric<?> entityUnitTester = prepareEntityUnitTester(DataEntityUnitTester.class);

        entityUnitTester.testEqualsWithNull();

        TestResultHelper.verifyCollector(entityUnitTester, TestResultHelper.NO_ERROR);
    }

    @Test
    public void testTestEqualsWithItself_raise_noException() {
        ConcreteEntityUnitTesterGeneric<?> entityUnitTester = prepareEntityUnitTester(DataEntityUnitTester.class);

        entityUnitTester.testEqualsWithItself();

        TestResultHelper.verifyCollector(entityUnitTester, TestResultHelper.NO_ERROR);
    }

    @Test
    public void testTestEqualsLogicalAreTheSame_with_defaultEquals_defaultCompare_raise_noException() {
        ConcreteEntityUnitTesterGeneric<?> entityUnitTester = prepareEntityUnitTester(DataEntityUnitTester.class);

        entityUnitTester._setCheckLogicalEqualsOnly(false);
        assertThat(entityUnitTester._isCheckLogicalEqualsOnly(), is(false));

        entityUnitTester.testEqualsLogicalAreTheSame();

        TestResultHelper.verifyCollector(entityUnitTester, TestResultHelper.NO_ERROR);
    }

    @Test
    public void testTestEqualsLogicalAreTheSame_with_defaultEquals_logicalCompare_raise_exception() {
        ConcreteEntityUnitTesterGeneric<?> entityUnitTester = prepareEntityUnitTester(DataEntityUnitTester.class);

        entityUnitTester._setCheckLogicalEqualsOnly(true);
        assertThat(entityUnitTester._isCheckLogicalEqualsOnly(), is(true));

        entityUnitTester.testEqualsLogicalAreTheSame();

        TestResultHelper.verifyCollector(entityUnitTester, TestResultHelper.WITH_ERROR);
    }
}
