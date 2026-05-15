package Files.hw.task2;

import java.io.*;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;



// TODO переделать под побайтовое считывание
public class CopyFile {
    public static void main(String[] args) throws IOException {
        Path pathSource = Path.of("./resources/text.poem");
        Path pathTarget = Path.of("./resources/text3.txt");
        long size = Files.size(pathSource);
        long copied = 0;
        int bufferSize = 256;
        try (InputStream inputStream = Files.newInputStream(pathSource);
             OutputStream outputStream = Files.newOutputStream(pathTarget)) {
                int b;
                byte[] buffer = new byte[bufferSize];
                while ((b = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, b);
                    copied += b;
                    double percent = (double) copied / size * 100;
                    System.out.println("процент копирования " + percent);
                }
            System.out.println("процесс завершен");
            }
        }


    }
// процент = скопированное / общий размер * 100