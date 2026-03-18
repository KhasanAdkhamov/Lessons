### Дерево исключений

```
Throwable (корень)
├── Error (системные ошибки — не ловить!)
│   ├── OutOfMemoryError
│   ├── StackOverflowError
│   └── ...
└── Exception (ошибки программы — можно ловить)
    ├── RuntimeException (unchecked)
    │   ├── NullPointerException
    │   ├── ArrayIndexOutOfBoundsException
    │   ├── IllegalArgumentException
    │   └── ...
    └── Checked Exceptions
        ├── IOException
        ├── SQLException
        └── ...
```


```java
// IOException — checked exception
void readFile() {
    FileReader reader = new FileReader("file.txt");  // Ошибка компиляции!
}

// Вариант 1: поймать
void readFile() {
    try {
        FileReader reader = new FileReader("file.txt");
    } catch (IOException e) {
        e.printStackTrace();
    }
}

// Вариант 2: пробросить
void readFile() throws IOException {
    FileReader reader = new FileReader("file.txt");
}
```

### Проблема: забыли закрыть ресурс

```java
FileReader reader = null;
try {
    reader = new FileReader("file.txt");
    // чтение...
} catch (IOException e) {
    e.printStackTrace();
} finally {
    if (reader != null) {
        try {
            reader.close();  // А тут тоже может быть исключение!
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

```java
import java.util.Scanner;

try(FileReader reader = new FileReader("file.txt");
    Scanner scanner = new Scanner(System.in)){
        // чтение...
}catch(IOException e){
    printStackTrace();
}
```

```
// reader автоматически закроется!


        ### Какие объекты можно использовать?

        Только те, которые реализуют интерфейс `AutoCloseable`:

        ```java
public interface AutoCloseable {
    void close() throws Exception;
}
```

Примеры: `InputStream`, `OutputStream`, `Reader`, `Writer`, `Connection`, `Scanner`...

### Создание своего AutoCloseable

```java
public class DatabaseConnection implements AutoCloseable {
    public DatabaseConnection() {
        System.out.println("Соединение открыто");
    }
    
    @Override
    public void close() {
        System.out.println("Соединение закрыто");
    }
}

try (DatabaseConnection conn = new DatabaseConnection()) {
    // работа с БД...
}
// "Соединение закрыто" — вызовется автоматически
```
