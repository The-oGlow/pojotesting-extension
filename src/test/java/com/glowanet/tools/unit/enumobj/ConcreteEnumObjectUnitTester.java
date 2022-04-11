package com.glowanet.tools.unit.enumobj;

import com.glowanet.tools.unit.enumobj.data.DataEnumObjectUnitTester;
import org.junit.Ignore;

import java.util.List;

/**
 * A concrete tester, which verifies the enum {@code E}.
 *
 * @param <E> the type of the enum which will be tested
 */
@SuppressWarnings("UnconstructableJUnitTestCase")
@Ignore("Do not call this as test class!!")
public class ConcreteEnumObjectUnitTester<
        E extends DataEnumObjectUnitTester> extends SimulationEnumObjectTester<E> {

    /* constructors */
    protected ConcreteEnumObjectUnitTester(Class<E> typeOfo2E) {
        super(typeOfo2E);
    }

    @Override
    protected List<String> enumObjectsToIgnoreForCode() {
        return List.of();
    }
}
