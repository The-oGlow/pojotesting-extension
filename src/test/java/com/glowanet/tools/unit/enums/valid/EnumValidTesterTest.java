package com.glowanet.tools.unit.enums.valid;

import com.glowanet.data.enums.DataEnumsCode;
import com.glowanet.tools.unit.enums.BaseEnumCodeTesterTest;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * A junit test class, which verifies, that complete enums are identified as valid.
 *
 * @param <E> the type of the enum which will be tested
 */
@RunWith(Parameterized.class)
public class EnumValidTesterTest<E extends DataEnumsCode> extends BaseEnumCodeTesterTest<E> {

    // constructors
    public EnumValidTesterTest() {
        super((Class<E>) DataEnumsCode.class);
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
    protected EnumValidTester<E> prepareEnumTester() {
        EnumValidTester<E> o2T = new EnumValidTester<>();
        o2T._setCodeCheckEnabled(parameterCodeCheckEnabled);
        return o2T;
    }
// end - methods
}