package com.glowanet.tools.unit.entity;

import com.glowanet.tools.unit.entity.data.DataEntityUnitTester;
import com.glowanet.tools.unit.entity.data.DataEntityUnitTesterToString;
import com.glowanet.util.junit.TestResultHelper;
import org.junit.Test;

public class ConcreteEntityUnitTesterToStringImplementedTest<
        T extends DataEntityUnitTester> extends SimulationEntityTesterCommon<T> {

    // methods
    @Test
    public void testTestToStringWithValues_withToString_raise_oneException() {
        SimulationEntityTester<T> entityUnitTester = prepareEntityUnitTester((Class<T>) DataEntityUnitTesterToString.class);
        entityUnitTester.testToStringWithValues();

        TestResultHelper.verifyCollector(entityUnitTester, TestResultHelper.WITH_ERROR);
    }

    @Test
    public void testTestToString_withToString_raise_noException() {
        SimulationEntityTester<T> entityUnitTester = prepareEntityUnitTester((Class<T>) DataEntityUnitTesterToString.class);
        entityUnitTester.testToString();

        TestResultHelper.verifyCollector(entityUnitTester, TestResultHelper.NO_ERROR);
    }

    @Override
    protected SimulationEntityTester<T> prepareEntityUnitTester(Class<T> typeOfO2T) {
        return new ConcreteEntityUnitTesterToStringDefault<>(typeOfO2T, prepareTheCreator(typeOfO2T));
    }

    @Override
    protected CallTheCreator<T> prepareTheCreator(Class<T> typeOfO2T) {
        return new CallTheCreator<>() {
            // methods
            /* methods */
            @Override
            public T call() {
                T newO2T = null;
                if (DataEntityUnitTester.class.equals(typeOfO2T)) {
                    newO2T = (T) new DataEntityUnitTester();
                } else if (DataEntityUnitTesterToString.class.equals(typeOfO2T)) {
                    newO2T = (T) new DataEntityUnitTesterToString();
                }
                return newO2T;
            }
// end - methods
        };
    }
// end - methods
}
