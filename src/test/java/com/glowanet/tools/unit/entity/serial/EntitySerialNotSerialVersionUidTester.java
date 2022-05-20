package com.glowanet.tools.unit.entity.serial;

import com.glowanet.data.entity.serial.DataEntityNotSerialVersionUid;
import com.glowanet.tools.unit.entity.BaseEntityTester;
import org.junit.Ignore;

@Ignore("Do not call this as test class!!")
class EntitySerialNotSerialVersionUidTester<T extends DataEntityNotSerialVersionUid> extends BaseEntityTester<T> {

    public EntitySerialNotSerialVersionUidTester() {
        super((Class<T>) DataEntityNotSerialVersionUid.class);
    }
}
