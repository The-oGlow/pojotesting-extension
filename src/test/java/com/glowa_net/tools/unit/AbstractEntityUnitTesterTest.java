package com.glowa_net.tools.unit;

import com.glowa_net.util.reflect.ReflectionHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;
import org.junit.rules.ErrorCollector;

import java.beans.PropertyDescriptor;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThrows;

public class AbstractEntityUnitTesterTest {


    static class AbstractEntityUnitTesterParam {
        private Integer simInt;
        private String  simString;

        public Integer getSimInt() {
            return simInt;
        }

        public String getSimString() {
            return simString;
        }
    }


    static class AbstractEntityUnitTesterParamEquals extends AbstractEntityUnitTesterParam {
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            AbstractEntityUnitTesterParam that = (AbstractEntityUnitTesterParam) o;

            if (getSimInt() != null ? !getSimInt().equals(that.getSimInt()) : that.getSimInt() != null) return false;
            return getSimString() != null ? getSimString().equals(that.getSimString()) : that.getSimString() == null;
        }

        @Override
        public int hashCode() {
            int result = getSimInt() != null ? getSimInt().hashCode() : 0;
            result = 31 * result + (getSimString() != null ? getSimString().hashCode() : 0);
            return result;
        }
    }

    @SuppressWarnings("UnconstructableJUnitTestCase")
    static class AbstractEntityUnitTesterClazz<K extends AbstractEntityUnitTesterParam> extends AbstractEntityUnitTester<K> {

        protected AbstractEntityUnitTesterClazz(Class<K> typeOfT) {
            super(typeOfT);
        }

        @Override
        protected K createEntity() {
            if (getTypeOfT().equals(AbstractEntityUnitTesterParam.class)) {
                return (K) new AbstractEntityUnitTesterParam();
            } else {
                return (K) new AbstractEntityUnitTesterParamEquals();
            }
        }
    }

    AbstractEntityUnitTesterClazz<? extends AbstractEntityUnitTesterParam> o2T;

    @Before
    public void setUp() {
        initWithEqual();
    }

    public void initWithoutEqual() {
        o2T = new AbstractEntityUnitTesterClazz(AbstractEntityUnitTesterParam.class);
        o2T.setUp();
    }

    public void initWithEqual() {
        o2T = new AbstractEntityUnitTesterClazz(AbstractEntityUnitTesterParamEquals.class);
        o2T.setUp();
    }

    public void verifyCollector(Object instance, int size) {
        Object actual = ReflectionHelper.readField("collector", instance);

        assertThat(actual, notNullValue());
        assertThat(actual, instanceOf(ErrorCollector.class));

        Object actualThrows = ReflectionHelper.readField("errors", actual);

        assertThat(actualThrows, notNullValue());
        assertThat(actualThrows, instanceOf(Collection.class));
        assertThat(((Collection<?>) actualThrows), hasSize(size));
    }

    public void verifyException(ThrowingRunnable instance, Class<?> expected) {
        Throwable actual = assertThrows(Throwable.class, instance);

        assertThat(actual, notNullValue());
        assertThat(actual, instanceOf(expected));

    }

    @Test
    public void test_createEntity_return_entity() {
        AbstractEntityUnitTesterParam actual = o2T.createEntity();

        assertThat(actual, notNullValue());
        assertThat(actual, instanceOf(AbstractEntityUnitTesterParamEquals.class));
    }

    @Test
    public void test_getEntity_return_null() {
        o2T = new AbstractEntityUnitTesterClazz(AbstractEntityUnitTesterParam.class);
        AbstractEntityUnitTesterParam actual = o2T.getEntity();

        assertThat(actual, nullValue());
    }

    @Test
    public void test_setEntity_return_value() {
        AbstractEntityUnitTesterParam before = o2T.getEntity();
        assertThat(before, instanceOf(AbstractEntityUnitTesterParamEquals.class));

        o2T.setEntity(null);
        AbstractEntityUnitTesterParam actual = o2T.getEntity();

        assertThat(actual, nullValue());
    }

    @Test
    public void test_findGetter_return_list() {
        List<PropertyDescriptor> actual = o2T.findGetter();
        assertThat(actual, hasSize(3));
    }

    @Test
    public void test_findSetter_return_emptyList() {
        List<PropertyDescriptor> actual = o2T.findSetter();

        assertThat(actual, hasSize(0));
    }

    @Test
    public void test_fieldsDeniedForToString() {
        List<String> actual = o2T.fieldsDeniedForToString();

        assertThat(actual, notNullValue());
        assertThat(actual, hasSize(0));
    }

    @Test
    public void test_allFieldsToIgnoreForToString() {
        Collection<String> actual = ReflectionHelper.readField("allFieldsToIgnoreForToString", o2T);

        assertThat(actual, notNullValue());
        assertThat(actual, hasSize(1));
    }

    @Test
    public void test_allFieldsDeniedForToString() {
        Collection<String> actual = ReflectionHelper.readField("allFieldsDeniedForToString", o2T);

        assertThat(actual, notNullValue());
        assertThat(actual, hasSize(0));
    }

    @Test
    public void test_testAllGetterAccessible() {
        o2T.testAllGetterAccessible();
    }

    @Test
    public void test_testAllSetterAccessible() {
        o2T.testAllSetterAccessible();
    }

    @Test
    public void test_testGetterSetterCollaboration() {
        o2T.testGetterSetterCollaboration();
    }

    @Test
    public void test_testToString() {
        o2T.testToString();
    }

    @Test
    public void test_testToStringWithValues() {
        o2T.testToStringWithValues();

        verifyCollector(o2T, 1);
    }

    @Test
    public void test_testHashcodeOtherThan0_valid() {
        verifyException(() -> o2T.testHashcodeOtherThan0(), AssertionError.class);
    }

    @Test
    public void test_testHashcodeOtherThan0_with_incompleteEquals_valid() {
        initWithoutEqual();
        o2T.testHashcodeOtherThan0();
    }

    @Test
    public void test_testEqualsLogicalAreTheSame_valid() {
        assertThat(o2T.isCheckLogicalEqualsOnly(), is(false));
        o2T.testEqualsLogicalAreTheSame();
    }


    @Test
    public void test_testEqualsLogicalAreTheSame_logicalEquals_valid() {
        o2T.setCheckLogicalEqualsOnly(true);
        assertThat(o2T.isCheckLogicalEqualsOnly(), is(true));

        o2T.testEqualsLogicalAreTheSame();
    }

    @Test
    public void test_testEqualsLogicalAreTheSame_with_incompleteEquals_invalid() {
        initWithoutEqual();
        assertThat(o2T.isCheckLogicalEqualsOnly(), is(false));
        o2T.testEqualsLogicalAreTheSame();

        verifyException(() -> o2T.testEqualsLogicalAreTheSame(), AssertionError.class);
    }

    @Test
    public void test_testEqualsLogicalAreTheSame_with_incompleteEquals_logicalEquals_invalid() {
        initWithoutEqual();
        o2T.setCheckLogicalEqualsOnly(true);
        assertThat(o2T.isCheckLogicalEqualsOnly(), is(true));
        o2T.testEqualsLogicalAreTheSame();

        verifyException(() -> o2T.testEqualsLogicalAreTheSame(), AssertionError.class);
    }


    @Test
    public void test_testEqualsWithNull_valid() {
        o2T.testEqualsWithNull();
    }

    @Test
    public void test_testEqualsWithNull__with_incompleteEquals_valid() {
        initWithoutEqual();
        o2T.testEqualsWithNull();
    }

    @Test
    public void test_testEqualsWithItself_valid() {
        o2T.testEqualsWithItself();
    }

    @Test
    public void test_testEqualsWithItself__with_incompleteEquals_valid() {
        initWithoutEqual();
        o2T.testEqualsWithItself();
    }

}
