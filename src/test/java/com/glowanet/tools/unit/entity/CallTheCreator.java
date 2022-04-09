package com.glowanet.tools.unit.entity;

import java.util.concurrent.Callable;

/**
 * In case the simulating test class needs to construct different pojo classes, use this class.
 *
 * @param <T> the type of the pojo/entity/class which will be tested
 */
public abstract class CallTheCreator<T> implements Callable<T> {

    // abstract methods

    /**
     * @return a new instance of type {@code T}
     */
    @Override
    public abstract T call();
// end -  abstract methods
}
