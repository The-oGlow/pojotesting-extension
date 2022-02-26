package com.glowanet.tools.unit.entity;

import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AbstractEntityUnitTesterSerializableTest {

    static class ClazzNoSerializable {
    }

    static class ClazzWithSerializable extends ClazzNoSerializable implements Serializable {
    }

    static class ClazzWithWrongSerialVersionId extends ClazzWithSerializable {
        private static final long serialVersionUID = AbstractEntityUnitTester.SERIAL_VERSION_UID_INVALID_RANGE.getLeft();
    }

    static class ClazzWithSerialVersionId extends ClazzWithSerializable {
        private static final long serialVersionUID = AbstractEntityUnitTester.SERIAL_VERSION_UID_INVALID_RANGE.getRight() + 1;
    }

    @SuppressWarnings("UnconstructableJUnitTestCase")
    static class AbstractEntityUnitTesterConcrete<T extends ClazzNoSerializable> extends AbstractEntityUnitTester<T> {

        protected AbstractEntityUnitTesterConcrete(Class<T> typeOfT) {
            super(typeOfT);
        }

        @Override
        protected T createObject2Test() {
            return (T) new ClazzNoSerializable();
        }
    }

    AbstractEntityUnitTesterConcrete<? extends ClazzNoSerializable> o2T;

    @Before
    public void setUp() {
        o2T = new AbstractEntityUnitTesterConcrete<>(ClazzNoSerializable.class);
        o2T.setUp();
    }

    @Test
    public void testHasSerializableIF_return_true() {
        boolean actual = o2T.hasSerializableIF(ClazzWithSerializable.class);
        assertThat(actual, equalTo(true));
    }

    @Test
    public void testHasSerializableIF_return_false() {
        boolean actual = o2T.hasSerializableIF(ClazzNoSerializable.class);
        assertThat(actual, equalTo(false));
    }

    @Test
    public void testValidateSerialVersionUID_valid_NotSerializable() {
        ClazzNoSerializable instance = new ClazzNoSerializable();
        o2T = new AbstractEntityUnitTesterConcrete<>(instance.getClass());

        o2T.validateSerialVersionUID(instance);
    }

    @Test(expected = AssertionError.class)
    public void testValidateSerialVersionUID_invalid_NoSerialVersionUID() {
        ClazzWithSerializable instance = new ClazzWithSerializable();
        o2T = new AbstractEntityUnitTesterConcrete<>(instance.getClass());

        o2T.validateSerialVersionUID(instance);
    }

    @Test(expected = AssertionError.class)
    public void testValidateSerialVersionUID_invalid_WrongSerialVersionUID() {
        ClazzWithWrongSerialVersionId instance = new ClazzWithWrongSerialVersionId();
        o2T = new AbstractEntityUnitTesterConcrete<>(instance.getClass());

        o2T.validateSerialVersionUID(instance);
    }

    @Test
    public void testValidateSerialVersionUID_valid_CorrectSerialVersionUID() {
        ClazzWithSerialVersionId instance = new ClazzWithSerialVersionId();
        o2T = new AbstractEntityUnitTesterConcrete<>(instance.getClass());

        o2T.validateSerialVersionUID(instance);
    }


    @Test
    public void test_testSerialVersionUIDIsCorrectInEntity() {
        o2T.testSerialVersionUIDIsCorrectInEntity();
    }

}
