package com.glowanet.tools.unit;

import com.glowanet.util.junit.rules.ErrorCollectorExt;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.not;
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

    @Parameterized.Parameters(name = "{index}: left:{0}, right:{0}")
    public static List<Pair<Object, List<?>>> dataForTest() {
        return Arrays.asList(
                new ImmutablePair<>(ClazzAdapterTestPojo.class, Arrays.asList(equalTo(0), false)),
                new ImmutablePair<>(ClazzAdapterTest.class, Arrays.asList(not(equalTo(0)), false)),
                new ImmutablePair<>(ClazzAdapter.class, Arrays.asList(not(equalTo(0)), false)),
                new ImmutablePair<>(Object.class, Arrays.asList(not(equalTo(0)), false)),
                new ImmutablePair<>(int.class, Arrays.asList(not(equalTo(0)), false)),
                new ImmutablePair<>(Integer.class, Arrays.asList(not(equalTo(0)), false)),
                new ImmutablePair<>(Number.class, Arrays.asList(not(equalTo(0)), false)),
                new ImmutablePair<>(String.class, Arrays.asList(not(equalTo(0)), false)),
                new ImmutablePair<>(CharSequence.class, Arrays.asList(not(equalTo(0)), false)),
                new ImmutablePair<>(null, Arrays.asList(not(equalTo(0)), false)),
                new ImmutablePair<>(ClazzAdapter.newI(ClazzAdapterTestPojo.class), Arrays.asList(not(equalTo(99)), true))
        );
    }

    @Parameterized.Parameter
    public Pair<Object, List<?>> itemForTest;

    private ClazzAdapter o2T;

    @Before
    public void setUp() {
        o2T = ClazzAdapter.newI(ClazzAdapterTestPojo.class);
    }

    @Rule
    public final ErrorCollectorExt collector = new ErrorCollectorExt();

    @Test
    public void testCompareTo() {
        assumeThat(itemForTest.getLeft(), instanceOf(Class.class));
        for (var idx = 0; idx <= 10; idx++) {
            ClazzAdapter thisO2T = ClazzAdapter.newI(ClazzAdapterTestPojo.class);
            Class<?> typedItemForTest = (Class<?>) itemForTest.getLeft();
            Matcher<Integer> expected = (Matcher<Integer>) itemForTest.getRight().get(0);
            int actual = thisO2T.compareTo(typedItemForTest);
            collector.checkThat(
                    String.format("%s: not matching o2T:%s with actual:%s", idx, thisO2T.hashCode(), itemForTest.getLeft().hashCode()),
                    actual, expected
            );
        }
    }

    @Test
    public void testEquals() {
        boolean actual = o2T.equals(itemForTest.getLeft());
        assertThat(actual, equalTo(itemForTest.getRight().get(1)));
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