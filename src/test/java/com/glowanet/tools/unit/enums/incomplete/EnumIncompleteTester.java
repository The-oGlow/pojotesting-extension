package com.glowanet.tools.unit.enums.incomplete;

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
class EnumIncompleteTester<E extends DataEnums> extends BaseEnumTester<E> {

    /* constructors */
    public EnumIncompleteTester() {
        super((Class<E>) DataEnums.class);
    }

    @Override
    protected List<String> enumObjectsToIgnoreForCode() {
        return List.of();
    }
}
