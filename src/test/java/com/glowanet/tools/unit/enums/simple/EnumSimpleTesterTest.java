package com.glowanet.tools.unit.enums.simple;

import com.glowanet.data.enums.DataEnumsCode;
import com.glowanet.tools.unit.enums.BaseEnumCodeTesterTest;
import com.glowanet.tools.unit.enums.BaseEnumTester;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class EnumSimpleTesterTest<E extends DataEnumsCode> extends BaseEnumCodeTesterTest<E> {

    // constructors
    public EnumSimpleTesterTest() {
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
    protected BaseEnumTester<E> prepareEnumTester() {
        EnumSimpleTester<E> o2T = new EnumSimpleTester<>();
        o2T._setCodeCheckEnabled(parameterCodeCheckEnabled);
        return o2T;
    }
// end - methods
}
