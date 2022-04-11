package com.glowanet.tools.unit.enumobj;

import com.glowanet.tools.unit.enumobj.data.DataEnumObjectUnitTester;
import org.junit.Ignore;

import java.util.List;

@SuppressWarnings("UnconstructableJUnitTestCase")
@Ignore("Do not call this as test class!!")
public class ConcreteEnumObjectUnitTesterIgnore<
        E extends DataEnumObjectUnitTester> extends SimulationEnumObjectTester<E> {

    protected ConcreteEnumObjectUnitTesterIgnore(Class<E> typeOfo2E) {
        super(typeOfo2E);
    }

    @Override
    protected List<String> enumObjectsToIgnoreForCode() {
        return List.of(DataEnumObjectUnitTester.ITEMNAME_IGNORED);
    }
}
