package com.glowanet.tools.unit.entity.data;

import com.glowanet.tools.unit.entity.AbstractEntityUnitTester;

import java.io.Serializable;

public class DataEntityUnitTesterSerializable {
    public static class ClazzNoSerializable extends DataEntityUnitTester {
    }

    public static class ClazzWithSerializableNoSerialVersionUid extends ClazzNoSerializable implements Serializable {
    }

    public static class ClazzWithWrongSerialVersionUid extends ClazzWithSerializableNoSerialVersionUid {
        private static final long serialVersionUID = AbstractEntityUnitTester.SERIAL_VERSION_UID_INVALID_RANGE.getLeft();
    }

    public static class ClazzWithSerialVersionUid extends ClazzWithSerializableNoSerialVersionUid {
        private static final long serialVersionUID = AbstractEntityUnitTester.SERIAL_VERSION_UID_INVALID_RANGE.getRight() + 1;
    }
}
