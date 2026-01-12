package base.specs;

import java.util.Map;

/**
 * Тестовые данные для проверки конструктора и начальных значений геттеров.
 * 
 * @param constructorArgs аргументы для конструктора
 * @param expectedGetterValues ожидаемые значения геттеров (ключ — имя геттера, значение — ожидаемый результат)
 */
public record TestData(
    Object[] constructorArgs,
    Map<String, Object> expectedGetterValues
) {}
