package com.glowanet.tools.unit;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;

/**
 * Unit Tests to verify {@code  com.glowanet.tools.unit.ConcreteEnumObjectUnitTester}.
 *
 * @see ConcreteAbstractEnumObjectUnitTester
 * @deprecated Will be removed with {@code com.glowanet.tools.unit.AbstractEnumObjectUnitTester}
 */
@Deprecated(forRemoval = true, since = "1.0")
public class ConcreteAbstractEnumObjectUnitTesterTest {

    /* fields */
    private ConcreteAbstractEnumObjectUnitTester o2T;
    /* end - fields */

    /* methods */
    @Before
    public void setUp() {
        o2T = new ConcreteAbstractEnumObjectUnitTester();
    }

    @Test
    public void testCreateObject2Test_return_newCreatedObject() {
        Object actual = o2T.createObject2Test();
        Object actual2 = o2T.createObject2Test();
        assertThat(actual, nullValue());
        assertThat(actual2, nullValue());
    }
    /* end - methods */
}
