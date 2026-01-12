package base.specs;

import java.util.List;

/**
 * Правило валидации для поля.
 * 
 * @param fieldName имя поля
 * @param setterName имя сеттера для проверки
 * @param getterName имя геттера для проверки неизменности
 * @param validValues список валидных значений
 * @param invalidValues список невалидных значений
 * @param description описание правила валидации
 */
public record ValidationRule(
    String fieldName,
    String setterName,
    String getterName,
    List<Object> validValues,
    List<Object> invalidValues,
    String description
) {
    /**
     * Создаёт правило валидации.
     * 
     * @param fieldName имя поля
     * @param setterName имя сеттера для проверки
     * @param getterName имя геттера для проверки неизменности
     * @param validValues список валидных значений
     * @param invalidValues список невалидных значений
     * @param description описание правила валидации
     * @return правило валидации
     */
    public static ValidationRule of(
        String fieldName,
        String setterName,
        String getterName,
        List<Object> validValues,
        List<Object> invalidValues,
        String description
    ) {
        return new ValidationRule(fieldName, setterName, getterName, validValues, invalidValues, description);
    }
}
