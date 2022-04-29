package com.glowanet.tools.unit.enums.ignore;

import com.glowanet.data.enums.DataEnums;
import com.glowanet.tools.unit.enums.BaseEnumTester;
import org.junit.Ignore;

import java.util.List;

/**
 * A concrete tester, which verifies the entity {@code E}.
 *
 * @param <E> the type of the enum which will be tested
 */
@Ignore("Do not call this as test class!!")
class EnumIncompleteIgnoreTester<E extends DataEnums> extends BaseEnumTester<E> {

    public EnumIncompleteIgnoreTester() {
        super((Class<E>) DataEnums.class);
    }

    @Override
    protected List<String> enumObjectsToIgnoreForCode() {
        return List.of(DataEnums.ITEMNAME_IGNORED);
    }
}
