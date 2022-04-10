package com.glowanet.tools.unit.enumobj;

import com.glowanet.tools.unit.enumobj.data.DataEnumObjectUnitTesterCounter;
import org.junit.Ignore;

import java.util.List;

/**
 * A concrete tester, which verifies the enum {@code E}.
 *
 * @param <E> the type of the enum which will be tested
 */
@Ignore("Do not call this as test class!!")
public class ConcreteEnumObjectUnitTesterCounter<
        E extends DataEnumObjectUnitTesterCounter> extends EnumObjectUnitTester<E> {

    public ConcreteEnumObjectUnitTesterCounter() {
        super((Class<E>) DataEnumObjectUnitTesterCounter.class);
    }

    @Override
    protected List<String> enumObjectsToIgnoreForCode() {
        return List.of();
    }
}
