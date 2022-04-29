package com.glowanet.tools.unit.entity.serial;

import com.glowanet.data.entity.serial.DataEntityWithSerialVersionUid;
import com.glowanet.tools.unit.entity.BaseEntityTester;
import org.junit.Ignore;

@Ignore("Do not call this as test class!!")
class EntitySerialWithSerialVersionUidTester<T extends DataEntityWithSerialVersionUid> extends BaseEntityTester<T> {

    public EntitySerialWithSerialVersionUidTester() {
        super((Class<T>) DataEntityWithSerialVersionUid.class);
    }
}
