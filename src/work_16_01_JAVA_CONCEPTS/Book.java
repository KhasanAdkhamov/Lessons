package work_16_01_JAVA_CONCEPTS;

import java.util.Objects;

/**
 * Книга: равенство по ISBN (а не по названию).
 * isbn: not null/blank
 */
public final class Book {
    private final String isbn;
    private final String title;

    public Book(String isbn, String title) {
        requireNonBlank(isbn, "isbn");
        this.isbn = isbn;
        this.title = title;
    }

    public String isbn() {
        return isbn;
    }

    public String title() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        if (this == o) return true;
        Book bookOther = (Book) o;
        return Objects.equals(isbn, bookOther.isbn) && Objects.equals(title, bookOther.title);
    }

    @Override
    public int hashCode() {
        int result = 13;
        result = 7*result + (isbn!=null?isbn.hashCode():0);
        result = 7*result +(title!=null?title.hashCode():0);
        return result;
    }

    @Override
    public String toString() {
        return "Book[isbn=%s, title=%s]".formatted(isbn, title);
    }

    static void requireNonBlank(String value, String fieldName) {
        Objects.requireNonNull(value, fieldName);
        if (value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " must be not blank");
        }
    }
}