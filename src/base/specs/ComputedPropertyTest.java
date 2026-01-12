package base.specs;

/**
 * Тестовые данные для проверки вычисляемых свойств.
 * 
 * @param constructorArgs аргументы для создания объекта
 * @param methodName имя метода вычисляемого свойства
 * @param expectedValue ожидаемое значение
 * @param epsilon погрешность для double (0 для точных сравнений)
 */
public record ComputedPropertyTest(
    Object[] constructorArgs,
    String methodName,
    Object expectedValue,
    double epsilon
) {}
