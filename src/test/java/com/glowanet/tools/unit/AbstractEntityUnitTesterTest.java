package com.glowanet.tools.unit;

import com.glowanet.util.reflect.ReflectionHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;
import org.junit.rules.ErrorCollector;

import java.beans.PropertyDescriptor;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThrows;

public class AbstractEntityUnitTesterTest {

    protected static final String NOT_THROWN = "expected %s to be thrown, but nothing was thrown";

    /**
     * Test Object with
     * <ul>
     *     <li>a default {@code #equals()}, {@code #hashCode()}, {@code #testToString()}-method.</li>
     * </ul>
     */
    static class AbstractEntityUnitTesterDefaultEquals {
        private Integer simInt;
        private String  simString;

        private AbstractEntityUnitTesterDefaultEquals() {
        }

        public Integer getSimInt() {
            return simInt;
        }

        public String getSimString() {
            return simString;
        }

        static AbstractEntityUnitTesterDefaultEquals newInstance() {
            return new AbstractEntityUnitTesterDefaultEquals();
        }
    }


    /**
     * Test Object with
     * <ul>
     *     <li>an {@code #equals()}-method, which compares only logically equalness</li>
     *     <li>a default {@code #hashCode()}, {@code #testToString()}-method.</li>
     * </ul>
     */
    static class AbstractEntityUnitTesterLogicalEquals extends AbstractEntityUnitTesterDefaultEquals {

        static AbstractEntityUnitTesterDefaultEquals newInstance() {
            return new AbstractEntityUnitTesterLogicalEquals();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            AbstractEntityUnitTesterDefaultEquals that = (AbstractEntityUnitTesterDefaultEquals) o;

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

    /**
     * Container class, which holds the class which will be tested.
     *
     * @param <K> the type of the class to test
     */
    @SuppressWarnings("UnconstructableJUnitTestCase")
    static class AbstractEntityUnitTesterClazz<K extends AbstractEntityUnitTesterDefaultEquals> extends AbstractEntityUnitTester<K> {

        static <K extends AbstractEntityUnitTesterDefaultEquals> AbstractEntityUnitTesterClazz<K> newInstance(Class<K> typeOfT) {
            return new AbstractEntityUnitTesterClazz<>(typeOfT);
        }

        private AbstractEntityUnitTesterClazz(Class<K> typeOfT) {
            super(typeOfT);
        }

        @Override
        protected K createObject2Test() {
            if (getTypeOfo2T().equals(AbstractEntityUnitTesterDefaultEquals.class)) {
                return (K) AbstractEntityUnitTesterDefaultEquals.newInstance();
            } else {
                return (K) AbstractEntityUnitTesterLogicalEquals.newInstance();
            }
        }
    }

    protected AbstractEntityUnitTesterClazz<?> o2T;

    @Before
    public void setUp() {
        initWithEqual();
    }

    public void initWithoutEqual() {
        o2T = new AbstractEntityUnitTesterClazz<>(AbstractEntityUnitTesterDefaultEquals.class);
        o2T.setUp();
    }

    public void initWithEqual() {
        o2T = new AbstractEntityUnitTesterClazz<>(AbstractEntityUnitTesterLogicalEquals.class);
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

    public void verifyNoException(ThrowingRunnable instance) {
        AssertionError actual = assertThrows(AssertionError.class, () -> assertThrows(Throwable.class, instance));
        assertThat(actual, notNullValue());
        assertThat(actual.toString(), containsString(String.format(NOT_THROWN, Throwable.class.getName())));
    }

    public void verifyException(ThrowingRunnable instance, Class<?> expected) {
        Throwable actual = assertThrows(Throwable.class, instance);

        assertThat(actual, notNullValue());
        assertThat(actual, instanceOf(expected));

    }

    @Test
    public void test_createEntity_return_entity() {
        AbstractEntityUnitTesterDefaultEquals actual = o2T.createObject2Test();

        assertThat(actual, notNullValue());
        assertThat(actual, instanceOf(AbstractEntityUnitTesterLogicalEquals.class));
    }

    @Test
    public void test_getEntity_return_null() {
        o2T = new AbstractEntityUnitTesterClazz<>(AbstractEntityUnitTesterDefaultEquals.class);
        AbstractEntityUnitTesterDefaultEquals actual = o2T.getObject2Test();

        assertThat(actual, nullValue());
    }

    @Test
    public void test_setEntity_return_value() {
        AbstractEntityUnitTesterDefaultEquals before = o2T.getObject2Test();
        assertThat(before, instanceOf(AbstractEntityUnitTesterLogicalEquals.class));

        o2T.setObject2Test(null);
        AbstractEntityUnitTesterDefaultEquals actual = o2T.getObject2Test();

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

        assertThat(actual, notNullValue());
        assertThat(actual, hasSize(0));
    }

    @Test
    public void test_fieldsDeniedForToString_return_emptyList() {
        List<String> actual = o2T.fieldsDeniedForToString();

        assertThat(actual, notNullValue());
        assertThat(actual, hasSize(0));
    }

    @Test
    public void test_allFieldsDeniedForToString_return_emptyList() {
        Collection<String> actual = ReflectionHelper.readField("allFieldsDeniedForToString", o2T);

        assertThat(actual, notNullValue());
        assertThat(actual, hasSize(0));
    }

    @Test
    public void test_fieldsToIgnoreForToString_return_emptyList() {
        List<String> actual = o2T.fieldsToIgnoreForToString();

        assertThat(actual, notNullValue());
        assertThat(actual, hasSize(0));
    }

    @Test
    public void test_allFieldsToIgnoreForToString_return_oneElement() {
        Collection<String> actual = ReflectionHelper.readField("allFieldsToIgnoreForToString", o2T);

        assertThat(actual, notNullValue());
        assertThat(actual, hasSize(1));
    }

    @Test
    public void test_testAllGetterAccessiblewith_raise_noException() {
        o2T.testAllGetterAccessible();

        verifyCollector(o2T, 0);
    }

    @Test
    public void test_testAllSetterAccessible_raise_noException() {
        o2T.testAllSetterAccessible();

        verifyCollector(o2T, 0);
    }

    @Test
    public void test_testGetterSetterCollaboration_raise_noException() {
        o2T.testGetterSetterCollaboration();

        verifyCollector(o2T, 0);
    }

    @Test
    public void test_testToString_raise_2Exception() {
        o2T.testToString();

        verifyCollector(o2T, 2);
    }

    @Test
    public void test_testToStringWithValues_raise_exception() {
        o2T.testToStringWithValues();

        verifyCollector(o2T, 1);
    }

    @Test
    public void test_testHashcodeOtherThan0_raise_exception() {
        verifyException(() -> o2T.testHashcodeOtherThan0(), AssertionError.class);
    }

    @Test
    public void test_testHashcodeOtherThan0_with_incompleteEquals_raise_noException() {
        initWithoutEqual();
        o2T.testHashcodeOtherThan0();
    }

    @Test
    public void test_testEqualsLogicalAreTheSame_with_logicalEquals_defaultCompare_raise_exception() {
        assertThat(o2T.isCheckLogicalEqualsOnly(), is(false));
        verifyException(() -> o2T.testEqualsLogicalAreTheSame(), AssertionError.class);
    }


    @Test
    public void test_testEqualsLogicalAreTheSame_with_logicalEquals_logicalCompare_raise_noException() {
        o2T.setCheckLogicalEqualsOnly(true);
        assertThat(o2T.isCheckLogicalEqualsOnly(), is(true));

        o2T.testEqualsLogicalAreTheSame();
    }

    @Test
    public void test_testEqualsLogicalAreTheSame_with_defaultEquals_defaultCompare_raise_noException() {
        initWithoutEqual();
        o2T.setCheckLogicalEqualsOnly(false);
        assertThat(o2T.isCheckLogicalEqualsOnly(), is(false));

        o2T.testEqualsLogicalAreTheSame();
    }

    @Test
    public void test_testEqualsLogicalAreTheSame_with_defaultEquals_logicalCompare_raise_exception() {
        initWithoutEqual();
        o2T.setCheckLogicalEqualsOnly(true);
        assertThat(o2T.isCheckLogicalEqualsOnly(), is(true));

        verifyException(() -> o2T.testEqualsLogicalAreTheSame(), AssertionError.class);
    }


    @Test
    public void test_testEqualsWithNull_raise_noException() {
        o2T.testEqualsWithNull();
    }

    @Test
    public void test_testEqualsWithNull_with_incompleteEquals_raise_noException() {
        initWithoutEqual();
        o2T.testEqualsWithNull();
    }

    @Test
    public void test_testEqualsWithItself_raise_noException() {
        o2T.testEqualsWithItself();
    }

    @Test
    public void test_testEqualsWithItself_with_incompleteEquals_raise_noException() {
        initWithoutEqual();
        o2T.testEqualsWithItself();
    }
}
