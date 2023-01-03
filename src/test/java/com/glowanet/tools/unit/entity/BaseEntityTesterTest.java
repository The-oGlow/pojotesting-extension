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

    protected static final Logger LOGGER = LogManager.getLogger();

    @Parameterized.Parameter
    public boolean     parameterCheckSVUID;
    @Rule
    public TestWatcher watcher = new TestFailedWatcher("checkSVUID", parameterCheckSVUID);

    /**
     * Prepare the testerclazz, which creates the entity which will test the pojo.
     *
     * @param typeOfO2T clazz of {@code T}
     *
     * @return new instance of {@code BaseEntityTester}
     */
    protected abstract BaseEntityTester<T> prepareEntityUnitTester(Class<T> typeOfO2T);

    protected static Object[] prepareParameterCheckSVUID() {
        return new Object[]{
                Boolean.TRUE, Boolean.FALSE
        };
    }

    protected boolean assumeParameterCheckSVUIDIsTrue() {
        boolean proceedNextStep = true;
        if (!parameterCheckSVUID) {
            proceedNextStep = false;
            LOGGER.debug(String.format("Ignore test, assumeTrue(parameterCheckSVUID) not met: '%s'", parameterCheckSVUID));
            assertThat(true, is(true));
        }
        return proceedNextStep;
    }

    protected boolean assumeParameterCheckSVUIDIsFalse() {
        boolean proceedNextStep = true;
        if (parameterCheckSVUID) {
            proceedNextStep = false;
            LOGGER.debug(String.format("Ignore test, assumeFalse(parameterCheckSVUID) not met: '%s'", parameterCheckSVUID));
            assertThat(false, is(false));
        }
        return proceedNextStep;
    }
}
