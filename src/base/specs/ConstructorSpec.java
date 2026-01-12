package base.specs;

/**
 * Спецификация ожидаемого конструктора класса.
 * 
 * @param parameterTypes типы параметров конструктора
 */
public record ConstructorSpec(
    Class<?>[] parameterTypes
) {
    /**
     * Создаёт спецификацию конструктора.
     * 
     * @param paramTypes типы параметров конструктора
     * @return спецификация конструктора
     */
    public static ConstructorSpec of(Class<?>... paramTypes) {
        return new ConstructorSpec(paramTypes);
    }
}
