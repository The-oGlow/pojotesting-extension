package com.glowanet.tools.unit.entity;

import com.glowanet.tools.unit.entity.data.DataEntityUnitTester;
import com.glowanet.util.junit.TestResultHelper;
import org.junit.Test;

public class ConcreteEntityUnitTesterGetterSetterTest<
        T extends DataEntityUnitTester> extends SimulationEntityTesterCommon<T> {

    /* methods */
    @Test
    public void testTestAllGetterAccessiblewith_raise_noException() {
        AbstractEntityUnitTester<T> entityUnitTester = prepareEntityUnitTester((Class<T>) DataEntityUnitTester.class);

        entityUnitTester.testAllGetterAccessible();

        TestResultHelper.verifyCollector(entityUnitTester, TestResultHelper.NO_ERROR);
    }

    @Test
    public void testTestAllSetterAccessible_raise_noException() {
        AbstractEntityUnitTester<T> entityUnitTester = prepareEntityUnitTester((Class<T>) DataEntityUnitTester.class);

        entityUnitTester.testAllSetterAccessible();

        TestResultHelper.verifyCollector(entityUnitTester, TestResultHelper.NO_ERROR);
    }

    @Test
    public void testTestGetterSetterCollaboration_raise_noException() {
        AbstractEntityUnitTester<T> entityUnitTester = prepareEntityUnitTester((Class<T>) DataEntityUnitTester.class);

        entityUnitTester.testGetterSetterCollaboration();

        TestResultHelper.verifyCollector(entityUnitTester, TestResultHelper.NO_ERROR);
    }

    @Override
    protected SimulationEntityTester<T> prepareEntityUnitTester(Class<T> typeOfO2T) {
        return new ConcreteEntityUnitTester(typeOfO2T, prepareTheCreator(typeOfO2T));
    }

    @Override
    protected CallTheCreator<T> prepareTheCreator(Class<T> typeOfO2T) {
        return new CallTheCreator<>() {
            /* methods */
            @Override
            public T call() {
                T newO2T = null;
                if (DataEntityUnitTester.class.equals(typeOfO2T)) {
                    newO2T = (T) new DataEntityUnitTester();
                }
                return newO2T;
            }
        };
    }
}
