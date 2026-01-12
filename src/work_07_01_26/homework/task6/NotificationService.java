package work_07_01_26.homework.task6;

import java.util.ArrayList;
import java.util.List;

public class NotificationService {
    List<Notification> notif;

    public NotificationService() {
        notif = new ArrayList<>();
    }

    public void sendNotification(Notification notification) {
        for (Notification notification1 : notif) {
            notification1.send();
        }
    }

    public void sendBulk(Notification notification) {

    }

    public void sendToUser(String user, Notification notification) {

    }
}
