package lessonTest;

public class Book extends LibraryItem{

    private String author;
    private int page;
    private String isbn;
    private String genre;

    public Book(String id, String title, int publicationYear, String author,
                int page, String isbn, String genre) {
        super(id, title, publicationYear);
        this.author = author;
        this.page = page;
        this.isbn = isbn;
        this.genre = genre;
    }

    @Override
    public void calculateLateFee(int daysLate) {
        int fee = daysLate + 10;
        System.out.println(fee + " рублей штраф за " + daysLate + " дней просрочки");
    }
}
