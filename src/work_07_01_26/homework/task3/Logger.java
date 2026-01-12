package work_07_01_26.homework.task3;

import javax.sound.midi.MidiSystem;

public interface Logger {
    void log(String message);

    default void logError(String message) {
        log("ERROR: " + message);
    }

    default void logInfo(String message) {
        log("INFO " + message);
    }

    default void logWarning(String message) {
        log("WARNING " + message);
    }
}
