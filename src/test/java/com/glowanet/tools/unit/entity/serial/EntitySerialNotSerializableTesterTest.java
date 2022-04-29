package com.glowanet.tools.unit.entity.serial;

import com.glowanet.data.entity.serial.DataEntityNotSerialVersionUid;
import com.glowanet.data.entity.serial.DataEntityNotSerializable;
import com.glowanet.tools.unit.entity.BaseEntityTester;
import com.glowanet.tools.unit.entity.BaseEntityTesterTest;
import com.glowanet.util.junit.TestResultHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static com.glowanet.util.junit.TestResultHelper.NO_ERROR;

@RunWith(Parameterized.class)
public class EntitySerialNotSerializableTesterTest<T extends DataEntityNotSerializable> extends BaseEntityTesterTest<T> {

    @Parameterized.Parameters
    public static Object[] data() {
        return prepareParameterCheckSVUID();
    }

    @Override
    protected BaseEntityTester<T> prepareEntityUnitTester(Class<T> typeOfO2T) {
        EntitySerialNotSerializableTester<T> o2T = new EntitySerialNotSerializableTester<>();
        o2T._setCheckSVUID(parameterCheckSVUID);
        return o2T;
    }

    @Test
    public void testValidateSerialVersionUID_notSerializable_raise_noException() {
        BaseEntityTester<?> o2T = prepareEntityUnitTester((Class<T>) DataEntityNotSerialVersionUid.class);

        o2T._validateSerialVersionUID();

        TestResultHelper.verifyCollector(o2T, NO_ERROR);
    }
}
