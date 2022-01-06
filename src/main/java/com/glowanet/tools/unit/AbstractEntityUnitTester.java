package com.glowanet.tools.unit;

import com.glowanet.util.reflect.ReflectionHelper;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.typeCompatibleWith;

/**
 * Abstract class to use for unit-testing on entities, beans, pojos.
 * However you name your classes with an amount of getter and setter.
 *
 * @param <T> the type of the class to test
 */
public abstract class AbstractEntityUnitTester<T> extends AbstractUnitTester<T> {

    /**
     * Fieldnames in the class, which should be generally ignored on testing {@link #toString()}.
     */
    protected static final Collection<String> FIELDS_COMMON_IGNORE             = new HashSet<>(List.of("class"));
    protected static final String             SERIAL_VERSION_UID_NAME          = "serialVersionUID";
    protected static final Pair<Long, Long>   SERIAL_VERSION_UID_INVALID_RANGE = new ImmutablePair<>(-100L, 100L);

    private static final Logger LOGGER = LogManager.getLogger();

    private Collection<String> allFieldsToIgnoreForToString = FIELDS_COMMON_IGNORE;
    private Collection<String> allFieldsDeniedForToString   = new HashSet<>();
    private boolean            checkSVUID                   = true;
    private boolean            checkLogicalEqualsOnly;

    protected AbstractEntityUnitTester(Class<T> typeOfT) {
        super(typeOfT);
    }

    @Override
    @Before
    public void setUp() {
        super.setUp();
        assertThat(getObject2Test(), notNullValue());

        allFieldsToIgnoreForToString.addAll(fieldsToIgnoreForToString() == null ? List.of() : fieldsToIgnoreForToString());
        allFieldsToIgnoreForToString = allFieldsToIgnoreForToString.stream().map(String::toLowerCase).collect(Collectors.toSet());

        allFieldsDeniedForToString.addAll(fieldsDeniedForToString() == null ? List.of() : fieldsDeniedForToString());
        allFieldsDeniedForToString = allFieldsDeniedForToString.stream().map(String::toLowerCase).collect(Collectors.toSet());
    }

    /**
     * Fields in the current class, which should be ignored on testing @toString().
     *
     * @return list of fieldnames
     *
     * @see #testToString()
     */
    protected List<String> fieldsToIgnoreForToString() {
        return List.of();
    }

    /**
     * Fields in the current class, which are denied to appear on testing @toString().
     *
     * @return list of fieldnames
     *
     * @see #testToString()
     */
    protected List<String> fieldsDeniedForToString() {
        return List.of();
    }

    /**
     * Flag, if @SERIAL_VERSION_UID_NAME should be checked.
     *
     * @return TRUE = will be checked, else FALSE
     *
     * @see #testSerialVersionUIDIsCorrectInEntity()
     */
    protected boolean isCheckSVUID() {
        return checkSVUID;
    }

    /**
     * Sets the flag, if @SERIAL_VERSION_UID_NAME should be checked.
     *
     * @param checkSVUID TRUE = will be checked, else FALSE
     *
     * @see #testSerialVersionUIDIsCorrectInEntity()
     */
    protected void setCheckSVUID(boolean checkSVUID) {
        this.checkSVUID = checkSVUID;
    }

    /**
     * Flag, if #testEqualsLogicalAreTheSame expects only logical equalness.
     *
     * @return TRUE = will check only logical equalness, else FALSE = checks object equalness
     *
     * @see #testEqualsLogicalAreTheSame()
     */
    protected boolean isCheckLogicalEqualsOnly() {
        return checkLogicalEqualsOnly;
    }

    /**
     * Sets the flag, if #testEqualsLogicalAreTheSame expects only logical equalness.
     *
     * @param checkLogicalEqualsOnly TRUE = will be checked, else FALSE
     *
     * @see #testEqualsLogicalAreTheSame()
     */
    protected void setCheckLogicalEqualsOnly(boolean checkLogicalEqualsOnly) {
        this.checkLogicalEqualsOnly = checkLogicalEqualsOnly;
    }


    private Matcher<Long> inBetweenWithBounds(Pair<Long, Long> numberRange) {
        Matcher<Long> greater = Matchers.greaterThanOrEqualTo(numberRange.getLeft());
        Matcher<Long> smaller = Matchers.lessThanOrEqualTo(numberRange.getRight());
        return Matchers.allOf(greater, smaller);
    }

    /**
     * #see #isCheckSVUID()
     * #see #setCheckSVUID(boolean)
     */
    protected void validateSerialVersionUID(Object instance) {
        if (checkSVUID && ReflectionHelper.hasSerializableIF(instance.getClass())) {
            Field idField = ReflectionHelper.findField(SERIAL_VERSION_UID_NAME, instance);

            Matcher<Class<?>> typeMatcher = typeCompatibleWith(Number.class);
            Matcher<Long> numberRangeMatcher = not(inBetweenWithBounds(SERIAL_VERSION_UID_INVALID_RANGE));
            String reasonType = "For '" + (idField == null ? SERIAL_VERSION_UID_NAME : idField.getName()) + "' it must be valid: " + typeMatcher + "!";
            String reasonRange = "For '" + (idField == null ? SERIAL_VERSION_UID_NAME : idField.getName()) + "'it must be valid: " + numberRangeMatcher + "!";

            Object idValue = ReflectionHelper.readStaticValue(SERIAL_VERSION_UID_NAME, getTypeOfo2T());

            assertThat(idValue, notNullValue());
            assertThat(reasonType, idValue.getClass(), typeMatcher);
            assertThat(reasonRange, Long.parseLong(idValue.toString()), numberRangeMatcher);
        }
    }

    /**
     * Tests, if using all getters is possible, what means it doesn't raise an exception.
     */
    @Test
    public void testAllGetterAccessible() {
        List<PropertyDescriptor> getterList = findGetter();
        LOGGER.info("Testing access on '{}' for {} getters", getTypeOfo2T(), getterList.size());
        for (PropertyDescriptor getter : getterList) {
            try {
                Object value = MethodUtils.invokeMethod(getObject2Test(), getter.getReadMethod().getName());
                collector.checkThat("'" + getter.getName() + "' doesn't have a proper value!", value, anyOf(nullValue(), notNullValue()));
            } catch (IllegalArgumentException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                collector.addError(e);
            }
        }
    }

    /**
     * Tests, if using all setters is possible, what means it doesn't raise an exception.
     *
     * @see #verifyAllGetterSetterCollaboration(boolean)
     */
    @Test
    public void testAllSetterAccessible() {
        verifyAllGetterSetterCollaboration(false);
    }

    /**
     * Tests, if using all setters is possible, what means it doesn't raise an exception AND the same value could be retrieved from the getter.
     *
     * @see #verifyAllGetterSetterCollaboration(boolean)
     */
    @Test
    public void testGetterSetterCollaboration() {
        verifyAllGetterSetterCollaboration(true);
    }

    /**
     * Tests,if the getter and setter are working smoothly together, which means
     * what you put in the field by the setter is the same what you will retrieve from the getter.
     *
     * @param verifyValue TRUE= compares the values from setter and getter, FALSE = just checks if setting the value won't raise an exception
     */
    private void verifyAllGetterSetterCollaboration(boolean verifyValue) {
        List<PropertyDescriptor> setterList = findSetter();
        LOGGER.info("Testing access{} on '{}' for {} setters", verifyValue ? " and value setting" : "", getTypeOfo2T(), setterList.size());

        for (PropertyDescriptor setter : setterList) {
            Class<?>[] paramTypes = new Class[0];
            Object[] paramValues = new Object[0];
            try {
                Map<Class<?>, Object> setterParams = retrieveMethodParameters(setter.getWriteMethod());
                paramTypes = setterParams.keySet().toArray(new Class[]{});
                paramValues = setterParams.values().toArray();
                MethodUtils.invokeMethod(getObject2Test(), setter.getWriteMethod().getName(), paramValues, paramTypes);
                if (verifyValue) {
                    Object expected = paramValues[0];
                    Object actual = MethodUtils.invokeMethod(getObject2Test(), setter.getReadMethod().getName());
                    collector.checkThat("'" + setter.getName() + "' doesn't have a proper value!", actual, equalTo(expected));
                }
            } catch (NoSuchMethodException | IllegalAccessException e) {
                collector.checkThat("'" + setter.getName() + "' doesn't exists or is accessible!", e, nullValue());
            } catch (IllegalArgumentException | InvocationTargetException e) {
                collector.checkThat("'" + setter.getName() + "' has the wrong arguments '" //
                        + Arrays.toString(paramTypes) + "' -> '" + Arrays.toString(paramValues) + "'!", e, nullValue());
            }
        }
    }

    /**
     * Tests, if all getter will be listed (except those, not needed) or not allowed to be listed.
     *
     * @see #fieldsToIgnoreForToString()
     * @see #fieldsDeniedForToString()
     */
    @Test
    public void testToString() {
        CharSequence actual = getObject2Test().toString();

        assertThat(actual, notNullValue());

        List<PropertyDescriptor> getterList = findGetter();
        for (PropertyDescriptor getter : getterList) {
            if (!allFieldsToIgnoreForToString.contains(getter.getName().toLowerCase())) {
                collector.checkThat("'" + getter.getName() + "' doesn't appear on toString()!", actual.toString(), containsString(getter.getName()));
            }
            if (allFieldsDeniedForToString.contains(getter.getName().toLowerCase())) {
                collector.addError(new IllegalArgumentException("'" + getter.getName() + "' is not allowed to appear on toString()!"));
            }
        }
    }

    @Ignore("TBD")
    @Test
    public void testToStringWithValues() {
        collector.addError(new NotImplementedException("TBD"));
    }

    @Test
    public void testHashcodeOtherThan0() {
        assertThat("Hashcode must differ from '0'!", getObject2Test().hashCode(), not(is(0)));
    }

    @SuppressWarnings({"EqualsWithItself", "java:S1764"})
    @Test
    public void testEqualsWithItself() {
        assertThat(getObject2Test().equals(getObject2Test()), is(true));
    }

    @SuppressWarnings({"ConstantConditions", "ObjectEqualsCanBeEquality", "RedundantSuppression", "RedundantCast"})
    @Test
    public void testEqualsWithNull() {
        assertThat(getObject2Test().equals((T) null), is(false));
    }

    /**
     * Verify, if the
     *
     * @see #isCheckLogicalEqualsOnly()
     * @see #setCheckLogicalEqualsOnly(boolean)
     */
    @Test
    public void testEqualsLogicalAreTheSame() {
        T entity2 = createObject2Test();
        boolean expected = isCheckLogicalEqualsOnly();
        assertThat(getObject2Test().equals(entity2), is(expected));
    }

    /**
     * #see #isCheckSVUID()
     * #see #setCheckSVUID(boolean)
     */
    @Test
    public void testSerialVersionUIDIsCorrectInEntity() {
        validateSerialVersionUID(getObject2Test());
    }

}
