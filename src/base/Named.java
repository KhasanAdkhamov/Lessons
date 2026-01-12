package base;


public record Named<T>(String name, T value) {
    public static <T> Named<T> of(final String name, final T f) {
        return new Named<>(name, f);
    }

    @Override
    public String toString() {
        return name;
    }
}
