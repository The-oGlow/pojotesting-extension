package com.glowanet.tools;

import java.util.concurrent.Callable;

/**
 * In case the simulating test clazz needs to construct different pojo clazzes, use this clazz.
 *
 * @param <T> the type of the pojo/entity/class which will be tested
 */
public abstract class CallTheCreator<T> implements Callable<T> {

    /**
     * @return a new instance of type {@code T}
     */
    @Override
    public abstract T call();
}
