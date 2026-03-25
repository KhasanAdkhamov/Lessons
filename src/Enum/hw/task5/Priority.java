package Enum.hw.task5;

public enum Priority {
    LOW(1, "low"),
    MEDIUM(3, "medium"),
    HIGH(5, "high"),
    CRITICAL(10, "critical");

    private final int num;
    private final String name;

    Priority(int num, String name) {
        this.num = num;
        this.name = name;
    }

    public boolean isHigherThan(Priority other) {
        return this.num > other.num;
    }

    public static Priority fromLevel(int level) {
        for (Priority o : Priority.values()) {
            if (o.num == level) {
                return o;
            }
        }
        throw new IllegalArgumentException("error " + level);
    }
}
