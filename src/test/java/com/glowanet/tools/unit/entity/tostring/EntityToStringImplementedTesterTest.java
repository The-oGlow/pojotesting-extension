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

    // methods
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

        TestResultHelper.verifyCollector(entityUnitTester, TestResultHelper.NO_ERROR);
    }

    @Override
    protected BaseEntityTester<T> prepareEntityUnitTester(Class<T> typeOfO2T) {
        return new EntityToStringImplementedTester<>();
    }

    //    @Override
//    protected BaseEntityTester<T> prepareEntityUnitTester(Class<T> typeOfO2T) {
//        return new EntityToStringDefaultTester<>(typeOfO2T, prepareTheCreator(typeOfO2T));
//    }

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
//                } else if (DataEntityToStringImplemented.class.equals(typeOfO2T)) {
//                    newO2T = (T) new DataEntityToStringImplemented();
//                }
//                return newO2T;
//            }
//// end - methods
//        };
//    }
// end - methods
}
