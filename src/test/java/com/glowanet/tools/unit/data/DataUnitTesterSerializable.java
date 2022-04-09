package com.glowanet.tools.unit.data;

import com.glowanet.tools.unit.entity.EntityUnitTester;
import com.glowanet.tools.unit.entity.data.DataEntityUnitTester;

import java.io.Serializable;

/**
 * Container class, which holds the variants for serializable classes.
 */
public class DataUnitTesterSerializable {
    /**
     * A class not serializable.
     */
    public static class ClazzNoSerializable extends DataEntityUnitTester {
    }

    /**
     * A serializable class without the unique id.
     */
    public static class ClazzWithSerializableNoSerialVersionUid extends ClazzNoSerializable implements Serializable {
    }

    /**
     * A serializable class with a wrong unique id.
     */
    public static class ClazzWithWrongSerialVersionUid extends ClazzWithSerializableNoSerialVersionUid {
        // static fields
        private static final long serialVersionUID = EntityUnitTester.SERIAL_VERSION_UID_INVALID_RANGE.getLeft();
        // end - static fields
    }

    /**
     * A serializable class with a correct unique id.
     */
    public static class ClazzWithSerialVersionUid extends ClazzWithSerializableNoSerialVersionUid {
        // static fields
        private static final long serialVersionUID = EntityUnitTester.SERIAL_VERSION_UID_INVALID_RANGE.getRight() + 1;
        // end - static fields
    }
}
