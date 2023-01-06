package com.glowanet.tools.unit;

import com.glowanet.data.enums.DataEnums;
import com.glowanet.data.simple.DataSimple;
import com.glowanet.util.junit.TestResultHelper;
import com.glowanet.util.reflect.ReflectionHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatchersExtend.isInstanceOfExact;

@RunWith(Parameterized.class)
public class AbstractUnitTesterTest {

    public static final String FIELD_TYPE_OF_O2T = "typeOfo2T";

    private static class AbstractUnitTesterTestDataSimple extends AbstractUnitTester<DataSimple> {

        public AbstractUnitTesterTestDataSimple() {
            super(DataSimple.class);
        }
    }

    @Parameterized.Parameters(name = "{index}: clazz:{0}, expected:{1}")
    public static List<Object[]> data() {
        return Arrays.asList(
                new Object[]{null, false},
                new Object[]{100L, false},
                new Object[]{DataEnums.A0, false},
                new Object[]{DataEnums.A0.getClass(), true}
        );
    }

    @Parameterized.Parameter
    public Object  testObject;
    @Parameterized.Parameter(1)
    public boolean expectedResult;

    private AbstractUnitTesterTestDataSimple o2T;

    @Before
    public void setup() {
        o2T = new AbstractUnitTesterTestDataSimple();
    }

    @Test
    public void testCreate() {
        assertThat(o2T, isInstanceOfExact(AbstractUnitTesterTestDataSimple.class));
    }

    @Test
    public void testCreateObject2Test_return_object() {
        DataSimple actual = o2T.createObject2Test();
        assertThat(actual, isInstanceOfExact(DataSimple.class));
    }

    @Test
    public void testCreateObject2Test_withNull_raise_exception() {
        ReflectionHelper.writeField(FIELD_TYPE_OF_O2T, o2T, null);
        TestResultHelper.verifyException(() -> o2T.createObject2Test(), AssertionError.class, AbstractUnitTester.MSG_CANNOT_CREATE_FROM_NULL);
    }

    @Test
    public void testCreateObject2Test_withWrongType_raise_exception() {
        ReflectionHelper.writeField(FIELD_TYPE_OF_O2T, o2T, Integer.class);
        TestResultHelper.verifyException(() -> o2T.createObject2Test(), AssertionError.class, AbstractUnitTester.MSG_CANNOT_CREATE_FROM_TYPE);
    }

    @Test
    public void testIsEnum() {
        boolean actual = o2T.isEnum(testObject);
        assertThat(actual, equalTo(expectedResult));
    }

    @Test
    public void testRetrieveDefaultValue_byType_return_integer() {
        Object actual = o2T.retrieveDefaultValue(Integer.class);
        assertThat(actual, isInstanceOfExact(Integer.class));
    }

    @Test
    public void testRetrieveDefaultValue_notByType_return_boolean() {
        Object actual = o2T.retrieveDefaultValue(Boolean.class);
        assertThat(actual, isInstanceOfExact(Boolean.class));
    }

    @Test
    public void testSetFinalStatic() {
        TestResultHelper.verifyException(
                () -> AbstractUnitTesterTestDataSimple.setFinalStatic(null, null, null),
                UnsupportedOperationException.class);
    }
}

