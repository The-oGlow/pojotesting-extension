package com.glowanet.tools.unit.entity.serial;

import com.glowanet.data.entity.serial.DataEntityNotSerializable;
import com.glowanet.tools.unit.entity.BaseEntityTester;
import org.junit.Ignore;

@Ignore("Do not call this as test class!!")
class EntitySerialNotSerializableTester<T extends DataEntityNotSerializable> extends BaseEntityTester<T> {

    public EntitySerialNotSerializableTester() {
        super((Class<T>) DataEntityNotSerializable.class);
    }
}
