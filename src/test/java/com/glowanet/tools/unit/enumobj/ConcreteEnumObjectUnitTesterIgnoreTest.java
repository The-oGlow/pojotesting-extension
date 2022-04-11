package com.glowanet.tools.unit.enumobj;

import com.glowanet.tools.unit.enumobj.data.DataEnumObjectUnitTester;
import com.glowanet.util.junit.TestResultHelper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.Field;

import static com.glowanet.tools.unit.enumobj.data.DataEnumObjectUnitTester.ITEMNAME_EXISTS;
import static com.glowanet.tools.unit.enumobj.data.DataEnumObjectUnitTester.ITEMNAME_IGNORED;
import static com.glowanet.util.junit.TestResultHelper.WITH_ERROR;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class ConcreteEnumObjectUnitTesterIgnoreTest<
        E extends DataEnumObjectUnitTester> extends SimulationEnumObjectTesterTest<E> {

    // fields
    @Parameterized.Parameter(0)
    public boolean codeCheckEnabled;

    @Rule
    public  TestWatcher                   watcher = new TestWatcher() {

        // end - fields

        // methods
        @Override
        protected void failed(Throwable e, Description description) {
            // fields
            LOGGER.info("The test failed with {}={}", "codeCheckEnabled", codeCheckEnabled);
        }
        // end - methods
    };
    private SimulationEnumObjectTester<E> o2T;
// end - fields

    // static method
    @Parameterized.Parameters
    public static Object[] data() {
        return prepareParameterCodeCheckEnabled();
    }
// end - static method

    // methods
    @Before
    public void setUp() {
        o2T = prepareEnumObjectTester((Class<E>) DataEnumObjectUnitTester.class);
        o2T.setCodeCheckEnabled(codeCheckEnabled);
    }

    @Test
    public void testCheckIgnoredFields_existingField_return_false_raise_noException() {
        Field expectedField = prepareFieldExists();
        assertThat(expectedField, notNullValue());
        boolean actual = o2T.checkIgnoredFields(expectedField);
        if (codeCheckEnabled) {
            TestResultHelper.verifyCollector(o2T, WITH_ERROR, false, actual);
        } else {
            TestResultHelper.verifyCollectorNoError(o2T, false, actual);
        }
    }

    @Test
    public void testCheckIgnoredFields_null_return_false_raise_noException() {
        boolean actual = o2T.checkIgnoredFields(null);
        TestResultHelper.verifyCollectorNoError(o2T, false, actual);
    }

    @Test
    public void testIsInIgnoreListForCode_existingField_return_false_raise_noException() {
        boolean actual = o2T.isInIgnoreListForCode(ITEMNAME_EXISTS);
        TestResultHelper.verifyCollectorNoError(o2T, false, actual);
    }

    @Test
    public void testIsInIgnoreListForCode_ignoredField_return_false_raise_noException() {
        boolean actual = o2T.isInIgnoreListForCode(ITEMNAME_IGNORED);
        TestResultHelper.verifyCollectorNoError(o2T, true, actual);
    }

    @Test
    public void testIsInIgnoreListForCode_null_return_false_raise_noException() {
        boolean actual = o2T.isInIgnoreListForCode(null);
        TestResultHelper.verifyCollectorNoError(o2T, false, actual);
    }

    @Override
    protected SimulationEnumObjectTester<E> prepareEnumObjectTester(Class<E> typeOfO2E) {
        return new ConcreteEnumObjectUnitTesterIgnore<>(typeOfO2E);
    }

// end - methods
}
