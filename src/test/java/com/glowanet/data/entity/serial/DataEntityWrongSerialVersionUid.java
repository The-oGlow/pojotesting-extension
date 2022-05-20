package com.glowanet.data.entity.serial;

import com.glowanet.tools.unit.entity.EntityUnitTester;

/**
 * A serializable class with a wrong unique id.
 */
public class DataEntityWrongSerialVersionUid extends DataEntityNotSerialVersionUid {
    // static fields
    private static final long serialVersionUID = EntityUnitTester.SERIAL_VERSION_UID_INVALID_RANGE.getLeft();
    // end - static fields
}
