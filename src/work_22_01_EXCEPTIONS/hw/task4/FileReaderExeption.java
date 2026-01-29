package work_22_01_EXCEPTIONS.hw.task4;

import java.io.*;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReaderExeption {
    public static void main(String[] args) {
        File file = Path.of("resources", "text2.txt").toFile();
        try {
            parseConfig(file);
        } catch (ConfigException e) {
            System.out.println(e.getMessage());;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void parseConfig(File path) throws ConfigException, IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            if (!path.getName().contains("poem")) {
                throw new ConfigException("неверный формат, используй .poem");
            }
            String collect = reader.lines()
                    .collect(Collectors.joining("\n"));
            System.out.println(collect);
        }
    }
}