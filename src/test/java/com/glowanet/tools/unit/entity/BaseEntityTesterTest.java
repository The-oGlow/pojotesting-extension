package com.glowanet.tools.unit.entity;

import com.glowanet.data.entity.BaseDataEntity;
import com.glowanet.tools.unit.TestFailedWatcher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runners.Parameterized;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * An abstract clazz which operates as base clazz for the junit tests.
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
     * Prepare the testerclazz, which creates the entity which will test the pojo.
     *
     * @param typeOfO2T clazz of {@code T}
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

    protected boolean assumeParameterCheckSVUIDIsTrue() {
        boolean proceedNextStep = true;
        if (!parameterCheckSVUID) {
            proceedNextStep = false;
            LOGGER.debug(String.format("Ignore test, assumeTrue(parameterCheckSVUID) not met: '%s'", parameterCheckSVUID));
            assertThat(true, is(true));
        }
        //Assume.assumeTrue(String.format("parameterCheckSVUID: '%s'", parameterCheckSVUID), parameterCheckSVUID);
        return proceedNextStep;
    }

    protected boolean assumeParameterCheckSVUIDIsFalse() {
        boolean proceedNextStep = true;
        if (parameterCheckSVUID) {
            proceedNextStep = false;
            LOGGER.debug(String.format("Ignore test, assumeFalse(parameterCheckSVUID) not met: '%s'", parameterCheckSVUID));
            assertThat(false, is(false));
        }
        //Assume.assumeFalse(String.format("parameterCheckSVUID: '%s'", parameterCheckSVUID), parameterCheckSVUID);
        return proceedNextStep;
    }

// end - methods

}
