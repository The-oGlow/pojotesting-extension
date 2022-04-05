package com.glowanet.tools.unit.entity;

import org.junit.Assume;

import static org.hamcrest.Matchers.is;

/**
 * @param <T> type of the pojo
 */
public abstract class SimulationEntityTesterCommon<T> {

    /* abstract methods */
    protected abstract SimulationEntityTester<T> prepareEntityUnitTester(Class<T> typeOfO2T);

    /**
     * Prepare the class, which creates the pojo to test.
     *
     * @param typeOfO2T class of {@code T}
     *
     * @return new instance of {@code CallTheCreator}
     */
    protected abstract CallTheCreator<T> prepareTheCreator(Class<T> typeOfO2T);

    protected void deactivateTest(Class<?> clazz) {
        deactivateTest(clazz == null ? "" : clazz.getName());
    }

    @SuppressWarnings("ConstantConditions")
    protected void deactivateTest(String clazzName) {
        Assume.assumeThat(String.format("Ignore with test '%sÄ", clazzName), false, is(true));
    }
}
