package com.glowanet.tools.unit.entity.serial;

import com.glowanet.data.entity.serial.DataEntityWithSerialVersionUid;
import com.glowanet.tools.unit.entity.BaseEntityTester;
import com.glowanet.tools.unit.entity.BaseEntityTesterTest;
import com.glowanet.util.junit.TestResultHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class EntitySerialWithSerialVersionUidTesterTest<T extends DataEntityWithSerialVersionUid> extends BaseEntityTesterTest<T> {

    @Parameterized.Parameters
    public static Object[] data() {
        return prepareParameterCheckSVUID();
    }

    @Override
    protected BaseEntityTester<T> prepareEntityUnitTester(Class<T> typeOfO2T) {
        EntitySerialWithSerialVersionUidTester<T> o2T = new EntitySerialWithSerialVersionUidTester<>();
        o2T._setCheckSVUID(parameterCheckSVUID);
        return o2T;
    }

    @Test
    public void testValidateSerialVersionUID_withSerialVersionUid_raise_noException() {
        BaseEntityTester<?> o2T = prepareEntityUnitTester((Class<T>) DataEntityWithSerialVersionUid.class);

        o2T._validateSerialVersionUID();

        TestResultHelper.verifyCollectorNoError(o2T);
    }
}
