```
                       Iterable<E>
                           │
                      Collection<E>
                      /    │    \
                   List   Set   Queue
                    │      │      │
              ┌─────┼──────┼──────┼─────┐
              │     │      │      │     │
         ArrayList │   HashSet  TreeSet │
                   │      │             │
             LinkedList   │        PriorityQueue
                          │
                    LinkedHashSet
                    
                          
                       Map<K,V>   ← Отдельная иерархия
                           │
                  ┌────────┼────────┐
                  │        │        │
              HashMap   TreeMap  LinkedHashMap
```


### ArrayList vs LinkedList: производительность

| Операция | ArrayList | LinkedList |
|----------|-----------|------------|
| `get(index)` | **O(1)** | O(n) |
| `add(element)` в конец | **O(1)*** | O(1) |
| `add(0, element)` в начало | O(n) | **O(1)** |
| `add(index, element)` в середину | O(n) | **O(1)** после поиска |
| `remove(index)` | O(n) | **O(1)** после поиска |
| `contains(element)` | O(n) | O(n) |
| Память | Меньше | Больше (ссылки prev/next) |

*Амортизированно O(1), иногда O(n) при расширении


### Сравнение Set'ов

| | HashSet | TreeSet | LinkedHashSet |
|-|---------|---------|---------------|
| Порядок | Нет | Сортировка | Добавления |
| add/remove | **O(1)** | O(log n) | O(1) |
| contains | **O(1)** | O(log n) | O(1) |
| Допускает null | Да (один) | Нет* | Да (один) |
| Память | Средне | Больше | Больше |

*TreeSet требует сравнения, null нельзя сравнить



### HashMap vs TreeMap vs LinkedHashMap

| | HashMap | TreeMap | LinkedHashMap |
|-|---------|---------|---------------|
| Порядок ключей | Нет | Сортировка | Добавления |
| get/put | **O(1)** | O(log n) | O(1) |
| null-ключи | Да (один) | Нет | Да (один) |
| null-значения | Да | Да | Да |