package com.glowanet.tools.unit.entity.simple;

import com.glowanet.data.entity.simple.DataEntitySimple;
import com.glowanet.tools.unit.entity.BaseEntityTester;
import com.glowanet.tools.unit.entity.BaseEntityTesterTest;
import com.glowanet.tools.unit.entity.EntityUnitTester;
import com.glowanet.util.junit.TestResultHelper;
import org.junit.Test;

/**
 * A junit test clazz, which verifies, that the {@code AbstractEntityUnitTester} is working correctly.
 *
 * @param <T> the type of the entity which will be tested
 */
public class EntitySimpleTesterGetterSetterTest<T extends DataEntitySimple> extends BaseEntityTesterTest<T> {

    @Test
    public void testTestAllGetterAccessiblewith_raise_noException() {
        EntityUnitTester<T> entityUnitTester = prepareEntityUnitTester((Class<T>) DataEntitySimple.class);

        entityUnitTester.testAllGetterAccessible();

        TestResultHelper.verifyCollectorNoError(entityUnitTester);
    }

    @Test
    public void testTestAllSetterAccessible_raise_noException() {
        EntityUnitTester<T> entityUnitTester = prepareEntityUnitTester((Class<T>) DataEntitySimple.class);

        entityUnitTester.testAllSetterAccessible();

        TestResultHelper.verifyCollectorNoError(entityUnitTester);
    }

    @Test
    public void testTestGetterSetterCollaboration_raise_noException() {
        EntityUnitTester<T> entityUnitTester = prepareEntityUnitTester((Class<T>) DataEntitySimple.class);

        entityUnitTester.testGetterSetterCollaboration();

        TestResultHelper.verifyCollectorNoError(entityUnitTester);
    }

    @Override
    protected BaseEntityTester<T> prepareEntityUnitTester(Class<T> typeOfO2T) {
        return new EntitySimpleTester<>();
    }
}
