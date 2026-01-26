package work_22_01_EXCEPTIONS;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;

public class Lesson {

    private static boolean hasError = false;

    public static void main(String[] args) {
        System.out.println(div3(4,4));
    }


    public static int div(int a, int b){
        if (b == 0) {
            return -1;
        }
        return a/b;
    }

    public static int div2(int a, int b){
        if (b == 0) {
            hasError = true;
            return -1;
        }
        return a/b;
    }


    public static int div3(int a, int b){
        try{
            String str = null;
            str.length();
            int[] arr = new int[2];
            arr[20] = 5;
            return a/b;
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("ArrayIndexOutOfBoundsException ошибка");
            throw e;
        }catch (NullPointerException e){
            System.out.println("NullPointerException ошибка");
            throw new RuntimeException("XXX");
        }catch (ArithmeticException e){
            System.out.println("ArithmeticException ошибка");
        }catch (Exception e){
            System.out.println("Какая-то ошибка");
            throw new CustomEx("");
        }finally {
            System.out.println("Это обязательный блок кода");
        }
        return 0;
    }

    public void processFile(){
        try{
            byte[] data = readFile();
            // обработка и выполнение другой логики
        }catch (IOException | SQLException e){
            System.out.println("Обработка ошибки");
        }
    }

    byte[] readFile() throws IOException, SQLException {
        return Files.readAllBytes(Path.of("text.txt"));
    }


    public static int setAge(int age){
        if(age<0){
            throw new IllegalArgumentException("Возраст не может быть отрицательным");
        }
        return age;
    }



}
