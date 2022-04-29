package com.glowanet.tools.unit.entity.serial;

import com.glowanet.data.entity.serial.DataEntityWrongSerialVersionUid;
import com.glowanet.tools.unit.entity.BaseEntityTester;
import org.junit.Ignore;

@Ignore("Do not call this as test class!!")
class EntitySerialWrongSerialVersionUidTester<T extends DataEntityWrongSerialVersionUid> extends BaseEntityTester<T> {

    public EntitySerialWrongSerialVersionUidTester() {
        super((Class<T>) DataEntityWrongSerialVersionUid.class);
    }
}
