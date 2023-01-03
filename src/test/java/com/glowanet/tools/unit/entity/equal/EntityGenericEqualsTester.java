package com.glowanet.tools.unit.entity.equal;

import com.glowanet.data.entity.equal.DataEntityGenericEquals;
import com.glowanet.tools.unit.entity.BaseEntityTester;
import org.junit.Ignore;

/**
 * A concrete tester, which verifies the entity {@code T}.
 *
 * @param <T> the type of the entity which will be tested
 */
@Ignore("Do not call this as test class!!")
class EntityGenericEqualsTester<T extends DataEntityGenericEquals> extends BaseEntityTester<T> {

    public EntityGenericEqualsTester() {
        super((Class<T>) DataEntityGenericEquals.class);
    }
}
