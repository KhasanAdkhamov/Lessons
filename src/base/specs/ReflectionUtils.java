package base.specs;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Утилитный класс для работы с Java Reflection API.
 * Предоставляет методы для загрузки классов, получения полей/методов/конструкторов,
 * вызова методов и проверки модификаторов.
 * 
 * <p>Класс является utility class и не предназначен для создания экземпляров.
 */
public final class ReflectionUtils {
    
    /**
     * Приватный конструктор для предотвращения создания экземпляров utility класса.
     */
    private ReflectionUtils() {
        throw new AssertionError("Utility class cannot be instantiated");
    }
    
    /**
     * Загружает класс по имени. Сначала пытается загрузить из default package,
     * затем пробует найти класс в том же пакете, что и тестер.
     * 
     * @param className имя класса без пакета (например, "Student") или полное имя (например, "product.Product")
     * @param testerClass класс тестера (для определения пакета поиска)
     * @return загруженный класс
     * @throws RuntimeException если класс не найден, с информативным сообщением
     */
    public static Class<?> loadClass(String className, Class<?> testerClass) {
        // Если имя содержит точку, считаем это полным именем класса
        if (className.contains(".")) {
            try {
                return Class.forName(className);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(
                    "Класс '" + className + "' не найден. " +
                    "Убедитесь, что класс скомпилирован и находится в classpath."
                );
            }
        }
        
        // Пробуем загрузить из default package
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e1) {
            // Если не найден в default package, пробуем найти в пакете тестера
            if (testerClass != null) {
                String testerPackage = testerClass.getPackage() != null ? testerClass.getPackage().getName() : null;
                if (testerPackage != null && !testerPackage.isEmpty()) {
                    String fullClassName = testerPackage + "." + className;
                    try {
                        return Class.forName(fullClassName);
                    } catch (ClassNotFoundException e2) {
                        // Продолжаем с ошибкой по умолчанию
                    }
                }
            }
            
            // Если ничего не помогло, выбрасываем исключение
            throw new RuntimeException(
                "Класс '" + className + "' не найден. " +
                "Убедитесь, что класс скомпилирован и находится в classpath. " +
                "Проверьте, что класс находится в default package или укажите полное имя класса (например, 'product.Product')."
            );
        }
    }
    
    /**
     * Загружает класс из default package по имени (устаревший метод для обратной совместимости).
     * 
     * @param className имя класса без пакета (например, "Student")
     * @return загруженный класс
     * @throws RuntimeException если класс не найден, с информативным сообщением
     * @deprecated Используйте loadClass(String, Class) для поддержки классов из разных пакетов
     */
    @Deprecated
    public static Class<?> loadClass(String className) {
        return loadClass(className, null);
    }
    
    /**
     * Получает все объявленные поля класса.
     * 
     * @param clazz класс для анализа
     * @return список всех объявленных полей класса
     */
    public static List<Field> getDeclaredFields(Class<?> clazz) {
        return Arrays.asList(clazz.getDeclaredFields());
    }
    
    /**
     * Получает метод по имени и типам параметров.
     * 
     * @param clazz класс для поиска метода
     * @param name имя метода
     * @param paramTypes типы параметров (varargs)
     * @return найденный метод
     * @throws RuntimeException если метод не найден, с информативным сообщением
     */
    public static Method getMethod(Class<?> clazz, String name, Class<?>... paramTypes) {
        try {
            return clazz.getMethod(name, paramTypes);
        } catch (NoSuchMethodException e) {
            String signature = formatSignature(name, paramTypes);
            throw new RuntimeException(
                "Метод '" + signature + "' не найден в классе " + clazz.getSimpleName()
            );
        }
    }
    
    /**
     * Ищет метод по имени и типам параметров без бросания исключения.
     * 
     * @param clazz класс для поиска метода
     * @param name имя метода
     * @param paramTypes типы параметров (varargs)
     * @return найденный метод или null, если метод не найден
     */
    public static Method findMethod(Class<?> clazz, String name, Class<?>... paramTypes) {
        try {
            return clazz.getMethod(name, paramTypes);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }
    
    /**
     * Получает конструктор по типам параметров.
     * 
     * @param clazz класс для поиска конструктора
     * @param paramTypes типы параметров (varargs)
     * @return найденный конструктор
     * @throws RuntimeException если конструктор не найден, с информативным сообщением
     */
    public static Constructor<?> getConstructor(Class<?> clazz, Class<?>... paramTypes) {
        try {
            return clazz.getConstructor(paramTypes);
        } catch (NoSuchMethodException e) {
            String signature = formatSignature(clazz.getSimpleName(), paramTypes);
            throw new RuntimeException(
                "Конструктор " + signature + " не найден"
            );
        }
    }
    
    /**
     * Ищет конструктор по типам параметров без бросания исключения.
     * 
     * @param clazz класс для поиска конструктора
     * @param paramTypes типы параметров (varargs)
     * @return найденный конструктор или null, если конструктор не найден
     */
    public static Constructor<?> findConstructor(Class<?> clazz, Class<?>... paramTypes) {
        try {
            return clazz.getConstructor(paramTypes);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }
    
    /**
     * Создаёт экземпляр объекта через конструктор.
     * 
     * @param ctor конструктор для вызова
     * @param args аргументы конструктора
     * @return созданный объект
     * @throws IllegalArgumentException если конструктор бросил IllegalArgumentException
     *         (перебрасывается как есть для тестирования валидации)
     * @throws RuntimeException для других ошибок создания объекта
     */
    public static Object newInstance(Constructor<?> ctor, Object... args) {
        try {
            ctor.setAccessible(true);
            return ctor.newInstance(args);
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            if (cause instanceof IllegalArgumentException) {
                throw (IllegalArgumentException) cause;
            }
            throw new RuntimeException("Ошибка при создании экземпляра: " + cause.getMessage(), cause);
        } catch (IllegalAccessException | InstantiationException e) {
            throw new RuntimeException("Ошибка доступа при создании экземпляра: " + e.getMessage(), e);
        }
    }
    
    /**
     * Вызывает метод на экземпляре объекта.
     * 
     * @param instance объект, на котором вызывается метод (null для статических методов)
     * @param method метод для вызова
     * @param args аргументы метода
     * @return результат вызова метода
     * @throws IllegalArgumentException если метод бросил IllegalArgumentException
     *         (перебрасывается как есть для тестирования валидации)
     * @throws RuntimeException для других ошибок вызова метода
     */
    public static Object invokeMethod(Object instance, Method method, Object... args) {
        try {
            method.setAccessible(true);
            return method.invoke(instance, args);
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            if (cause instanceof IllegalArgumentException) {
                throw (IllegalArgumentException) cause;
            }
            throw new RuntimeException(
                "Ошибка при вызове метода '" + method.getName() + "': " + cause.getMessage(),
                cause
            );
        } catch (IllegalAccessException e) {
            throw new RuntimeException(
                "Ошибка доступа при вызове метода '" + method.getName() + "': " + e.getMessage(),
                e
            );
        }
    }
    
    /**
     * Вызывает статический метод.
     * 
     * @param method статический метод для вызова
     * @param args аргументы метода
     * @return результат вызова метода
     */
    public static Object invokeStaticMethod(Method method, Object... args) {
        return invokeMethod(null, method, args);
    }
    
    /**
     * Проверяет, является ли поле private.
     * 
     * @param field поле для проверки
     * @return true если поле private, false иначе
     */
    public static boolean isPrivate(Field field) {
        return Modifier.isPrivate(field.getModifiers());
    }
    
    /**
     * Проверяет, является ли метод public.
     * 
     * @param method метод для проверки
     * @return true если метод public, false иначе
     */
    public static boolean isPublic(Method method) {
        return Modifier.isPublic(method.getModifiers());
    }
    
    /**
     * Проверяет, является ли метод static.
     * 
     * @param method метод для проверки
     * @return true если метод static, false иначе
     */
    public static boolean isStatic(Method method) {
        return Modifier.isStatic(method.getModifiers());
    }
    
    /**
     * Проверяет, является ли поле final.
     * 
     * @param field поле для проверки
     * @return true если поле final, false иначе
     */
    public static boolean isFinal(Field field) {
        return Modifier.isFinal(field.getModifiers());
    }
    
    /**
     * Форматирует сигнатуру метода или конструктора для читаемых сообщений об ошибках.
     * 
     * @param methodName имя метода или класса (для конструктора)
     * @param paramTypes типы параметров
     * @return строка вида "methodName(Type1, Type2)"
     */
    public static String formatSignature(String methodName, Class<?>[] paramTypes) {
        if (paramTypes == null || paramTypes.length == 0) {
            return methodName + "()";
        }
        
        String params = Arrays.stream(paramTypes)
            .map(Class::getSimpleName)
            .collect(Collectors.joining(", "));
        
        return methodName + "(" + params + ")";
    }
}
