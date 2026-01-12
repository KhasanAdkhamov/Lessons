package base.specs;

/**
 * Спецификация ожидаемого метода класса.
 * 
 * @param name имя метода
 * @param returnType тип возвращаемого значения
 * @param parameterTypes типы параметров
 * @param isStatic является ли метод статическим
 */
public record MethodSpec(
    String name,
    Class<?> returnType,
    Class<?>[] parameterTypes,
    boolean isStatic
) {
    /**
     * Создаёт спецификацию геттера.
     * 
     * @param name имя геттера
     * @param returnType тип возвращаемого значения
     * @return спецификация геттера (без параметров, isStatic=false)
     */
    public static MethodSpec getter(String name, Class<?> returnType) {
        return new MethodSpec(name, returnType, new Class<?>[0], false);
    }
    
    /**
     * Создаёт спецификацию сеттера.
     * 
     * @param name имя сеттера
     * @param paramType тип параметра
     * @return спецификация сеттера (returnType=void, один параметр, isStatic=false)
     */
    public static MethodSpec setter(String name, Class<?> paramType) {
        return new MethodSpec(name, void.class, new Class<?>[]{paramType}, false);
    }
    
    /**
     * Создаёт спецификацию статического метода.
     * 
     * @param name имя метода
     * @param returnType тип возвращаемого значения
     * @param paramTypes типы параметров
     * @return спецификация статического метода (isStatic=true)
     */
    public static MethodSpec staticMethod(String name, Class<?> returnType, Class<?>... paramTypes) {
        return new MethodSpec(name, returnType, paramTypes, true);
    }
}
