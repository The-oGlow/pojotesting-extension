package com.glowanet.tools.unit;

import com.glowanet.util.junit.rules.ErrorCollectorExt;
import org.apache.commons.lang3.tuple.MutableTriple;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assume.assumeThat;

@RunWith(Parameterized.class)
public class ClazzAdapterTest {

    private static class ClazzAdapterTestPojo {

        private int    intInt;
        private String stringString;
        private Number numberNumber;

        public int getIntInt() {
            return intInt;
        }

        public void setIntInt(int intInt) {
            this.intInt = intInt;
        }

        public String getStringString() {
            return stringString;
        }

        public void setStringString(String stringString) {
            this.stringString = stringString;
        }

        public Number getNumberNumber() {
            return numberNumber;
        }

        public void setNumberNumber(Number numberNumber) {
            this.numberNumber = numberNumber;
        }
    }

    protected static class TestSet<M extends Number> extends MutableTriple<Object, Matcher<M>, Boolean> {

        /**
         * @param testObject Testobject for {@link #testCompareTo()}
         * @param matcher    Matcher for {@link #testCompareTo()}
         * @param result     Expected result for {@link #testEquals()}
         */
        protected TestSet(Object testObject, Matcher<M> matcher, Boolean result) {
            super(testObject, matcher, result);
        }
    }

    @Parameterized.Parameters(name = "{index}: testSet:{0}")
    public static List<TestSet<Number>> dataForTest() {
        return Arrays.asList(
                new TestSet<>(ClazzAdapterTestPojo.class, equalTo(0), false),
                new TestSet<>(ClazzAdapterTest.class, not(equalTo(0)), false),
                new TestSet<>(ClazzAdapter.class, not(equalTo(0)), false),
                new TestSet<>(Object.class, not(equalTo(0)), false),
                new TestSet<>(int.class, not(equalTo(0)), false),
                new TestSet<>(Integer.class, not(equalTo(0)), false),
                new TestSet<>(Number.class, not(equalTo(0)), false),
                new TestSet<>(String.class, not(equalTo(0)), false),
                new TestSet<>(CharSequence.class, not(equalTo(0)), false),
                new TestSet<>(null, not(equalTo(0)), false),
                new TestSet<>("NOTACLAZZ", not(equalTo(99)), false),
                new TestSet<>(ClazzAdapter.newI(ClazzAdapterTestPojo.class), not(equalTo(99)), true)
        );
    }

    @Parameterized.Parameter
    public TestSet<Number> itemForTest;

    private ClazzAdapter o2T;

    @Before
    public void setUp() {
        o2T = ClazzAdapter.newI(ClazzAdapterTestPojo.class);
    }

    @Rule
    public final ErrorCollectorExt collector = new ErrorCollectorExt();

    @Test
    public void testCompareTo() {
        assumeThat(itemForTest.getLeft(), anyOf(nullValue(), instanceOf(Class.class)));
        for (var idx = 0; idx <= 10; idx++) {
            ClazzAdapter thisO2T = ClazzAdapter.newI(ClazzAdapterTestPojo.class);
            Object typedItemForTest = itemForTest.getLeft();
            Matcher<Number> expected = itemForTest.getMiddle();
            int actual = thisO2T.compareTo((Class<?>) typedItemForTest);
            collector.checkThat(
                    String.format("%s: not matching o2T:%s with actual:%s",
                            idx, thisO2T.hashCode(), itemForTest.getLeft() == null ? "null" : itemForTest.getLeft().hashCode()),
                    actual, expected
            );
        }
    }

    @Test
    public void testCompareToWithItself() {
        boolean actual = o2T.equals(o2T);
        assertThat(actual, equalTo(true));
    }

    @Test
    public void testEquals() {
        boolean actual = o2T.equals(itemForTest.getLeft());
        assertThat(actual, equalTo(itemForTest.getRight()));
    }

    @Test
    public void testHashCode() {
        int actual = o2T.hashCode();
        assertThat(actual, greaterThan(0));
    }

    @Test
    public void testToString() {
        String actual = o2T.toString();
        assertThat(actual, containsString(ClazzAdapterTestPojo.class.getName()));
    }

    @Test
    public void testGetClazz() {
        Class<?> actual = o2T.getClazz();
        assertThat(actual, equalTo(ClazzAdapterTestPojo.class));
    }
}
