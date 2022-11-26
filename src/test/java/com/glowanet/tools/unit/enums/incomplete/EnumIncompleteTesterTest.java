package com.glowanet.tools.unit.enums.incomplete;

import com.glowanet.data.enums.DataEnums;
import com.glowanet.tools.unit.enums.BaseEnumTester;
import com.glowanet.tools.unit.enums.BaseEnumTesterTest;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * A junit test clazz, which verifies, that incomplete enums will be recognized.
 *
 * @param <E> the type of the enum which will be tested
 */
@RunWith(Parameterized.class)
public class EnumIncompleteTesterTest<E extends DataEnums> extends BaseEnumTesterTest<E> {

    // constructors
    public EnumIncompleteTesterTest() {
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
    @Before
    public void setUp() {
    }

    @Override
    protected BaseEnumTester<E> prepareEnumTester() {
        EnumIncompleteTester<E> o2T = new EnumIncompleteTester<>();
        o2T._setCodeCheckEnabled(parameterCodeCheckEnabled);
        return o2T;
    }
// end - methods

}