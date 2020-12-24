package com.glowa_net.tools.unit;

import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AbstractEntityUnitTesterSerializableTest {


    static class AbstractEntityUnitTesterParamNoSerializable {
    }

    static class AbstractEntityUnitTesterParamNoSerialVersionId extends AbstractEntityUnitTesterParamNoSerializable implements Serializable {
    }

    static class AbstractEntityUnitTesterParamWrongSerialVersionId extends AbstractEntityUnitTesterParamNoSerialVersionId {
        private static final long serialVersionUID = AbstractEntityUnitTester.SERIAL_VERSION_UID_INVALID_RANGE.getLeft();
    }

    static class AbstractEntityUnitTesterParamSerializable extends AbstractEntityUnitTesterParamWrongSerialVersionId {
        private static final long serialVersionUID = AbstractEntityUnitTester.SERIAL_VERSION_UID_INVALID_RANGE.getRight() + 1;
    }

    @SuppressWarnings("UnconstructableJUnitTestCase")
    static class AbstractEntityUnitTesterClazz<K> extends AbstractEntityUnitTester<K> {

        protected AbstractEntityUnitTesterClazz(Class<K> typeOfT) {
            super(typeOfT);
        }

        @Override
        protected K createEntity() {
            return (K) new AbstractEntityUnitTesterParamNoSerializable();
        }
    }

    AbstractEntityUnitTesterClazz<? extends AbstractEntityUnitTesterParamNoSerializable> o2T;

    @Before
    public void setUp() {
        o2T = new AbstractEntityUnitTesterClazz(AbstractEntityUnitTesterParamSerializable.class);
        o2T.setUp();
    }

    @Test
    public void test_hasSerializableIF_return_true() {
        Class<?> clazz = String.class;

        boolean actual = o2T.hasSerializableIF(clazz);

        assertThat(actual, is(true));
    }

    @Test
    public void test_hasSerializableIF_return_false() {
        Class<?> clazz = o2T.getClass();

        boolean actual = o2T.hasSerializableIF(clazz);

        assertThat(actual, is(false));
    }

    @Test
    public void test_validateSerialVersionUID_valid() {
        AbstractEntityUnitTesterParamSerializable instance = new AbstractEntityUnitTesterParamSerializable();
        o2T = new AbstractEntityUnitTesterClazz(instance.getClass());

        o2T.validateSerialVersionUID(instance);
    }

    @Test
    public void test_validateSerialVersionUID_valid_NotSerializable() {
        AbstractEntityUnitTesterParamNoSerializable instance = new AbstractEntityUnitTesterParamNoSerializable();
        o2T = new AbstractEntityUnitTesterClazz(instance.getClass());

        o2T.validateSerialVersionUID(instance);
    }

    @Test(expected = AssertionError.class)
    public void test_validateSerialVersionUID_invalid_WrongSerialVersionUID() {
        AbstractEntityUnitTesterParamWrongSerialVersionId instance = new AbstractEntityUnitTesterParamWrongSerialVersionId();
        o2T = new AbstractEntityUnitTesterClazz(instance.getClass());

        o2T.validateSerialVersionUID(instance);
    }

    @Test(expected = AssertionError.class)
    public void test_validateSerialVersionUID_invalid_NoSerialVersionUID() {
        AbstractEntityUnitTesterParamNoSerialVersionId instance = new AbstractEntityUnitTesterParamNoSerialVersionId();
        o2T = new AbstractEntityUnitTesterClazz(instance.getClass());

        o2T.validateSerialVersionUID(instance);
    }

    @Test
    public void test_testSerialVersionUIDIsCorrectInEntity() {
        o2T.testSerialVersionUIDIsCorrectInEntity();
    }

}
