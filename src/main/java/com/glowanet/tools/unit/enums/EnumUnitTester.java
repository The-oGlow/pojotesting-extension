package com.glowanet.tools.unit.enums;

import com.glowanet.tools.unit.AbstractUnitTester;
import com.glowanet.util.reflect.ReflectionHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Abstract clazz to use for unit-testing on {@link Object}, beans, pojos.
 * However, you name your clazzes with an amount of getter and setter.
 *
 * @param <E> the type of the enum which will be tested
 */
public abstract class EnumUnitTester<E> extends AbstractUnitTester<E> {

    // static fields
    public static final  NameCheckTypeEnum DEFAULT_NAME_CHECK_TYPE    = NameCheckTypeEnum.CF;
    public static final  boolean           DEFAULT_CODE_CHECK_ENABLED = true;
    private static final String            FIELD_NAME_CODE            = "code";
    private static final String            FIELD_NAME_NAME            = "name";
    private static final Logger            LOGGER                     = LogManager.getLogger();
    // end - static fields

    // fields
    private Collection<String> allFieldsToIgnoreForCode;
    private NameCheckTypeEnum  nameCheckType    = DEFAULT_NAME_CHECK_TYPE;
    private boolean            codeCheckEnabled = DEFAULT_CODE_CHECK_ENABLED;
// end - fields

    // constructors
    @SuppressWarnings("java:S1699")
    protected EnumUnitTester(Class<E> typeOfo2E) {
        super(typeOfo2E);
        localSetup();
    }
// end - constructors

    // abstract methods

    /**
     * Specific enums in the current clazz, which should be ignored on testing 'toCode()'.
     *
     * @return list of specific enums
     *
     * @see #validateEnumObjectCode(Field, Object)
     * @see #testValidateAllEnumObjects()
     */
    protected abstract List<String> enumObjectsToIgnoreForCode();
// end -  abstract methods

    // methods
    protected void localSetup() {
        allFieldsToIgnoreForCode = new HashSet<>();
        allFieldsToIgnoreForCode.addAll(enumObjectsToIgnoreForCode() == null ? List.of() : enumObjectsToIgnoreForCode());
        allFieldsToIgnoreForCode = allFieldsToIgnoreForCode.stream().map(String::toLowerCase).collect(Collectors.toSet());
    }

    @Test
    public void testValidateAllEnumObjects() {
        List<Field> listEnumFields = retrieveEnumFromList(getTypeOfo2T());
        for (Field enumField : listEnumFields) {
            collector.checkThat(String.format("Checking '%s'", enumField.getName()),
                    validateEnumObject(enumField, getTypeOfo2T()), equalTo(true)
            );
        }
    }

    /**
     * @param expectedField the field to check, if it's in the ignore list
     *
     * @return TRUE=is in the ignore list, else FALSE
     */
    protected boolean checkIgnoredField(Field expectedField) {
        Throwable e = new IllegalArgumentException(String.format("Field '%s' is not in ignore list!", expectedField));
        return checkIgnoredField(expectedField, e);
    }

    /**
     * @param expectedField the field to check, if it's in the ignore list
     * @param throwable     specific exception to add to the collector
     *
     * @return TRUE=is in the ignore list, else FALSE
     */
    protected boolean checkIgnoredField(Field expectedField, Throwable throwable) {
        boolean isIgnored = false;
        if (expectedField != null) {
            if (isInIgnoreListForCode(expectedField.getName())) {
                isIgnored = true;
            } else {
                collector.addError(throwable);
            }
        }
        return isIgnored;
    }

    /**
     * @return always null
     */
    @Override
    protected E createObject2Test() {
        // Cannot instantiate an enum.
        return null;
    }

    /**
     * Flag, so 'toName()' must match the given {@link NameCheckTypeEnum}-check.
     *
     * @return #NAME_CHECK_ENUM
     *
     * @see NameCheckTypeEnum
     * @see #validateEnumObjectName(Field, Object)
     * @see #testValidateAllEnumObjects()
     */
    protected NameCheckTypeEnum getNameCheckType() {
        return nameCheckType;
    }

    /**
     * Flag, if 'toCode()' should be checked.
     *
     * @return TRUE = will be checked, else FALSE
     *
     * @see #validateEnumObjectCode(Field, Object)
     * @see #testValidateAllEnumObjects()
     */
    protected boolean isCodeCheckEnabled() {
        return codeCheckEnabled;
    }

    /**
     * @param fieldName the fieldName to check
     *
     * @return TRUE=fieldName is in ignore list, else FALSE
     */
    protected boolean isInIgnoreListForCode(String fieldName) {
        boolean isInList = false;
        if (fieldName != null) {
            isInList = (allFieldsToIgnoreForCode.contains(fieldName.toLowerCase(Locale.getDefault())));
        }
        if (isInList) {
            LOGGER.info("'{}' is an ignored field!", fieldName);
        }
        return isInList;
    }

    /**
     * Sets the flag, if 'toCode()' should be checked.
     *
     * @param codeCheckEnabled TRUE = will be checked, else FALSE
     *
     * @see #validateEnumObjectCode(Field, Object)
     * @see #testValidateAllEnumObjects()
     */
    protected void setCodeCheckEnabled(boolean codeCheckEnabled) {
        this.codeCheckEnabled = codeCheckEnabled;
    }

    /**
     * Set the flag, so 'toName()' must match the given {@link NameCheckTypeEnum}-check.
     *
     * @param nameCheckType #NAME_CHECK_ENUM
     *
     * @see NameCheckTypeEnum
     * @see #validateEnumObjectName(Field, Object)
     * @see #testValidateAllEnumObjects()
     */
    protected void setNameCheckType(NameCheckTypeEnum nameCheckType) {
        this.nameCheckType = nameCheckType;
    }

    /**
     * Verifies that an enumObject is correct defined.
     *
     * @param expectedField the field-object, containing the expected enumObject
     * @param actualClazz   the type of the enumObject to check
     *
     * @return TRUE = is correct declared, else FALSE
     */
    protected boolean validateEnumObject(Field expectedField, Class<E> actualClazz) {
        boolean isValid = false;

        if (expectedField == null && actualClazz == null) {
            isValid = true;
        } else if (expectedField == null || actualClazz == null) {
            collector.addError(new IllegalArgumentException(String.format("Expected field '%s' or class '%s' is null", expectedField, actualClazz)));
        } else if (expectedField.getType().isAssignableFrom(actualClazz)) {
            try {
                E actualInstance = ReflectionHelper.readField(expectedField.getName(), actualClazz);
                boolean resultName = validateEnumObjectName(expectedField, actualInstance);
                boolean resultCode = true;
                if (isCodeCheckEnabled()) {
                    resultCode = validateEnumObjectCode(expectedField, actualInstance);
                } else {
                    LOGGER.info("Checking getCode() is disabled for '{}' !", expectedField.getName());
                }
                isValid = resultName && resultCode;

            } catch (Exception e) { //NOSONAR java:S2221
                isValid = checkIgnoredField(expectedField, e);
            }
        } else {
            // nothing2do
        }
        return isValid;
    }

    /**
     * Verifies that the declared name of an enumObject matches with the getCode()-value.
     *
     * @param expectedField  the field-object, containing the expected enumObject
     * @param actualInstance an instance of the enumObject to check
     *
     * @return TRUE = is correct declared, else FALSE
     */
    protected boolean validateEnumObjectCode(Field expectedField, E actualInstance) {
        boolean isValid = false;

        Number expectedCode = retrieveNumberFromText(expectedField.getName());
        Object objectCode = null;
        try {
            objectCode = ReflectionHelper.readField(FIELD_NAME_CODE, actualInstance);
        } catch (IllegalArgumentException | AssertionError e) {
            //nothing2do
            LOGGER.debug(e);
        }
        Number actualCode = null;
        if (objectCode != null) {
            actualCode = Integer.parseInt(objectCode.toString());
        }

        collector.checkThat(String.format("'%s' is null!", expectedField.getName()), expectedCode, notNullValue());
        collector.checkThat(String.format("'%s#%s' is null!", expectedField.getName(), FIELD_NAME_CODE), actualCode, notNullValue());
        if (actualCode != null && expectedCode != null) {
            if (actualCode.intValue() == expectedCode.intValue()) {
                isValid = true;
            } else {
                collector.checkThat(String.format("checking getCode() of '%s'", expectedField.getName()),
                        actualCode, equalTo(expectedCode)
                );
            }
        }
        return isValid;
    }

    /**
     * Verifies that the declared name of an enumObject matches with the name()-value.
     *
     * @param expectedField  the field-object, containing the expected enumObject
     * @param actualInstance an instance of the enumObject to check
     *
     * @return TRUE = is correct declared, else FALSE
     */
    protected boolean validateEnumObjectName(Field expectedField, E actualInstance) {
        boolean isValid = false;

        boolean nameCheck;
        String expected = expectedField.getName();
        String actual = ReflectionHelper.readField(FIELD_NAME_NAME, actualInstance);

        switch (getNameCheckType()) {
            case CF:
                nameCheck = expected.equals(actual);
                break;

            case CIF:
                nameCheck = expected.equalsIgnoreCase(actual);
                break;

            case CSW:
                nameCheck = expected.startsWith(actual);
                break;

            case CISW:
                expected = expected.toLowerCase(Locale.getDefault());
                nameCheck = expected.equalsIgnoreCase(actual);
                break;

            default:
                nameCheck = false;

        }

        if (nameCheck) {
            isValid = true;
        } else {
            collector.checkThat(String.format("checking name() of '%s'", expectedField.getName()),
                    actual, equalTo(expectedField.getName())
            );
        }
        return isValid;
    }
// end - methods

}
