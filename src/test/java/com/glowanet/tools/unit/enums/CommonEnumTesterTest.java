package com.glowanet.tools.unit.enums;

import com.glowanet.util.reflect.ReflectionHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.Parameterized;

import java.lang.reflect.Field;

public abstract class CommonEnumTesterTest<E> {
    // static fields
    protected static final Logger LOGGER = LogManager.getLogger();
    // end - static fields

    // fields
    private final Class<E>    typeOfE;
    @Parameterized.Parameter
    public        boolean     parameterCodeCheckEnabled;
    @Rule
    public        TestWatcher watcher = new TestWatcher() {
        // methods
        @Override
        protected void failed(Throwable e, Description description) {
            // fields
            LOGGER.info("The test failed with {}={}", "codeCheckEnabled", parameterCodeCheckEnabled);
        }
        // end - methods
    };
// end - fields

    // constructors
    protected CommonEnumTesterTest(Class<E> typeOfE) {
        this.typeOfE = typeOfE;
    }
// end - constructors

    // abstract methods
    protected abstract BaseEnumTester<E> prepareEnumTester();

    protected abstract Field prepareFieldExistsInClazz();

    protected abstract Field prepareFieldNotExistsInClazz();

    protected abstract Field prepareFieldWithNoInName();

    protected abstract Field prepareFieldWithOutNoInName();

// end -  abstract methods

    // static method
    protected static Object[] prepareParameterCodeCheckEnabled() {
        return new Object[]{
                Boolean.TRUE, Boolean.FALSE
        };
    }
// end - static method

    // methods
    protected Class<E> getTypeOfE() {
        return typeOfE;
    }

    protected Field prepareField(String fieldName) {
        return ReflectionHelper.findField(fieldName, getTypeOfE());
    }
// end - methods

}
