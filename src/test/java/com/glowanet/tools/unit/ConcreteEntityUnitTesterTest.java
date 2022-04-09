package com.glowanet.tools.unit;

import com.glowanet.tools.unit.data.DataUnitTester;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToObject;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.not;

/**
 * Unit Tests to verify {@code com.glowanet.tools.unit.ConcreteEntityUnitTester}.
 *
 * @see com.glowanet.tools.unit.ConcreteEntityUnitTester
 * @deprecated Will be removed with {@code com.glowanet.tools.unit.AbstractEntityUnitTester}
 */
@Deprecated(forRemoval = true, since = "1.0")
public class ConcreteEntityUnitTesterTest {

    /* fields */
    private ConcreteEntityUnitTester o2T;
    /* end - fields */

    /* methods */
    @Before
    public void setUp() {
        o2T = new ConcreteEntityUnitTester();
    }

    @Test
    public void testCreateObject2Test_return_newCreatedObject() {
        Object actual = o2T.createObject2Test();
        Object actual2 = o2T.createObject2Test();
        assertThat(actual, isA(DataUnitTester.class));
        assertThat(actual2, isA(DataUnitTester.class));
        assertThat(actual, not(equalToObject(actual2)));
    }
    /* end - methods */
}
