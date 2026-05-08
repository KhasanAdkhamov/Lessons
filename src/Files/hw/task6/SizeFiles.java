package Files.hw.task6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SizeFiles {
    public static void main(String[] args) throws IOException {
//        Path scr = Path.of("src");
//        List<Path> javaFiles;
//        try (Stream<Path> path = Files.walk(scr, 3)) {
//            path
//                    .filter(Files::isRegularFile)
//                    .filter(p -> p.toString().endsWith(".java"))
//                    .forEach(p -> {
//                        try {
//                            long size = Files.size(p);
//                            System.out.println(p.getFileName() + "size " +size);
//                        } catch (IOException e) {
//                            throw new RuntimeException(e);
//                        }
//                    });
//        }
        Path scr = Path.of("src");
        Long size = 0L;
        try(Stream<Path> walk = Files.walk(scr, 3)) {
            size = walk.filter(Files::isRegularFile)
                    .filter(p -> p.toString().endsWith("java"))
                    .mapToLong(p -> {
                        try {
                            return Files.size(p);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .sum();
            System.out.println(size);
        }

    }


}
