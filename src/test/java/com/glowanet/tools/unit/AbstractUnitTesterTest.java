package com.glowanet.tools.unit;

import com.glowanet.data.simple.DataSimple;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class AbstractUnitTesterTest {

    private static class AbstractUnitTesterTestClazz extends AbstractUnitTester<DataSimple> {

        public AbstractUnitTesterTestClazz() {
            super(DataSimple.class);
        }
    }

    @Test
    public void testCreate() {
        AbstractUnitTesterTestClazz o2T = new AbstractUnitTesterTestClazz();
        assertThat(o2T, notNullValue());
        assertThat(o2T.getClass(), equalTo(AbstractUnitTesterTestClazz.class));
    }
}

