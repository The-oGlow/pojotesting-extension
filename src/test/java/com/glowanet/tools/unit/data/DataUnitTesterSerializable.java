package com.glowanet.tools.unit.data;

import com.glowanet.tools.unit.entity.AbstractEntityUnitTester;
import com.glowanet.tools.unit.entity.data.DataEntityUnitTester;

import java.io.Serializable;

public class DataUnitTesterSerializable {
    public static class ClazzNoSerializable extends DataEntityUnitTester {
    }

    public static class ClazzWithSerializableNoSerialVersionUid extends ClazzNoSerializable implements Serializable {
    }

    public static class ClazzWithWrongSerialVersionUid extends ClazzWithSerializableNoSerialVersionUid {
        // static fields
        private static final long serialVersionUID = AbstractEntityUnitTester.SERIAL_VERSION_UID_INVALID_RANGE.getLeft();
// end - static fields
    }

    public static class ClazzWithSerialVersionUid extends ClazzWithSerializableNoSerialVersionUid {
        // static fields
        private static final long serialVersionUID = AbstractEntityUnitTester.SERIAL_VERSION_UID_INVALID_RANGE.getRight() + 1;
// end - static fields
    }
}
