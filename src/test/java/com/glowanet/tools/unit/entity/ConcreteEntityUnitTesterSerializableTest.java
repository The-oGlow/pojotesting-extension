package com.glowanet.tools.unit.entity;

import com.glowanet.tools.unit.data.DataUnitTesterSerializable.ClazzNoSerializable;
import com.glowanet.tools.unit.data.DataUnitTesterSerializable.ClazzWithSerialVersionUid;
import com.glowanet.tools.unit.data.DataUnitTesterSerializable.ClazzWithSerializableNoSerialVersionUid;
import com.glowanet.tools.unit.data.DataUnitTesterSerializable.ClazzWithWrongSerialVersionUid;
import com.glowanet.util.junit.TestResultHelper;
import org.junit.Test;

public class ConcreteEntityUnitTesterSerializableTest<
        T extends ClazzNoSerializable> extends SimulationEntityTesterCommon<T> {

    @Override
    protected SimulationEntityTester<T> prepareEntityUnitTester(Class<T> typeOfO2T) {
        return new ConcreteEntityUnitTester(typeOfO2T, prepareTheCreator(typeOfO2T));
    }

    public CallTheCreator<T> prepareTheCreator(Class<T> typeOfO2T) {
        return new CallTheCreator<>() {
            @Override
            public T call() {
                T newO2T = null;
                if (ClazzNoSerializable.class.equals(typeOfO2T)) {
                    newO2T = (T) new ClazzNoSerializable();
                } else if (ClazzWithSerializableNoSerialVersionUid.class.equals(typeOfO2T)) {
                    newO2T = (T) new ClazzWithSerializableNoSerialVersionUid();
                } else if (ClazzWithWrongSerialVersionUid.class.equals(typeOfO2T)) {
                    newO2T = (T) new ClazzWithWrongSerialVersionUid();
                } else if (ClazzWithSerialVersionUid.class.equals(typeOfO2T)) {
                    newO2T = (T) new ClazzWithSerialVersionUid();
                }
                return newO2T;
            }
        };
    }

    @Test
    public void testValidateSerialVersionUID_valid_NotSerializable() {
        SimulationEntityTester<?> o2T = prepareEntityUnitTester((Class<T>) ClazzNoSerializable.class);

        o2T.validateSerialVersionUID();

        TestResultHelper.verifyCollectorNoError(o2T);
    }

    @Test
    public void testValidateSerialVersionUID_invalid_NoSerialVersionUID() {
        SimulationEntityTester<?> o2T = prepareEntityUnitTester((Class<T>) ClazzWithSerializableNoSerialVersionUid.class);

        o2T.validateSerialVersionUID();

        TestResultHelper.verifyCollector(o2T, TestResultHelper.WITH_ERROR);
    }

    @Test
    public void testValidateSerialVersionUID_invalid_WrongSerialVersionUID() {
        SimulationEntityTester<?> o2T = prepareEntityUnitTester((Class<T>) ClazzWithWrongSerialVersionUid.class);

        o2T.validateSerialVersionUID();

        TestResultHelper.verifyCollector(o2T, TestResultHelper.WITH_ERROR);
    }

    @Test
    public void testValidateSerialVersionUID_valid_CorrectSerialVersionUID() {
        SimulationEntityTester<?> o2T = prepareEntityUnitTester((Class<T>) ClazzWithSerialVersionUid.class);

        o2T.validateSerialVersionUID();

        TestResultHelper.verifyCollectorNoError(o2T);
    }


    @Test
    public void test_testSerialVersionUIDIsCorrectInEntity() {
        SimulationEntityTester<?> o2T = prepareEntityUnitTester((Class<T>) ClazzNoSerializable.class);

        o2T.testSerialVersionUIDIsCorrectInEntity();

        TestResultHelper.verifyCollectorNoError(o2T);
    }
}
