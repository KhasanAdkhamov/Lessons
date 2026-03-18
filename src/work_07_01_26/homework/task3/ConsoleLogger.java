package work_07_01_26.homework.task3;

public class ConsoleLogger implements Logger{

    @Override
    public void log(String message) {
        System.out.println(message);
    }

    @Override
    public void logError(String message) {
        log("ERROR: " + message);
    }
}
