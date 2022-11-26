package com.glowanet.tools.unit.entity;

import com.glowanet.annotation.ExcludeFromTesting;
import com.glowanet.tools.unit.AbstractUnitTester;
import com.glowanet.util.junit.rules.ExcludeFromTestingRule;
import com.glowanet.util.reflect.ReflectionHelper;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
import static org.hamcrest.MatchersExtend.betweenWithBound;

/**
 * Abstract clazz to use for unit-testing on entities, beans, pojos.
 * However, you name your clazzes with an amount of getter and setter.
 *
 * @param <T> the type of the entity which will be tested
 */
public abstract class EntityUnitTester<T> extends AbstractUnitTester<T> {

    // static fields
    /** Field names in the clazz, which should be generally ignored on testing {@link #toString()}. */
    public static final  Collection<String> FIELDS_COMMON_IGNORE              = Set.of("class");
    public static final  boolean            DEFAULT_CHECK_LOGICAL_EQUALS_ONLY = false;
    private static final Logger             LOGGER                            = LogManager.getLogger();
// end - static fields

    // fields
    @Rule
    public  ExcludeFromTestingRule excludeFromTestingRule = new ExcludeFromTestingRule();
    private boolean                checkLogicalEqualsOnly = DEFAULT_CHECK_LOGICAL_EQUALS_ONLY;
    private boolean                checkSVUID             = DEFAULT_CHECK_SVUID;

    private Collection<String> allFieldsToIgnoreForToString;
    private Collection<String> allFieldsDeniedForToString;
// end - fields

    // constructors
    protected EntityUnitTester(Class<T> typeOfo2T) {
        super(typeOfo2T);
        localSetup(); //NOSONAR java:S1699
    }
// end - constructors

    // methods
    protected void localSetup() {
        allFieldsToIgnoreForToString = new HashSet<>(FIELDS_COMMON_IGNORE);
        allFieldsToIgnoreForToString.addAll(fieldsToIgnoreForToString() == null ? List.of() : fieldsToIgnoreForToString());
        allFieldsToIgnoreForToString = allFieldsToIgnoreForToString.stream().map(String::toLowerCase).collect(Collectors.toSet());

        allFieldsDeniedForToString = new HashSet<>();
        allFieldsDeniedForToString.addAll(fieldsDeniedForToString() == null ? List.of() : fieldsDeniedForToString());
        allFieldsDeniedForToString = allFieldsDeniedForToString.stream().map(String::toLowerCase).collect(Collectors.toSet());
    }

    /**
     * Tests, if using all getters is possible, what means it doesn't raise an exception.
     */
    @Test
    public void testAllGetterAccessible() {
        List<PropertyDescriptor> getterList = findGetter();
        LOGGER.info("Testing access on '{}' for {} getters", getTypeOfo2T(), getterList.size());
        Object instance = getObject2Test();
        for (PropertyDescriptor getter : getterList) {
            try {
                Object value = MethodUtils.invokeMethod(instance, getter.getReadMethod().getName());
                collector.checkThat(String.format("'%s' doesn't have a proper value!", getter.getName()), value, anyOf(nullValue(), notNullValue()));
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
     * Tests, if two objects from the same type are logical equal.
     * <ul>
     *     <li>{@link #isCheckLogicalEqualsOnly()}==TRUE    -&gt; expecting logical equality</li>
     *     <li>{@link #isCheckLogicalEqualsOnly()}==FALSE   -&gt; expecting object equality</li>
     * </ul>
     *
     * @see #isCheckLogicalEqualsOnly()
     * @see #setCheckLogicalEqualsOnly(boolean)
     */
    @Test
    public void testEqualsLogicalAreTheSame() {
        T itSelf = getObject2Test();
        T itSelf2 = getObject2Test();
        boolean expected = isCheckLogicalEqualsOnly();
        collector.checkThat(itSelf.equals(itSelf2), is(expected));
    }

    /**
     * Tests, if the object compares to itSelf is always {@code TRUE}, regardless if object or logical equality.
     */
    @SuppressWarnings({"EqualsWithItself", "java:S1764"})
    @Test
    public void testEqualsWithItself() {
        T itSelf = getObject2Test();
        collector.checkThat(itSelf.equals(itSelf), is(true));
    }

    /**
     * Tests, if the object compares to {@code NULL} is always {@code FALSE}.
     */
    @SuppressWarnings({"ConstantConditions", "ObjectEqualsCanBeEquality", "RedundantSuppression", "RedundantCast"})
    @Test
    public void testEqualsWithNull() {
        T itSelf = getObject2Test();
        collector.checkThat(itSelf.equals((T) null), is(false));
    }

    /**
     * Tests, if using all setters is possible, what means it doesn't raise an exception, AND the same value could be retrieved from the getter.
     *
     * @see #verifyAllGetterSetterCollaboration(boolean)
     */
    @Test
    public void testGetterSetterCollaboration() {
        verifyAllGetterSetterCollaboration(true);
    }

    /**
     * Tests, that the result of {@link #hashCode()} is not 0;
     */
    @Test
    public void testHashcodeOtherThan0() {
        collector.checkThat("Hashcode must differ from '0'!", getObject2Test().hashCode(), not(is(0)));
    }

    /**
     * Tests, that a {@code serialVersionUid} is correctly defined.
     *
     * @see #isCheckSVUID()
     * @see #setCheckSVUID(boolean)
     */
    @Test
    public void testSerialVersionUIDIsCorrectInEntity() {
        validateSerialVersionUID();
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
                collector.checkThat(String.format("'%s' doesn't appear on toString()!", getter.getName()), actual.toString(), containsString(getter.getName()));
            }
            if (allFieldsDeniedForToString.contains(getter.getName().toLowerCase())) {
                collector.addError(new IllegalArgumentException(String.format("'%s' is not allowed to appear on toString()!", getter.getName())));
            }
        }
    }

    @ExcludeFromTesting
    @Test
    public void testToStringWithValues() {
        collector.addError(new NotImplementedException("TBD"));
    }

    /**
     * The Fields in the current clazz, which are denied to appear on testing @toString().
     *
     * @return list of field names
     *
     * @see #testToString()
     */
    protected List<String> fieldsDeniedForToString() {
        return List.of();
    }

    /**
     * The Fields in the current clazz, which should be ignored on testing @toString().
     *
     * @return list of field names
     *
     * @see #testToString()
     */
    protected List<String> fieldsToIgnoreForToString() {
        return List.of();
    }

    /**
     * Flag, if #testEqualsLogicalAreTheSame expects only logical equality.
     *
     * @return TRUE = will check only logical equality, else FALSE = checks object equality
     *
     * @see #testEqualsLogicalAreTheSame()
     */
    protected boolean isCheckLogicalEqualsOnly() {
        return checkLogicalEqualsOnly;
    }

    /**
     * The flag, if @SERIAL_VERSION_UID_NAME should be checked.
     *
     * @return TRUE = will be checked, else FALSE
     *
     * @see #testSerialVersionUIDIsCorrectInEntity()
     */
    protected boolean isCheckSVUID() {
        return checkSVUID;
    }

    /**
     * Sets the flag, if #testEqualsLogicalAreTheSame expects only logical equality.
     *
     * @param checkLogicalEqualsOnly TRUE = will be checked, else FALSE
     *
     * @see #testEqualsLogicalAreTheSame()
     */
    protected void setCheckLogicalEqualsOnly(boolean checkLogicalEqualsOnly) {
        this.checkLogicalEqualsOnly = checkLogicalEqualsOnly;
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
     * Verify, that a {@code serialVersionUid} is correctly defined.
     *
     * @see #isCheckSVUID()
     * @see #setCheckSVUID(boolean)
     */
    protected void validateSerialVersionUID() {
        Object instance = getObject2Test();
        if (checkSVUID && instance != null && ReflectionHelper.hasSerializableIF(instance.getClass())) {
            Field idField = ReflectionHelper.findField(SERIAL_VERSION_UID_NAME, instance);

            Matcher<Object> notNullMatcher = notNullValue();
            Matcher<Class<?>> typeMatcher = typeCompatibleWith(Number.class);
            Matcher<Long> numberRangeMatcher = not(betweenWithBound(SERIAL_VERSION_UID_INVALID_RANGE));

            String clazzName = instance.getClass().getName();
            String fieldName = (idField == null ? SERIAL_VERSION_UID_NAME : idField.getName());
            String reasonNull = String.format("For '%s#%s' it must not be null!", clazzName, fieldName);
            String reasonType = String.format("For '%s#%s' it must be valid: %s!", clazzName, fieldName, typeMatcher);
            String reasonRange = String.format("For '%s#%s' it must be valid: %s!", clazzName, fieldName, numberRangeMatcher);

            Object idValue = null;
            try {
                idValue = ReflectionHelper.readStaticValue(SERIAL_VERSION_UID_NAME, instance.getClass());
            } catch (AssertionError error) { //NOSONAR java:S1166
                //can be ignored
            }
            collector.checkThat(reasonNull, idValue, notNullMatcher);
            if (idValue != null) {
                collector.checkThat(reasonType, idValue.getClass(), typeMatcher);
                collector.checkThat(reasonRange, Long.parseLong(idValue.toString()), numberRangeMatcher);
            }
        }
    }

    /**
     * Tests, if the getter and setter are working smoothly together, which means
     * what you put in the field by the setter is the same what you will retrieve from the getter.
     *
     * @param verifyValue TRUE= compares the values from setter and getter, FALSE = just checks if setting the value won't raise an exception
     */
    protected void verifyAllGetterSetterCollaboration(boolean verifyValue) {
        List<PropertyDescriptor> setterList = findSetter();
        LOGGER.info("Testing access{} on '{}' for {} setters", verifyValue ? " and value setting" : "", getTypeOfo2T(), setterList.size());

        Object instance = getObject2Test();
        for (PropertyDescriptor setter : setterList) {
            Class<?>[] paramTypes = new Class[0];
            Object[] paramValues = new Object[0];
            try {
                Map<Class<?>, Object> setterParams = retrieveMethodParameters(setter.getWriteMethod());
                paramTypes = setterParams.keySet().toArray(new Class[]{});
                paramValues = setterParams.values().toArray();
                MethodUtils.invokeMethod(instance, setter.getWriteMethod().getName(), paramValues, paramTypes);
                if (verifyValue) {
                    Object expected = paramValues[0];
                    Object actual = MethodUtils.invokeMethod(instance, setter.getReadMethod().getName());
                    collector.checkThat(String.format("'%s' doesn't have a proper value!", setter.getName()), actual, equalTo(expected));
                }
            } catch (NoSuchMethodException | IllegalAccessException e) {
                collector.checkThat(String.format("'%s' doesn't exists or is accessible!", setter.getName()), e, nullValue());
            } catch (IllegalArgumentException | InvocationTargetException e) {
                collector.checkThat(
                        String.format("'%s' has the wrong arguments '%s' -> '%s'!",
                                setter.getName(), Arrays.toString(paramTypes), Arrays.toString(paramValues)), e, nullValue()
                );
            }
        }
    }
// end - methods
}
