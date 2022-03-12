package com.glowanet.tools.unit.entity;

import com.glowanet.tools.unit.entity.data.DataClazzSerializable;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AbstractEntityUnitTesterSerializableTest extends AbstractEntityUnitTesterCommon {

    @SuppressWarnings("UnconstructableJUnitTestCase")
    private static class AbstractEntityUnitTesterConcrete<T extends DataClazzSerializable.ClazzNoSerializable> extends AbstractEntityUnitTester<T> {

        protected AbstractEntityUnitTesterConcrete(Class<T> typeOfT) {
            super(typeOfT);
        }

        @Override
        protected T createObject2Test() {
            DataClazzSerializable.ClazzNoSerializable newO2T = null;
            if (DataClazzSerializable.ClazzNoSerializable.class.equals(getTypeOfo2T())) {
                newO2T = new DataClazzSerializable.ClazzNoSerializable();
            } else if (DataClazzSerializable.ClazzWithSerializableNoSerialVersionUid.class.equals(getTypeOfo2T())) {
                newO2T = new DataClazzSerializable.ClazzWithSerializableNoSerialVersionUid();
            } else if (DataClazzSerializable.ClazzWithWrongSerialVersionUid.class.equals(getTypeOfo2T())) {
                newO2T = new DataClazzSerializable.ClazzWithWrongSerialVersionUid();
            } else if (DataClazzSerializable.ClazzWithSerialVersionUid.class.equals(getTypeOfo2T())) {
                newO2T = new DataClazzSerializable.ClazzWithSerialVersionUid();
            }
            return (T) newO2T;
        }
    }

    private AbstractEntityUnitTesterConcrete<? extends DataClazzSerializable.ClazzNoSerializable> o2T;

    @Before
    public void setUp() {
        o2T = new AbstractEntityUnitTesterConcrete<DataClazzSerializable.ClazzNoSerializable>(DataClazzSerializable.ClazzNoSerializable.class);
        o2T.setUp();
    }

    @Test
    public void testHasSerializableIF_return_true() {
        boolean actual = o2T.hasSerializableIF(DataClazzSerializable.ClazzWithSerializableNoSerialVersionUid.class);
        assertThat(actual, equalTo(true));
    }

    @Test
    public void testHasSerializableIF_return_false() {
        boolean actual = o2T.hasSerializableIF(DataClazzSerializable.ClazzNoSerializable.class);
        assertThat(actual, equalTo(false));
    }

    @Test
    public void testValidateSerialVersionUID_valid_NotSerializable() {
        o2T = new AbstractEntityUnitTesterConcrete<DataClazzSerializable.ClazzNoSerializable>(DataClazzSerializable.ClazzNoSerializable.class);

        o2T.validateSerialVersionUID();

        verifyCollector(o2T, NO_ERROR);
    }

    @Test
    public void testValidateSerialVersionUID_invalid_NoSerialVersionUID() {
        o2T = new AbstractEntityUnitTesterConcrete<DataClazzSerializable.ClazzWithSerializableNoSerialVersionUid>(DataClazzSerializable.ClazzWithSerializableNoSerialVersionUid.class);

        o2T.validateSerialVersionUID();

        verifyCollector(o2T, WITH_ERROR);
    }

    @Test
    public void testValidateSerialVersionUID_invalid_WrongSerialVersionUID() {
        o2T = new AbstractEntityUnitTesterConcrete<>(DataClazzSerializable.ClazzWithWrongSerialVersionUid.class);

        o2T.validateSerialVersionUID();

        verifyCollector(o2T, WITH_ERROR);
    }

    @Test
    public void testValidateSerialVersionUID_valid_CorrectSerialVersionUID() {
        o2T = new AbstractEntityUnitTesterConcrete<>(DataClazzSerializable.ClazzWithSerialVersionUid.class);

        o2T.validateSerialVersionUID();

        verifyCollector(o2T, NO_ERROR);
    }


    @Test
    public void test_testSerialVersionUIDIsCorrectInEntity() {
        o2T.testSerialVersionUIDIsCorrectInEntity();

        verifyCollector(o2T, NO_ERROR);
    }
}
