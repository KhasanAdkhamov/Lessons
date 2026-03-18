package work_07_01_26.homework.task3;

public class FileLogger implements Logger{
    private String filename;

    public FileLogger(String filename) {
        this.filename = filename;
    }

    public void saveToFile() {
        System.out.println("вывод всех логов");
        logError("error");
        logInfo("info");
        logWarning("warning");
    }

    @Override
    public void log(String message) {
        System.out.println("записано в файл " + filename + " сообщение " + message);
    }
}
