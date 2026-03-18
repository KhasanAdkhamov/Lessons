# Generics (Обобщения)

## Зачем нужны дженерики

### Проблема: отсутствие типизации

До Java 5 коллекции работали с `Object`:

```java
List list = new ArrayList();
list.add("Hello");
list.add(123);  // Можно положить что угодно!

String s = (String) list.get(0);  // Нужно приводить тип
String s2 = (String) list.get(1); // ClassCastException в runtime!
```

Проблемы:
1. Нет проверки типов при компиляции
2. Нужно вручную приводить типы
3. Ошибки обнаруживаются только при выполнении

### Решение: дженерики

```java
List<String> list = new ArrayList<>();
list.add("Hello");
// list.add(123);  // Ошибка компиляции!

String s = list.get(0);  // Приведение не нужно
```

Преимущества:
1. **Типобезопасность** — ошибки на этапе компиляции
2. **Читаемость** — видно, какие типы используются
3. **Нет приведения типов** — код чище

### Аналогия

Дженерики — это как контейнер с наклейкой. Коробка с надписью "Книги" не даст положить туда посуду (ошибка компиляции), и ты точно знаешь, что достанешь книгу.

---

## Создание generic классов

### Простейший пример

```java
public class Box<T> {
    private T content;
    
    public void put(T item) {
        this.content = item;
    }
    
    public T get() {
        return content;
    }
}
```

`T` — это **параметр типа** (type parameter). При использовании указываем конкретный тип:

```java
Box<String> stringBox = new Box<>();
stringBox.put("Hello");
String s = stringBox.get();

Box<Integer> intBox = new Box<>();
intBox.put(42);
Integer i = intBox.get();
```

### Соглашения об именовании

| Буква | Значение |
|-------|----------|
| `T` | Type (тип) |
| `E` | Element (элемент коллекции) |
| `K` | Key (ключ) |
| `V` | Value (значение) |
| `N` | Number (число) |

### Несколько параметров типа

```java
public class Pair<K, V> {
    private K key;
    private V value;
    
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }
    
    public K getKey() { return key; }
    public V getValue() { return value; }
    
    @Override
    public String toString() {
        return key + " = " + value;
    }
}

Pair<String, Integer> pair = new Pair<>("возраст", 25);
String key = pair.getKey();      // "возраст"
Integer value = pair.getValue(); // 25
```

### Diamond operator <>

С Java 7 можно не повторять типы справа:

```java
// До Java 7
Box<String> box = new Box<String>();

// Java 7+
Box<String> box = new Box<>();  // Тип выводится из левой части
```

---

## Generic методы

### Метод может иметь свои параметры типа

```java
public class Util {
    // <T> перед возвращаемым типом — объявление параметра
    public static <T> T getFirst(List<T> list) {
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}

List<String> names = List.of("Аня", "Боря");
String first = Util.getFirst(names);  // Java выводит тип автоматически
```

### Несколько параметров типа в методе

```java
public static <K, V> Map<K, V> createMap(K key, V value) {
    Map<K, V> map = new HashMap<>();
    map.put(key, value);
    return map;
}

Map<String, Integer> map = createMap("age", 25);
```

### Когда использовать generic методы?

- Когда тип не связан с классом
- Для утилитных методов
- Когда нужен тип только в одном методе

---

## Ограничения типов (Bounded Types)

### Проблема: нужно ограничить допустимые типы

```java
public class Calculator<T> {
    public T add(T a, T b) {
        return a + b;  // Ошибка! T может быть чем угодно
    }
}
```

### Upper bound — ограничение сверху

```java
public class Calculator<T extends Number> {
    public double add(T a, T b) {
        return a.doubleValue() + b.doubleValue();  // Number имеет этот метод
    }
}

Calculator<Integer> intCalc = new Calculator<>();
Calculator<Double> doubleCalc = new Calculator<>();
// Calculator<String> strCalc;  // Ошибка! String не наследует Number
```

`extends` здесь означает "расширяет или реализует" — работает и для классов, и для интерфейсов.

### Множественные ограничения

```java
// T должен наследовать Number И реализовать Comparable
public class SortedBox<T extends Number & Comparable<T>> {
    // ...
}
```

**Правило:** сначала класс, потом интерфейсы (класс может быть только один).

### Пример: поиск максимума

```java
public static <T extends Comparable<T>> T max(T a, T b) {
    return a.compareTo(b) > 0 ? a : b;
}

String maxStr = max("apple", "banana");  // "banana"
Integer maxInt = max(10, 20);            // 20
```

---

## Wildcards — подстановочные символы

### Проблема: List\<Dog\> не является List\<Animal\>

```java
class Animal {}
class Dog extends Animal {}

List<Dog> dogs = new ArrayList<>();
// List<Animal> animals = dogs;  // Ошибка компиляции!
```

Почему? Потому что если бы это работало:

```java
List<Animal> animals = dogs;
animals.add(new Cat());  // Добавили кота в список собак!
Dog dog = dogs.get(0);   // ClassCastException!
```

### Решение: wildcards

#### `?` — неизвестный тип

```java
public void printAll(List<?> list) {
    for (Object item : list) {
        System.out.println(item);
    }
}

printAll(List.of("a", "b"));
printAll(List.of(1, 2, 3));
printAll(List.of(new Dog(), new Cat()));
```

`List<?>` — список **чего-то**, но мы не знаем чего. Можем только читать как Object.

#### `? extends T` — верхняя граница

```java
public void processAnimals(List<? extends Animal> animals) {
    for (Animal a : animals) {
        a.eat();  // Можем вызывать методы Animal
    }
    // animals.add(new Dog());  // Нельзя добавлять!
}

List<Dog> dogs = List.of(new Dog());
processAnimals(dogs);  // Работает!
```

`List<? extends Animal>` — список **какого-то подтипа** Animal. Можем читать как Animal, но не можем добавлять.

#### `? super T` — нижняя граница

```java
public void addDogs(List<? super Dog> list) {
    list.add(new Dog());      // Можем добавлять Dog
    list.add(new Puppy());    // И подтипы Dog
    // list.add(new Animal()); // Нельзя — Animal не Dog
    
    Object obj = list.get(0); // Можем читать только как Object
}

List<Animal> animals = new ArrayList<>();
addDogs(animals);  // Работает!
```

`List<? super Dog>` — список **какого-то супертипа** Dog. Можем добавлять Dog, но читаем только как Object.

---

## PECS — Producer Extends, Consumer Super

### Мнемоническое правило

- **Producer Extends** — если коллекция **производит** элементы (ты читаешь из неё), используй `? extends`
- **Consumer Super** — если коллекция **потребляет** элементы (ты пишешь в неё), используй `? super`

### Пример: копирование списка

```java
public static <T> void copy(List<? extends T> src, List<? super T> dest) {
    for (T item : src) {      // src — producer (читаем)
        dest.add(item);       // dest — consumer (пишем)
    }
}

List<Integer> ints = List.of(1, 2, 3);
List<Number> nums = new ArrayList<>();
copy(ints, nums);  // Копируем Integer в List<Number>
```

### Шпаргалка

| Действие | Wildcard | Пример |
|----------|----------|--------|
| Только читать | `? extends T` | `List<? extends Number>` |
| Только писать | `? super T` | `List<? super Integer>` |
| Читать и писать | Без wildcard | `List<Integer>` |

---

## Type Erasure — стирание типов

### Как работают дженерики под капотом

Java использует **стирание типов**: информация о дженериках существует только при компиляции. В runtime все превращается в Object (или в границу).

```java
// Твой код
List<String> list = new ArrayList<>();
list.add("Hello");
String s = list.get(0);

// После компиляции (примерно)
List list = new ArrayList();
list.add("Hello");
String s = (String) list.get(0);  // Компилятор добавляет приведение
```

### Последствия стирания типов

**Нельзя создать массив generic типа:**
```java
// T[] arr = new T[10];  // Ошибка!
```

**Нельзя использовать instanceof с параметром типа:**
```java
// if (obj instanceof T) { }  // Ошибка!
if (obj instanceof List<?>) { }  // Так можно
```

**Нельзя иметь перегруженные методы, отличающиеся только параметром типа:**
```java
// void process(List<String> list) { }
// void process(List<Integer> list) { }  // После стирания — одинаковые!
```

### Почему так?

Обратная совместимость. Дженерики добавили в Java 5, но старый код без дженериков должен был продолжать работать.

---

## Задачи для практики

### Generic Stack

Реализуй generic стек с методами `push`, `pop`, `peek`, `isEmpty`, `size`.

---

### Метод swap

Напиши generic метод, который меняет местами два элемента в списке по их индексам.

---

### Фильтрация списка

Напиши generic метод, который принимает список и предикат (`Predicate<T>`), а возвращает новый список, содержащий только элементы, удовлетворяющие предикату.

---

### Generic Pair

Создай класс `Pair<F, S>` с методами `getFirst()`, `getSecond()`, `toString()`.

---

### Generic минимум/максимум

Напиши методы `min()` и `max()`, которые принимают список элементов, реализующих `Comparable`, и возвращают минимальный/максимальный элемент. Также сделай перегруженный вариант, который принимает `Comparator<T>` вторым аргументом.

---

### Generic Cache

Реализуй класс `Cache<K, V>` с методами `put`, `get`, `containsKey`, `size`, `clear`.

---

### Generic преобразование

Напиши метод `transform(List<T> list, Function<T, R> mapper)`, который возвращает `List<R>` — результат применения функции `mapper` к каждому элементу списка.

---

### Generic Repository

Создай интерфейс `Repository<T, ID>` с CRUD-операциями (`findById`, `findAll`, `save`, `delete`) и напиши его реализацию, хранящую данные в памяти (например, в `HashMap`).

---

### Generic Result

Создай класс `Result<T, E extends Exception>` — обёртку, которая хранит либо успешный результат, либо ошибку. Реализуй:
- фабричные методы `success(T value)` и `failure(E error)`
- метод `isSuccess()`
- метод `getValue()`, который возвращает значение или бросает хранимое исключение
- метод `getError()`

---

### Generic Tree

Реализуй класс `TreeNode<T>` — узел дерева с произвольным числом дочерних элементов. Класс должен поддерживать:
- хранение значения типа `T`
- добавление дочерних узлов
- обход дерева в глубину (метод `traverse()`, который печатает значения)

---

### Суммирование с wildcards

Напиши метод `sumNumbers(List<? extends Number> list)`, который суммирует все числа из списка и возвращает `double`. Метод должен работать с `List<Integer>`, `List<Double>`, `List<Long>` и другими наследниками `Number`.
