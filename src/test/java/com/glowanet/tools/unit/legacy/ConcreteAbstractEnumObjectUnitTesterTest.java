package com.glowanet.tools.unit.legacy;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;

/**
 * Unit Tests to verify {@code com.glowanet.tools.unit.ConcreteEnumUnitTester}.
 *
 * @see ConcreteAbstractEnumUnitTester
 * @deprecated Will be removed with {@code com.glowanet.tools.unit.AbstractEnumObjectUnitTester}
 */
@Deprecated(forRemoval = true, since = "1.0")
public class ConcreteAbstractEnumObjectUnitTesterTest {

    /* fields */
    private ConcreteAbstractEnumUnitTester o2T;
    /* end - fields */

    /* methods */
    @Before
    public void setUp() {
        o2T = new ConcreteAbstractEnumUnitTester();
    }

    @Test
    public void testGetObject2Test_return_null() {
        Object actual = o2T.getObject2Test();
        Object actual2 = o2T.getObject2Test();
        assertThat(actual, nullValue());
        assertThat(actual2, nullValue());
    }
    /* end - methods */
}
