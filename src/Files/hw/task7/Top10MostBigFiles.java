package Files.hw.task7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Top10MostBigFiles {
    public static void main(String[] args) {
        try {
             getBigSizesFiles("src");
//            System.out.println(bigSizesFiles);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void getBigSizesFiles(String filename) throws IOException {
        Path path = Path.of(filename);
        try {
             Files.walk(path)
                    .filter(Files::isRegularFile)
                    .sorted(Comparator.comparingLong(p->{
                        try{
                            return -Files.size((Path) p);
                        }catch (IOException e){
                            return 0L;
                        }
                    }))
                    .limit(10)
                     .forEach(p->{
                         try {
                             double size = Files.size(p);
                             System.out.println("Размер: " + size/(1024.0*1024.0));
                             System.out.println("Путь: " + p.toAbsolutePath());
                             System.out.println();
                         }catch (IOException e){
                             System.out.println("Ошибка");;
                         }
                     });


//                    .map(path1 -> {
//                        try {
//                            return new FileInfo(path, Files.size(path));
//                        } catch (IOException e) {
//                            throw new RuntimeException(e);
//                        }
//                    })
//                    .filter(fileInfo -> fileInfo.size > 0)
//                    .sorted(Comparator.comparingLong(FileInfo::getSize).reversed())
//                    .limit(10)
//                    .peek(fileInfo -> fileInfo.path.toAbsolutePath())
//                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    public static class FileInfo {
//        final Path path;
//        final long size;
//
//        public FileInfo(Path path, long size) {
//            this.path = path;
//            this.size = size;
//        }
//
//        long getSize() {
//            return size;
//        }
//    }

}
