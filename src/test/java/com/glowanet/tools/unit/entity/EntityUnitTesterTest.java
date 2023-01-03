package com.glowanet.tools.unit.entity;

import com.glowanet.data.simple.DataSimple;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class EntityUnitTesterTest {

    private static class EntityUnitTesterTestClazz extends EntityUnitTester<DataSimple> {

        public EntityUnitTesterTestClazz() {
            super(DataSimple.class);
        }
    }

    @Test
    public void testCreate() {
        EntityUnitTesterTest.EntityUnitTesterTestClazz o2T = new EntityUnitTesterTest.EntityUnitTesterTestClazz();
        assertThat(o2T, notNullValue());
        assertThat(o2T.getClass(), equalTo(EntityUnitTesterTest.EntityUnitTesterTestClazz.class));
    }
}
