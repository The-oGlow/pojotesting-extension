package com.glowanet.tools.unit.entity;

import com.glowanet.tools.unit.entity.data.DataEntityUnitTesterSerializable.ClazzNoSerializable;
import com.glowanet.tools.unit.entity.data.DataEntityUnitTesterSerializable.ClazzWithSerialVersionUid;
import com.glowanet.tools.unit.entity.data.DataEntityUnitTesterSerializable.ClazzWithSerializableNoSerialVersionUid;
import com.glowanet.tools.unit.entity.data.DataEntityUnitTesterSerializable.ClazzWithWrongSerialVersionUid;
import com.glowanet.util.junit.TestResultHelper;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ConcreteEntityUnitTesterSerializableTest extends AbstractEntityUnitTesterCommon {

//    private ConcreteEntityUnitTester<? extends ClazzNoSerializable> o2T;

//    @Before
//    public void setUp() {
//        o2T = new ConcreteEntityUnitTester(ClazzNoSerializable.class, prepareCallback(ClazzNoSerializable.class));
//        o2T.setUp();
//    }

    @Override
    public Object prepareEntityUnitTester(Class<?> typeOfO2T) {
        ConcreteEntityUnitTester entityUnitTester = new ConcreteEntityUnitTester(typeOfO2T, prepareCallback(typeOfO2T));
        return entityUnitTester;
    }

    public Callback<?> prepareCallback(Class<?> typeOfO2T) {
        return new Callback<>() {
            @Override
            public Object call() {
                ClazzNoSerializable newO2T = null;
                if (ClazzNoSerializable.class.equals(typeOfO2T)) {
                    newO2T = new ClazzNoSerializable();
                } else if (ClazzWithSerializableNoSerialVersionUid.class.equals(typeOfO2T)) {
                    newO2T = new ClazzWithSerializableNoSerialVersionUid();
                } else if (ClazzWithWrongSerialVersionUid.class.equals(typeOfO2T)) {
                    newO2T = new ClazzWithWrongSerialVersionUid();
                } else if (ClazzWithSerialVersionUid.class.equals(typeOfO2T)) {
                    newO2T = new ClazzWithSerialVersionUid();
                }
                return newO2T;
            }
        };
    }

    @Test
    public void testHasSerializableIF_return_true() {
        Class<?> typeOfT = ClazzNoSerializable.class;
        ConcreteEntityUnitTester<?> o2T = new ConcreteEntityUnitTester(typeOfT, prepareCallback(typeOfT));

        boolean actual = o2T.hasSerializableIF(ClazzWithSerializableNoSerialVersionUid.class);
        assertThat(actual, equalTo(true));
    }

    @Test
    public void testHasSerializableIF_return_false() {
        Class<?> typeOfT = ClazzNoSerializable.class;
        ConcreteEntityUnitTester<?> o2T = new ConcreteEntityUnitTester(typeOfT, prepareCallback(typeOfT));

        boolean actual = o2T.hasSerializableIF(ClazzNoSerializable.class);
        assertThat(actual, equalTo(false));
    }

    @Test
    public void testValidateSerialVersionUID_valid_NotSerializable() {
        ConcreteEntityUnitTester o2T = new ConcreteEntityUnitTester<>(ClazzNoSerializable.class);

        o2T.validateSerialVersionUID();

        TestResultHelper.verifyCollectorNoError(o2T);
    }

    @Test
    public void testValidateSerialVersionUID_invalid_NoSerialVersionUID() {
        ConcreteEntityUnitTester o2T = new ConcreteEntityUnitTester<>(ClazzWithSerializableNoSerialVersionUid.class);

        o2T.validateSerialVersionUID();

        TestResultHelper.verifyCollector(o2T, TestResultHelper.WITH_ERROR);
    }

    @Test
    public void testValidateSerialVersionUID_invalid_WrongSerialVersionUID() {
        ConcreteEntityUnitTester o2T = new ConcreteEntityUnitTester<>(ClazzWithWrongSerialVersionUid.class);

        o2T.validateSerialVersionUID();

        TestResultHelper.verifyCollector(o2T, TestResultHelper.WITH_ERROR);
    }

    @Test
    public void testValidateSerialVersionUID_valid_CorrectSerialVersionUID() {
        ConcreteEntityUnitTester o2T = new ConcreteEntityUnitTester<>(ClazzWithSerialVersionUid.class);

        o2T.validateSerialVersionUID();

        TestResultHelper.verifyCollectorNoError(o2T);
    }


    @Test
    public void test_testSerialVersionUIDIsCorrectInEntity() {
        Class<?> typeOfT = ClazzNoSerializable.class;
        ConcreteEntityUnitTester<?> o2T = new ConcreteEntityUnitTester(typeOfT, prepareCallback(typeOfT));

        o2T.testSerialVersionUIDIsCorrectInEntity();

        TestResultHelper.verifyCollectorNoError(o2T);
    }
}
