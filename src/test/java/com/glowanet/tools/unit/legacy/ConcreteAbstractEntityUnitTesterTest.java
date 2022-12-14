package com.glowanet.tools.unit.legacy;

import com.glowanet.data.entity.simple.DataEntitySimple;
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
    public void testGetObject2Test_return_newCreatedObject() {
        Object actual = o2T.getObject2Test();
        Object actual2 = o2T.getObject2Test();
        assertThat(actual, isA(DataEntitySimple.class));
        assertThat(actual2, isA(DataEntitySimple.class));
        assertThat(actual, not(equalToObject(actual2)));
    }
    /* end - methods */
}
