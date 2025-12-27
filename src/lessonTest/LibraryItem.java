package lessonTest;

public abstract class LibraryItem {
    protected String id;
    protected String title;
    protected int publicationYear;
    protected boolean isAvailable;
    protected String currentBorrower;

    public LibraryItem(String id, String title, int publicationYear) {
        this.id = id;
        this.title = title;
        this.publicationYear = publicationYear;
        this.isAvailable = true;
        this.currentBorrower = null;
    }

    public boolean borrowItem(String borrowerName){
        if(borrowerName == null || borrowerName.isEmpty()) {
            throw new IllegalArgumentException("имя заемщика не можем быть пустым");
        }
        if(!isAvailable) {
            System.out.println("книги нет");
            return false;
        }
        isAvailable = false;
        currentBorrower = borrowerName;
        return true;
    }

    public boolean returnItem() {
        if (isAvailable) {
            System.out.println("книгу никто не брал, вы ошиблись");
            return false;
        }
        isAvailable = true;
        currentBorrower = null;
        return true;
    }

    public void displayInfo() {

    }

    public abstract void calculateLateFee(int daysLate);

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }
}