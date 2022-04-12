package com.glowanet.tools.unit.enumobj;

import com.glowanet.tools.unit.AbstractUnitTester;
import com.glowanet.util.reflect.ReflectionHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Abstract class to use for unit-testing on {@link Object}, beans, pojos.
 * However you name your classes with an amount of getter and setter.
 *
 * @param <E> the type of the entity which will be tested
 */
public abstract class EnumObjectUnitTester<E> extends AbstractUnitTester<E> {

    // static fields
    public static final  NAME_CHECK_ENUM DEFAULT_NAME_CHECK_TYPE    = NAME_CHECK_ENUM.CF;
    public static final  boolean         DEFAULT_CODE_CHECK_ENABLED = true;
    private static final String          FIELD_NAME_CODE            = "code";
    private static final String          FIELD_NAME_NAME            = "name";
    private static final String          UNDERLINE_CHAR             = "_";
    private static final String          ENUM_NAME_SRCH             = "_(.)";
    private static final String          ENUM_NAME_REPL             = "$1";
    private static final Pattern         ENUM_NAME_SRCH_PATTERN     = Pattern.compile(ENUM_NAME_SRCH);
    private static final Logger          LOGGER                     = LogManager.getLogger();
    // end - static fields

    // fields
    private Collection<String> allFieldsToIgnoreForCode;
    private NAME_CHECK_ENUM    nameCheckType    = DEFAULT_NAME_CHECK_TYPE;
    private boolean            codeCheckEnabled = DEFAULT_CODE_CHECK_ENABLED;
// end - fields

    // constructors
    protected EnumObjectUnitTester(Class<E> typeOfo2E) {
        super(typeOfo2E);
        localSetup();
    }
// end - constructors

    // abstract methods

    /**
     * Fields in the current class, which should be ignored on testing 'toCode()'.
     *
     * @return list of fieldnames
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
        List<Field> allEnumObjects = retrievePublicConstantsfromClass(getTypeOfo2T());

        for (Field expectedEnumObject : allEnumObjects) {
            //FIXME: das rausnehmen als "retrieveEnumsfromClass()"
            if (isEnum(expectedEnumObject)) {
                collector.checkThat(String.format("Checking '%s'", expectedEnumObject.getName()),
                        validateEnumObject(expectedEnumObject, getTypeOfo2T()), equalTo(true)
                );
            }
        }
    }

    protected boolean checkIgnoredFields(Field expectedField) {
        Throwable e = new IllegalArgumentException(String.format("Field '%s' is not in ignore list!", expectedField));
        return checkIgnoredFields(expectedField, e);
    }

    protected boolean checkIgnoredFields(Field expectedField, Throwable e) {
        boolean isIgnored = false;
        if (isCodeCheckEnabled()) {
            if (expectedField != null) {
                if (isInIgnoreListForCode(expectedField.getName())) {
                    isIgnored = true;
                    LOGGER.warn("'{}' is listed to be ignored!", expectedField.getName());
                } else {
                    collector.addError(e);
                }
            }
        }
        return isIgnored;
    }

    @Override
    protected final Class<E> getTypeOfo2T() {
        //nothing will be created
        return null;
    }

    /**
     * Flag, so 'toName()' must match the given {@link NAME_CHECK_ENUM}-check.
     *
     * @return #NAME_CHECK_ENUM
     *
     * @see NAME_CHECK_ENUM
     * @see #validateEnumObjectName(Field, Object)
     * @see #testValidateAllEnumObjects()
     */
    protected NAME_CHECK_ENUM getNameCheckType() {
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
     * @param object the type to check
     *
     * @return TRUE=the type is an enum, else FALSE
     */
    protected boolean isEnum(Object object) {
        boolean isAnEnum = false;
        if (object != null) {
            if (Field.class.isAssignableFrom(object.getClass())) {
                isAnEnum = ((Field) object).isEnumConstant();
            } else if (Class.class.isAssignableFrom(object.getClass())) {
                isAnEnum = ((Class<?>) object).isEnum();
            }
        }
        return isAnEnum;
    }

    /**
     * @param fieldName the fieldName to check
     *
     * @return TRUE=fieldName is in ignore list, else FALSE
     */
    protected boolean isInIgnoreListForCode(String fieldName) {
        boolean isInList = false;
        if (fieldName != null) {
            isInList = (allFieldsToIgnoreForCode.contains(fieldName.toLowerCase()));
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
     * Set the flag, so 'toName()' must match the given {@link NAME_CHECK_ENUM}-check.
     *
     * @param nameCheckType #NAME_CHECK_ENUM
     *
     * @see NAME_CHECK_ENUM
     * @see #validateEnumObjectName(Field, Object)
     * @see #testValidateAllEnumObjects()
     */
    protected void setNameCheckType(NAME_CHECK_ENUM nameCheckType) {
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
                    LOGGER.warn("Checking getCode() is disabled for '{}' !", expectedField.getName());
                }
                isValid = resultName && resultCode;

            } catch (Exception e) {
                isValid = checkIgnoredFields(expectedField, e);
            }
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
                if (expected.contains(UNDERLINE_CHAR)) {
                    expected = ENUM_NAME_SRCH_PATTERN.matcher(expected).replaceAll(ENUM_NAME_REPL);
                    // expected = expected.replaceAll(ENUM_NAME_SRCH, ENUM_NAME_REPL);
                }
                nameCheck = expected.equalsIgnoreCase(actual);
                break;

            case CSW:
                nameCheck = expected.startsWith(actual);
                break;

            case CISW:
                if (expected.contains(UNDERLINE_CHAR)) {
                    expected = ENUM_NAME_SRCH_PATTERN.matcher(expected).replaceAll(ENUM_NAME_REPL);
                    // expected = expected.replaceAll(ENUM_NAME_SRCH, ENUM_NAME_REPL);
                }
                expected = expected.toLowerCase();
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

    public enum NAME_CHECK_ENUM {
        /** <strong>C</strong>ase sensitive, <strong>F</strong>ullname Check */
        CF,
        /** <strong>C</strong>ase <strong>I</strong>nsensitive, <strong>F</strong>ullname Check */
        CIF,
        /** <strong>C</strong>ase sensitive, <strong>S</strong>tarts <strong>W</strong>ith Check */
        CSW,
        /** <strong>C</strong>ase<strong>I</strong>nsensitive, <strong>S</strong>tarts <strong>W</strong>ith Check */
        CISW
    }
}
