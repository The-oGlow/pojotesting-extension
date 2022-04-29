package com.glowanet.tools.unit.entity.serial;

import com.glowanet.data.entity.serial.DataEntityWrongSerialVersionUid;
import com.glowanet.tools.unit.entity.BaseEntityTester;
import com.glowanet.tools.unit.entity.BaseEntityTesterTest;
import com.glowanet.util.junit.TestResultHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static com.glowanet.util.junit.TestResultHelper.WITH_ERROR;

@RunWith(Parameterized.class)
public class EntitySerialWrongSerialVersionUidTesterTest<T extends DataEntityWrongSerialVersionUid> extends BaseEntityTesterTest<T> {

    @Parameterized.Parameters
    public static Object[] data() {
        return prepareParameterCheckSVUID();
    }

    @Override
    protected BaseEntityTester<T> prepareEntityUnitTester(Class<T> typeOfO2T) {
        EntitySerialWrongSerialVersionUidTester<T> o2T = new EntitySerialWrongSerialVersionUidTester<>();
        o2T._setCheckSVUID(parameterCheckSVUID);
        return o2T;
    }

    @Test
    public void testValidateSerialVersionUID_wrongSerialVersionUid_raise_exception() {
        BaseEntityTester<?> o2T = prepareEntityUnitTester((Class<T>) DataEntityWrongSerialVersionUid.class);

        o2T._validateSerialVersionUID();

        TestResultHelper.verifyCollector(o2T, WITH_ERROR);
    }
}
