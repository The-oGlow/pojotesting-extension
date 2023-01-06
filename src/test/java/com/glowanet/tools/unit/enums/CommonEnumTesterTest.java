package com.glowanet.tools.unit.enums;

import com.glowanet.tools.unit.TestFailedWatcher;
import com.glowanet.tools.unit.entity.enums.NameCheckTypeEnum;
import com.glowanet.util.reflect.ReflectionHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runners.Parameterized;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class CommonEnumTesterTest<E> {

    public static final  List<NameCheckTypeEnum> LIST_NC_TYPES = List.of(NameCheckTypeEnum.CF, NameCheckTypeEnum.CIF, NameCheckTypeEnum.CISW, NameCheckTypeEnum.CSW);
    private static final Logger                  LOGGER        = LogManager.getLogger();

    @Parameterized.Parameter
    public boolean     parameterCodeCheckEnabled;
    @Rule
    public TestWatcher watcher = new TestFailedWatcher("codeCheckEnabled", parameterCodeCheckEnabled);

    private final Class<E> typeOfE;

    protected CommonEnumTesterTest(Class<E> typeOfE) {
        this.typeOfE = typeOfE;
    }

    protected abstract BaseEnumTester<E> prepareEnumTester();

    protected abstract Field prepareFieldExistsInClazz();

    protected abstract Field prepareFieldNotExistsInClazz();

    protected abstract Field prepareFieldWithNoInName();

    protected abstract Field prepareFieldWithOutNoInName();

    protected static Object[] prepareParameterCodeCheckEnabled() {
        return new Object[]{
                Boolean.TRUE, Boolean.FALSE
        };
    }

    protected static Collection<Object[]> prepareParameterNameCheckType() {
        Collection<Object[]> allParms = new ArrayList<>();
        for (Object cce : prepareParameterCodeCheckEnabled()) {
            for (Object ncte : LIST_NC_TYPES) {
                Object[] n = new Object[]{cce, ncte};
                allParms.add(n);
            }
        }
        return allParms;
    }

    protected Class<E> getTypeOfE() {
        return typeOfE;
    }

    protected Field prepareField(String fieldName) {
        return ReflectionHelper.findField(fieldName, getTypeOfE());
    }

    protected boolean assumeParameterCodeCheckEnabledIsTrue() {
        boolean proceedNextStep = true;
        if (!parameterCodeCheckEnabled) {
            proceedNextStep = false;
            LOGGER.debug(String.format("assumeTrue() not met: parameterCodeCheckEnabled: '%s'", parameterCodeCheckEnabled));
        }
        return proceedNextStep;
    }

    protected boolean assumeParameterCodeCheckEnabledIsFalse() {
        boolean proceedNextStep = true;
        if (parameterCodeCheckEnabled) {
            proceedNextStep = false;
            LOGGER.debug(String.format("assumeFalse() not met: parameterCodeCheckEnabled: '%s'", parameterCodeCheckEnabled));
        }
        return proceedNextStep;
    }
}
