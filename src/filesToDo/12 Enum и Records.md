# Enum, вложенные классы и Records

## Enum -- перечисления

### Проблема: "магические" константы

```java
public static final int STATUS_NEW = 0;
public static final int STATUS_PROCESSING = 1;
public static final int STATUS_DONE = 2;

int status = STATUS_NEW;
status = 999;  // Компилятор не ругается, но это ошибка!
```

Что тут плохо:
- Нет проверки допустимых значений
- Легко перепутать числа
- Не информативный вывод (`0` вместо `NEW`)

### Решение: enum

```java
public enum OrderStatus {
    NEW, PROCESSING, SHIPPED, DELIVERED, CANCELLED
}

OrderStatus status = OrderStatus.NEW;
// status = 999;  // Ошибка компиляции!

System.out.println(status);  // "NEW" — читаемый вывод
```

### Что такое enum под капотом?

Enum -- это **класс**, где каждая константа -- **объект** этого класса. Java автоматически:
- Делает класс final (нельзя наследовать)
- Создает объекты для каждой константы
- Добавляет полезные методы

### Встроенные методы enum

```java
// Получить все значения
OrderStatus[] allStatuses = OrderStatus.values();

// Преобразовать строку в enum
OrderStatus status = OrderStatus.valueOf("NEW");

// Получить позицию (индекс)
int index = OrderStatus.NEW.ordinal();  // 0

// Имя константы
String name = status.name();  // "NEW"
```

### Enum в switch

```java
OrderStatus status = getStatus();

switch (status) {
    case NEW -> System.out.println("Новый заказ");
    case PROCESSING -> System.out.println("В обработке");
    case SHIPPED -> System.out.println("Отправлен");
    case DELIVERED -> System.out.println("Доставлен");
    case CANCELLED -> System.out.println("Отменён");
}
```

### Enum с полями и методами

Enum может иметь поля, конструкторы и методы -- как обычный класс:

```java
public enum Planet {
    MERCURY(3.303e23, 2.4397e6),
    VENUS(4.869e24, 6.0518e6),
    EARTH(5.976e24, 6.37814e6),
    MARS(6.421e23, 3.3972e6);
    
    private final double mass;   // в килограммах
    private final double radius; // в метрах
    
    // Конструктор — всегда private!
    Planet(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
    }
    
    public double getMass() { return mass; }
    public double getRadius() { return radius; }
    
    // G — гравитационная постоянная
    private static final double G = 6.67300E-11;
    
    public double surfaceGravity() {
        return G * mass / (radius * radius);
    }
    
    public double surfaceWeight(double otherMass) {
        return otherMass * surfaceGravity();
    }
}

double earthWeight = 75;  // кг
double marsWeight = Planet.MARS.surfaceWeight(earthWeight);
System.out.println("Вес на Марсе: " + marsWeight);  // ~28.4 кг
```

### Enum с абстрактными методами

Каждая константа может иметь свою реализацию:

```java
public enum Operation {
    ADD("+") {
        @Override
        public double apply(double a, double b) {
            return a + b;
        }
    },
    SUBTRACT("-") {
        @Override
        public double apply(double a, double b) {
            return a - b;
        }
    },
    MULTIPLY("*") {
        @Override
        public double apply(double a, double b) {
            return a * b;
        }
    },
    DIVIDE("/") {
        @Override
        public double apply(double a, double b) {
            if (b == 0) throw new ArithmeticException("Division by zero");
            return a / b;
        }
    };
    
    private final String symbol;
    
    Operation(String symbol) {
        this.symbol = symbol;
    }
    
    public String getSymbol() { return symbol; }
    
    public abstract double apply(double a, double b);
}

double result = Operation.ADD.apply(10, 5);  // 15
```

Такой подход иногда называют Strategy через enum -- каждая константа определяет свое поведение. Можно ещё добавить метод для поиска операции по символу:

```java
public static Operation fromSymbol(String symbol) {
    for (Operation op : values()) {
        if (op.symbol.equals(symbol)) {
            return op;
        }
    }
    throw new IllegalArgumentException("Unknown operation: " + symbol);
}
```

### EnumSet и EnumMap

Специализированные коллекции для enum -- очень эффективные:

```java
// EnumSet — множество значений enum
Set<OrderStatus> activeStatuses = EnumSet.of(
    OrderStatus.NEW, 
    OrderStatus.PROCESSING
);

// EnumMap — карта с enum-ключами
Map<OrderStatus, String> descriptions = new EnumMap<>(OrderStatus.class);
descriptions.put(OrderStatus.NEW, "Ожидает обработки");
descriptions.put(OrderStatus.SHIPPED, "В пути");
```

### Enum как Singleton

Через enum удобно реализовать потокобезопасный singleton -- Java гарантирует, что каждая константа создается ровно один раз:

```java
public enum DatabaseConnection {
    INSTANCE;
    
    private Connection connection;
    
    DatabaseConnection() {
        System.out.println("Connecting to database...");
    }
    
    public void executeQuery(String sql) {
        System.out.println("Executing: " + sql);
    }
}

// Использование
DatabaseConnection.INSTANCE.executeQuery("SELECT * FROM users");
```

---

## Вложенные классы

### Зачем нужны вложенные классы?

1. **Группировка** -- класс используется только в одном месте
2. **Инкапсуляция** -- скрыть вспомогательный класс
3. **Читаемость** -- связанный код рядом

### Типы вложенных классов

```java
public class Outer {
    // 1. Static nested class
    static class StaticNested { }
    
    // 2. Inner class (нестатический)
    class Inner { }
    
    void method() {
        // 3. Local class
        class Local { }
        
        // 4. Anonymous class
        Runnable r = new Runnable() {
            @Override
            public void run() { }
        };
    }
}
```

### Static nested class

Не имеет доступа к нестатическим членам внешнего класса:

```java
public class Calculator {
    private int value;
    
    public static class Operation {
        public static int add(int a, int b) {
            return a + b;
        }
        
        // this.value — нет доступа!
    }
}

// Использование
int result = Calculator.Operation.add(5, 3);
```

Когда использовать: когда вложенный класс логически связан с внешним, но не зависит от его состояния. Часто для Builder, Comparator.

### Inner class

Имеет доступ ко всем членам внешнего класса, включая private:

```java
public class LinkedList<T> {
    private Node<T> head;
    
    private class Node<T> {
        T data;
        Node<T> next;
        
        Node(T data) {
            this.data = data;
        }
    }
    
    private class ListIterator implements Iterator<T> {
        private Node<T> current = head;  // Доступ к head внешнего класса!
        
        @Override
        public boolean hasNext() {
            return current != null;
        }
        
        @Override
        public T next() {
            T data = current.data;
            current = current.next;
            return data;
        }
    }
}
```

### Anonymous class

Класс без имени, создается "на лету":

```java
// Создаём объект и класс одновременно
Comparator<String> byLength = new Comparator<String>() {
    @Override
    public int compare(String s1, String s2) {
        return Integer.compare(s1.length(), s2.length());
    }
};

// То же самое с лямбдой (Java 8+)
Comparator<String> byLength2 = (s1, s2) -> Integer.compare(s1.length(), s2.length());
```

Когда использовать: для создания одноразовой реализации интерфейса. Часто заменяется лямбдами.

### Сравнение типов вложенных классов

| Тип | Доступ к внешнему | Создание |
|-----|-------------------|----------|
| Static nested | Только static члены | `new Outer.Nested()` |
| Inner | Все члены | `outer.new Inner()` |
| Local | Все + final локальные | Внутри метода |
| Anonymous | Все + final локальные | `new Interface() { }` |

---

## Records -- классы данных (Java 14+)

### Проблема: много boilerplate кода

Для простого класса данных нужно написать:

```java
public class Person {
    private final String name;
    private final int age;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public String getName() { return name; }
    public int getAge() { return age; }
    
    @Override
    public boolean equals(Object o) { /* ... */ }
    
    @Override
    public int hashCode() { /* ... */ }
    
    @Override
    public String toString() { /* ... */ }
}
```

30+ строк для хранения двух полей!

### Решение: record

```java
public record Person(String name, int age) { }
```

Одна строка! Java автоматически создает:
- `private final` поля
- Конструктор
- Геттеры (`name()`, `age()` -- без get!)
- `equals()`, `hashCode()`, `toString()`

### Использование

```java
Person person = new Person("Миша", 25);

// Геттеры — без "get"
String name = person.name();  // "Миша"
int age = person.age();       // 25

// toString
System.out.println(person);   // Person[name=Миша, age=25]

// equals и hashCode работают
Person another = new Person("Миша", 25);
System.out.println(person.equals(another));  // true
```

### Компактный конструктор

Для валидации:

```java
public record Person(String name, int age) {
    // Компактный конструктор — без параметров!
    public Person {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be blank");
        }
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
        // Присваивание полей происходит автоматически после этого блока
    }
}
```

### Дополнительные методы

```java
public record Rectangle(double width, double height) {
    
    public double area() {
        return width * height;
    }
    
    public double perimeter() {
        return 2 * (width + height);
    }
    
    public static Rectangle square(double side) {
        return new Rectangle(side, side);
    }
}

Rectangle rect = new Rectangle(10, 5);
System.out.println(rect.area());  // 50.0

Rectangle square = Rectangle.square(5);
```

### Ограничения records

1. **Неизменяемые** -- нельзя изменить поля после создания
2. **Нельзя наследовать** -- record неявно final
3. **Нельзя добавлять поля** -- только те, что в объявлении
4. **Можно реализовывать интерфейсы**

```java
public record Point(int x, int y) implements Comparable<Point> {
    @Override
    public int compareTo(Point other) {
        int cmpX = Integer.compare(this.x, other.x);
        return cmpX != 0 ? cmpX : Integer.compare(this.y, other.y);
    }
}
```

### Когда использовать records?

**Используй:**
- DTO (Data Transfer Objects)
- Неизменяемые value objects
- Ключи в Map
- Возврат нескольких значений из метода

**Не используй:**
- Когда нужна изменяемость
- Когда нужно наследование
- Для сложной бизнес-логики

### Records для DTO

Records отлично подходят для представления структурированных данных:

```java
// API Response
record UserResponse(int id, String username, String email) { }

// Database Row
record OrderRow(int orderId, String customerName, double total, LocalDate date) { }

// Event
record UserRegisteredEvent(String userId, String email, Instant timestamp) { }

// Config
record DatabaseConfig(String url, String username, String password, int maxConnections) {
    public DatabaseConfig {
        if (maxConnections <= 0) {
            throw new IllegalArgumentException("Max connections must be positive");
        }
    }
}
```

---

## Задачи для практики

### Enum DayOfWeek

Создай enum дней недели с методом `isWeekend()` и методом `isWeekday()`.

---

### Enum Currency

Создай enum `Currency` с полями: код, символ и название валюты. Добавь метод `format(double amount)`, который возвращает красиво отформатированную строку суммы с символом валюты.

---

### Record Money

Создай record `Money` для денежной суммы с валютой. Реализуй компактный конструктор с проверкой, что сумма не отрицательная и валюта не null. Добавь метод `add(Money other)`, который складывает две суммы (с проверкой, что валюты совпадают).

---

### Enum Season

Создай enum времён года с методами `next()` (следующее время года) и `getMonths()` (список месяцев этого сезона).

---

### Enum HttpStatus

Создай enum `HttpStatus` с полями: числовой код и текстовое описание. Добавь методы `isSuccess()` (код 200-299) и `isError()` (код 400+). Включи как минимум OK, CREATED, BAD_REQUEST, UNAUTHORIZED, NOT_FOUND, INTERNAL_ERROR.

---

### Builder через nested class

Реализуй паттерн Builder с помощью static nested class для класса `Email` с полями: to, from, subject, body. Builder должен позволять выстраивать цепочку вызовов и вызвать `build()` в конце.

---

### Record Range

Создай record `Range` с полями `from` и `to`. Добавь методы:
- `contains(int value)` -- проверяет, входит ли значение в диапазон
- `overlaps(Range other)` -- проверяет, пересекаются ли два диапазона
- `length()` -- возвращает длину диапазона

---

### Калькулятор с Operation enum

Напиши консольный калькулятор, который использует enum операций с абстрактным методом `apply(double a, double b)`. Пользователь вводит два числа и операцию, программа выводит результат.

---

### Priority Enum

Создай enum `Priority` с четырьмя уровнями: LOW, MEDIUM, HIGH, CRITICAL. Каждый уровень имеет числовой приоритет и отображаемое имя. Добавь метод `isHigherThan(Priority other)` для сравнения приоритетов и статический метод `fromLevel(int level)` для поиска по числовому значению.

---

### Result Record (sealed interface)

Создай sealed interface `Result<T>` с двумя реализациями-record: `Success<T>` (содержит значение) и `Failure<T>` (содержит текст ошибки). Продемонстрируй использование с pattern matching в switch.

---

### State Machine через enum

Реализуй enum `OrderStatus` (NEW, PROCESSING, SHIPPED, DELIVERED) как конечный автомат. Каждая константа переопределяет абстрактный метод `next()`, возвращающий следующий статус (или бросающий исключение, если переход невозможен). Добавь метод `canTransitionTo(OrderStatus target)`, который проверяет, можно ли перейти из текущего состояния в целевое.
