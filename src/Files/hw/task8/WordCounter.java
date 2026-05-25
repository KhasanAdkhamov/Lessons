package Files.hw.task8;

import java.io.IOException;
import java.nio.file.Path;
import java.security.Key;
import java.util.*;

public class WordCounter {
    public static void main(String[] args) {
        try {
            getMap("resources/text.poem");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getMap(String filename) throws IOException {
        Scanner scanner = new Scanner(Path.of(filename));
        Map<String, Integer> map = new HashMap<>();
        String currentWord;
        int count = 0;
        while (scanner.hasNext()) {
            currentWord = scanner.next();
            if (map.containsKey(currentWord)) {
                Integer oldCount = map.get(currentWord);
                map.put(currentWord, oldCount + 1);
            } else {
                map.put(currentWord, count + 1);
            }
        }
        System.out.println(map);
    }
}
