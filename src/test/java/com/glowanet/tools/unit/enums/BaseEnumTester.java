package com.glowanet.tools.unit.enums;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Abstract class to use for unit-testing on {@link Object}, beans, pojos.
 * However, you name your classes with an amount of getter and setter.
 *
 * @param <E> the type of the enum which will be tested
 */
public abstract class BaseEnumTester<E> extends EnumUnitTester<E> {

    public BaseEnumTester(Class<E> typeOfo2E) {
        super(typeOfo2E);
    }

    public boolean _checkIgnoredFields(Field expectedField) {
        return super.checkIgnoredFields(expectedField);
    }

    public boolean _checkIgnoredFields(Field expectedField, Throwable e) {
        return super.checkIgnoredFields(expectedField, e);
    }

    public List<String> _enumObjectsToIgnoreForCode() {
        return List.of();
    }

    public NameCheckTypeEnum _getNameCheckType() {
        return super.getNameCheckType();
    }

    public boolean _isCodeCheckEnabled() {
        return super.isCodeCheckEnabled();
    }

    public boolean _isInIgnoreListForCode(String fieldName) {
        return super.isInIgnoreListForCode(fieldName);
    }

    public boolean _isEnum(Object object) {
        return super.isEnum(object);
    }

    public void _setCodeCheckEnabled(boolean codeCheckEnabled) {
        super.setCodeCheckEnabled(codeCheckEnabled);
    }

    public void _setNameCheckType(NameCheckTypeEnum nameCheckType) {
        super.setNameCheckType(nameCheckType);
    }

    public boolean _validateEnumObject(Field expectedField, Class<E> actualClazz) {
        return super.validateEnumObject(expectedField, actualClazz);
    }

    public boolean _validateEnumObjectCode(Field expectedField, E actualInstance) {
        return super.validateEnumObjectCode(expectedField, actualInstance);
    }

    public boolean _validateEnumObjectName(Field expectedField, E actualInstance) {
        return super.validateEnumObjectName(expectedField, actualInstance);
    }

    public void _makeFieldAccessible(Field field, E instance) {
        super.makeFieldAccessible(field, instance);
    }
}
