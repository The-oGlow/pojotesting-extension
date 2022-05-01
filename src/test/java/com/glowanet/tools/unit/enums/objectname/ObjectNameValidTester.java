package com.glowanet.tools.unit.enums.objectname;

import com.glowanet.data.enums.DataEnums;
import com.glowanet.tools.unit.enums.BaseEnumTester;
import org.junit.Ignore;

import java.util.List;

/**
 * A concrete tester, which verifies the enum {@code E}.
 *
 * @param <E> the type of the enum which will be tested
 */
@Ignore("Do not call this as test class!!")
class ObjectNameValidTester<E extends DataEnums> extends BaseEnumTester<E> {

    public ObjectNameValidTester() {
        super((Class<E>) DataEnums.class);
    }

    @Override
    protected List<String> enumObjectsToIgnoreForCode() {
        return List.of();
    }
}
