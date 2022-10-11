package com.glowanet.tools.unit.entity;

import com.glowanet.data.entity.BaseDataEntity;
import com.glowanet.tools.unit.TestFailedWatcher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assume;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runners.Parameterized;

import static org.hamcrest.Matchers.is;

/**
 * An abstract class which operates as base class for the junit tests.
 *
 * @param <T> the type of the entity which will be tested
 */
public abstract class BaseEntityTesterTest<T extends BaseDataEntity> {

    // static fields
    protected static final Logger LOGGER = LogManager.getLogger();
    // end - static fields

    // fields
    @Parameterized.Parameter
    public boolean     parameterCheckSVUID;
    @Rule
    public TestWatcher watcher = new TestFailedWatcher("checkSVUID", parameterCheckSVUID);
// end - fields

    // abstract methods

    /**
     * Prepare the testerClass, which creates the entity which will test the pojo.
     *
     * @param typeOfO2T class of {@code T}
     *
     * @return new instance of {@code BaseEntityTester}
     */
    protected abstract BaseEntityTester<T> prepareEntityUnitTester(Class<T> typeOfO2T);
// end -  abstract methods

    // static method
    protected static Object[] prepareParameterCheckSVUID() {
        return new Object[]{
                Boolean.TRUE, Boolean.FALSE
        };
    }
// end - static method

    // methods

    /**
     * @param clazz the type having the test which should be deactivated
     */
    protected void deactivateTest(Class<?> clazz) {
        deactivateTest(clazz == null ? "" : clazz.getName());
    }

    /**
     * @param clazzName the classname having the test which should be deactivated
     */
    @SuppressWarnings("ConstantConditions")
    protected void deactivateTest(String clazzName) {
        Assume.assumeThat(String.format("Ignore with test '%s'", clazzName), false, is(true));
    }

    protected void assumeParameterCheckSVUIDIsTrue() {
        Assume.assumeTrue(String.format("parameterCheckSVUID: '%s'", parameterCheckSVUID), parameterCheckSVUID);
    }

    protected void assumeParameterCheckSVUIDIsFalse() {
        Assume.assumeFalse(String.format("parameterCheckSVUID: '%s'", parameterCheckSVUID), parameterCheckSVUID);
    }

// end - methods

}
