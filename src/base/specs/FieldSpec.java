package base.specs;

/**
 * Спецификация ожидаемого поля класса.
 * 
 * @param name имя поля
 * @param type тип поля
 */
public record FieldSpec(
    String name,
    Class<?> type
) {}
