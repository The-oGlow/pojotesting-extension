package com.glowanet.tools.unit.enums.custom;

import com.glowanet.data.enums.DataEnumsCounter;
import com.glowanet.tools.unit.enums.EnumUnitTester;
import org.junit.Ignore;

import java.util.List;

/**
 * A concrete tester, which verifies the enum {@code E}.
 *
 * @param <E> the type of the enum which will be tested
 */
@Ignore("Do not call this as test class!!")
class ConcreteEnumUnitTesterCounter<E extends DataEnumsCounter> extends EnumUnitTester<E> {

    public ConcreteEnumUnitTesterCounter() {
        super((Class<E>) DataEnumsCounter.class);
    }

    @Override
    protected List<String> enumObjectsToIgnoreForCode() {
        return List.of();
    }
}
