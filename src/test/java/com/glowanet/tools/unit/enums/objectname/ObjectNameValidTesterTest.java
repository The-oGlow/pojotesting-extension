package com.glowanet.tools.unit.enums.objectname;

import com.glowanet.data.enums.DataEnums;
import com.glowanet.tools.unit.TestFailedWatcher;
import com.glowanet.tools.unit.entity.enums.NameCheckTypeEnum;
import com.glowanet.tools.unit.enums.BaseEnumTester;
import com.glowanet.tools.unit.enums.BaseEnumTesterTest;
import com.glowanet.util.junit.TestResultHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.Field;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ObjectNameValidTesterTest<E extends DataEnums> extends BaseEnumTesterTest<E> {

    private static final Logger LOGGER = LogManager.getLogger();

    @Parameterized.Parameter(1)
    public       NameCheckTypeEnum parameterNameCheckType;
    @Rule
    public final TestFailedWatcher watcher = new TestFailedWatcher(
            "codeCheckEnabled/nameCheckType",
            "" + parameterCodeCheckEnabled + "/" + parameterNameCheckType);

    public ObjectNameValidTesterTest() {
        super((Class<E>) DataEnums.class);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return prepareParameterNameCheckType();
    }

    @Test
    public void testValidateEnumObjectName() {
        BaseEnumTester<E> o2T = prepareEnumTester();
        for (DataEnums de : DataEnums.values()) {

            Field expectedField = prepareField(de.name());
            E actualInstance = (E) de;

            boolean actual = o2T._validateEnumObjectName(expectedField, actualInstance);
            TestResultHelper.verifyCollectorNoError(o2T, true, actual);
        }
    }

    @Override
    protected BaseEnumTester<E> prepareEnumTester() {
        ObjectNameValidTester<E> o2T = new ObjectNameValidTester<>();
        o2T._setNameCheckType(parameterNameCheckType);
        o2T._setCodeCheckEnabled(parameterCodeCheckEnabled);
        watcher.setParameterValue("" + parameterCodeCheckEnabled + "/" + parameterNameCheckType);
        return o2T;
    }
}
