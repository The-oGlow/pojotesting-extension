package com.glowanet.tools.unit.enumobj;

import com.glowanet.tools.unit.enumobj.data.DataEnumObjectUnitTesterCode;
import org.junit.Ignore;

import java.util.List;

/**
 * A concrete tester, which verifies the enum {@code E}.
 *
 * @param <E> the type of the enum which will be tested
 */
@Ignore("Do not call this as test class!!")
public class ConcreteEnumObjectUnitTesterCode<
        E extends DataEnumObjectUnitTesterCode> extends EnumObjectUnitTester<E> {

    public ConcreteEnumObjectUnitTesterCode() {
        super((Class<E>) DataEnumObjectUnitTesterCode.class);
    }

    @Override
    protected List<String> enumObjectsToIgnoreForCode() {
        return List.of();
    }
}
