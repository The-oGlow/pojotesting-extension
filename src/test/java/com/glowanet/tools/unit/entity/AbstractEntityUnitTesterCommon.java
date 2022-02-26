package com.glowanet.tools.unit.entity;

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

public abstract class AbstractEntityUnitTesterCommon {

    protected static final String                 NOT_THROWN  = "expected %s to be thrown, but nothing was thrown";
    protected static final Matcher<Collection<?>> EMPTY_LIST  = Matchers.hasSize(0);
    protected static final Matcher<Collection<?>> SINGLE_LIST = Matchers.hasSize(1);
    protected static final int                    NO_ERROR    = 0;
    protected static final int                    WITH_ERROR  = 1;
    protected static final int                    TWO_ERROR   = 2;

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

    public void verifyNoNull(Object actual) {
        assertThat(actual, notNullValue());
    }

    public void verifyNull(Object actual) {
        assertThat(actual, nullValue());
    }

    public void verifyInstance(Object actual, Class<?> expected) {
        assertThat(actual, instanceOf(expected));
    }
}
