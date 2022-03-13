package com.glowanet.tools.unit.entity;

import com.glowanet.tools.unit.entity.data.DataEntityUnitTesterSerializable.ClazzNoSerializable;
import com.glowanet.tools.unit.entity.data.DataEntityUnitTesterSerializable.ClazzWithSerialVersionUid;
import com.glowanet.tools.unit.entity.data.DataEntityUnitTesterSerializable.ClazzWithSerializableNoSerialVersionUid;
import com.glowanet.tools.unit.entity.data.DataEntityUnitTesterSerializable.ClazzWithWrongSerialVersionUid;
import org.junit.Ignore;

@SuppressWarnings("UnconstructableJUnitTestCase")
@Ignore("Do not call this call directly!")
class ConcreteEntityUnitTester<T extends ClazzNoSerializable> extends AbstractEntityUnitTester<T> {

    protected ConcreteEntityUnitTester(Class<T> typeOfT) {
        super(typeOfT);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected T createObject2Test() {
        ClazzNoSerializable newO2T = null;
        if (ClazzNoSerializable.class.equals(getTypeOfo2T())) {
            newO2T = new ClazzNoSerializable();
        } else if (ClazzWithSerializableNoSerialVersionUid.class.equals(getTypeOfo2T())) {
            newO2T = new ClazzWithSerializableNoSerialVersionUid();
        } else if (ClazzWithWrongSerialVersionUid.class.equals(getTypeOfo2T())) {
            newO2T = new ClazzWithWrongSerialVersionUid();
        } else if (ClazzWithSerialVersionUid.class.equals(getTypeOfo2T())) {
            newO2T = new ClazzWithSerialVersionUid();
        }
        return (T) newO2T;
    }
}
