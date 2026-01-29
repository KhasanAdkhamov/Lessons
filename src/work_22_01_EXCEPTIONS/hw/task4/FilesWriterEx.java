package work_22_01_EXCEPTIONS.hw.task4;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class FilesWriterEx {
    public static void main(String[] args) throws IOException {
        File file = Path.of("resources", "text.poem").toFile();
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file, true))) {
            fileWriter.append("hello");
            fileWriter.newLine();
            fileWriter.append("java");
        }

        File file2 = Path.of("resources", "text2.txt").toFile();
        try (BufferedWriter fileWriter2 = new BufferedWriter(new FileWriter(file, true))) {
            fileWriter2.append("good morning");
            fileWriter2.newLine();
            fileWriter2.append("world");
        }

    }
}
