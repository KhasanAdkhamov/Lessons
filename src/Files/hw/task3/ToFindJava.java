package Files.hw.task3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class ToFindJava {
    public static void main(String[] args) throws IOException {
        Path path = Path.of("./src", "algo", "arrays");
        try (Stream<Path> list = Files.list(path)) {
            list.filter(p -> p.toString().endsWith(".java"))
                    .forEach(System.out::println);
        }

    }
}
