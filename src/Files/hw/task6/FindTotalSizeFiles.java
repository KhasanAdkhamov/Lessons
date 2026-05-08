package Files.hw.task6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class FindTotalSizeFiles {
    public static void main(String[] args) {
        try {
            long algo = getDirectorySize("src/algo");
            System.out.println(algo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public static long getDirectorySize(String filename) throws IOException {
        Path path = Path.of(filename);

        try (Stream<Path> walk = Files.walk(path, 4)) {
            long sum = walk.filter(Files::isRegularFile)
                    .mapToLong(path1 -> {
                        try {
                            return Files.size(path);
                        } catch (Exception e) {
                            return 0;
                        }
                    })
                    .sum();
            return sum;
        }
    }
}
