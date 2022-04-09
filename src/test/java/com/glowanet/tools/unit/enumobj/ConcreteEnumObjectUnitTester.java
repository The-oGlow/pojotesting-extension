package com.glowanet.tools.unit.enumobj;

import com.glowanet.tools.unit.enumobj.data.DataEnumObjectUnitTesterIncorrect;
import org.junit.Ignore;

import java.util.List;

/**
 * A concrete tester, which verifies the enum {@code E}.
 *
 * @param <E> the type of the enum which will be tested
 */
@Ignore("Do not call this as test class!!")
public class ConcreteEnumObjectUnitTester<
        E extends DataEnumObjectUnitTesterIncorrect> extends EnumObjectUnitTester<E> {

    /* constructors */
    public ConcreteEnumObjectUnitTester() {
        super((Class<E>) DataEnumObjectUnitTesterIncorrect.class);
    }

    @Override
    protected List<String> enumObjectsToIgnoreForCode() {
        return List.of();
    }
}
