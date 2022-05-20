package com.glowanet.data.entity.equal;

import com.glowanet.data.entity.BaseDataEntity;

/**
 * Test Object with
 * <ul>
 *     <li>a default {@code #equals()}- & {@code #hashCode()}-method</li>
 *     <li>an implemented {@code #toString()}-method.</li>
 * </ul>
 */
public class DataEntityGenericEquals extends BaseDataEntity {

    // methods
    @Override
    public String toString() {
        return _toString();
    }
// end - methods
}
