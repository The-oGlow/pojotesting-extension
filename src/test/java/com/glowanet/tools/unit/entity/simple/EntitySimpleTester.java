package com.glowanet.tools.unit.entity.simple;

import com.glowanet.data.entity.simple.DataEntitySimple;
import com.glowanet.tools.unit.entity.BaseEntityTester;
import org.junit.Ignore;

/**
 * A concrete tester, which verifies the entity {@code T}.
 *
 * @param <T> the type of the entity which will be tested
 */
@Ignore("Do not call this as test class!!")
class EntitySimpleTester<T extends DataEntitySimple> extends BaseEntityTester<T> {

    public EntitySimpleTester() {
        super((Class<T>) DataEntitySimple.class);
    }
}
