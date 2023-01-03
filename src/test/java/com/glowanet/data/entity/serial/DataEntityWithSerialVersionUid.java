package com.glowanet.data.entity.serial;

import com.glowanet.tools.unit.entity.EntityUnitTester;

/**
 * A serializable clazz with a correct unique id.
 */
public class DataEntityWithSerialVersionUid extends DataEntityNotSerialVersionUid {

    private static final long serialVersionUID = EntityUnitTester.SERIAL_VERSION_UID_INVALID_RANGE.getRight() + 1;

}
