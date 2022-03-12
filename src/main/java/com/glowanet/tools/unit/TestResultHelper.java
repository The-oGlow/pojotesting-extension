package com.glowanet.tools.unit;

import com.glowanet.util.reflect.ReflectionHelper;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.function.ThrowingRunnable;
import org.junit.rules.ErrorCollector;

import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThrows;

public class TestResultHelper {

    public static final String                 NOT_THROWN  = "expected %s to be thrown, but nothing was thrown";
    public static final Matcher<Collection<?>> EMPTY_LIST  = Matchers.hasSize(0);
    public static final Matcher<Collection<?>> SINGLE_LIST = Matchers.hasSize(1);
    public static final int                    NO_ERROR    = 0;
    public static final int                    WITH_ERROR  = 1;
    public static final int                    TWO_ERROR   = 2;

    private TestResultHelper() {
    }

    public static void verifyCollector(Object instance, int size) {
        Object actual = ReflectionHelper.readField("collector", instance);

        assertThat(actual, notNullValue());
        assertThat(actual, instanceOf(ErrorCollector.class));

        Object actualThrows = ReflectionHelper.readField("errors", actual);

        assertThat(actualThrows, notNullValue());
        assertThat(actualThrows, instanceOf(Collection.class));
        assertThat(((Collection<?>) actualThrows), hasSize(size));
    }

    public static void verifyNoException(ThrowingRunnable instance) {
        AssertionError actual = assertThrows(AssertionError.class, () -> assertThrows(Throwable.class, instance));

        assertThat(actual, notNullValue());
        assertThat(actual.toString(), containsString(String.format(NOT_THROWN, Throwable.class.getName())));
    }

    public static Throwable verifyException(ThrowingRunnable instance, Class<?> expected) {
        Throwable actual = assertThrows(Throwable.class, instance);

        assertThat(actual, notNullValue());
        assertThat(actual, instanceOf(expected));
        return actual;
    }

    public static void verifyException(ThrowingRunnable instance, Class<?> expected, String expectedMsg) {
        Throwable actual = verifyException(instance, expected);

        assertThat(actual.getMessage(), containsString(expectedMsg));
    }

    public static void verifyNoNull(Object actual) {
        assertThat(actual, notNullValue());
    }

    public static void verifyNull(Object actual) {
        assertThat(actual, nullValue());
    }

    public static void verifyInstance(Object actual, Class<?> expected) {
        assertThat(actual, instanceOf(expected));
    }
}
