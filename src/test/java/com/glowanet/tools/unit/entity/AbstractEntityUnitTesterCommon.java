package com.glowanet.tools.unit.entity;

public abstract class AbstractEntityUnitTesterCommon {

    public abstract Object prepareEntityUnitTester(Class<?> typeOfO2T);

//    public IConcreteEntityUnitTester prepareEntityUnitTester(Class<?> typeOfO2T) {
//        IConcreteEntityUnitTester entityUnitTester = new ConcreteEntityUnitTester(typeOfO2T, prepareCallback(typeOfO2T));
//        return entityUnitTester;
//    }

    protected abstract Callback<?> prepareCallback(Class<?> typeOfO2T);
}
