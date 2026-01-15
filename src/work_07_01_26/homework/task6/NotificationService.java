package work_07_01_26.homework.task6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NotificationService {
    List<Notification> notifaciton;

    public NotificationService() {
        this.notifaciton = new ArrayList<>();
    }

    public void sendNotification(Notification n) {
        notifaciton.add(n);
        n.send();
    }

    public void sendBulk(List<Notification> nAll) {
        for (Notification notification : nAll) {
            notification.send();
            System.out.println("массовая рассылка");
        }
    }

    public void sendToUser(String user, Notification[] notifications) {
        for (Notification notification : notifications) {
            notification.send();
            System.out.println(user + " получил уведомление от " + notification.formatMessage());
        }


    }

}
