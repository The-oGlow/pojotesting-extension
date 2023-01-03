package com.glowanet.tools.unit.entity.tostring;

import com.glowanet.data.entity.tostring.DataEntityToStringImplemented;
import com.glowanet.tools.unit.entity.BaseEntityTester;
import com.glowanet.tools.unit.entity.BaseEntityTesterTest;
import com.glowanet.util.junit.TestResultHelper;
import org.junit.Test;

/**
 * A junit test clazz, which verifies, that the {@code AbstractEntityUnitTester} is working correctly.
 *
 * @param <T> the type of the entity which will be tested
 */
public class EntityToStringImplementedTesterTest<T extends DataEntityToStringImplemented> extends BaseEntityTesterTest<T> {

    @Test
    public void testTestToStringWithValues_withToString_raise_oneException() {
        BaseEntityTester<T> entityUnitTester = prepareEntityUnitTester((Class<T>) DataEntityToStringImplemented.class);
        entityUnitTester.testToStringWithValues();

        TestResultHelper.verifyCollector(entityUnitTester, TestResultHelper.WITH_ERROR);
    }

    @Test
    public void testTestToString_withToString_raise_noException() {
        BaseEntityTester<T> entityUnitTester = prepareEntityUnitTester((Class<T>) DataEntityToStringImplemented.class);
        entityUnitTester.testToString();

        TestResultHelper.verifyCollectorNoError(entityUnitTester);
    }

    @Override
    protected BaseEntityTester<T> prepareEntityUnitTester(Class<T> typeOfO2T) {
        return new EntityToStringImplementedTester<>();
    }
}
