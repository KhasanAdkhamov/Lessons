package Files.hw.task10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.Comparator;
import java.util.concurrent.Callable;
import java.util.stream.Stream;

public class CopyNewOrModifiedFiles {
    public static void main(String[] args) {
        try {
            copyNewModFiles("src");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void copyNewModFiles(String filename) throws IOException {
        Path path = Path.of(filename);
        Files.walk(path)
                .filter(Files::isRegularFile)
                .sorted(Comparator.comparingLong(p-> {
                    try {
                        return -Files.getLastModifiedTime((Path) p).toMillis();
                    } catch (IOException e) {
                        return 0L;
                    }
                }).thenComparingLong(p-> {
                    try {
                        return -Files.size((Path) p);
                    } catch (IOException e) {
                        return 0L;
                    }
                }))
                .limit(10)
                .forEach(path1 -> {
                    try {
                        long millis = Files.getLastModifiedTime(path).toMillis();
                        long size = Files.size(path);
                        System.out.println(size);
                        System.out.println(millis);
                        System.out.println(path1.getFileName());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
