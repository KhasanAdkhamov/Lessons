package lessonTest;

public class Magazine extends LibraryItem{
    private int issueNumber;
    private String month;
    private String publisher;

    public Magazine(String id, String title, int publicationYear, int issueNumber,
                    String month, String publisher) {
        super(id, title, publicationYear);
        this.issueNumber = issueNumber;
        this.month = month;
        this.publisher = publisher;
    }

    @Override
    public void calculateLateFee(int daysLate) {
        int fee = daysLate + 5;
        System.out.println(fee + " рублей штраф за " + daysLate + " дней просрочки");
    }
}
