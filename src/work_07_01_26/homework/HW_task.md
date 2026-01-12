/*
* ═══════════════════════════════════════════════════════════════════════
* ЗАДАНИЕ 1: Comparable - Сортируемые объекты
* ═══════════════════════════════════════════════════════════════════════
*
* Создай класс Product, который можно сортировать.
*
* Интерфейс Comparable (уже есть в Java):
* - метод compareTo(Object o) - возвращает:
*   • отрицательное число, если this < o
*   • 0, если this == o
*   • положительное число, если this > o
*
* Класс Product implements Comparable<Product>:
* - private String name
* - private double price
* - private int rating (от 1 до 5)
* - конструктор, геттеры
* - @Override compareTo() - сравнение по цене
* - метод displayInfo()
*
* Создай класс ProductCatalog:
* - private ArrayList<Product> products
* - метод addProduct(Product p)
* - метод sortByPrice() - использует compareTo
* - метод sortByRating() - своя логика сортировки
* - метод findCheapest() - самый дешёвый товар
* - метод displayAll()
*
* Протестируй с несколькими товарами.
*/

    // TODO: Реализуй Comparable продукты
    
    /*
     * ═══════════════════════════════════════════════════════════════════════
     * ЗАДАНИЕ 2: Множественная реализация
     * ═══════════════════════════════════════════════════════════════════════
     * 
     * Создай систему с множественной реализацией интерфейсов.
     * 
     * Интерфейс Readable:
     * - void open()
     * - String readContent()
     * - void close()
     * 
     * Интерфейс Writable:
     * - void write(String content)
     * - void save()
     * 
     * Интерфейс Searchable:
     * - boolean contains(String keyword)
     * - int countOccurrences(String keyword)
     * 
     * Класс TextFile implements Readable, Writable, Searchable:
     * - private String filename
     * - private String content
     * - private boolean isOpen
     * - реализовать все методы интерфейсов
     * 
     * Класс ReadOnlyFile implements Readable, Searchable:
     * - только чтение и поиск, без записи
     * 
     * Класс Database implements Writable, Searchable:
     * - может писать и искать, но не читать как файл
     * 
     * Создай метод processFile(Readable file) - работает с любым Readable
     * Создай метод saveData(Writable storage) - работает с любым Writable
     * 
     * Протестируй полиморфизм с разными комбинациями.
     */
    
    // TODO: Реализуй множественную реализацию
    
    /*
     * ═══════════════════════════════════════════════════════════════════════
     * ЗАДАНИЕ 3: Default методы
     * ═══════════════════════════════════════════════════════════════════════
     * 
     * Создай интерфейсы с default методами.
     * 
     * Интерфейс Logger:
     * - void log(String message) - абстрактный
     * - default void logError(String message) {
     *       log("ERROR: " + message);
     *   }
     * - default void logInfo(String message) {
     *       log("INFO: " + message);
     *   }
     * - default void logWarning(String message) {
     *       log("WARNING: " + message);
     *   }
     * 
     * Класс ConsoleLogger implements Logger:
     * - @Override log() - выводит в консоль
     * - может переопределить logError() для красного цвета (опционально)
     * 
     * Класс FileLogger implements Logger:
     * - private String filename
     * - @Override log() - пишет в "файл" (ArrayList<String>)
     * - метод saveToFile() - выводит все логи
     * 
     * Класс DatabaseLogger implements Logger:
     * - private ArrayList<LogEntry> logs
     * - @Override log() - сохраняет в список
     * - метод getLogs() - возвращает все логи
     * 
     * Протестируй все три реализации с разными уровнями логирования.
     */
    
    // TODO: Реализуй систему логирования
    
    /*
     * ═══════════════════════════════════════════════════════════════════════
     * ЗАДАНИЕ 4: Наследование интерфейсов
     * ═══════════════════════════════════════════════════════════════════════
     * 
     * Создай иерархию интерфейсов.
     * 
     * Интерфейс Shape2D:
     * - double getArea()
     * - double getPerimeter()
     * 
     * Интерфейс Colorable:
     * - void setColor(String color)
     * - String getColor()
     * 
     * Интерфейс Drawable extends Shape2D, Colorable:
     * - void draw()
     * - default void display() {
     *       System.out.println("Фигура: площадь = " + getArea() + ", цвет = " + getColor());
     *   }
     * 
     * Класс ColoredCircle implements Drawable:
     * - private double radius
     * - private String color
     * - реализовать все методы
     * 
     * Класс ColoredRectangle implements Drawable:
     * - private double width, height
     * - private String color
     * - реализовать все методы
     * 
     * Создай массив Drawable[] с разными фигурами и вызови display() для всех.
     */
    
    // TODO: Реализуй иерархию интерфейсов
    
    /*
     * ═══════════════════════════════════════════════════════════════════════
     * ЗАДАНИЕ 5: Практическое применение - Плагины
     * ═══════════════════════════════════════════════════════════════════════
     * 
     * Создай систему плагинов для приложения.
     * 
     * Интерфейс Plugin:
     * - String getName()
     * - String getVersion()
     * - void initialize()
     * - void execute()
     * - void shutdown()
     * - default boolean isCompatible(String appVersion) {
     *       return true;  // по умолчанию совместим
     *   }
     * 
     * Класс SpellCheckPlugin implements Plugin:
     * - проверка орфографии
     * - реализовать все методы
     * 
     * Класс AutoSavePlugin implements Plugin:
     * - автосохранение
     * - реализовать все методы
     * 
     * Класс ThemePlugin implements Plugin:
     * - изменение темы
     * - реализовать все методы
     * 
     * Класс PluginManager:
     * - private ArrayList<Plugin> plugins
     * - метод registerPlugin(Plugin plugin)
     * - метод initializeAll() - инициализирует все плагины
     * - метод executeAll() - запускает все плагины
     * - метод shutdownAll() - останавливает все
     * - метод findPlugin(String name) - поиск по имени
     * - метод listPlugins() - список всех плагинов
     * 
     * Протестируй регистрацию и работу нескольких плагинов.
     */
    
    // TODO: Реализуй систему плагинов

/*
* ═══════════════════════════════════════════════════════════════════════
* ЗАДАНИЕ 6: Система уведомлений
* ═══════════════════════════════════════════════════════════════════════
*
* Создай полиморфную систему отправки уведомлений.
*
* Базовый класс Notification:
* - protected String recipient (получатель)
* - protected String message (сообщение)
* - protected String timestamp (время отправки)
* - конструктор
* - метод send() - базовая реализация
* - метод formatMessage() - форматирует сообщение
* - метод displayInfo()
*
* Класс EmailNotification extends Notification:
* - private String subject (тема письма)
* - private String senderEmail
* - @Override send() - отправка email
* - @Override formatMessage() - форматирование с темой
*
* Класс SMSNotification extends Notification:
* - private String phoneNumber
* - @Override send() - отправка SMS (макс 160 символов)
* - метод truncateMessage() - обрезает сообщение если > 160
*
* Класс PushNotification extends Notification:
* - private String appName
* - private boolean withSound
* - @Override send() - отправка push-уведомления
* - метод playSound() - если withSound == true
*
* Создай класс NotificationService:
* - метод sendNotification(Notification notif) - ПОЛИМОРФНЫЙ метод
* - метод sendBulk(Notification[] notifications) - массовая рассылка
* - метод sendToUser(String user, Notification[] notifs) - все уведомления пользователю
*
* Протестируй: создай массив с разными типами уведомлений и отправь через сервис.
*/

    // TODO: Реализуй систему уведомлений
    
    /*
     * ═══════════════════════════════════════════════════════════════════════
     * ЗАДАНИЕ 7: Медиа плеер
     * ═══════════════════════════════════════════════════════════════════════
     * 
     * Создай полиморфный медиа плеер.
     * 
     * Базовый класс MediaFile:
     * - protected String filename
     * - protected long sizeInBytes
     * - protected int duration (секунды)
     * - конструктор
     * - метод play() - базовая реализация
     * - метод pause()
     * - метод stop()
     * - метод getInfo()
     * - метод getSizeInMB() - размер в мегабайтах
     * 
     * Класс AudioFile extends MediaFile:
     * - private String artist
     * - private String album
     * - private int bitrate
     * - @Override play() - "Воспроизведение аудио..."
     * - метод adjustVolume(int level)
     * 
     * Класс VideoFile extends MediaFile:
     * - private int width, height
     * - private int fps
     * - private String codec
     * - @Override play() - "Воспроизведение видео..."
     * - метод getResolution() - возвращает "1920x1080"
     * - метод toggleSubtitles()
     * 
     * Класс ImageFile extends MediaFile:
     * - private int width, height
     * - private String format (JPG, PNG, etc.)
     * - @Override play() - "Показ изображения..."
     * - метод rotate(int degrees)
     * - метод crop(int x, int y, int width, int height)
     * 
     * Создай класс MediaPlayer:
     * - private MediaFile currentMedia
     * - private ArrayList<MediaFile> playlist
     * - метод loadMedia(MediaFile media) - ПОЛИМОРФНЫЙ
     * - метод playNext() - следующий в плейлисте
     * - метод playAll() - воспроизвести весь плейлист
     * - метод getPlaylistDuration() - общая длительность
     * - метод getMediaByType(String type) - фильтрация по типу
     * 
     * Протестируй с плейлистом из разных типов файлов.
     */
    
    // TODO: Реализуй медиа плеер
    
    /*
     * ═══════════════════════════════════════════════════════════════════════
     * ЗАДАНИЕ 8: Система скидок
     * ═══════════════════════════════════════════════════════════════════════
     * 
     * Создай полиморфную систему расчёта скидок.
     * 
     * Базовый класс Discount:
     * - protected String code (промокод)
     * - protected String description
     * - protected boolean isActive
     * - конструктор
     * - abstract метод apply(double price) - возвращает цену со скидкой
     * - метод activate() / deactivate()
     * - метод getDiscountAmount(double price) - размер скидки в рублях
     * 
     * Класс PercentDiscount extends Discount:
     * - private int percent (процент скидки)
     * - @Override apply() - price * (1 - percent/100)
     * 
     * Класс FixedDiscount extends Discount:
     * - private double amount (фиксированная сумма)
     * - @Override apply() - Math.max(0, price - amount)
     * 
     * Класс BuyOneGetOneFree extends Discount:
     * - @Override apply() - цена за 2 товара = цена за 1
     * 
     * Класс TieredDiscount extends Discount:
     * - private double[] thresholds (пороги: 1000, 5000, 10000)
     * - private int[] discounts (скидки: 5%, 10%, 15%)
     * - @Override apply() - скидка зависит от суммы покупки
     * 
     * Класс ShoppingCart:
     * - private ArrayList<Double> items
     * - метод addItem(double price)
     * - метод getSubtotal() - сумма без скидок
     * - метод applyDiscount(Discount discount) - ПОЛИМОРФНЫЙ
     * - метод applyBestDiscount(Discount[] discounts) - применить лучшую скидку
     * - метод calculateTotal() - итог с учётом скидки
     * 
     * Протестируй разные комбинации товаров и скидок.
     */
    
    // TODO: Реализуй систему скидок
    
    /*
     * ═══════════════════════════════════════════════════════════════════════
     * ЗАДАНИЕ 9: Калькулятор фигур (с instanceof)
     * ═══════════════════════════════════════════════════════════════════════
     * 
     * Создай систему с использованием instanceof для специальной обработки.
     * 
     * Используй классы из TaskInheritance (Shape, Circle, Rectangle, Triangle)
     * или создай свои.
     * 
     * Создай класс ShapeCalculator:
     * - метод describeShape(Shape shape) - используй instanceof для определения типа
     *   и вывода специфичной информации:
     *   • Для Circle: "Круг радиусом X"
     *   • Для Rectangle: "Прямоугольник WxH"
     *   • Для Triangle: "Треугольник со сторонами A, B, C"
     * 
     * - метод compareShapes(Shape s1, Shape s2) - сравнивает площади,
     *   использует instanceof для детального вывода
     * 
     * - метод calculateTotalArea(Shape[] shapes) - общая площадь всех фигур
     * 
     * - метод findLargestShape(Shape[] shapes) - находит фигуру с макс площадью
     * 
     * - метод groupByType(Shape[] shapes) - группирует фигуры по типам
     *   (возвращает Map<String, ArrayList<Shape>> или просто выводит статистику)
     * 
     * Протестируй с массивом разных фигур.
     */
    
    // TODO: Реализуй калькулятор фигур
    
    /*
     * ═══════════════════════════════════════════════════════════════════════
     * ЗАДАНИЕ 10: Фабрика объектов (Factory Pattern)
     * ═══════════════════════════════════════════════════════════════════════
     * 
     * Создай систему с паттерном "Фабрика".
     * 
     * Базовый класс Transport:
     * - protected String type
     * - protected double speed
     * - protected int capacity (вместимость пассажиров)
     * - метод travel(double distance) - возвращает время в пути
     * - метод displayInfo()
     * 
     * Классы-наследники:
     * - Car extends Transport (скорость 80 км/ч)
     * - Train extends Transport (скорость 120 км/ч)
     * - Plane extends Transport (скорость 800 км/ч)
     * - Bicycle extends Transport (скорость 15 км/ч)
     * 
     * Класс TransportFactory:
     * - static метод createTransport(String type) - возвращает Transport
     *   в зависимости от type ("car", "train", "plane", "bicycle")
     * - static метод createOptimalTransport(double distance, int passengers)
     *   - выбирает оптимальный транспорт по параметрам
     * 
     * Класс TravelPlanner:
     * - метод planTrip(double distance, int passengers)
     *   • Использует фабрику для создания транспорта
     *   • Сравнивает разные варианты
     *   • Выводит рекомендацию
     * 
     * Протестируй планирование разных поездок.
     */
    
    // TODO: Реализуй систему с фабрикой