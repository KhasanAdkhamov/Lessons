### Стандартные функциональные интерфейсы

Java предоставляет набор готовых в пакете `java.util.function`:

| Интерфейс | Метод | Описание |
|-----------|-------|----------|
| `Predicate<T>` | `test(T) → boolean` | Проверка условия |
| `Function<T, R>` | `apply(T) → R` | Преобразование |
| `Consumer<T>` | `accept(T)` | Действие без результата |
| `Supplier<T>` | `get() → T` | Поставщик значения |
| `BiFunction<T, U, R>` | `apply(T, U) → R` | Два аргумента |
| `UnaryOperator<T>` | `apply(T) → T` | То же что Function<T, T> |
| `BinaryOperator<T>` | `apply(T, T) → T` | То же что BiFunction<T, T, T> |


### Промежуточные операции

### filter() — фильтрация

```java
List<Integer> evens = numbers.stream()
    .filter(n -> n % 2 == 0)
    .toList();
```

### map() — преобразование

```java
List<String> upperNames = names.stream()
    .map(String::toUpperCase)
    .toList();

List<Integer> lengths = names.stream()
    .map(String::length)
    .toList();
```

### flatMap() — развёртывание

Когда каждый элемент превращается в stream:

```java
List<List<Integer>> nested = List.of(
    List.of(1, 2),
    List.of(3, 4),
    List.of(5, 6)
);

List<Integer> flat = nested.stream()
    .flatMap(List::stream)
    .toList();  // [1, 2, 3, 4, 5, 6]
```

### distinct() — уникальные элементы

```java
List<Integer> unique = List.of(1, 2, 2, 3, 3, 3).stream()
    .distinct()
    .toList();  // [1, 2, 3]
```

### sorted() — сортировка

```java
// Естественный порядок
List<String> sorted = names.stream()
    .sorted()
    .toList();

// С компаратором
List<String> byLength = names.stream()
    .sorted(Comparator.comparingInt(String::length))
    .toList();
```

### limit() и skip()

```java
List<Integer> first5 = numbers.stream()
    .limit(5)
    .toList();

List<Integer> afterFirst5 = numbers.stream()
    .skip(5)
    .toList();
```

### peek() — для отладки

```java
List<String> result = names.stream()
    .peek(s -> System.out.println("До фильтра: " + s))
    .filter(s -> s.length() > 3)
    .peek(s -> System.out.println("После фильтра: " + s))
    .toList();
```

### Терминальные операции

### forEach() — для каждого элемента

```java
names.stream().forEach(System.out::println);
// Или просто:
names.forEach(System.out::println);
```

### collect() — сбор в коллекцию

```java
// В List
List<String> list = stream.collect(Collectors.toList());
List<String> list2 = stream.toList();  // Java 16+

// В Set
Set<String> set = stream.collect(Collectors.toSet());

// В Map
Map<String, Integer> map = names.stream()
    .collect(Collectors.toMap(
        name -> name,           // ключ
        name -> name.length()   // значение
    ));

// Объединение в строку
String joined = names.stream()
    .collect(Collectors.joining(", "));  // "Аня, Боря, Вика"
```

### count(), min(), max()

```java
long count = names.stream().count();

Optional<String> min = names.stream().min(Comparator.naturalOrder());

Optional<Integer> max = numbers.stream().max(Integer::compareTo);
```

### findFirst(), findAny()

```java
Optional<String> first = names.stream()
    .filter(s -> s.startsWith("А"))
    .findFirst();

Optional<String> any = names.parallelStream()
    .filter(s -> s.length() > 3)
    .findAny();  // Любой подходящий (для параллельных)
```

### anyMatch(), allMatch(), noneMatch()

```java
boolean hasShort = names.stream().anyMatch(s -> s.length() < 3);
boolean allLong = names.stream().allMatch(s -> s.length() > 2);
boolean noEmpty = names.stream().noneMatch(String::isEmpty);
```

### reduce() — свёртка

```java
// Сумма
int sum = numbers.stream().reduce(0, Integer::sum);

// Произведение
int product = numbers.stream().reduce(1, (a, b) -> a * b);

// Конкатенация
String concat = words.stream().reduce("", (a, b) -> a + b);
```

```Java

import java.util.Optional;

public static void main(String[] args) {
    String name = person.getAddress().getCity().getName();

    Optional<String> opt1 = Optional.of("Hello");
    Optional<String> opt2 = Optional.empty();
    Optional<String> opt3 = Optional.ofNullable(null);

    // 1
    if(opt1.isPresent()) {
        String val = opt1.get();
    }
    
    //2
    String val = opt1.orElse("default");
    
    //3
    String val = opt1.orElseThrow(() -> new RuntimeException("Нет значения"));
    
    opt1.map(String::trim);
    
    // String name = person.getAddress().getCity().getName();
    String name = Optional.ofNullable(person)
            .map(Person::getAddress)
            .map(Address::getCity)
            .map(City::getName)
            .orElse(null);           
            
    
    
}

    
        



```