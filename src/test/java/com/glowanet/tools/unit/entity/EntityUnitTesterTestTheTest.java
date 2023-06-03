package com.glowanet.tools.unit.entity;

import com.glowanet.data.simple.DataSimple;
import com.glowanet.util.junit.TestResultHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Verifies the test methods raises no errors.
 * <p>
 * Testing {@link EntityUnitTester}
 */
@RunWith(Parameterized.class)
public class EntityUnitTesterTestTheTest {

    private static class EntityUnitTesterTestTheTestClazz extends EntityUnitTester<DataSimple> {

        public EntityUnitTesterTestTheTestClazz() {
            super(DataSimple.class);
        }
    }

    private static EntityUnitTesterTestTheTestClazz o2T;

    @Before
    public void setUp() {
        o2T = new EntityUnitTesterTestTheTestClazz();
    }

    @Test
    public void testCreate() {
        assertThat(o2T, notNullValue());
        assertThat(o2T.getClass(), equalTo(EntityUnitTesterTestTheTestClazz.class));
    }

    @Parameterized.Parameters
    public static List<ThrowingRunnable> data() {
        return Arrays.asList(
                () -> o2T.testAllGetterAccessible(),
                () -> o2T.testAllSetterAccessible(),
                () -> o2T.testEqualsLogicalAreEqual(),
                () -> o2T.testEqualsNotTheSame(),
                () -> o2T.testEqualsWithItself(),
                () -> o2T.testEqualsWithNull(),
                () -> o2T.testGetterSetterCollaboration(),
                () -> o2T.testHashcodeOtherThan0(),
                () -> o2T.testSerialVersionUIDIsCorrectInEntity(),
                () -> o2T.testToString(),
                () -> o2T.testToStringWithValues()
        );
    }

    @Parameterized.Parameter
    public ThrowingRunnable runnableTest;

    @Test
    public void testTestSerialVersionUIDIsCorrectInEntity() {
        TestResultHelper.verifyNoException(runnableTest);
    }
}
