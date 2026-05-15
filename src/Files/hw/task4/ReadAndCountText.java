package Files.hw.task4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
// TODO убрать лишний try и цикл
public class ReadAndCountText {
    public static void main(String[] args) throws IOException {
//
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader("resources/text.poem", StandardCharsets.UTF_8))) {
                int countChar = 0;
                int countLine = 0;
                String line;
                int wordCount = 0;
                while ((line = bufferedReader.readLine()) != null) {
                    countChar += line.length();
                    String[] split = line.trim().split("[\\p{Punct}\\s]+");
                    wordCount += split.length;
                    countLine++;
                }
                System.out.println(countLine + " строки");
                System.out.println(wordCount + " слов");
                System.out.println(countChar + " символов");
            }
        }
    }