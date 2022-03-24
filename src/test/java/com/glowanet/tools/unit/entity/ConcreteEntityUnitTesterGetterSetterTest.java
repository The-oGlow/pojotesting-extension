package com.glowanet.tools.unit.entity;

import com.glowanet.tools.unit.entity.data.DataEntityUnitTester;
import com.glowanet.util.junit.TestResultHelper;
import org.junit.Test;

public class ConcreteEntityUnitTesterGetterSetterTest extends AbstractEntityUnitTesterCommon {

    @Override
    protected Callback<?> prepareCallback(Class<?> typeOfO2T) {
        return new Callback<>() {
            @Override
            public Object call() {
                return new DataEntityUnitTester();
            }
        };
    }

    @Override
    public ConcreteEntityUnitTester prepareEntityUnitTester(Class<?> typeOfO2T) {
        ConcreteEntityUnitTester entityUnitTester = new ConcreteEntityUnitTester(typeOfO2T, prepareCallback(typeOfO2T));
        return entityUnitTester;
    }

    @Test
    public void testTestAllGetterAccessiblewith_raise_noException() {
        ConcreteEntityUnitTester entityUnitTester = prepareEntityUnitTester(DataEntityUnitTester.class);

        entityUnitTester.testAllGetterAccessible();

        TestResultHelper.verifyCollector(entityUnitTester, TestResultHelper.NO_ERROR);
    }


    @Test
    public void testTestAllSetterAccessible_raise_noException() {
        ConcreteEntityUnitTester entityUnitTester = prepareEntityUnitTester(DataEntityUnitTester.class);

        entityUnitTester.testAllSetterAccessible();

        TestResultHelper.verifyCollector(entityUnitTester, TestResultHelper.NO_ERROR);
    }

    @Test
    public void testTestGetterSetterCollaboration_raise_noException() {
        ConcreteEntityUnitTester entityUnitTester = prepareEntityUnitTester(DataEntityUnitTester.class);

        entityUnitTester.testGetterSetterCollaboration();

        TestResultHelper.verifyCollector(entityUnitTester, TestResultHelper.NO_ERROR);
    }
}
