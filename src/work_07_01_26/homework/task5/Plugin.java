package work_07_01_26.homework.task5;

public interface Plugin {
    String getName();
    String getVersion();
    void initialize();
    void execute();
    void shutdown();
    default boolean isCompatible(String appVersion) {
        return true;
    }

}
