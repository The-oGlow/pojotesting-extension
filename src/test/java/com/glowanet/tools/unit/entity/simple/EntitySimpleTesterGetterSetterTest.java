package com.glowanet.tools.unit.entity.simple;

import com.glowanet.data.entity.simple.DataEntitySimple;
import com.glowanet.tools.unit.entity.BaseEntityTester;
import com.glowanet.tools.unit.entity.BaseEntityTesterTest;
import com.glowanet.tools.unit.entity.EntityUnitTester;
import com.glowanet.util.junit.TestResultHelper;
import org.junit.Test;

/**
 * A junit test class, which verifies, that the {@code AbstractEntityUnitTester} is working correctly.
 *
 * @param <T> the type of the entity which will be tested
 */
public class EntitySimpleTesterGetterSetterTest<T extends DataEntitySimple> extends BaseEntityTesterTest<T> {

    // methods
    @Test
    public void testTestAllGetterAccessiblewith_raise_noException() {
        EntityUnitTester<T> entityUnitTester = prepareEntityUnitTester((Class<T>) DataEntitySimple.class);

        entityUnitTester.testAllGetterAccessible();

        TestResultHelper.verifyCollector(entityUnitTester, TestResultHelper.NO_ERROR);
    }

    @Test
    public void testTestAllSetterAccessible_raise_noException() {
        EntityUnitTester<T> entityUnitTester = prepareEntityUnitTester((Class<T>) DataEntitySimple.class);

        entityUnitTester.testAllSetterAccessible();

        TestResultHelper.verifyCollector(entityUnitTester, TestResultHelper.NO_ERROR);
    }

    @Test
    public void testTestGetterSetterCollaboration_raise_noException() {
        EntityUnitTester<T> entityUnitTester = prepareEntityUnitTester((Class<T>) DataEntitySimple.class);

        entityUnitTester.testGetterSetterCollaboration();

        TestResultHelper.verifyCollector(entityUnitTester, TestResultHelper.NO_ERROR);
    }

    @Override
    protected BaseEntityTester<T> prepareEntityUnitTester(Class<T> typeOfO2T) {
        return new EntitySimpleTester<>();
    }

//    @Override
//    protected CallTheCreator<T> prepareTheCreator(Class<T> typeOfO2T) {
//        return new CallTheCreator<>() {
//            // methods
//            /* methods */
//            @Override
//            public T call() {
//                T newO2T = null;
//                if (DataEntitySimple.class.equals(typeOfO2T)) {
//                    newO2T = (T) new DataEntitySimple();
//                }
//                return newO2T;
//            }
//// end - methods
//        };
//    }
// end - methods
}
