package com.glowanet.tools.unit.entity.tostring;

import com.glowanet.data.entity.tostring.DataEntityToStringImplemented;
import com.glowanet.tools.unit.entity.BaseEntityTester;
import org.junit.Ignore;

/**
 * A concrete tester, which verifies the entity {@code T}.
 *
 * @param <T> the type of the entity which will be tested
 */
@Ignore("Do not call this as test class!!")
class EntityToStringImplementedTester<T extends DataEntityToStringImplemented> extends BaseEntityTester<T> {
    // constructors

    public EntityToStringImplementedTester() {
        super((Class<T>) DataEntityToStringImplemented.class);
    }
// end - constructors
}
