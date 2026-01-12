package base.specs;

import java.util.Map;

/**
 * Тестовые данные для проверки бизнес-методов.
 * 
 * @param constructorArgs аргументы для создания объекта
 * @param methodName имя бизнес-метода
 * @param methodArgs аргументы вызова метода
 * @param expectedResult ожидаемый результат (null для void)
 * @param shouldThrow ожидается ли исключение
 * @param expectedExceptionType тип ожидаемого исключения
 * @param expectedStateAfter ожидаемое состояние после вызова (ключ — имя геттера)
 * @param isStatic является ли метод статическим
 */
public record BusinessMethodTest(
    Object[] constructorArgs,
    String methodName,
    Object[] methodArgs,
    Object expectedResult,
    boolean shouldThrow,
    Class<? extends Exception> expectedExceptionType,
    Map<String, Object> expectedStateAfter,
    boolean isStatic
) {
    /**
     * Создаёт тест для void-метода без исключений.
     * 
     * @param constructorArgs аргументы для создания объекта
     * @param methodName имя метода
     * @param methodArgs аргументы вызова метода
     * @param expectedStateAfter ожидаемое состояние после вызова
     * @return тест бизнес-метода
     */
    public static BusinessMethodTest voidMethod(
        Object[] constructorArgs,
        String methodName,
        Object[] methodArgs,
        Map<String, Object> expectedStateAfter
    ) {
        return new BusinessMethodTest(
            constructorArgs,
            methodName,
            methodArgs,
            null,
            false,
            null,
            expectedStateAfter,
            false
        );
    }
    
    /**
     * Создаёт тест для метода с возвращаемым значением.
     * 
     * @param constructorArgs аргументы для создания объекта
     * @param methodName имя метода
     * @param methodArgs аргументы вызова метода
     * @param expectedResult ожидаемый результат
     * @return тест бизнес-метода
     */
    public static BusinessMethodTest returningMethod(
        Object[] constructorArgs,
        String methodName,
        Object[] methodArgs,
        Object expectedResult
    ) {
        return new BusinessMethodTest(
            constructorArgs,
            methodName,
            methodArgs,
            expectedResult,
            false,
            null,
            null,
            false
        );
    }
    
    /**
     * Создаёт тест для метода, который должен бросить исключение.
     * 
     * @param constructorArgs аргументы для создания объекта
     * @param methodName имя метода
     * @param methodArgs аргументы вызова метода
     * @param exceptionType тип ожидаемого исключения
     * @return тест бизнес-метода
     */
    public static BusinessMethodTest throwingMethod(
        Object[] constructorArgs,
        String methodName,
        Object[] methodArgs,
        Class<? extends Exception> exceptionType
    ) {
        return new BusinessMethodTest(
            constructorArgs,
            methodName,
            methodArgs,
            null,
            true,
            exceptionType,
            null,
            false
        );
    }
    
    /**
     * Создаёт тест для статического метода.
     * 
     * @param methodName имя метода
     * @param methodArgs аргументы вызова метода
     * @param expectedResult ожидаемый результат
     * @return тест бизнес-метода
     */
    public static BusinessMethodTest staticMethod(
        String methodName,
        Object[] methodArgs,
        Object expectedResult
    ) {
        return new BusinessMethodTest(
            null,
            methodName,
            methodArgs,
            expectedResult,
            false,
            null,
            null,
            true
        );
    }
    
    /**
     * Создаёт тест для метода, который не должен бросать исключения (проверка displayInfo).
     * 
     * @param constructorArgs аргументы для создания объекта
     * @param methodName имя метода
     * @param methodArgs аргументы вызова метода
     * @return тест бизнес-метода
     */
    public static BusinessMethodTest noThrowMethod(
        Object[] constructorArgs,
        String methodName,
        Object[] methodArgs
    ) {
        return new BusinessMethodTest(
            constructorArgs,
            methodName,
            methodArgs,
            null,
            false,
            null,
            null,
            false
        );
    }
}
