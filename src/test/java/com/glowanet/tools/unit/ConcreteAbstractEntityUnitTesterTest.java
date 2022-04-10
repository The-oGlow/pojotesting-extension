package com.glowanet.tools.unit;

import com.glowanet.tools.unit.entity.data.DataEntityUnitTester;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToObject;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.not;

/**
 * Unit Tests to verify {@code com.glowanet.tools.unit.ConcreteEntityUnitTester}.
 *
 * @see ConcreteAbstractEntityUnitTester
 * @deprecated Will be removed with {@code com.glowanet.tools.unit.AbstractEntityUnitTester}
 */
@Deprecated(forRemoval = true, since = "1.0")
public class ConcreteAbstractEntityUnitTesterTest {

    /* fields */
    private ConcreteAbstractEntityUnitTester o2T;
    /* end - fields */

    /* methods */
    @Before
    public void setUp() {
        o2T = new ConcreteAbstractEntityUnitTester();
    }

    @Test
    public void testCreateObject2Test_return_newCreatedObject() {
        Object actual = o2T.createObject2Test();
        Object actual2 = o2T.createObject2Test();
        assertThat(actual, isA(DataEntityUnitTester.class));
        assertThat(actual2, isA(DataEntityUnitTester.class));
        assertThat(actual, not(equalToObject(actual2)));
    }
    /* end - methods */
}
