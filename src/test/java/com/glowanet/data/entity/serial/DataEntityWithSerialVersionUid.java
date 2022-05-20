package com.glowanet.data.entity.serial;

import com.glowanet.tools.unit.entity.EntityUnitTester;

/**
 * A serializable class with a correct unique id.
 */
public class DataEntityWithSerialVersionUid extends DataEntityNotSerialVersionUid {
    // static fields
    private static final long serialVersionUID = EntityUnitTester.SERIAL_VERSION_UID_INVALID_RANGE.getRight() + 1;
    // end - static fields
}
