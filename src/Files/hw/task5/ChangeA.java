package Files.hw.task5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Scanner;

public class ChangeA {
    public static void main(String[] args) throws IOException {
        char target = 'а';
        char replacement = 'в';
        try (Scanner scanner = new Scanner(Path.of("resources/text.poem"))) {

            while (scanner.hasNext()) {
                String word = scanner.next();
                if (word.charAt(0) == target) {
                    word = word.replace(target, replacement);
                }
                System.out.println(word);
            }

        }
    }
}
