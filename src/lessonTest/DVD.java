package lessonTest;

public class DVD extends LibraryItem{
    private String director;
    private int durationMinutes;
    private String genre;
    private int minimumAge;

    public DVD(String id, String title, int publicationYear, String director,
               int durationMinutes, String genre) {
        super(id, title, publicationYear);
        this.director = director;
        this.durationMinutes = durationMinutes;
        this.genre = genre;
        this.minimumAge = 21;
    }

    @Override
    public void calculateLateFee(int daysLate) {
        int fee = daysLate + 20;
        System.out.println(fee + " рублей штраф за " + daysLate + " дней просрочки");
    }

    public boolean canBorrow(int age) {
        if(minimumAge > age) {
            System.out.println("возрастное ограничение");
            return false;
        }
        return true;
    }
}
