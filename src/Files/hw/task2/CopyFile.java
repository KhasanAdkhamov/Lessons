package Files.hw.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class CopyFile {
    public static void main(String[] args) {
//        try {
//            Files.createFile(Path.of("./resources/", "text4.txt"));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        Path path = Path.of("./resources/", "text3.txt");
        try {
            int amount = 0;
            for (String string: Files.readAllLines(path)) {
                if (!string.isEmpty() || string != null) {
                    Files.readAllLines(path);
                    amount++;
                }
                switch (amount) {
                    case 1 -> System.out.println("25%");
                    case 2 -> System.out.println("50%");
                    case 3 -> System.out.println("75%");
                    case 4 -> System.out.println("100");
                    default -> System.out.println("100%");
                    }
            }
            Files.copy(Path.of("./resources/", "text3.txt"), Path.of("./resources/", "text4.txt"), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
