package com.glowanet.tools.unit.entity;

import com.glowanet.tools.unit.entity.data.DataEntityUnitTesterSerializable.ClazzNoSerializable;
import com.glowanet.tools.unit.entity.data.DataEntityUnitTesterSerializable.ClazzWithSerialVersionUid;
import com.glowanet.tools.unit.entity.data.DataEntityUnitTesterSerializable.ClazzWithSerializableNoSerialVersionUid;
import com.glowanet.tools.unit.entity.data.DataEntityUnitTesterSerializable.ClazzWithWrongSerialVersionUid;
import com.glowanet.util.junit.TestResultHelper;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ConcreteEntityUnitTesterSerializableTest extends AbstractEntityUnitTesterCommon {

    private ConcreteEntityUnitTester<? extends ClazzNoSerializable> o2T;

    @Before
    public void setUp() {
        o2T = new ConcreteEntityUnitTester<>(ClazzNoSerializable.class);
        o2T.setUp();
    }

    @Test
    public void testHasSerializableIF_return_true() {
        boolean actual = o2T.hasSerializableIF(ClazzWithSerializableNoSerialVersionUid.class);
        assertThat(actual, equalTo(true));
    }

    @Test
    public void testHasSerializableIF_return_false() {
        boolean actual = o2T.hasSerializableIF(ClazzNoSerializable.class);
        assertThat(actual, equalTo(false));
    }

    @Test
    public void testValidateSerialVersionUID_valid_NotSerializable() {
        o2T = new ConcreteEntityUnitTester<>(ClazzNoSerializable.class);

        o2T.validateSerialVersionUID();

        TestResultHelper.verifyCollectorNoError(o2T);
    }

    @Test
    public void testValidateSerialVersionUID_invalid_NoSerialVersionUID() {
        o2T = new ConcreteEntityUnitTester<>(ClazzWithSerializableNoSerialVersionUid.class);

        o2T.validateSerialVersionUID();

        TestResultHelper.verifyCollector(o2T, TestResultHelper.WITH_ERROR);
    }

    @Test
    public void testValidateSerialVersionUID_invalid_WrongSerialVersionUID() {
        o2T = new ConcreteEntityUnitTester<>(ClazzWithWrongSerialVersionUid.class);

        o2T.validateSerialVersionUID();

        TestResultHelper.verifyCollector(o2T, TestResultHelper.WITH_ERROR);
    }

    @Test
    public void testValidateSerialVersionUID_valid_CorrectSerialVersionUID() {
        o2T = new ConcreteEntityUnitTester<>(ClazzWithSerialVersionUid.class);

        o2T.validateSerialVersionUID();

        TestResultHelper.verifyCollectorNoError(o2T);
    }


    @Test
    public void test_testSerialVersionUIDIsCorrectInEntity() {
        o2T.testSerialVersionUIDIsCorrectInEntity();

        TestResultHelper.verifyCollectorNoError(o2T);
    }
}
