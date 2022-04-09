package com.glowanet.tools.unit.entity;

import org.junit.Assume;

import static org.hamcrest.Matchers.is;

/**
 * @param <T> the type of the class to test
 */
public abstract class SimulationEntityTesterCommon<T> {

    // abstract methods

    /**
     * Prepare the testerClass, which creates the entity which will test the pojo.
     *
     * @param typeOfO2T class of {@code T}
     *
     * @return new instance of {@code SimulationEntityTester}
     */
    protected abstract SimulationEntityTester<T> prepareEntityUnitTester(Class<T> typeOfO2T);

    /**
     * Prepare the creatorClass, which creates the pojo to test.
     *
     * @param typeOfO2T class of {@code T}
     *
     * @return new instance of {@code CallTheCreator}
     */
    protected abstract CallTheCreator<T> prepareTheCreator(Class<T> typeOfO2T);
// end -  abstract methods

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
// end - methods
}
