package com.glowanet.tools.unit.entity;

public class AbstractEntityUnitTesterTest {
/*

    protected static final String NOT_THROWN = "expected %s to be thrown, but nothing was thrown";

    protected ConcreteEntityGenericEqualsUnitTester<?> entityUnitTester;

    @Before
    public void setUp() {
        initWithEqual();
    }

    public void initWithoutEqual() {
        entityUnitTester = new ConcreteEntityGenericEqualsUnitTester<>(ConcreteEntityGenericEquals.class);
        entityUnitTester.setUp();
    }

    public void initWithEqual() {
        entityUnitTester = new ConcreteEntityGenericEqualsUnitTester<>(ConcreteEntityLogicalEquals.class);
        entityUnitTester.setUp();
    }

    public void verifyCollector(Object instance, int size) {
        Object actual = ReflectionHelper.readField("collector", instance);

        assertThat(actual, notNullValue());
        assertThat(actual, instanceOf(ErrorCollector.class));

        Object actualThrows = ReflectionHelper.readField("errors", actual);

        assertThat(actualThrows, notNullValue());
        assertThat(actualThrows, instanceOf(Collection.class));
        assertThat(((Collection<?>) actualThrows), hasSize(size));
    }

    public void verifyNoException(ThrowingRunnable instance) {
        AssertionError actual = assertThrows(AssertionError.class, () -> assertThrows(Throwable.class, instance));
        assertThat(actual, notNullValue());
        assertThat(actual.toString(), containsString(String.format(NOT_THROWN, Throwable.class.getName())));
    }

    public void verifyException(ThrowingRunnable instance, Class<?> expected) {
        Throwable actual = assertThrows(Throwable.class, instance);

        assertThat(actual, notNullValue());
        assertThat(actual, instanceOf(expected));
    }

    @Test
    public void test_createEntity_return_entity() {
        Object actual = entityUnitTester.createObject2Test();

        assertThat(actual, notNullValue());
        assertThat(actual, instanceOf(ConcreteEntityLogicalEquals.class));
    }

    @Test
    public void test_getEntity_return_null() {
        entityUnitTester = new ConcreteEntityGenericEqualsUnitTester<>(ConcreteEntityGenericEquals.class);
        Object actual = entityUnitTester.getObject2Test();

        assertThat(actual, nullValue());
    }

    @Test
    public void test_setEntity_return_value() {
        Object before = entityUnitTester.getObject2Test();
        assertThat(before, instanceOf(ConcreteEntityLogicalEquals.class));

        entityUnitTester.setObject2Test(null);
        Object actual = entityUnitTester.getObject2Test();

        assertThat(actual, nullValue());
    }

    @Test
    public void test_findGetter_return_list() {
        List<PropertyDescriptor> actual = entityUnitTester.findGetter();

        assertThat(actual, hasSize(3));
    }

    @Test
    public void test_findSetter_return_emptyList() {
        List<PropertyDescriptor> actual = entityUnitTester.findSetter();

        assertThat(actual, notNullValue());
        assertThat(actual, hasSize(0));
    }

    @Test
    public void test_fieldsDeniedForToString_return_emptyList() {
        List<String> actual = entityUnitTester.fieldsDeniedForToString();

        assertThat(actual, notNullValue());
        assertThat(actual, hasSize(0));
    }

    @Test
    public void test_allFieldsDeniedForToString_return_emptyList() {
        Collection<String> actual = ReflectionHelper.readField("allFieldsDeniedForToString", entityUnitTester);

        assertThat(actual, notNullValue());
        assertThat(actual, hasSize(0));
    }

    @Test
    public void test_fieldsToIgnoreForToString_return_emptyList() {
        List<String> actual = entityUnitTester.fieldsToIgnoreForToString();

        assertThat(actual, notNullValue());
        assertThat(actual, hasSize(0));
    }

    @Test
    public void test_allFieldsToIgnoreForToString_return_oneElement() {
        Collection<String> actual = ReflectionHelper.readField("allFieldsToIgnoreForToString", entityUnitTester);

        assertThat(actual, notNullValue());
        assertThat(actual, hasSize(1));
    }

    @Test
    public void test_testAllGetterAccessiblewith_raise_noException() {
        entityUnitTester.testAllGetterAccessible();

        verifyCollector(entityUnitTester, 0);
    }

    @Test
    public void test_testAllSetterAccessible_raise_noException() {
        entityUnitTester.testAllSetterAccessible();

        verifyCollector(entityUnitTester, 0);
    }

    @Test
    public void test_testGetterSetterCollaboration_raise_noException() {
        entityUnitTester.testGetterSetterCollaboration();

        verifyCollector(entityUnitTester, 0);
    }

    @Test
    public void test_testToString_raise_2Exception() {
        entityUnitTester.testToString();

        verifyCollector(entityUnitTester, 2);
    }

    @Test
    public void test_testToStringWithValues_raise_exception() {
        entityUnitTester.testToStringWithValues();

        verifyCollector(entityUnitTester, 1);
    }

    @Test
    public void test_testHashcodeOtherThan0_raise_exception() {
        verifyException(() -> entityUnitTester.testHashcodeOtherThan0(), AssertionError.class);
    }

    @Test
    public void test_testHashcodeOtherThan0_with_incompleteEquals_raise_noException() {
        initWithoutEqual();
        entityUnitTester.testHashcodeOtherThan0();
    }

    @Test
    public void test_testEqualsLogicalAreTheSame_with_logicalEquals_defaultCompare_raise_exception() {
        assertThat(entityUnitTester.isCheckLogicalEqualsOnly(), is(false));
        verifyException(() -> entityUnitTester.testEqualsLogicalAreTheSame(), AssertionError.class);
    }


    @Test
    public void test_testEqualsLogicalAreTheSame_with_logicalEquals_logicalCompare_raise_noException() {
        entityUnitTester.setCheckLogicalEqualsOnly(true);
        assertThat(entityUnitTester.isCheckLogicalEqualsOnly(), is(true));

        entityUnitTester.testEqualsLogicalAreTheSame();
    }

    @Test
    public void test_testEqualsLogicalAreTheSame_with_defaultEquals_defaultCompare_raise_noException() {
        initWithoutEqual();
        entityUnitTester.setCheckLogicalEqualsOnly(false);
        assertThat(entityUnitTester.isCheckLogicalEqualsOnly(), is(false));

        entityUnitTester.testEqualsLogicalAreTheSame();
    }

    @Test
    public void test_testEqualsLogicalAreTheSame_with_defaultEquals_logicalCompare_raise_exception() {
        initWithoutEqual();
        entityUnitTester.setCheckLogicalEqualsOnly(true);
        assertThat(entityUnitTester.isCheckLogicalEqualsOnly(), is(true));

        verifyException(() -> entityUnitTester.testEqualsLogicalAreTheSame(), AssertionError.class);
    }


    @Test
    public void test_testEqualsWithNull_raise_noException() {
        entityUnitTester.testEqualsWithNull();
    }

    @Test
    public void test_testEqualsWithNull_with_incompleteEquals_raise_noException() {
        initWithoutEqual();
        entityUnitTester.testEqualsWithNull();
    }

    @Test
    public void test_testEqualsWithItself_raise_noException() {
        entityUnitTester.testEqualsWithItself();
    }

    @Test
    public void test_testEqualsWithItself_with_incompleteEquals_raise_noException() {
        initWithoutEqual();
        entityUnitTester.testEqualsWithItself();
    }
*/
}
