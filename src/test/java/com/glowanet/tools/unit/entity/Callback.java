package com.glowanet.tools.unit.entity;

import java.util.concurrent.Callable;

public abstract class Callback<T> implements Callable<T> {
    @Override
    public abstract T call();
}
