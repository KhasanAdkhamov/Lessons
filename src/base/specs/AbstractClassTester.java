package base.specs;

import base.Asserts;
import base.TestCounter;
import base.Tester;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public abstract class AbstractClassTester extends Tester {

    protected Class<?> targetClass;

    protected AbstractClassTester(TestCounter counter) {
        super(counter);
    }

    protected abstract String getTargetClassName();

    protected abstract List<FieldSpec> getFieldSpecs();

    protected abstract List<MethodSpec> getGetterSpecs();

    protected abstract List<MethodSpec> getSetterSpecs();
    

    protected abstract List<MethodSpec> getForbiddenMethods();

    protected abstract List<ConstructorSpec> getConstructorSpecs();
    

    protected abstract List<ValidationRule> getValidationRules();
    

    protected abstract List<TestData> getTestData();
    

    protected abstract List<ComputedPropertyTest> getComputedPropertyTests();

    protected abstract List<BusinessMethodTest> getBusinessMethodTests();
    

    @Override
    public void test() {
        targetClass = ReflectionUtils.loadClass(getTargetClassName(), this.getClass());

        counter.scope("Testing structure", () -> {
            testFieldsPrivacy();
            testGetters();
            testSetters();
            testForbiddenMethods();
        });

        counter.scope("Testing constructors", () -> testConstructors());

        counter.scope("Testing validation", () -> testValidation());

        counter.scope("Testing computed properties", () -> testComputedProperties());

        counter.scope("Testing business methods", () -> testBusinessMethods());
    }
    

    protected void testFieldsPrivacy() {
        List<FieldSpec> fieldSpecs = getFieldSpecs();
        
        for (FieldSpec fieldSpec : fieldSpecs) {
            counter.test(() -> {
                try {
                    Field field = targetClass.getDeclaredField(fieldSpec.name());

                    counter.checkTrue(
                        ReflectionUtils.isPrivate(field),
                        "Поле '%s' должно быть private", fieldSpec.name()
                    );

                    counter.checkTrue(
                        field.getType().equals(fieldSpec.type()),
                        "Поле '%s' должно иметь тип %s, но имеет %s",
                        fieldSpec.name(), fieldSpec.type().getSimpleName(), field.getType().getSimpleName()
                    );
                } catch (NoSuchFieldException e) {
                    counter.fail("Поле '%s' не найдено в классе %s", fieldSpec.name(), targetClass.getSimpleName());
                }
            });
        }
    }
    

    protected void testGetters() {
        List<MethodSpec> getterSpecs = getGetterSpecs();
        
        for (MethodSpec methodSpec : getterSpecs) {
            counter.test(() -> {
                String methodName = methodSpec.name();
                Class<?> expectedReturnType = methodSpec.returnType();

                Method method = ReflectionUtils.findMethod(targetClass, methodName);
                
                counter.checkTrue(
                    method != null,
                    "Геттер '%s()' не найден в классе %s",
                    methodName, targetClass.getSimpleName()
                );
                
                if (method == null) return;

                counter.checkTrue(
                    ReflectionUtils.isPublic(method),
                    "Геттер '%s()' должен быть public",
                    methodName
                );

                counter.checkTrue(
                    method.getReturnType().equals(expectedReturnType),
                    "Геттер '%s()' должен возвращать %s, но возвращает %s",
                    methodName, expectedReturnType.getSimpleName(), method.getReturnType().getSimpleName()
                );

                counter.checkTrue(
                    method.getParameterCount() == 0,
                    "Геттер '%s()' не должен принимать параметров, но принимает %d",
                    methodName, method.getParameterCount()
                );
            });
        }
    }

    protected void testSetters() {
        List<MethodSpec> setterSpecs = getSetterSpecs();
        
        for (MethodSpec methodSpec : setterSpecs) {
            counter.test(() -> {
                String methodName = methodSpec.name();
                Class<?>[] paramTypes = methodSpec.parameterTypes();
                
                // 1. Найти метод по имени и типам параметров
                Method method = ReflectionUtils.findMethod(targetClass, methodName, paramTypes);
                
                counter.checkTrue(
                    method != null,
                    "Сеттер '%s(%s)' не найден в классе %s",
                    methodName, formatTypes(paramTypes), targetClass.getSimpleName()
                );
                
                if (method == null) return;
                
                // 2. Проверить, что метод public
                counter.checkTrue(
                    ReflectionUtils.isPublic(method),
                    "Сеттер '%s' должен быть public",
                    methodName
                );
                
                // 3. Проверить, что возвращает void
                counter.checkTrue(
                    method.getReturnType().equals(void.class),
                    "Сеттер '%s' должен возвращать void, но возвращает %s",
                    methodName, method.getReturnType().getSimpleName()
                );
                
                // 4. Проверить количество параметров (должен быть 1)
                counter.checkTrue(
                    method.getParameterCount() == 1,
                    "Сеттер '%s' должен принимать 1 параметр, но принимает %d",
                    methodName, method.getParameterCount()
                );
            });
        }
    }

    protected void testForbiddenMethods() {
        List<MethodSpec> forbiddenMethods = getForbiddenMethods();
        
        for (MethodSpec methodSpec : forbiddenMethods) {
            counter.test(() -> {
                String methodName = methodSpec.name();
                Class<?>[] paramTypes = methodSpec.parameterTypes();
                
                // paramTypes может быть null или пустым для геттеров
                if (paramTypes == null) {
                    paramTypes = new Class<?>[0];
                }
                
                // Попытаться найти метод
                Method method = ReflectionUtils.findMethod(targetClass, methodName, paramTypes);
                
                // Метод НЕ должен существовать
                counter.checkTrue(
                    method == null,
                    "Метод '%s' не должен существовать в классе %s",
                    formatMethodSignature(methodName, paramTypes),
                    targetClass.getSimpleName()
                );
            });
        }
    }

    protected void testConstructors() {
        // Часть 1: Проверка наличия конструкторов
        List<ConstructorSpec> constructorSpecs = getConstructorSpecs();
        for (ConstructorSpec constructorSpec : constructorSpecs) {
            counter.test(() -> {
                Class<?>[] paramTypes = constructorSpec.parameterTypes();
                
                Constructor<?> ctor = ReflectionUtils.findConstructor(targetClass, paramTypes);
                
                counter.checkTrue(
                    ctor != null,
                    "Конструктор %s(%s) не найден",
                    targetClass.getSimpleName(), formatTypes(paramTypes)
                );
            });
        }
        
        // Часть 2: Проверка работы с валидными данными
        List<TestData> testDataList = getTestData();
        for (TestData testData : testDataList) {
            counter.test(() -> {
                // Создать объект с тестовыми данными
                Object instance = createInstance(testData.constructorArgs());
                
                counter.checkTrue(
                    instance != null,
                    "Не удалось создать экземпляр с аргументами: %s",
                    Arrays.toString(testData.constructorArgs())
                );
                
                // Проверить значения геттеров
                Map<String, Object> expectedValues = testData.expectedGetterValues();
                for (Map.Entry<String, Object> entry : expectedValues.entrySet()) {
                    String getterName = entry.getKey();
                    Object expectedValue = entry.getValue();
                    Object actualValue = invokeGetter(instance, getterName);
                    
                    // Для double использовать сравнение с epsilon
                    if (expectedValue instanceof Double) {
                        Asserts.assertEquals(
                            String.format("Геттер %s() после конструктора", getterName),
                            (Double) expectedValue,
                            (Double) actualValue,
                            0.0001
                        );
                    } else {
                        Asserts.assertEquals(
                            String.format("Геттер %s() после конструктора", getterName),
                            expectedValue,
                            actualValue
                        );
                    }
                }
            });
        }
    }

    protected void testValidation() {
        List<ValidationRule> validationRules = getValidationRules();
        
        for (ValidationRule validationRule : validationRules) {
            String fieldName = validationRule.fieldName();
            String setterName = validationRule.setterName();
            String getterName = validationRule.getterName();
            
            // Часть 1: Проверка сеттера с валидными значениями
            for (Object validValue : validationRule.validValues()) {
                counter.test(() -> {
                    // Создать объект с базовыми валидными данными (из первого TestData)
                    Object instance = createInstanceWithDefaults();
                    
                    // Вызвать сеттер с валидным значением
                    invokeSetter(instance, setterName, validValue);
                    
                    // Проверить, что значение установлено
                    Object actualValue = invokeGetter(instance, getterName);
                    assertEqualsWithEpsilon(
                        String.format("Сеттер %s(%s) должен установить значение", setterName, validValue),
                        validValue, actualValue
                    );
                });
            }
            
            // Часть 2: Проверка сеттера с невалидными значениями
            for (Object invalidValue : validationRule.invalidValues()) {
                counter.test(() -> {
                    // Создать объект с валидными данными
                    Object instance = createInstanceWithDefaults();
                    
                    // Запомнить текущее значение
                    Object valueBefore = invokeGetter(instance, getterName);
                    
                    // Ожидаем исключение при невалидном значении
                    // Студенты могут бросать IllegalArgumentException или RuntimeException
                    boolean thrownException = false;
                    try {
                        invokeSetter(instance, setterName, invalidValue);
                    } catch (IllegalArgumentException e) {
                        // Идеальный случай - IllegalArgumentException
                        thrownException = true;
                    } catch (RuntimeException e) {
                        // ReflectionUtils оборачивает исключения из методов в RuntimeException
                        // Если исключение брошено из метода студента (есть cause), это валидация
                        // Если это ошибка рефлексии (нет cause или сообщение об ошибке рефлексии), перебрасываем
                        Throwable cause = e.getCause();
                        String message = e.getMessage();
                        
                        // Проверяем, что это не ошибка рефлексии (метод не найден и т.д.)
                        boolean isReflectionError = (message != null && 
                            (message.contains("не найден") || 
                             message.contains("not found") ||
                             message.contains("Ошибка доступа")));
                        
                        if (isReflectionError && cause == null) {
                            // Это ошибка рефлексии - перебрасываем
                            throw e;
                        } else {
                            // Любое другое исключение (включая обёрнутые из методов) считаем валидацией
                            thrownException = true;
                        }
                    }
                    
                    counter.checkTrue(
                        thrownException,
                        "Сеттер %s должен бросить исключение (IllegalArgumentException или RuntimeException) для значения %s",
                        setterName, invalidValue
                    );
                    
                    // Проверить, что значение НЕ изменилось
                    Object valueAfter = invokeGetter(instance, getterName);
                    assertEqualsWithEpsilon(
                        String.format("Значение не должно измениться после невалидного %s(%s)", setterName, invalidValue),
                        valueBefore, valueAfter
                    );
                });
            }
            
            // Часть 3: Проверка конструктора с невалидными значениями
            for (Object invalidValue : validationRule.invalidValues()) {
                counter.test(() -> {
                    Object[] args = createConstructorArgsWithInvalidValue(fieldName, invalidValue);
                    
                    boolean thrownException = false;
                    try {
                        createInstance(args);
                    } catch (IllegalArgumentException e) {
                        // Идеальный случай - IllegalArgumentException
                        thrownException = true;
                    } catch (RuntimeException e) {
                        // ReflectionUtils оборачивает исключения из конструктора в RuntimeException
                        // Если исключение брошено из конструктора студента (есть cause), это валидация
                        // Если это ошибка рефлексии (нет cause или сообщение об ошибке рефлексии), перебрасываем
                        Throwable cause = e.getCause();
                        String message = e.getMessage();
                        
                        // Проверяем, что это не ошибка рефлексии (конструктор не найден и т.д.)
                        // Если есть cause, это исключение из конструктора студента - валидация
                        // Если нет cause и сообщение об ошибке доступа - это ошибка рефлексии
                        boolean isReflectionError = (cause == null && message != null && 
                            (message.contains("не найден") || 
                             message.contains("not found") ||
                             message.contains("Ошибка доступа")));
                        
                        if (isReflectionError) {
                            // Это ошибка рефлексии - перебрасываем
                            throw e;
                        } else {
                            // Любое другое исключение (включая обёрнутые из конструктора) считаем валидацией
                            // Если есть cause, это исключение из конструктора студента
                            // Если сообщение содержит "Ошибка при создании экземпляра" и есть cause - это валидация
                            thrownException = true;
                        }
                    }
                    
                    counter.checkTrue(
                        thrownException,
                        "Конструктор должен бросить исключение (IllegalArgumentException или RuntimeException) для %s = %s",
                        fieldName, invalidValue
                    );
                });
            }
        }
    }

    protected void testComputedProperties() {
        List<ComputedPropertyTest> computedTests = getComputedPropertyTests();
        
        for (ComputedPropertyTest computedTest : computedTests) {
            counter.test(() -> {
                // 1. Создать объект с указанными аргументами
                Object instance = createInstance(computedTest.constructorArgs());
                
                // 2. Вызвать метод вычисляемого свойства
                String methodName = computedTest.methodName();
                Object actualValue = invokeGetter(instance, methodName);
                
                // 3. Сравнить с ожидаемым значением
                Object expectedValue = computedTest.expectedValue();
                double epsilon = computedTest.epsilon();
                
                if (epsilon > 0 && expectedValue instanceof Number && actualValue instanceof Number) {
                    // Сравнение с погрешностью для числовых типов
                    Asserts.assertEquals(
                        String.format("Вычисляемое свойство %s()", methodName),
                        ((Number) expectedValue).doubleValue(),
                        ((Number) actualValue).doubleValue(),
                        epsilon
                    );
                } else {
                    // Точное сравнение
                    Asserts.assertEquals(
                        String.format("Вычисляемое свойство %s()", methodName),
                        expectedValue,
                        actualValue
                    );
                }
            });
        }
    }

    protected void testBusinessMethods() {
        List<BusinessMethodTest> businessTests = getBusinessMethodTests();
        
        for (BusinessMethodTest businessTest : businessTests) {
            counter.test(() -> {
                String methodName = businessTest.methodName();
                Object[] methodArgs = businessTest.methodArgs();
                boolean isStatic = businessTest.isStatic();
                boolean shouldThrow = businessTest.shouldThrow();
                
                Object instance = null;
                Method method;
                
                // Получить метод
                Class<?>[] paramTypes = toTypes(methodArgs);
                method = ReflectionUtils.getMethod(targetClass, methodName, paramTypes);
                
                // Создать экземпляр (если не статический метод)
                if (!isStatic) {
                    instance = createInstance(businessTest.constructorArgs());
                }
                
                if (shouldThrow) {
                    // Ожидаем исключение
                    testMethodThrows(instance, method, methodArgs, businessTest.expectedExceptionType(), methodName);
                } else {
                    // Не ожидаем исключение
                    Object result = testMethodSucceeds(instance, method, methodArgs, methodName, isStatic);
                    
                    // Проверить возвращаемое значение (если ожидается)
                    if (businessTest.expectedResult() != null) {
                        assertEqualsWithEpsilon(
                            String.format("Результат %s()", methodName),
                            businessTest.expectedResult(),
                            result
                        );
                    }
                    
                    // Проверить состояние объекта после вызова (если указано)
                    if (businessTest.expectedStateAfter() != null && !isStatic) {
                        for (Map.Entry<String, Object> entry : businessTest.expectedStateAfter().entrySet()) {
                            Object actualValue = invokeGetter(instance, entry.getKey());
                            assertEqualsWithEpsilon(
                                String.format("Состояние %s() после %s()", entry.getKey(), methodName),
                                entry.getValue(),
                                actualValue
                            );
                        }
                    }
                }
            });
        }
    }

    private void testMethodThrows(Object instance, Method method, Object[] args, 
                                  Class<? extends Exception> expectedType, String methodName) {
        boolean thrown = false;
        Class<?> actualType = null;
        try {
            ReflectionUtils.invokeMethod(instance, method, args);
        } catch (Exception e) {
            thrown = true;
            actualType = e.getClass();
        }
        
        counter.checkTrue(thrown,
            "Метод %s() должен бросить исключение", methodName);
        
        if (thrown && expectedType != null) {
            counter.checkTrue(
                expectedType.isAssignableFrom(actualType),
                "Метод %s() должен бросить %s, но бросил %s",
                methodName, expectedType.getSimpleName(), actualType.getSimpleName()
            );
        }
    }
    

    private Object testMethodSucceeds(Object instance, Method method, Object[] args, 
                                      String methodName, boolean isStatic) {
        try {
            if (isStatic) {
                return ReflectionUtils.invokeStaticMethod(method, args);
            } else {
                return ReflectionUtils.invokeMethod(instance, method, args);
            }
        } catch (Exception e) {
            counter.fail("Метод %s() не должен бросать исключение, но бросил %s: %s",
                methodName, e.getClass().getSimpleName(), e.getMessage());
            return null;
        }
    }
    

    protected Object createInstance(Object... args) {
        Class<?>[] paramTypes = toTypes(args);
        
        // Если есть null в args, пытаемся определить типы из спецификации конструктора
        boolean hasNull = false;
        for (Object arg : args) {
            if (arg == null) {
                hasNull = true;
                break;
            }
        }
        
        if (hasNull) {
            // Пытаемся найти подходящий конструктор из спецификации
            List<ConstructorSpec> constructorSpecs = getConstructorSpecs();
            if (constructorSpecs != null && !constructorSpecs.isEmpty() && args.length > 0) {
                // Ищем конструктор с подходящим количеством параметров
                for (ConstructorSpec spec : constructorSpecs) {
                    Class<?>[] specTypes = spec.parameterTypes();
                    if (specTypes.length == args.length) {
                        // Используем типы из спецификации, заменяя только те, где args[i] != null
                        Class<?>[] resolvedTypes = new Class<?>[args.length];
                        for (int i = 0; i < args.length; i++) {
                            if (args[i] == null) {
                                // Для null используем тип из спецификации конструктора
                                resolvedTypes[i] = specTypes[i];
                            } else {
                                // Для не-null используем тип, определённый из значения
                                resolvedTypes[i] = paramTypes[i];
                            }
                        }
                        paramTypes = resolvedTypes;
                        break;
                    }
                }
            } else {
                // Если нет спецификации конструктора, пытаемся использовать типы из FieldSpecs
                // Предполагаем, что порядок полей соответствует порядку параметров конструктора
                List<FieldSpec> fieldSpecs = getFieldSpecs();
                if (fieldSpecs != null && fieldSpecs.size() == args.length) {
                    Class<?>[] resolvedTypes = new Class<?>[args.length];
                    for (int i = 0; i < args.length; i++) {
                        if (args[i] == null) {
                            resolvedTypes[i] = fieldSpecs.get(i).type();
                        } else {
                            resolvedTypes[i] = paramTypes[i];
                        }
                    }
                    paramTypes = resolvedTypes;
                }
            }
        }
        
        Constructor<?> constructor = ReflectionUtils.getConstructor(targetClass, paramTypes);
        return ReflectionUtils.newInstance(constructor, args);
    }

    protected Object invokeGetter(Object instance, String getterName) {
        Method method = ReflectionUtils.getMethod(targetClass, getterName);
        return ReflectionUtils.invokeMethod(instance, method);
    }

    protected void invokeSetter(Object instance, String setterName, Object value) {
        Class<?> paramType;
        if (value == null) {
            // Для null нужно найти метод по имени и определить тип параметра из сигнатуры
            // Ищем метод с одним параметром
            Method[] methods = targetClass.getMethods();
            Method setterMethod = null;
            for (Method m : methods) {
                if (m.getName().equals(setterName) && m.getParameterCount() == 1) {
                    setterMethod = m;
                    break;
                }
            }
            if (setterMethod == null) {
                throw new RuntimeException("Сеттер '" + setterName + "' не найден в классе " + targetClass.getSimpleName());
            }
            paramType = setterMethod.getParameterTypes()[0];
        } else if (value instanceof Integer) {
            paramType = int.class;
        } else if (value instanceof Double) {
            paramType = double.class;
        } else if (value instanceof Boolean) {
            paramType = boolean.class;
        } else if (value instanceof Long) {
            paramType = long.class;
        } else if (value instanceof Float) {
            paramType = float.class;
        } else if (value instanceof Short) {
            paramType = short.class;
        } else if (value instanceof Byte) {
            paramType = byte.class;
        } else if (value instanceof Character) {
            paramType = char.class;
        } else {
            paramType = value.getClass();
        }
        Method method = ReflectionUtils.getMethod(targetClass, setterName, paramType);
        ReflectionUtils.invokeMethod(instance, method, value);
    }
    

    protected Class<?>[] toTypes(Object[] args) {
        if (args == null || args.length == 0) {
            return new Class<?>[0];
        }
        
        Class<?>[] types = new Class<?>[args.length];
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            if (arg == null) {
                types[i] = Object.class;
            } else if (arg instanceof Integer) {
                types[i] = int.class;
            } else if (arg instanceof Double) {
                types[i] = double.class;
            } else if (arg instanceof Boolean) {
                types[i] = boolean.class;
            } else if (arg instanceof Long) {
                types[i] = long.class;
            } else if (arg instanceof Float) {
                types[i] = float.class;
            } else if (arg instanceof Short) {
                types[i] = short.class;
            } else if (arg instanceof Byte) {
                types[i] = byte.class;
            } else if (arg instanceof Character) {
                types[i] = char.class;
            } else {
                types[i] = arg.getClass();
            }
        }
        return types;
    }
    

    private String formatTypes(Class<?>[] types) {
        if (types == null || types.length == 0) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < types.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(types[i].getSimpleName());
        }
        return sb.toString();
    }

    private String formatMethodSignature(String name, Class<?>[] paramTypes) {
        if (paramTypes == null || paramTypes.length == 0) {
            return name + "()";
        }
        
        StringBuilder sb = new StringBuilder(name);
        sb.append("(");
        for (int i = 0; i < paramTypes.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(paramTypes[i].getSimpleName());
        }
        sb.append(")");
        return sb.toString();
    }

    protected Object createInstanceWithDefaults() {
        List<TestData> testDataList = getTestData();
        if (testDataList == null || testDataList.isEmpty()) {
            throw new IllegalStateException("Не найдены тестовые данные для создания экземпляра");
        }
        TestData firstTestData = testDataList.get(0);
        return createInstance(firstTestData.constructorArgs());
    }
    

    protected Object[] createConstructorArgsWithInvalidValue(String fieldName, Object invalidValue) {
        List<TestData> testDataList = getTestData();
        if (testDataList == null || testDataList.isEmpty()) {
            throw new IllegalStateException("Не найдены тестовые данные для создания аргументов конструктора");
        }
        
        // Берём первый TestData как базовый
        TestData firstTestData = testDataList.get(0);
        Object[] baseArgs = firstTestData.constructorArgs();
        
        // Определяем позицию поля в конструкторе по порядку полей в getFieldSpecs()
        List<FieldSpec> fieldSpecs = getFieldSpecs();
        int fieldIndex = -1;
        for (int i = 0; i < fieldSpecs.size(); i++) {
            if (fieldSpecs.get(i).name().equals(fieldName)) {
                fieldIndex = i;
                break;
            }
        }
        
        if (fieldIndex == -1) {
            throw new IllegalArgumentException("Поле '" + fieldName + "' не найдено в спецификации полей");
        }
        
        if (fieldIndex >= baseArgs.length) {
            throw new IllegalStateException(
                String.format("Индекс поля %d превышает количество аргументов конструктора (%d)", 
                    fieldIndex, baseArgs.length)
            );
        }
        
        // Создаём копию массива аргументов и подставляем невалидное значение
        Object[] args = baseArgs.clone();
        args[fieldIndex] = invalidValue;
        
        return args;
    }
    

    protected void assertEqualsWithEpsilon(String message, Object expected, Object actual) {
        if (expected instanceof Double && actual instanceof Double) {
            Asserts.assertEquals(message, (Double) expected, (Double) actual, 0.0001);
        } else {
            Asserts.assertEquals(message, expected, actual);
        }
    }
}
