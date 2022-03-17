package com.glowanet.tools.unit;

import com.glowanet.tools.random.RandomValueFactory;
import com.glowanet.util.junit.rules.ErrorCollectorExt;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.hamcrest.beans.PropertyUtil;
import org.junit.Rule;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.fail;

/**
 * Abstract class as base for testing a specilized type of classes, like entities, enums, etc.
 *
 * @param <T> the type of {@code object2Test}
 */
public abstract class AbstractUnitTester<T> {

    private static com.glowanet.tools.random.RandomValueFactory randomValueFactory;

    @Rule
    public final  ErrorCollectorExt collector = new ErrorCollectorExt();
    private final Class<T>          typeOfo2T;

    static {
        try {
            randomValueFactory = com.glowanet.tools.random.RandomValueFactory.getInstance();
        } catch (RuntimeException ignored) {
            //ignore
        }
    }

    /**
     * @param typeOfo2T the class object of {@code T}
     */
    protected AbstractUnitTester(Class<T> typeOfo2T) {
        this.typeOfo2T = typeOfo2T;
        init();
    }

    /**
     * Put a value into a static final field.
     *
     * @param clazzA    the type of the object, where the field is located
     * @param fieldName the exact name of the field
     * @param newValue  the new value to put in the field
     *
     * @throws IllegalAccessException it is not allowed to modify this field in this class
     */
    @SuppressWarnings({"java:S3011"})
    protected static void setFinalStatic(Class<?> clazzA, String fieldName, Object newValue) throws IllegalAccessException {
        throw new IllegalAccessException("Function currently not supported");
/*
        Field field = clazzA.getDeclaredField(fieldName);
        field.setAccessible(true);
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        field.set(null, newValue);
*/
    }

    /**
     * Initialize the unit tester.
     */
    protected void init() {
        validateObjectAndType();
    }

    /**
     * Create instance (with default constructor, if available).
     *
     * @return instance of the {@code T}
     *
     * @see #init()
     */
    protected abstract T createObject2Test();

    protected Class<T> getTypeOfo2T() {
        return this.typeOfo2T;
    }

    public T getObject2Test() {
        validateObjectAndType();
        return createObject2Test();
    }

    private void validateObjectAndType() {
        assertThat(createObject2Test(), anyOf(nullValue(), isA(this.typeOfo2T)));
    }

    /**
     * @param o2T an instance of type {@code T} to inspect
     *
     * @return list of all property descriptors of {@code o2T}
     */
    private List<PropertyDescriptor> getAllPropertyDescriptors(T o2T) {
        return Arrays.stream(PropertyUtil.propertyDescriptorsFor(o2T, null)).collect(Collectors.toList());
    }

    /**
     * @return list of fields, which have a getter
     */
    protected List<PropertyDescriptor> findGetter() {
        List<PropertyDescriptor> getterList = new ArrayList<>();
        getAllPropertyDescriptors(createObject2Test()).forEach((PropertyDescriptor pd) -> {
            if (pd.getReadMethod() != null) {
                getterList.add(pd);
            }
        });
        return getterList;
    }

    /**
     * @return list of fields, which have a setter
     */
    protected List<PropertyDescriptor> findSetter() {
        List<PropertyDescriptor> setterList = new ArrayList<>();
        getAllPropertyDescriptors(createObject2Test()).forEach((PropertyDescriptor pd) -> {
            if (pd.getWriteMethod() != null) {
                setterList.add(pd);
            }
        });
        return setterList;
    }

    /**
     * @param clazzA the type to inspect
     *
     * @return true=this type has the Serializable-IF, false=otherwise
     */
    public boolean hasSerializableIF(Class<?> clazzA) {
        boolean hasIt = false;
        List<Class<?>> listIF = ClassUtils.getAllInterfaces(clazzA);
        for (Class<?> clazzAIF : listIF) {
            if (Serializable.class.equals(clazzAIF)) {
                hasIt = true;
                break;
            }
        }
        return hasIt;
    }

    /**
     * @param instance  an instance of {@code T}
     * @param fieldName the name of the field
     *
     * @return a field instance
     */
    @SuppressWarnings("java:S5960")
    protected Field findField(T instance, String fieldName) {
        Field idField = null;
        try {
            idField = instance.getClass().getDeclaredField(fieldName);
            makeFieldAccessible(idField, instance);
        } catch (NoSuchFieldException e) {
            fail("No " + fieldName + " defined : " + e.getMessage());
        } catch (SecurityException e) {
            fail(fieldName + " not accessible : " + e.getMessage());
        }
        return idField;
    }

    /**
     * @param field    a field instance
     * @param instance the current instance where to make the {@code field} accessible
     */
    protected void makeFieldAccessible(Field field, T instance) {
        try {
            if (!field.canAccess(instance)) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            field.trySetAccessible();
        }
    }

    /**
     * Retrieves all public constants from a class.
     *
     * @param clazzA the type from which to retrieve the constants
     *
     * @return a list of constants as field objects or an empty list.
     */
    protected List<Field> retrievePublicConstantsfromClass(Class<?> clazzA) {
        List<Field> publicConsts = new ArrayList<>();
        for (Field field : clazzA.getDeclaredFields()) {
            int modifiers = field.getModifiers();
            if (Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers)) {
                publicConsts.add(field);
            }
        }
        return publicConsts;
    }

    /**
     * In case we have a "special interpretation" on a number extraction, you can
     * override this method.
     *
     * @param textWithNumber the text with the number
     * @param numberAsText   the currently extracted numeric values from the text
     *
     * @return the extracted "special" number for this text
     */
    protected String retrieveNumberFromTextSpecialized(String textWithNumber, String numberAsText) {
        return numberAsText;
    }

    /**
     * Extracts a number from an string.
     *
     * @param textWithNumber the text with the number
     *
     * @return the extracted number or NULL
     */
    protected Number retrieveNumberFromText(String textWithNumber) {
        String numberAsText = StringUtils.getDigits(textWithNumber);
        if (NumberUtils.isParsable(numberAsText)) {
            numberAsText = retrieveNumberFromTextSpecialized(textWithNumber, numberAsText);
            return NumberUtils.createNumber(numberAsText);
        } else {
            return null;
        }
    }

    /**
     * Generating the map of values for the method parameters.
     *
     * @param method the method to inspect
     *
     * @return map of types and values for a method
     */
    protected Map<Class<?>, Object> retrieveMethodParameters(Method method) {
        Map<Class<?>, Object> setterParams = new LinkedHashMap<>();
        if (method != null) {
            for (Parameter param : method.getParameters()) {
                Object paramValue = retrieveDefaultValue(param.getType());
                if (Object.class.isAssignableFrom(param.getType())) {
                    setterParams.put(param.getType(), paramValue);
                } else if (param.getType().isPrimitive()) {
                    setterParams.put(param.getType(), paramValue);
                } else {
                    setterParams.put(param.getType(), null);
                }
            }
        }
        return setterParams;
    }

    /**
     * Generating a value based on the class-clazzV.
     *
     * @param clazzV the class type of return value
     * @param <V>    the type of the return value
     *
     * @return the generated value or null
     */
    @SuppressWarnings("java:S2209")
    private <V> Object retrieveDefaultValue(Class<V> clazzV) {
        Object result = null;
        if (randomValueFactory != null) {
            try {
                result = RandomValueFactory.createRandomValue(clazzV).randomValue();
            } catch (NullPointerException e) {
                result = RandomValueFactory.createLegacyRandomValue().randomValue(clazzV);
            }
        }
        return result;
    }

}
