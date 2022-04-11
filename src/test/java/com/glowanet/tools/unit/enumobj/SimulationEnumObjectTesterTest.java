package com.glowanet.tools.unit.enumobj;

import com.glowanet.tools.unit.enumobj.data.DataEnumObjectUnitTester;
import com.glowanet.util.reflect.ReflectionHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;

import static com.glowanet.tools.unit.enumobj.data.DataEnumObjectUnitTester.ITEMNAME_EXISTS;
import static com.glowanet.tools.unit.enumobj.data.DataEnumObjectUnitTester.ITEMNAME_NOT_EXISTS;
import static com.glowanet.tools.unit.enumobj.data.DataEnumObjectUnitTester.ITEMNAME_WITHOUT_NUMBER;
import static com.glowanet.tools.unit.enumobj.data.DataEnumObjectUnitTester.ITEMNAME_WITH_NUMBER;

public abstract class SimulationEnumObjectTesterTest<E> {

    // static fields
    protected static final Logger LOGGER = LogManager.getLogger();
// end - static fields

    // abstract methods
    protected abstract SimulationEnumObjectTester<E> prepareEnumObjectTester(Class<E> typeOfO2E);
// end -  abstract methods

    // static method
    protected static Object[] prepareParameterCodeCheckEnabled() {
        return new Object[]{
                Boolean.TRUE, Boolean.FALSE
        };
    }
// end - static method

    // methods
    protected Field prepareFieldExists() {
        return ReflectionHelper.findField(ITEMNAME_EXISTS, DataEnumObjectUnitTester.class);
    }

    protected Field prepareFieldNotExists() {
        return ReflectionHelper.findField(ITEMNAME_NOT_EXISTS, DataEnumObjectUnitTester.class);
    }

    protected Field prepareFieldWithNo() {
        return ReflectionHelper.findField(ITEMNAME_WITH_NUMBER, DataEnumObjectUnitTester.class);
    }

    protected Field prepareFieldWithOutNo() {
        return ReflectionHelper.findField(ITEMNAME_WITHOUT_NUMBER, DataEnumObjectUnitTester.class);
    }
// end - methods
}
