package lessonTest.samples.visitcounter;

public class VisitCounter {
    private int totalVisits;
    private int todayVisits;
    private static final int MAX_DAILY_LIMIT = 1000;

    public VisitCounter() {
        this.totalVisits = 0;
        this.todayVisits = 0;
    }

    public void registerVisit() {
        if (totalVisits > MAX_DAILY_LIMIT || todayVisits > MAX_DAILY_LIMIT ) {
            throw new RuntimeException("too much visit");
        }
        totalVisits++;
        todayVisits++;
    }

    public int resetDailyCounter() {
        return this.todayVisits = 0;
    }

    public boolean hasReachedLimit() {
        if (todayVisits > MAX_DAILY_LIMIT) {
            throw new RuntimeException("max limit on this day");
        }
        return false;
    }

    public int getTotalVisits() {
        return totalVisits;
    }

    public int getTodayVisits() {
        return todayVisits;
    }
}
