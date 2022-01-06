package com.glowanet.tools.unit;

import java.util.List;

public class AbstractEnumObjectUnitTesterTest extends AbstractEntityUnitTesterTest {

    /**
     * Container class, which holds the class which will be tested.
     *
     * @param <K> the type of the class to test
     */
    @SuppressWarnings("UnconstructableJUnitTestCase")
    static class AbstractEntityUnitTesterClazz<K> extends AbstractEnumObjectUnitTester<K> {

        protected AbstractEntityUnitTesterClazz(Class<K> typeOfT) {
            super(typeOfT);
        }

        @Override
        protected List<String> enumObjectsToIgnoreForCode() {
            return List.of();
        }
    }

    AbstractEntityUnitTesterClazz<? extends AbstractEntityUnitTesterDefaultEquals> o2T;
}
