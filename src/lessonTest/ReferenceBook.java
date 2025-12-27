package lessonTest;

public class ReferenceBook extends LibraryItem{
    private String department;
    private boolean isRareEdition;

    public ReferenceBook(String id, String title, int publicationYear, String department,
                         boolean isRareEdition) {
        super(id, title, publicationYear);
        this.department = department;
        this.isRareEdition = isRareEdition;
    }

    @Override
    public void calculateLateFee(int daysLate) {

    }
}
