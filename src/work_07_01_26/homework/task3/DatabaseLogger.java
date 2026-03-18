package work_07_01_26.homework.task3;

import java.util.ArrayList;
import java.util.List;

public class DatabaseLogger implements Logger{
    private List<Logger> logs;

    public DatabaseLogger() {
        logs = new ArrayList<>();
    }

    @Override
    public void log(String message) {

    }
}
