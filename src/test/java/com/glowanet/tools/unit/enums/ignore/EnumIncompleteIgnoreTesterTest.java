package com.glowanet.tools.unit.enums.ignore;

import com.glowanet.data.enums.DataEnums;
import com.glowanet.tools.unit.enums.BaseEnumTester;
import com.glowanet.tools.unit.enums.BaseEnumTesterTest;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * A junit test clazz, which verifies, that ignoring specific enums is working correctly.
 *
 * @param <E> the type of the enum which will be tested
 */
@RunWith(Parameterized.class)
public class EnumIncompleteIgnoreTesterTest<E extends DataEnums> extends BaseEnumTesterTest<E> {

    // constructors
    public EnumIncompleteIgnoreTesterTest() {
        super((Class<E>) DataEnums.class);
    }
// end - constructors

    // static method
    @Parameterized.Parameters
    public static Object[] data() {
        return prepareParameterCodeCheckEnabled();
    }
// end - static method

    // methods
    @Override
    protected BaseEnumTester<E> prepareEnumTester() {
        EnumIncompleteIgnoreTester<E> o2T = new EnumIncompleteIgnoreTester<>();
        o2T._setCodeCheckEnabled(parameterCodeCheckEnabled);
        return o2T;
    }
// end - methods
}
