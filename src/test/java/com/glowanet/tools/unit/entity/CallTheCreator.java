package com.glowanet.tools.unit.entity;

import java.util.concurrent.Callable;

/**
 * In case the simulating test class needs to construct different pojo classes, use this class.
 *
 * @param <T> the type of the pojo class/classes to test
 */
public abstract class CallTheCreator<T> implements Callable<T> {
    /**
     * @return a new instance of type {@code T}
     */
    @Override
    public abstract T call();
}
