package work_07_01_26.homework.task6;

import java.util.List;

public class NotificationTest {
    public static void main(String[] args) {
        NotificationService notificationService = new NotificationService();
        Notification emailN = new EmailNotification("Anton", "hi", "Conversation", "Anna");
        Notification pushNotification = new PushNotification("Anton", "hello", "telegram");
        Notification smsNotification = new SMSNotification("Anton", "Good morning", "89771234567");
        notificationService.sendNotification(emailN);
        notificationService.sendNotification(pushNotification);
        notificationService.sendNotification(smsNotification);

    }
}
