package Files.hw.task1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class AmountString {
    public static void main(String[] args) throws IOException {
        //Files.createFile(Path.of("./resources/", "text3.txt"));
        Path path = Path.of("resources","text3.txt");
        Files.write(path, List.of("String1", "String2", "String3"));
        int amount = 0;
        for (String string : Files.readAllLines(path)) {
            if (!string.isEmpty() || string != null) {
                Files.readAllLines(path);
                amount++;
            }
        }
        System.out.println(amount);
    }
}
