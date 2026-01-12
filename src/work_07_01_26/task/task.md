    /*
     * ═══════════════════════════════════════════════════════════════════════
     * ЗАДАНИЕ 1: Система уведомлений
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