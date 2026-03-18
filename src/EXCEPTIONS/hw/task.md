### ДЗ 1: AgeException (⭐⭐)

Создай класс `Person` с методом `setAge()`, который выбрасывает `InvalidAgeException` для некорректного возраста (< 0 или > 150).

### ДЗ 2: Калькулятор с исключениями (⭐⭐)

Калькулятор, который выбрасывает исключения: деление на ноль, некорректная операция.

### ДЗ 3: Банковские операции (⭐⭐⭐)

Класс `BankAccount` с исключениями: `InsufficientFundsException`, `NegativeAmountException`, `AccountBlockedException`.

### ДЗ 4: Парсер конфигурации (⭐⭐⭐)

Метод `parseConfig(String path)` читает файл, выбрасывает `ConfigException` с деталями, если формат неверный.

### ДЗ 5: Retry механизм (⭐⭐⭐⭐)

Метод `retry(Runnable action, int attempts)`, который повторяет действие N раз при ошибке.
