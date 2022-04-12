package com.glowanet.tools.unit;

import com.glowanet.tools.random.IRandomStrategy;
import com.glowanet.tools.random.IRandomStrategyByType;
import com.glowanet.util.junit.rules.ErrorCollectorExt;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.beans.PropertyUtil;
import org.hamcrest.core.IsBetween;
import org.junit.Rule;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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
 * Abstract class as base for testing a specialized type of classes, like entities, enums, etc.
 *
 * @param <T> the type of {@code object2Test}
 */
public abstract class AbstractUnitTester<T> {
    // static fields
    public static final boolean               DEFAULT_CHECK_SVUID              = true;
    public static final String                SERIAL_VERSION_UID_NAME          = "serialVersionUID";
    /** Range of IDs which are not allowed to use. */
    public static final IsBetween.Range<Long> SERIAL_VERSION_UID_INVALID_RANGE = new IsBetween.Range<>(-100L, 100L);

    private static final Logger LOGGER = LogManager.getLogger();

    private static com.glowanet.tools.random.RandomValueFactory randomValueFactory;
// end - static fields

    static {
        try {
            randomValueFactory = com.glowanet.tools.random.RandomValueFactory.getInstance();
        } catch (RuntimeException ignored) {
            //ignore
        }
    }

    // fields
    @Rule
    public final  ErrorCollectorExt collector = new ErrorCollectorExt();
    private final Class<T>          typeOfo2T;
// end - fields

// constructors

    /**
     * @param typeOfo2T the class object of {@code T}
     */
    protected AbstractUnitTester(Class<T> typeOfo2T) {
        this.typeOfo2T = typeOfo2T;
        init();
    }
// end - constructors

// abstract methods

    //protected abstract T createObject2Test();
// end -  abstract methods

// static method

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
// end - static method

// methods

    /**
     * Create instance (with default constructor).
     *
     * @return instance of the {@code T}
     *
     * @see #init()
     */
    private T createObject2Test() {
        Class<T> type = getTypeOfo2T();
        T newObject = null;
        if (type != null) {
            try {
                newObject = type.getDeclaredConstructor((Class<?>[]) null).newInstance((Object[]) null);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                LOGGER.error("Cannot create from type '{}' : {}", type, e.getMessage());
                fail(e.getMessage());
            }
        } else {
            LOGGER.warn("Cannot create from type 'null'!");
            fail("Cannot create from type 'null'!");
        }
        return newObject;
    }

    /**
     * @return T
     */
    public T getObject2Test() {
        validateObjectAndType();
        return createObject2Test();
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
            fail(String.format("No '%s' defined : %s", fieldName, e.getMessage()));
        } catch (SecurityException e) {
            fail(String.format("'%s' not accessible : %s ", fieldName, e.getMessage()));
        }
        return idField;
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
     * @return the type of o2T
     */
    protected Class<T> getTypeOfo2T() {
        return this.typeOfo2T;
    }

    /**
     * @param clazzA the type to inspect
     *
     * @return true=this type has the Serializable-IF, false=otherwise
     */
    protected boolean hasSerializableIF(Class<?> clazzA) {
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
     * Initialize the unit tester.
     */
    protected void init() {
        validateObjectAndType();
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
     * Extracts a number from a string.
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
     * @param o2T an instance of type {@code T} to inspect
     *
     * @return list of all property descriptors of {@code o2T}
     */
    private List<PropertyDescriptor> getAllPropertyDescriptors(T o2T) {
        return Arrays.stream(PropertyUtil.propertyDescriptorsFor(o2T, null)).collect(Collectors.toList());
    }

    /**
     * Generating a value based on the class-clazzV.
     *
     * @param clazzV the class type of return value
     * @param <V>    the type of the return value
     *
     * @return the generated value or null
     */
    @SuppressWarnings({"java:S2209", "unchecked", "rawtypes"})
    private <V> Object retrieveDefaultValue(Class<V> clazzV) {
        Object result = null;
        if (randomValueFactory != null) {
            IRandomStrategy<?> randomValueCreator = randomValueFactory.getProvider(clazzV);
            if (randomValueCreator != null) {
                if (IRandomStrategyByType.class.isAssignableFrom(randomValueCreator.getClass())) {
                    result = ((IRandomStrategyByType) randomValueCreator).next(clazzV);
                } else {
                    result = randomValueCreator.next();
                }
            }
        }
        return result;
    }

    /**
     * Verify that the instance of o2T has the correct type.
     */
    private void validateObjectAndType() {
        assertThat(createObject2Test(), anyOf(nullValue(), isA(this.typeOfo2T)));
    }
// end - methods
}
