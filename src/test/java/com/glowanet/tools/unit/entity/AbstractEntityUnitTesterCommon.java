package com.glowanet.tools.unit.entity;

import com.glowanet.tools.unit.TestResultHelper;
import org.hamcrest.Matcher;
import org.junit.function.ThrowingRunnable;

import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

public abstract class AbstractEntityUnitTesterCommon {

    protected static final String                 NOT_THROWN  = TestResultHelper.NOT_THROWN;
    protected static final Matcher<Collection<?>> EMPTY_LIST  = TestResultHelper.EMPTY_LIST;
    protected static final Matcher<Collection<?>> SINGLE_LIST = TestResultHelper.SINGLE_LIST;
    protected static final int                    NO_ERROR    = TestResultHelper.NO_ERROR;
    protected static final int                    WITH_ERROR  = TestResultHelper.WITH_ERROR;
    protected static final int                    TWO_ERROR   = TestResultHelper.TWO_ERROR;

    public void verifyCollector(Object instance, int size) {
        TestResultHelper.verifyCollector(instance, size);
    }

    public void verifyNoException(ThrowingRunnable instance) {
        TestResultHelper.verifyNoException(instance);
    }

    public Throwable verifyException(ThrowingRunnable instance, Class<?> expected) {
        return TestResultHelper.verifyException(instance, expected);
    }

    public void verifyException(ThrowingRunnable instance, Class<?> expected, String expectedMsg) {
        TestResultHelper.verifyException(instance, expected, expectedMsg);
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
