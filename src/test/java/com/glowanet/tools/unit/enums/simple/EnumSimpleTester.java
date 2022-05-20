package com.glowanet.tools.unit.enums.simple;

import com.glowanet.data.enums.DataEnumsCode;
import com.glowanet.tools.unit.enums.BaseEnumCodeTester;
import org.junit.Ignore;

import java.util.List;

/**
 * A concrete tester, which verifies the enum {@code E}.
 *
 * @param <E> the type of the enum which will be tested
 */
@Ignore("Do not call this as test class!!")
class EnumSimpleTester<E extends DataEnumsCode> extends BaseEnumCodeTester<E> {

    public EnumSimpleTester() {
        super((Class<E>) DataEnumsCode.class);
    }

    @Override
    protected List<String> enumObjectsToIgnoreForCode() {
        return List.of();
    }
}
